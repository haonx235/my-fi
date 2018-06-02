package com.hht.myfi.Chart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.hht.myfi.DatabaseHelper;
import com.hht.myfi.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Loan_Debt_Fragment extends Fragment {
    protected PieChart pieChart;
    protected Button btnTo;
    Context context;
    protected BarChart barChart;

    public static Fragment newInstance() {
        return new IncomeFragment();
    }

    public Loan_Debt_Fragment() {
    }


    public void onResume() {
        super.onResume();
    }
    @Override
    public void onStart() {
        super.onStart();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View v = inflater.inflate(R.layout.activity_pnbarchart, container, false);
        context = v.getContext();


//        btnTo = (Button) v.findViewById(R.id.btnto_pie);
//        btnTo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent barIntent = new Intent(context, PieChartActivity.class);
//                startActivity(barIntent);
//            }
//        });

        barChart = (BarChart) v.findViewById(R.id.pn_barchart);
        barChart.setPinchZoom(true);


        final List<Data> data = new ArrayList<>();
        List<Pair<String, Integer>> myData = new ArrayList<Pair<String, Integer>>();
        int index = 0;


        myData = DatabaseHelper.getInstance(context).getOverallDataInformation(DatabaseHelper.TABLE_LOAN, "loanName", "loanName, SUM(loanAmount)");

        myData.addAll(DatabaseHelper.getInstance(context).getOverallDataInformation(DatabaseHelper.TABLE_DEBT, "debtName", "debtName, SUM(debtAmount)"));
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
        return v;
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


