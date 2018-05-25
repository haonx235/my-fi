package com.hht.myfi.CSVExporter;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.hht.myfi.DatabaseHelper;
import com.hht.myfi.R;

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
    List<String> checkBoxSelection = new ArrayList<>();

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
                    etxThangQuy.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                                if (spnTuyChonXuat.getSelectedItemPosition() == 0) {
                                    tvwThangQuy.setText("Tháng");
                                    createPickerDialog(1, 12, etxThangQuy, "Chọn tháng");
                                }
                                else {
                                    tvwThangQuy.setText("Quý");
                                    createPickerDialog(1, 4, etxThangQuy, "Chọn quý");
                                }
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
                    checkBoxSelection.add("TABLE_INCOME");
                }
                else
                {
                    checkBoxSelection.remove("TABLE_INCOME");
                }
                break;
            case R.id.chbKhoanChi:
                if(checked)
                {
                    checkBoxSelection.add("TABLE_EXPENSE");
                }
                else
                {
                    checkBoxSelection.remove("TABLE_EXPENSE");
                }
                break;
            case R.id.chbKhoanVay:
                if(checked)
                {
                    checkBoxSelection.add("TABLE_LOAN");
                }
                else
                {
                    checkBoxSelection.remove("TABLE_LOAN");
                }
                break;
            case R.id.chbKhoanChoVay:
                if(checked)
                {
                    checkBoxSelection.add("TABLE_DEBT");
                }
                else
                {
                    checkBoxSelection.remove("TABLE_DEBT");
                }
                break;
        }
    }

    public void onClickBtnXuat(View view)
    {
        for(String tableName : checkBoxSelection)
        {

        }
    }

}
