package com.hht.myfi;
import com.hht.myfi.Expense.ExpenseActivity;
import com.hht.myfi.Loan.LoanActivity;
import com.hht.myfi.Income.IncomeActivity;
import com.hht.myfi.Debt.DebtActivity;
import com.hht.myfi.ManageIncomeExpenseCategory.ManageIncomeExpenseCategoryActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] titles;
    String[] description;
    int[] imgs = {R.drawable.thu, R.drawable.chi, R.drawable.vay, R.drawable.cho_vay, R.drawable.quan_ly_the_loai};
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        //create database
        DatabaseHelper db = DatabaseHelper.getInstance(this);
        db.createDefaultIncomeCategory();
        db.createDefaultExpenseCategory();

        Resources res = getResources();
        titles = res.getStringArray(R.array.titles);
        description = res.getStringArray(R.array.description);
        listView = (ListView) findViewById(R.id.listView);
        MainListViewAdapter adapter = new MainListViewAdapter(this, titles, imgs, description);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentKhoanThu = new Intent(MainActivity.this, IncomeActivity.class);
                Intent intentKhoanChi = new Intent(MainActivity.this, ExpenseActivity.class);
                Intent intentKhoanVay = new Intent(MainActivity.this, DebtActivity.class);
                Intent intentKhoanChoVay = new Intent(MainActivity.this, LoanActivity.class);
                Intent intentQuanLyTheLoaiThuChi = new Intent(MainActivity.this, ManageIncomeExpenseCategoryActivity.class);

                if (((TextView) view.findViewById(R.id.tvwTitle)).getText().toString().equals("Khoản thu"))
                    startActivity(intentKhoanThu);
                else if (((TextView) view.findViewById(R.id.tvwTitle)).getText().toString().equals("Khoản chi"))
                    startActivity(intentKhoanChi);
                else if (((TextView) view.findViewById(R.id.tvwTitle)).getText().toString().equals("Khoản vay"))
                    startActivity(intentKhoanVay);
                else if (((TextView) view.findViewById(R.id.tvwTitle)).getText().toString().equals("Khoản cho vay"))
                    startActivity(intentKhoanChoVay);
                else if (((TextView) view.findViewById(R.id.tvwTitle)).getText().toString().equals("Quản lý loại thu/chi"))
                    startActivity(intentQuanLyTheLoaiThuChi);
            }
        });
    }
}
