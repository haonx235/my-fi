package com.hht.myfi.CSVExporter;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hht.myfi.DatabaseHelper;
import com.hht.myfi.R;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class CSVExporterActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView tvwThangQuy;
    EditText etxThangQuy;
    EditText etxNam;
    Spinner spnTuyChonXuat;
    DatabaseHelper db = DatabaseHelper.getInstance(this);
    List<Integer> lstTuyChonXuat = new ArrayList<>();
    final String lstThang[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Nov", "Dec"};
    final String incomeColumnNames[] = {"Mã khoản thu", "Tên khoản thu", "Số tiền", "Ngày", "Ghi chú"};
    final String expenseColumnNames[] = {"Mã khoản chi", "Tên khoản chi", "Số tiền", "Ngày", "Ghi chú"};
    final String debtColumnNames[] = {"Mã khoản vay", "Tên khoản vay", "Số tiền", "Lãi suất", "Ngày vay", "Ngày trả", "Ghi chú"};
    final String loanColumnNames[] = {"Mã khoản cho vay", "Tên khoản cho vay", "Số tiền", "Lãi suất", "Ngày cho vay", "Ngày thu lại", "Ghi chú"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csvexporter);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        spnTuyChonXuat = (Spinner) findViewById(R.id.spnTuyChonXuat);
        ArrayAdapter<String> aaTuyChonXuat = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                Arrays.asList("Xuất theo tháng", "Xuất theo quý", "Xuất theo năm"));
        spnTuyChonXuat.setAdapter(aaTuyChonXuat);
        spnTuyChonXuat.setSelection(0);

        etxThangQuy = (EditText) findViewById(R.id.etxThangQuy);
        etxThangQuy.setText("1");

        etxNam = (EditText) findViewById(R.id.etxNam);
        etxNam.setText(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));

        tvwThangQuy = (TextView) findViewById(R.id.tvwThangQuy);
        tvwThangQuy.setText("Tháng");

        spnTuyChonXuat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spnTuyChonXuat.getSelectedItemPosition() == 0 || spnTuyChonXuat.getSelectedItemPosition() == 1)
                {
                    etxThangQuy.setEnabled(true);
                    etxThangQuy.setInputType(InputType.TYPE_NULL);
                    etxThangQuy.setFocusable(true);

                    if (spnTuyChonXuat.getSelectedItemPosition() == 0)
                        tvwThangQuy.setText("Tháng");
                    else
                        tvwThangQuy.setText("Quý");

                    etxThangQuy.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                                if (spnTuyChonXuat.getSelectedItemPosition() == 0)
                                    createPickerDialog(1, 12, etxThangQuy, "Chọn tháng");
                                else
                                    createPickerDialog(1, 4, etxThangQuy, "Chọn quý");
                            }
                            return true;
                        }
                    });
                }
                else
                {
                    etxThangQuy.setEnabled(false);
                    etxThangQuy.setInputType(InputType.TYPE_CLASS_TEXT);
                    etxThangQuy.setFocusable(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        etxNam.setEnabled(true);
        etxNam.setInputType(InputType.TYPE_NULL);
        etxNam.setFocusable(true);
        etxNam.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP)
                {
                    createPickerDialog(1700, 2200, etxNam, "Chọn năm");
                }
                return true;
            }
        });

    }

    public void createPickerDialog(int minValue, int maxValue, final EditText etx, String title)
    {
        final NumberPicker numberPicker = new NumberPicker(this);
        numberPicker.setMinValue(minValue);
        numberPicker.setMaxValue(maxValue);

        int currentValue = Integer.parseInt(etx.getText().toString());
        if (currentValue >= minValue && currentValue <= maxValue)
            numberPicker.setValue(currentValue);
        else
            numberPicker.setValue(1);
        NumberPicker.OnValueChangeListener valueChangeListener = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {

            }
        };
        numberPicker.setOnValueChangedListener(valueChangeListener);
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(numberPicker);
        builder.setTitle(title);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                etx.setText((Integer.toString(numberPicker.getValue())));
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    public void onClickCheckBox(View view)
    {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId())
        {
            case R.id.chbKhoanThu:
                if(checked)
                {
                    lstTuyChonXuat.add(0);
                }
                else
                {
                    lstTuyChonXuat.remove(0);
                }
                break;
            case R.id.chbKhoanChi:
                if(checked)
                {
                    lstTuyChonXuat.add(1);
                }
                else
                {
                    lstTuyChonXuat.remove(1);
                }
                break;
            case R.id.chbKhoanVay:
                if(checked)
                {
                    lstTuyChonXuat.add(2);
                }
                else
                {
                    lstTuyChonXuat.remove(2);
                }
                break;
            case R.id.chbKhoanChoVay:
                if(checked)
                {
                    lstTuyChonXuat.add(3);
                }
                else
                {
                    lstTuyChonXuat.remove(3);
                }
                break;
        }
    }

    public void onClickBtnXuat(View view)
    {
        verifyStoragePermissions(this);
        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        int periodType = spnTuyChonXuat.getSelectedItemPosition();
        int thangQuy = Integer.parseInt(etxThangQuy.getText().toString());
        int nam = Integer.parseInt(etxNam.getText().toString());

        for (int i : lstTuyChonXuat)
        {
            exportCSV(view, i, periodType, thangQuy, nam);
        }
    }

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    public void exportCSV(View view, int selection, int periodType, int thangQuy, int nam)
    {
        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName;
        String fileExtension = ".csv";
        switch (selection)
        {
            case 0:
                fileName = "Income";
                break;
            case 1:
                fileName = "Expense";
                break;
            case 2:
                fileName = "Debt";
                break;
            case 3:
                fileName = "Loan";
                break;
            default:
                fileName = "MyFi";
                break;
        }

        switch (periodType)
        {
            case 0:
                fileName = fileName + "_" + lstThang[thangQuy] + "_" + nam;
                break;
            case 1:
                fileName = fileName + "_Q" + thangQuy + "_" + nam;
                break;
            case 2:
                fileName = fileName + "_" + nam;
                break;
        }

        String filePath = baseDir + File.separator + fileName;
        File f = new File(filePath + fileExtension);
        CSVWriter writer;
        try
        {
            // File exist
            int index = 0;
            while (f.exists() && !f.isDirectory()){
                index++;
                f = new File(filePath + "_" + index + fileExtension);
            }

            if (index > 0)
                filePath = filePath + "_" + index + fileExtension;
            else
                filePath = filePath + fileExtension;
            writer = new CSVWriter(new FileWriter(filePath));

            List<String[]> data = new ArrayList<>();
            switch (selection)
            {
                case 0:
                    data.add(incomeColumnNames);
                    data.addAll(db.exportIncome(thangQuy, nam, periodType));
                    break;
                case 1:
                    data.add(expenseColumnNames);
                    data.addAll(db.exportExpense(thangQuy, nam, periodType));
                    break;
                case 2:
                    data.add(debtColumnNames);
                    data.addAll(db.exportDebt(thangQuy, nam, periodType));
                    break;
                case 3:
                    data.add(loanColumnNames);
                    data.addAll(db.exportLoan(thangQuy, nam, periodType));
                    break;
                default:
                    break;
            }
            writer.writeAll(data);

            writer.close();
            Toast.makeText (view.getContext(), "File saved to " + filePath, Toast.LENGTH_LONG).show();
        }
        catch (IOException e)
        {
            Toast.makeText (view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
