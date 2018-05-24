package com.hht.myfi.Chart;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.hht.myfi.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.hht.myfi.DatabaseHelper;

public class PNBarChartActivity extends AppCompatActivity {

    protected BarChart barChart;
    protected Button btnTo;
    protected Spinner spChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pnbarchart);

        btnTo = (Button) findViewById(R.id.btnto_pie);
        btnTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent barIntent = new Intent(PNBarChartActivity.this, PieChartActivity.class);
                startActivity(barIntent);
            }
        });

        barChart = (BarChart) findViewById(R.id.pn_barchart);
        barChart.setPinchZoom(true);

        final List<Data> data = new ArrayList<>();
        List<Pair<String, Integer>> myData = new ArrayList<Pair<String, Integer>>();
        /*incomeData = DatabaseHelper.getInstance(this).getOverallIncomeInformation(DatabaseHelper.TABLE_INCOME, "incomeName", "incomeName, SUM(incomeAmount)");
        int index = 0;
        for (Pair<String, Integer> p : incomeData) {
            data.add(new Data(p.first, (float) index, (float) p.second));
            index++;
        }
        incomeData = DatabaseHelper.getInstance(PNBarChartActivity.this).getOverallIncomeInformation(DatabaseHelper.TABLE_EXPENSE, "expenseName", "expenseName, SUM(expenseAmount)");
        for (Pair<String, Integer> p : incomeData) {
            data.add(new Data(p.first, (float) index, (float) -p.second));
            index++;
        }

        setData(data);*/

        spChoice = (Spinner) findViewById(R.id.spchoice_pnbar);
        /* //dùng code để tạo item
        final String[]paths = {"item 1", "item 2", "item 3"};
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(PNBarChartActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spChoice.setAdapter(adapter);*/
        spChoice.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        final List<Data> data = new ArrayList<>();
                        List<Pair<String, Integer>> myData = new ArrayList<Pair<String, Integer>>();
                        int index = 0;

                        switch (spChoice.getSelectedItem().toString()) {
                            case "Thu và chi":
                                myData = DatabaseHelper.getInstance(PNBarChartActivity.this).getOverallIncomeInformation(DatabaseHelper.TABLE_INCOME, "incomeName", "incomeName, SUM(incomeAmount)");

                                myData.addAll(DatabaseHelper.getInstance(PNBarChartActivity.this).getOverallIncomeInformation(DatabaseHelper.TABLE_EXPENSE, "expenseName", "expenseName, (0-SUM(expenseAmount))"));
                                Collections.sort(myData, new Comparator<Pair<String, Integer>>() {
                                    @Override
                                    public int compare(final Pair<String, Integer> o1, final Pair<String, Integer> o2) {

                                        if (Math.abs(o1.second) > Math.abs(o2.second)) {
                                            return 1;
                                        } else if (Math.abs(o1.second) < Math.abs(o2.second)) {
                                            return -1;
                                        }
                                        return 0;
                                    }
                                });
                                index = 0;
                                for (Pair<String, Integer> p : myData) {
                                    data.add(new Data(p.first, (float) index, (float) p.second));
                                    index++;
                                }

                                setData(data);

                                break;
                            case "Vay và cho vay":
                                myData = DatabaseHelper.getInstance(PNBarChartActivity.this).getOverallIncomeInformation(DatabaseHelper.TABLE_LOAN, "loanName", "loanName, SUM(loanAmount)");

                                myData.addAll(DatabaseHelper.getInstance(PNBarChartActivity.this).getOverallIncomeInformation(DatabaseHelper.TABLE_DEBT, "debtName", "debtName, (0-SUM(debtAmount))"));
                                Collections.sort(myData, new Comparator<Pair<String, Integer>>() {
                                    @Override
                                    public int compare(final Pair<String, Integer> o1, final Pair<String, Integer> o2) {

                                        if (Math.abs(o1.second) > Math.abs(o2.second)) {
                                            return 1;
                                        } else if (Math.abs(o1.second) < Math.abs(o2.second)) {
                                            return -1;
                                        }
                                        return 0;
                                    }
                                });
                                index = 0;
                                for (Pair<String, Integer> p : myData) {
                                    data.add(new Data(p.first, (float) index, (float) p.second));
                                    index++;
                                }

                                setData(data);
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );
    }

    private void setData(List<Data> dataList) {
        ArrayList<BarEntry> values = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        int green = Color.GREEN;
        int red = Color.RED;

        for (int i = 0; i < dataList.size(); i++) {
            Data d = dataList.get(i);
            BarEntry entry = new BarEntry(d.xValue, d.yValue);
            values.add(entry);
            labels.add(d.xAxisValue);

            if (d.yValue >= 0) {
                colors.add(green);
            } else {
                colors.add(red);
            }
        }

        BarDataSet dset = new BarDataSet(values, "Gia tri");
        dset.setStackLabels(labels.toArray(new String[0]));
        dset.setColors(colors);
        dset.setValueTextColors(colors);

        BarData data = new BarData(dset);
        data.setValueTextSize(15f);

        data.setValueFormatter(new ValueFormatter());
        data.setBarWidth(0.9f);

        barChart.setData(data);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(Typeface.SANS_SERIF);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);

        barChart.animateY(1000, Easing.EasingOption.EaseInSine);
        barChart.invalidate();
    }

    private class Data {
        public String xAxisValue;
        public float xValue;
        public float yValue;

        public Data(String xAxisValue, float xValue, float yValue) {
            this.xAxisValue = xAxisValue;
            this.xValue = xValue;
            this.yValue = yValue;
        }
    }

    private class ValueFormatter implements IValueFormatter {

        private DecimalFormat mFormat;

        public ValueFormatter() {
            mFormat = new DecimalFormat("####.0");
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return mFormat.format(value);
        }
    }
}
