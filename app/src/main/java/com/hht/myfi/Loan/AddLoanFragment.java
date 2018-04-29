package com.hht.myfi.Loan;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.hht.myfi.DTO.DTO_Loan;
import com.hht.myfi.DatabaseHelper;
import com.hht.myfi.R;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddLoanFragment extends Fragment
{
    EditText etxTheLoaiChoVay;
    EditText etxSoTienChoVay;
    EditText etxLaiSuatChoVay;
    EditText etxNgayChoVay;
    EditText etxNgayThuLai;
    EditText etxGhiChuChoVay;
    Button btnThemChoVay;
    int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;
    DatabaseHelper db = DatabaseHelper.getInstance(getActivity());

    public AddLoanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_loan, container, false);

        //declare variables
        etxTheLoaiChoVay = (EditText) view.findViewById(R.id.etxTheLoaiChoVay);
        etxSoTienChoVay = (EditText) view.findViewById(R.id.etxSoTienChoVay);
        etxLaiSuatChoVay = (EditText) view.findViewById(R.id.etxLaiSuatChoVay);
        etxNgayChoVay = (EditText) view.findViewById(R.id.etxNgayChoVay);
        etxNgayThuLai = (EditText) view.findViewById(R.id.etxNgayThuLai);
        etxGhiChuChoVay = (EditText) view.findViewById(R.id.etxGhiChuChoVay);
        btnThemChoVay = (Button) view.findViewById(R.id.btnThemChoVay);

        //show calendar
        etxNgayChoVay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    final Calendar calendar = Calendar.getInstance();
                    day_x = calendar.get(Calendar.DAY_OF_MONTH);
                    month_x = calendar.get(Calendar.MONTH);
                    year_x = calendar.get(Calendar.YEAR);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            calendar.set(year, month, day);
                            String dateFormat = "dd/MM/yyyy";
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
                            etxNgayChoVay.setText(simpleDateFormat.format(calendar.getTime()));
                        }
                    }, year_x, month_x, day_x);
                    datePickerDialog.show();
                }
                return true;
            }
        });

        etxNgayThuLai.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    final Calendar calendar = Calendar.getInstance();
                    day_x = calendar.get(Calendar.DAY_OF_MONTH);
                    month_x = calendar.get(Calendar.MONTH);
                    year_x = calendar.get(Calendar.YEAR);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            calendar.set(year, month, day);
                            String dateFormat = "dd/MM/yyyy";
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
                            etxNgayThuLai.setText(simpleDateFormat.format(calendar.getTime()));
                        }
                    }, year_x, month_x, day_x);
                    datePickerDialog.show();
                }
                return true;
            }
        });

        btnThemChoVay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //convert date format
                String ngayChoVay = etxNgayChoVay.getText().toString();
                try
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date d = sdf.parse(ngayChoVay);
                    sdf.applyPattern("yyyy/MM/dd");
                    ngayChoVay = sdf.format(d);
                } catch (ParseException e)
                {
                    e.printStackTrace();
                }

                String ngayThuLai = etxNgayThuLai.getText().toString();
                try
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date d = sdf.parse(ngayThuLai);
                    sdf.applyPattern("yyyy/MM/dd");
                    ngayThuLai = sdf.format(d);
                } catch (ParseException e)
                {
                    e.printStackTrace();
                }

                DTO_Loan khoanChoVay = new DTO_Loan(
                        etxTheLoaiChoVay.getText().toString(),
                        Integer.parseInt(etxSoTienChoVay.getText().toString()),
                        Double.parseDouble(etxLaiSuatChoVay.getText().toString()),
                        ngayChoVay,
                        ngayThuLai,
                        etxGhiChuChoVay.getText().toString()
                );
                db.addLoan(khoanChoVay);
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                getActivity().finish();
            }
        });
        return view;
    }
}
