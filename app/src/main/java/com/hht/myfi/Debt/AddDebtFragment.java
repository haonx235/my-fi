package com.hht.myfi.Debt;

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

import com.hht.myfi.DTO.DTO_Debt;
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
public class AddDebtFragment extends Fragment
{
    EditText etxTheLoaiVay;
    EditText etxSoTienVay;
    EditText etxLaiSuatVay;
    EditText etxNgayVay;
    EditText etxNgayTraVay;
    EditText etxGhiChuVay;
    Button btnThemVay;
    int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;
    DatabaseHelper db = DatabaseHelper.getInstance(getActivity());

    public AddDebtFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_debt, container, false);

        //declare variables
        etxTheLoaiVay = (EditText) view.findViewById(R.id.etxTheLoaiVay);
        etxSoTienVay = (EditText) view.findViewById(R.id.etxSoTienVay);
        etxLaiSuatVay = (EditText) view.findViewById(R.id.etxLaiSuatVay);
        etxNgayVay = (EditText) view.findViewById(R.id.etxNgayVay);
        etxNgayTraVay = (EditText) view.findViewById(R.id.etxNgayTraVay);
        etxGhiChuVay = (EditText) view.findViewById(R.id.etxGhiChuVay);
        btnThemVay = (Button) view.findViewById(R.id.btnThemVay);

        //show calendar
        etxNgayVay.setOnTouchListener(new View.OnTouchListener() {
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
                            etxNgayVay.setText(simpleDateFormat.format(calendar.getTime()));
                        }
                    }, year_x, month_x, day_x);
                    datePickerDialog.show();
                }
                return true;
            }
        });

        etxNgayTraVay.setOnTouchListener(new View.OnTouchListener() {
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
                            etxNgayTraVay.setText(simpleDateFormat.format(calendar.getTime()));
                        }
                    }, year_x, month_x, day_x);
                    datePickerDialog.show();
                }
                return true;
            }
        });

        btnThemVay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //convert date format
                String ngayVay = etxNgayVay.getText().toString();
                try
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date d = sdf.parse(ngayVay);
                    sdf.applyPattern("yyyy/MM/dd");
                    ngayVay = sdf.format(d);
                } catch (ParseException e)
                {
                    e.printStackTrace();
                }

                String ngayTraVay = etxNgayTraVay.getText().toString();
                try
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date d = sdf.parse(ngayTraVay);
                    sdf.applyPattern("yyyy/MM/dd");
                    ngayTraVay = sdf.format(d);
                } catch (ParseException e)
                {
                    e.printStackTrace();
                }

                DTO_Debt debt = new DTO_Debt(
                        etxTheLoaiVay.getText().toString(),
                        Integer.parseInt(etxSoTienVay.getText().toString()),
                        Double.parseDouble(etxLaiSuatVay.getText().toString()),
                        ngayVay,
                        ngayTraVay,
                        etxGhiChuVay.getText().toString()
                );
                db.addDebt(debt);
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                getActivity().finish();
            }
        });
        return view;
    }
}
