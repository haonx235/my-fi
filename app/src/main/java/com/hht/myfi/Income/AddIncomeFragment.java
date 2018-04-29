package com.hht.myfi.Income;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hht.myfi.DTO.DTO_Income;
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
public class AddIncomeFragment extends Fragment
{
    Spinner spnTheLoaiThu;
    EditText etxSoTienThu;
    EditText etxNgayThu;
    EditText etxGhiChuThu;
    Button btnThemThu;
    int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;
    DatabaseHelper db = DatabaseHelper.getInstance(getActivity());

    public AddIncomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_income, container, false);

        //declare variables
        spnTheLoaiThu = (Spinner) view.findViewById(R.id.spnTheLoaiThu);
        etxSoTienThu = (EditText) view.findViewById(R.id.etxSoTienThu);
        etxNgayThu = (EditText) view.findViewById(R.id.etxNgayThu);
        etxGhiChuThu = (EditText) view.findViewById(R.id.etxGhiChuThu);
        btnThemThu = (Button) view.findViewById(R.id.btnThemThu);

        //set adapter for spinner
        ArrayAdapter<String> adapterTheLoaiThu = new ArrayAdapter<String>(
                getActivity(),
                R.layout.spinner_item,
                db.getIncomeCategory()
        );
        //adapterTheLoaiThu.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnTheLoaiThu.setAdapter(adapterTheLoaiThu);

        //show calendar
        etxNgayThu.setOnTouchListener(new View.OnTouchListener() {
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
                            etxNgayThu.setText(simpleDateFormat.format(calendar.getTime()));
                        }
                    }, year_x, month_x, day_x);
                    datePickerDialog.show();
                }
                return true;
            }
        });

        btnThemThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //convert date format
                String ngayThu = etxNgayThu.getText().toString();
                try
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date d = sdf.parse(ngayThu);
                    sdf.applyPattern("yyyy/MM/dd");
                    ngayThu = sdf.format(d);
                } catch (ParseException e)
                {
                    e.printStackTrace();
                }

                DTO_Income khoanThu = new DTO_Income(
                        spnTheLoaiThu.getSelectedItem().toString(),
                        Integer.parseInt(etxSoTienThu.getText().toString()),
                        ngayThu,
                        etxGhiChuThu.getText().toString()
                );
                db.addIncome(khoanThu);
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                getActivity().finish();
            }
        });
        return view;
    }
}
