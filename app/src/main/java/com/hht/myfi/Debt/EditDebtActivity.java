package com.hht.myfi.Debt;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hht.myfi.DTO.DTO_Debt;
import com.hht.myfi.DatabaseHelper;
import com.hht.myfi.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.hht.myfi.Helper.formatDate;

public class EditDebtActivity extends AppCompatActivity {

    EditText etxSuaTheLoaiVay;
    TextView tvwSuaMaKhoanVayContent;
    EditText etxSuaSoTienVay;
    EditText etxSuaLaiSuatVay;
    EditText etxSuaNgayVay;
    EditText etxSuaNgayTraVay;
    EditText etxSuaGhiChuVay;
    Button btnSuaKhoanVayCapNhat;
    Button btnSuaKhoanVayXoa;

    int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;

    DatabaseHelper db = DatabaseHelper.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_debt);

        int maKhoanVay = 0;
        String theLoaiVay = "";
        String soTienVay = "";
        String laiSuatVay = "";
        String ngayVay = "";
        String ngayTraVay = "";
        String ghiChuVay = "";

        etxSuaTheLoaiVay = (EditText) findViewById(R.id.etxSuaTheLoaiVay);
        tvwSuaMaKhoanVayContent = (TextView) findViewById(R.id.tvwSuaMaKhoanVayContent);
        etxSuaSoTienVay = (EditText) findViewById(R.id.etxSuaSoTienVay);
        etxSuaLaiSuatVay = (EditText) findViewById(R.id.etxSuaLaiSuatVay);
        etxSuaNgayVay = (EditText) findViewById(R.id.etxSuaNgayVay);
        etxSuaNgayTraVay = (EditText) findViewById(R.id.etxSuaNgayTraVay);
        etxSuaGhiChuVay = (EditText) findViewById(R.id.etxSuaGhiChuVay);
        btnSuaKhoanVayCapNhat = (Button) findViewById(R.id.btnSuaKhoanVayCapNhat);
        btnSuaKhoanVayXoa = (Button) findViewById(R.id.btnSuaKhoanVayXoa);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            maKhoanVay = Integer.parseInt(bundle.getString("MaKhoanVay"));
            theLoaiVay = bundle.getString("TenKhoanVay");
            soTienVay = bundle.getString("SoTienVay");
            laiSuatVay = bundle.getString("LaiSuatVay");
            ngayVay = bundle.getString("NgayVay");
            ngayTraVay = bundle.getString("NgayTraVay");
            ghiChuVay = bundle.getString("GhiChuVay");
        }

        tvwSuaMaKhoanVayContent.setText(String.valueOf(maKhoanVay));
        etxSuaTheLoaiVay.setText(theLoaiVay);
        etxSuaSoTienVay.setText(soTienVay);
        etxSuaLaiSuatVay.setText(laiSuatVay);
        etxSuaNgayVay.setText(formatDate(ngayVay));
        etxSuaNgayTraVay.setText(formatDate(ngayTraVay));
        etxSuaGhiChuVay.setText(ghiChuVay);

        //show calendar
        etxSuaNgayVay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    final Calendar calendar = Calendar.getInstance();
                    day_x = calendar.get(Calendar.DAY_OF_MONTH);
                    month_x = calendar.get(Calendar.MONTH);
                    year_x = calendar.get(Calendar.YEAR);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(EditDebtActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            calendar.set(year, month, day);
                            String dateFormat = "dd/MM/yyyy";
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
                            etxSuaNgayVay.setText(simpleDateFormat.format(calendar.getTime()));
                        }
                    }, year_x, month_x, day_x);
                    datePickerDialog.show();
                }
                return true;
            }
        });

        etxSuaNgayTraVay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    final Calendar calendar = Calendar.getInstance();
                    day_x = calendar.get(Calendar.DAY_OF_MONTH);
                    month_x = calendar.get(Calendar.MONTH);
                    year_x = calendar.get(Calendar.YEAR);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(EditDebtActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            calendar.set(year, month, day);
                            String dateFormat = "dd/MM/yyyy";
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
                            etxSuaNgayTraVay.setText(simpleDateFormat.format(calendar.getTime()));
                        }
                    }, year_x, month_x, day_x);
                    datePickerDialog.show();
                }
                return true;
            }
        });

        btnSuaKhoanVayCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //convert date format
                String suaNgayVay = etxSuaNgayVay.getText().toString();
                try
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date d = sdf.parse(suaNgayVay);
                    sdf.applyPattern("yyyy-MM-dd");
                    suaNgayVay = sdf.format(d);
                } catch (ParseException e)
                {
                    e.printStackTrace();
                }

                String suaNgayTraVay = etxSuaNgayTraVay.getText().toString();
                try
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date d = sdf.parse(suaNgayTraVay);
                    sdf.applyPattern("yyyy-MM-dd");
                    suaNgayTraVay = sdf.format(d);
                } catch (ParseException e)
                {
                    e.printStackTrace();
                }

                DTO_Debt khoanVay = new DTO_Debt(
                        etxSuaTheLoaiVay.getText().toString(),
                        (long) Float.parseFloat(etxSuaSoTienVay.getText().toString().replace(",", ".")),
                        Double.parseDouble(etxSuaLaiSuatVay.getText().toString().replace(",", ".")),
                        suaNgayVay,
                        suaNgayTraVay,
                        etxSuaGhiChuVay.getText().toString()
                );

                db.updateDebt(khoanVay, tvwSuaMaKhoanVayContent.getText().toString());
                Toast toast = Toast.makeText(getApplicationContext(), "Cập nhật thành công. \nVui lòng bấm trở về để xem thay đổi.", Toast.LENGTH_LONG);
                TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                if (v != null)
                    v.setGravity(Gravity.CENTER);
                toast.show();
                finish();
            }
        });

        btnSuaKhoanVayXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteDebt(tvwSuaMaKhoanVayContent.getText().toString());
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
