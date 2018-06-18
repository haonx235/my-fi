package com.hht.myfi.Chart;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.hht.myfi.DatabaseHelper;
import com.hht.myfi.R;


import java.util.ArrayList;
import java.util.List;

public class IncomeFragment extends Fragment  {
    protected PieChart pieChart;
    protected Button btnTo;
    protected Spinner spChoice;
    Context context;

    public static Fragment newInstance() {
        return new IncomeFragment();
    }

    public IncomeFragment() {
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

        View v = inflater.inflate(R.layout.activity_piechart, container, false);
        context = v.getContext();
//        btnTo = (Button) v.findViewById(R.id.btnto_bar);
//        btnTo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent barIntent = new Intent(context, PNBarChartActivity.class);
//                startActivity(barIntent);
//            }
//        });

        pieChart = (PieChart) v.findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);


        ArrayList<PieEntry> values = new ArrayList<>();
        List<Pair<String, Integer>> myData = new ArrayList<Pair<String, Integer>>();
        int index = 0;
        PieDataSet dataSet;
        PieData data;
        Description desc;


        values = new ArrayList<>();
        myData = DatabaseHelper.getInstance(context).getOverallDataInformation(DatabaseHelper.TABLE_INCOME, "incomeName", "incomeName, SUM(incomeAmount)");

        index = 0;
        for (Pair<String, Integer> p : myData) {
            values.add(new PieEntry(p.second, p.first));
            index++;
        }

        dataSet = new PieDataSet(values, "Chú thích");
        dataSet.setSliceSpace(1f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);

        desc = new Description("Phần description này méo biết viết gì hết!!!");
        desc.setTextSize(15);

        //pieChart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));
        pieChart.setCenterText(generateCenterSpannableText("Biểu đồ thu"));

        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setDrawCenterText(true);
        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        pieChart.setDescription(desc);
        pieChart.animateY(1000, Easing.EasingOption.EaseOutCubic);
        pieChart.setData(data);

        return v;
    }

    private SpannableString generateCenterSpannableText(String chartName) {

        SpannableString s = new SpannableString(chartName + "\ndeveloped by BDtren");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, chartName.length(), 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), chartName.length(), s.length() - 7, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), chartName.length(), s.length() - 7, 0);
        s.setSpan(new RelativeSizeSpan(.8f), chartName.length(), s.length() - 7, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 6, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 6, s.length(), 0);
        return s;
    }

}
