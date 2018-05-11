package com.hht.myfi.Chart;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.hht.myfi.R;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity {

    protected PieChart pieChart;
    protected Button btnTo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);

        btnTo = (Button) findViewById(R.id.btnto_bar);
        btnTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent barIntent = new Intent(PieChartActivity.this, PNBarChartActivity.class);
                startActivity(barIntent);
            }
        });

        pieChart = (PieChart) findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(true);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setHoleRadius(30f);
        pieChart.setTransparentCircleRadius(50f);

        ArrayList<PieEntry> values = new ArrayList<>();

        values.add(new PieEntry(34f,"ăn sáng"));
        values.add(new PieEntry(10f, "ỉa"));
        values.add(new PieEntry(34f,"ăn trưa"));
        values.add(new PieEntry(50f,"đánh rank"));
        values.add(new PieEntry(34f,"ăn tối"));

        PieDataSet dataSet = new PieDataSet(values,"Một ngày đẹp trời");
        dataSet.setSliceSpace(1f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);

        Description desc = new Description("Công việc 1 ngày");
        desc.setTextSize(15);

        pieChart.setDescription(desc);
        pieChart.animateY(1000, Easing.EasingOption.EaseOutCubic);
        pieChart.setData(data);
    }
}
