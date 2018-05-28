package com.hht.myfi.Expense;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hht.myfi.DTO.DTO_Expense;
import com.hht.myfi.DatabaseHelper;
import com.hht.myfi.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.hht.myfi.Helper.formatDate;

public class EditExpenseActivity extends AppCompatActivity {

    Spinner spnSuaTheLoaiChi;
    TextView tvwSuaMaKhoanChiContent;
    EditText etxSuaSoTienChi;
    EditText etxSuaNgayChi;
    EditText etxSuaGhiChuChi;
    Button btnSuaKhoanChiCapNhat;
    Button btnSuaKhoanChiXoa;

    int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;

    DatabaseHelper db = DatabaseHelper.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expense);

        int maKhoanChi = 0;
        String theLoaiChi = "";
        int theLoaiChiIndex = 0;
        String soTienChi = "";
        String ngayChi = "";
        String ghiChuChi = "";

        spnSuaTheLoaiChi = (Spinner) findViewById(R.id.spnSuaTheLoaiChi);
        tvwSuaMaKhoanChiContent = (TextView) findViewById(R.id.tvwSuaMaKhoanChiContent);
        etxSuaSoTienChi = (EditText) findViewById(R.id.etxSuaSoTienChi);
        etxSuaNgayChi = (EditText) findViewById(R.id.etxSuaNgayChi);
        etxSuaGhiChuChi = (EditText) findViewById(R.id.etxSuaGhiChuChi);
        btnSuaKhoanChiCapNhat = (Button) findViewById(R.id.btnSuaKhoanChiCapNhat);
        btnSuaKhoanChiXoa = (Button) findViewById(R.id.btnSuaKhoanChiXoa);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            maKhoanChi = Integer.parseInt(bundle.getString("MaKhoanChi"));
            theLoaiChi = bundle.getString("TenKhoanChi");
            theLoaiChiIndex = db.getExpenseCategoryIndex(theLoaiChi);
            soTienChi = bundle.getString("SoTienChi");
            ngayChi = bundle.getString("NgayChi");
            ghiChuChi = bundle.getString("GhiChuChi");
        }

        //set adapter for spinner
        ArrayAdapter<String> adapterSuaTheLoaiChi = new ArrayAdapter<String>(
                this,
                R.layout.spinner_item,
                db.getExpenseCategory()
        );
        //adapterTheLoaiChi.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnSuaTheLoaiChi.setAdapter(adapterSuaTheLoaiChi);
        spnSuaTheLoaiChi.setSelection(theLoaiChiIndex);

        tvwSuaMaKhoanChiContent.setText(String.valueOf(maKhoanChi));
        etxSuaSoTienChi.setText(soTienChi);
        etxSuaNgayChi.setText(formatDate(ngayChi));
        etxSuaGhiChuChi.setText(ghiChuChi);

        //show calendar
        etxSuaNgayChi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    final Calendar calendar = Calendar.getInstance();
                    day_x = calendar.get(Calendar.DAY_OF_MONTH);
                    month_x = calendar.get(Calendar.MONTH);
                    year_x = calendar.get(Calendar.YEAR);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(EditExpenseActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            calendar.set(year, month, day);
                            String dateFormat = "dd/MM/yyyy";
                            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
                            etxSuaNgayChi.setText(sdf.format(calendar.getTime()));
                        }
                    }, year_x, month_x, day_x);
                    datePickerDialog.show();
                }
                return true;
            }
        });

        btnSuaKhoanChiCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //convert date format
                String suaNgayChi = etxSuaNgayChi.getText().toString();
                try
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date d = sdf.parse(suaNgayChi);
                    sdf.applyPattern("yyyy-MM-dd");
                    suaNgayChi = sdf.format(d);
                } catch (ParseException e)
                {
                    e.printStackTrace();
                }

                DTO_Expense khoanChi = new DTO_Expense(
                        spnSuaTheLoaiChi.getSelectedItem().toString(),
                        (long) Float.parseFloat(etxSuaSoTienChi.getText().toString().replace(",", ".")),
                        suaNgayChi,
                        etxSuaGhiChuChi.getText().toString()
                );

                db.updateExpense(khoanChi, tvwSuaMaKhoanChiContent.getText().toString());
                Toast toast = Toast.makeText(getApplicationContext(), "Cập nhật thành công. \nVui lòng bấm trở về để xem thay đổi.", Toast.LENGTH_LONG);
                TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                if (v != null)
                    v.setGravity(Gravity.CENTER);
                toast.show();
                finish();
            }
        });

        btnSuaKhoanChiXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteExpense(tvwSuaMaKhoanChiContent.getText().toString());
                Toast toast = Toast.makeText(getApplicationContext(), "Xoá thành công. \nVui lòng bấm trở về để xem thay đổi.", Toast.LENGTH_LONG);
                TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                if (v != null)
                    v.setGravity(Gravity.CENTER);
                toast.show();
                finish();
            }
        });
    }
}
