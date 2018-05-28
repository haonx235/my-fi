package com.hht.myfi.Expense;

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

import com.hht.myfi.DTO.DTO_Expense;
import com.hht.myfi.DatabaseHelper;
import com.hht.myfi.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddExpenseFragment extends Fragment
{
    Spinner spnTheLoaiChi;
    EditText etxSoTienChi;
    EditText etxNgayChi;
    EditText etxGhiChuChi;
    Button btnThemChi;
    int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;
    DatabaseHelper db = DatabaseHelper.getInstance(getActivity());

    public AddExpenseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_expense, container, false);

        //declare variables
        spnTheLoaiChi = (Spinner) view.findViewById(R.id.spnTheLoaiChi);
        etxSoTienChi = (EditText) view.findViewById(R.id.etxSoTienChi);
        etxNgayChi = (EditText) view.findViewById(R.id.etxNgayChi);
        etxGhiChuChi = (EditText) view.findViewById(R.id.etxGhiChuChi);
        btnThemChi = (Button) view.findViewById(R.id.btnThemChi);

        //set adapter for spinner
        ArrayAdapter<String> adapterTheLoaiChi = new ArrayAdapter<String>(
                getActivity(),
                R.layout.spinner_item,
                db.getExpenseCategory()
        );
        //adapterTheLoaiChi.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnTheLoaiChi.setAdapter(adapterTheLoaiChi);

        //show calendar
        etxNgayChi.setOnTouchListener(new View.OnTouchListener() {
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
                            etxNgayChi.setText(simpleDateFormat.format(calendar.getTime()));
                        }
                    }, year_x, month_x, day_x);
                    datePickerDialog.show();
                }
                return true;
            }
        });

        btnThemChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //convert date format
                String ngayChi = etxNgayChi.getText().toString();
                try
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date d = sdf.parse(ngayChi);
                    sdf.applyPattern("yyyy-MM-dd");
                    ngayChi = sdf.format(d);
                } catch (ParseException e)
                {
                    e.printStackTrace();
                }

                DTO_Expense khoanChi = new DTO_Expense(
                        spnTheLoaiChi.getSelectedItem().toString(),
                        Integer.parseInt(etxSoTienChi.getText().toString()),
                        ngayChi,
                        etxGhiChuChi.getText().toString()
                );
                db.addExpense(khoanChi);
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                getActivity().finish();
            }
        });
        return view;
    }
}
