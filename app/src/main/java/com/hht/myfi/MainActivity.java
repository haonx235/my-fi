package com.hht.myfi;
import com.hht.myfi.Expense.ExpenseActivity;
import com.hht.myfi.Loan.LoanActivity;
import com.hht.myfi.Income.IncomeActivity;
import com.hht.myfi.Debt.DebtActivity;
import com.hht.myfi.ManageIncomeExpenseCategory.ManageIncomeExpenseCategoryActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView;
    String[] titles;
    String[] description;
    int[] imgs = {R.drawable.thu, R.drawable.chi, R.drawable.vay, R.drawable.cho_vay, R.drawable.quan_ly_the_loai};
    Toolbar toolbar;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView txtTenNhanVien_Navigation;
    FragmentManager fragmentManager;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.nvHome);
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);

        View view = navigationView.inflateHeaderView(R.layout.nav_header_main_menu);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);


        //create database
        DatabaseHelper db = DatabaseHelper.getInstance(this);
        db.createDefaultIncomeCategory();
        db.createDefaultExpenseCategory();
        
    }
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itThu:
                Intent intentKhoanThu = new Intent(MainActivity.this, IncomeActivity.class);
                startActivity(intentKhoanThu);

                item.setChecked(true);
                drawerLayout.closeDrawers();
                ;break;

            case R.id.itChi:
                Intent intentKhoanChi = new Intent(MainActivity.this, ExpenseActivity.class);
                startActivity(intentKhoanChi);

                item.setChecked(true);
                drawerLayout.closeDrawers();
                ;break;

            case R.id.itVay:
                Intent intentKhoanVay = new Intent(MainActivity.this, DebtActivity.class);
                startActivity(intentKhoanVay);
                item.setChecked(true);
                drawerLayout.closeDrawers();
                ;break;
            case R.id.itChoVay:
                Intent intentKhoanChoVay = new Intent(MainActivity.this, LoanActivity.class);
                startActivity(intentKhoanChoVay);
                item.setChecked(true);
                drawerLayout.closeDrawers();
                ;break;
            case R.id.itLoaiThuChi:
                Intent intentQuanLyTheLoaiThuChi = new Intent(MainActivity.this, ManageIncomeExpenseCategoryActivity.class);
                startActivity(intentQuanLyTheLoaiThuChi);
                item.setChecked(true);
                drawerLayout.closeDrawers();
                ;break;

        }
        return false;
    }
}
