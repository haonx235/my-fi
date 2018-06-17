package com.hht.myfi.Income;

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

import com.hht.myfi.DTO.DTO_Income;
import com.hht.myfi.DatabaseHelper;
import com.hht.myfi.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.hht.myfi.Helper.formatDate;

public class EditIncomeActivity extends AppCompatActivity {

    Spinner spnSuaTheLoaiThu;
    TextView tvwSuaMaKhoanThuContent;
    EditText etxSuaSoTienThu;
    EditText etxSuaNgayThu;
    EditText etxSuaGhiChuThu;
    Button btnSuaKhoanThuCapNhat;
    Button btnSuaKhoanThuXoa;

    int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;

    DatabaseHelper db = DatabaseHelper.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_income);

        int maKhoanThu = 0;
        String theLoaiThu = "";
        int theLoaiThuIndex = 0;
        String soTienThu = "";
        String ngayThu = "";
        String ghiChuThu = "";

        spnSuaTheLoaiThu = (Spinner) findViewById(R.id.spnSuaTheLoaiThu);
        tvwSuaMaKhoanThuContent = (TextView) findViewById(R.id.tvwSuaMaKhoanThuContent);
        etxSuaSoTienThu = (EditText) findViewById(R.id.etxSuaSoTienThu);
        etxSuaNgayThu = (EditText) findViewById(R.id.etxSuaNgayThu);
        etxSuaGhiChuThu = (EditText) findViewById(R.id.etxSuaGhiChuThu);
        btnSuaKhoanThuCapNhat = (Button) findViewById(R.id.btnSuaKhoanThuCapNhat);
        btnSuaKhoanThuXoa = (Button) findViewById(R.id.btnSuaKhoanThuXoa);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            maKhoanThu = Integer.parseInt(bundle.getString("MaKhoanThu"));
            theLoaiThu = bundle.getString("TenKhoanThu");
            theLoaiThuIndex = db.getIncomeCategoryIndex(theLoaiThu);
            soTienThu = bundle.getString("SoTienThu");
            ngayThu = bundle.getString("NgayThu");
            ghiChuThu = bundle.getString("GhiChuThu");
        }

        //set adapter for spinner
        ArrayAdapter<String> adapterSuaTheLoaiThu = new ArrayAdapter<String>(
                this,
                R.layout.spinner_item,
                db.getIncomeCategory()
        );
        //adapterTheLoaiThu.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnSuaTheLoaiThu.setAdapter(adapterSuaTheLoaiThu);
        spnSuaTheLoaiThu.setSelection(theLoaiThuIndex);

        tvwSuaMaKhoanThuContent.setText(String.valueOf(maKhoanThu));
        etxSuaSoTienThu.setText(soTienThu);
        etxSuaNgayThu.setText(formatDate(ngayThu));
        etxSuaGhiChuThu.setText(ghiChuThu);

        //show calendar
        etxSuaNgayThu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    final Calendar calendar = Calendar.getInstance();
                    day_x = calendar.get(Calendar.DAY_OF_MONTH);
                    month_x = calendar.get(Calendar.MONTH);
                    year_x = calendar.get(Calendar.YEAR);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(EditIncomeActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            calendar.set(year, month, day);
                            String dateFormat = "dd/MM/yyyy";
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
                            etxSuaNgayThu.setText(simpleDateFormat.format(calendar.getTime()));
                        }
                    }, year_x, month_x, day_x);
                    datePickerDialog.show();
                }
                return true;
            }
        });

        btnSuaKhoanThuCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //convert date format
                String suaNgayThu = etxSuaNgayThu.getText().toString();
                try
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date d = sdf.parse(suaNgayThu);
                    sdf.applyPattern("yyyy-MM-dd");
                    suaNgayThu = sdf.format(d);
                } catch (ParseException e)
                {
                    e.printStackTrace();
                }

                DTO_Income khoanThu = new DTO_Income(
                        spnSuaTheLoaiThu.getSelectedItem().toString(),
                        (long) Float.parseFloat(etxSuaSoTienThu.getText().toString().replace(",", ".")),
                        suaNgayThu,
                        etxSuaGhiChuThu.getText().toString()
                );

                db.updateIncome(khoanThu, tvwSuaMaKhoanThuContent.getText().toString());
                Toast toast = Toast.makeText(getApplicationContext(), "Cập nhật thành công. \nVui lòng bấm trở về để xem thay đổi.", Toast.LENGTH_LONG);
                TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                if (v != null)
                    v.setGravity(Gravity.CENTER);
                toast.show();
                finish();
            }
        });

        btnSuaKhoanThuXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteIncome(tvwSuaMaKhoanThuContent.getText().toString());
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
