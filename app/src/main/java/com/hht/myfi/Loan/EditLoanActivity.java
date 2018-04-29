package com.hht.myfi.Loan;

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

import com.hht.myfi.DTO.DTO_Loan;
import com.hht.myfi.DatabaseHelper;
import com.hht.myfi.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.hht.myfi.Helper.formatDate;

public class EditLoanActivity extends AppCompatActivity {

    EditText etxSuaTheLoaiChoVay;
    TextView tvwSuaMaKhoanChoVayContent;
    EditText etxSuaSoTienChoVay;
    EditText etxSuaLaiSuatChoVay;
    EditText etxSuaNgayChoVay;
    EditText etxSuaNgayThuLai;
    EditText etxSuaGhiChuChoVay;
    Button btnSuaKhoanChoVayCapNhat;
    Button btnSuaKhoanChoVayXoa;

    int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;

    DatabaseHelper db = DatabaseHelper.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_loan);

        int maKhoanChoVay = 0;
        String theLoaiChoVay = "";
        String soTienChoVay = "";
        String laiSuatChoVay = "";
        String ngayChoVay = "";
        String ngayThuLai = "";
        String ghiChuChoVay = "";

        etxSuaTheLoaiChoVay = (EditText) findViewById(R.id.etxSuaTheLoaiChoVay);
        tvwSuaMaKhoanChoVayContent = (TextView) findViewById(R.id.tvwSuaMaKhoanChoVayContent);
        etxSuaSoTienChoVay = (EditText) findViewById(R.id.etxSuaSoTienChoVay);
        etxSuaLaiSuatChoVay = (EditText) findViewById(R.id.etxSuaLaiSuatChoVay);
        etxSuaNgayChoVay = (EditText) findViewById(R.id.etxSuaNgayChoVay);
        etxSuaNgayThuLai = (EditText) findViewById(R.id.etxSuaNgayThuLai);
        etxSuaGhiChuChoVay = (EditText) findViewById(R.id.etxSuaGhiChuChoVay);
        btnSuaKhoanChoVayCapNhat = (Button) findViewById(R.id.btnSuaKhoanChoVayCapNhat);
        btnSuaKhoanChoVayXoa = (Button) findViewById(R.id.btnSuaKhoanChoVayXoa);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            maKhoanChoVay = Integer.parseInt(bundle.getString("MaKhoanChoVay"));
            theLoaiChoVay = bundle.getString("TenKhoanChoVay");
            soTienChoVay = bundle.getString("SoTienChoVay");
            laiSuatChoVay = bundle.getString("LaiSuatChoVay");
            ngayChoVay = bundle.getString("NgayChoVay");
            ngayThuLai = bundle.getString("NgayThuLai");
            ghiChuChoVay = bundle.getString("GhiChuChoVay");
        }

        tvwSuaMaKhoanChoVayContent.setText(String.valueOf(maKhoanChoVay));
        etxSuaTheLoaiChoVay.setText(theLoaiChoVay);
        etxSuaSoTienChoVay.setText(soTienChoVay);
        etxSuaLaiSuatChoVay.setText(laiSuatChoVay);
        etxSuaNgayChoVay.setText(formatDate(ngayChoVay));
        etxSuaNgayThuLai.setText(formatDate(ngayThuLai));
        etxSuaGhiChuChoVay.setText(ghiChuChoVay);

        //show calendar
        etxSuaNgayChoVay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    final Calendar calendar = Calendar.getInstance();
                    day_x = calendar.get(Calendar.DAY_OF_MONTH);
                    month_x = calendar.get(Calendar.MONTH);
                    year_x = calendar.get(Calendar.YEAR);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(EditLoanActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            calendar.set(year, month, day);
                            String dateFormat = "dd/MM/yyyy";
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
                            etxSuaNgayChoVay.setText(simpleDateFormat.format(calendar.getTime()));
                        }
                    }, year_x, month_x, day_x);
                    datePickerDialog.show();
                }
                return true;
            }
        });

        etxSuaNgayThuLai.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    final Calendar calendar = Calendar.getInstance();
                    day_x = calendar.get(Calendar.DAY_OF_MONTH);
                    month_x = calendar.get(Calendar.MONTH);
                    year_x = calendar.get(Calendar.YEAR);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(EditLoanActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            calendar.set(year, month, day);
                            String dateFormat = "dd/MM/yyyy";
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
                            etxSuaNgayThuLai.setText(simpleDateFormat.format(calendar.getTime()));
                        }
                    }, year_x, month_x, day_x);
                    datePickerDialog.show();
                }
                return true;
            }
        });

        btnSuaKhoanChoVayCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //convert date format
                String suaNgayChoVay = etxSuaNgayChoVay.getText().toString();
                try
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date d = sdf.parse(suaNgayChoVay);
                    sdf.applyPattern("yyyy/MM/dd");
                    suaNgayChoVay = sdf.format(d);
                } catch (ParseException e)
                {
                    e.printStackTrace();
                }

                String suaNgayThuLai = etxSuaNgayThuLai.getText().toString();
                try
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date d = sdf.parse(suaNgayThuLai);
                    sdf.applyPattern("yyyy/MM/dd");
                    suaNgayThuLai = sdf.format(d);
                } catch (ParseException e)
                {
                    e.printStackTrace();
                }

                DTO_Loan khoanChoVay = new DTO_Loan(
                        etxSuaTheLoaiChoVay.getText().toString(),
                        (long) Float.parseFloat(etxSuaSoTienChoVay.getText().toString().replace(",", ".")),
                        Double.parseDouble(etxSuaLaiSuatChoVay.getText().toString().replace(",", ".")),
                        suaNgayChoVay,
                        suaNgayThuLai,
                        etxSuaGhiChuChoVay.getText().toString()
                );

                db.updateLoan(khoanChoVay, tvwSuaMaKhoanChoVayContent.getText().toString());
                Toast toast = Toast.makeText(getApplicationContext(), "Cập nhật thành công. \nVui lòng bấm trở về để xem thay đổi.", Toast.LENGTH_LONG);
                TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                if (v != null)
                    v.setGravity(Gravity.CENTER);
                toast.show();
                finish();
            }
        });

        btnSuaKhoanChoVayXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteLoan(tvwSuaMaKhoanChoVayContent.getText().toString());
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
