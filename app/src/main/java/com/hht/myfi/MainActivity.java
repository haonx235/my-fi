package com.hht.myfi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.hht.myfi.CSVExporter.CSVExporterActivity;
import com.hht.myfi.Chart.PieChartActivity;
import com.hht.myfi.Debt.DebtActivity;
import com.hht.myfi.Expense.ExpenseActivity;
import com.hht.myfi.Income.IncomeActivity;
import com.hht.myfi.Loan.LoanActivity;
import com.hht.myfi.ManageIncomeExpenseCategory.ManageIncomeExpenseCategoryActivity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView1;
    String[] titles;
    String[] description;
    int[] imgs = {R.drawable.thu, R.drawable.chi, R.drawable.vay, R.drawable.cho_vay, R.drawable.quan_ly_the_loai, R.drawable.bieudo, R.drawable.csv};
    Toolbar toolbar;
    TextView sum_money;
    static int sum = 0;

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;
    static DatabaseHelper db;

    //NTN
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView txtTenNhanVien_Navigation;
    FragmentManager fragmentManager;
    //
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //NTN
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
        //

        //create database
        db = DatabaseHelper.getInstance(this);
        db.createDefaultIncomeCategory();
        db.createDefaultExpenseCategory();


//        Resources res = getResources();
//        titles = res.getStringArray(R.array.titles);
//        description = res.getStringArray(R.array.description);
//        listView1 = (ListView) findViewById(R.id.listView1);
//        MainListViewAdapter adapter = new MainListViewAdapter(this, titles, imgs, description);
//        listView1.setAdapter(adapter);
//
//
//        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intentKhoanThu = new Intent(MainActivity.this, IncomeActivity.class);
//                Intent intentKhoanChi = new Intent(MainActivity.this, ExpenseActivity.class);
//                Intent intentKhoanVay = new Intent(MainActivity.this, DebtActivity.class);
//                Intent intentKhoanChoVay = new Intent(MainActivity.this, LoanActivity.class);
//                Intent intentQuanLyTheLoaiThuChi = new Intent(MainActivity.this, ManageIncomeExpenseCategoryActivity.class);
//                Intent intentThongKe = new Intent(MainActivity.this, PieChartActivity.class);
//                Intent intentCSVExporter = new Intent(MainActivity.this, CSVExporterActivity.class);
//
//                if (((TextView) view.findViewById(R.id.tvwTitle)).getText().toString().equals("Khoản thu"))
//                    startActivity(intentKhoanThu);
//                else if (((TextView) view.findViewById(R.id.tvwTitle)).getText().toString().equals("Khoản chi"))
//                    startActivity(intentKhoanChi);
//                else if (((TextView) view.findViewById(R.id.tvwTitle)).getText().toString().equals("Khoản vay"))
//                    startActivity(intentKhoanVay);
//                else if (((TextView) view.findViewById(R.id.tvwTitle)).getText().toString().equals("Khoản cho vay"))
//                    startActivity(intentKhoanChoVay);
//                else if (((TextView) view.findViewById(R.id.tvwTitle)).getText().toString().equals("Quản lý loại thu/chi"))
//                    startActivity(intentQuanLyTheLoaiThuChi);
//                else if (((TextView) view.findViewById(R.id.tvwTitle)).getText().toString().equals("Thống kê"))
//                    startActivity(intentThongKe);
//                else if (((TextView) view.findViewById(R.id.tvwTitle)).getText().toString().equals("Xuất bảng biểu"))
//                    startActivity(intentCSVExporter);
//            }
//        });

        //Show ra màn hình chính
        listView = (ExpandableListView)findViewById(R.id.lvExp);
        initData();

        sum_money = (TextView) findViewById(R.id.sum_money);


        sum_money.setText(formatVnCurrence(this,String.valueOf(sum)));

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHash);
        listView.setAdapter(listAdapter);
        //Show ra màn hình chính
    }
    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Khoản thu");
        listDataHeader.add("Khoản chi");
        listDataHeader.add("Khoản vay");
        listDataHeader.add("Khoản cho vay");

        List<String> list_income = new ArrayList<>();
        List<Pair<String, Integer>> myDataIncome = new ArrayList<Pair<String, Integer>>();
        int index = 0;

        myDataIncome = db.getOverallDataInformation(DatabaseHelper.TABLE_INCOME, "incomeName", "incomeName, SUM(incomeAmount)");

        for (Pair<String, Integer> p : myDataIncome) {
            sum += p.second;
            String temp = p.first +":  "+formatVnCurrence(this,String.valueOf(p.second));
            list_income.add(temp);
            index++;
        }

        List<String> list_expense = new ArrayList<>();
        List<Pair<String, Integer>> myDataExpense = new ArrayList<Pair<String, Integer>>();
        int index_income = 0;
        myDataExpense = db.getOverallDataInformation(DatabaseHelper.TABLE_EXPENSE, "expenseName", "expenseName, (0-SUM(expenseAmount))");
        for (Pair<String, Integer> p : myDataExpense) {
            sum -= p.second;
            String temp = p.first +":  "+formatVnCurrence(this,String.valueOf(p.second));
            list_expense.add(temp);
            index_income++;
        }




        List<String> list_debt = new ArrayList<>();
        List<Pair<String, Integer>> myDataDebt = new ArrayList<Pair<String, Integer>>();
        int index_debt = 0;
        myDataDebt = db.getOverallDataInformation(DatabaseHelper.TABLE_DEBT, "debtName", "debtName, (0-SUM(debtAmount))");
        for (Pair<String, Integer> p : myDataDebt) {
            String temp = p.first +":  "+formatVnCurrence(this,String.valueOf(p.second));
            list_debt.add(temp);
            index_debt++;
        }



        List<String> list_loan = new ArrayList<>();
        List<Pair<String, Integer>> myDataLoan = new ArrayList<Pair<String, Integer>>();
        int index_loan = 0;
        myDataLoan =db.getOverallDataInformation(DatabaseHelper.TABLE_LOAN, "loanName", "loanName, SUM(loanAmount)");
        for (Pair<String, Integer> p : myDataLoan) {
            String temp = p.first +":  "+formatVnCurrence(this,String.valueOf(p.second));
            list_loan.add(temp);
            index_loan++;
        }
        listHash.put(listDataHeader.get(0),list_income);
        listHash.put(listDataHeader.get(1),list_expense);
        listHash.put(listDataHeader.get(2),list_debt);
        listHash.put(listDataHeader.get(3),list_loan);
    }

    public static String formatVnCurrence(Context context, String price) {

        NumberFormat format =
                new DecimalFormat("#,##0.00");// #,##0.00 ¤ (¤:// Currency symbol)
        format.setCurrency(Currency.getInstance(Locale.US));//Or default locale

        price = (!TextUtils.isEmpty(price)) ? price : "0";
        price = price.trim();
        price = format.format(Double.parseDouble(price));
        price = price.replaceAll(",", "\\.");

        if (price.endsWith(".00")) {
            int centsIndex = price.lastIndexOf(".00");
            if (centsIndex != -1) {
                price = price.substring(0, centsIndex);
            }
        }
        price = String.format("%s đ", price);
        return price;
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
            case R.id.itBieuDo:
                Intent intentThongKe = new Intent(MainActivity.this, PieChartActivity.class);
                startActivity(intentThongKe);
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.itXuatCSV:
                Intent intentCSVExporter = new Intent(MainActivity.this, CSVExporterActivity.class);
                startActivity(intentCSVExporter);
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
        }
        return false;
    }

}
