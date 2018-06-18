package com.hht.myfi.Chart;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;

import com.hht.myfi.MainActivity;
import com.hht.myfi.R;
import com.hht.myfi.ViewPagerAdapter;

public class PieChartActivity extends AppCompatActivity implements ActionBar.TabListener {


    ViewPager pager;
    TabLayout tabLayout;

    ViewPager mViewPager;
    static Context mContext;
//    PagerAdapter pagerAdapter;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setContentView(R.layout.activity_piechart);
        mContext = this;

        setContentView(R.layout.chart_viewpager);


        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new IncomeFragment(), "Khoản thu");
        viewPagerAdapter.addFragments(new ExpenseFragment(), "Khoản chi");
        viewPagerAdapter.addFragments(new DebtFragment(), "Khoản vay");
        viewPagerAdapter.addFragments(new LoanFragment(), "Khoản cho vay");
        mViewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);




        getSupportActionBar().show();
        getSupportActionBar().setTitle("Thống kê");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barchart, menu);
        menu.getItem(1).setEnabled(false);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            case R.id.bar_chart:
                Intent bar_chart = new Intent(this, PNBarChartActivity.class);
                bar_chart.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(bar_chart);
                return true;
            case R.id.pie_chart:
                Intent pie_chart = new Intent(this, PieChartActivity.class);
                pie_chart.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(pie_chart);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}

