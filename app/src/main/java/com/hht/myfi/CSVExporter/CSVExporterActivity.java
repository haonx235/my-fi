package com.hht.myfi.CSVExporter;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.hht.myfi.R;

import java.util.Calendar;

public class CSVExporterActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText etxThangQuy;
    int mMonth, int mQuarter, int mYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csvexporter);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        etxThangQuy = (EditText) findViewById(R.id.etxThangQuy);

        etxThangQuy.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {

                }
                return true;
            }
        });

    }

    public void createMonthPicker()
    {
        final Calendar cld = Calendar.getInstance();
        mMonth = cld.get(Calendar.MONTH);
        NumberPicker numberPicker = new NumberPicker(this, new NumberPicker())
    }

}
