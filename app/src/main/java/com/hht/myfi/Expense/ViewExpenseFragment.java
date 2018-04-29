package com.hht.myfi.Expense;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hht.myfi.DatabaseHelper;
import com.hht.myfi.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewExpenseFragment extends Fragment {

    ListView lvwKhoanChi;
    String[] maKhoanChi;
    String[] tenKhoanChi;
    String[] soTienChi;
    String[] ngayChi;
    String[] ghiChuChi;

    DatabaseHelper db = DatabaseHelper.getInstance(getActivity());
    
    public ViewExpenseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_expense, container, false);

        lvwKhoanChi = (ListView) view.findViewById(R.id.lvwKhoanChi);
        int size = db.getExpenseID().size();

        maKhoanChi = db.getExpenseID().toArray(new String[size]);
        tenKhoanChi = db.getExpenseName().toArray(new String[size]);
        soTienChi = db.getExpenseAmount().toArray(new String[size]);
        ngayChi = db.getExpenseDate().toArray(new String[size]);
        ghiChuChi = db.getExpenseNote().toArray(new String[size]);

        ExpenseListViewAdapter adapter = new ExpenseListViewAdapter(
                getContext(),
                maKhoanChi,
                tenKhoanChi,
                soTienChi,
                ngayChi,
                ghiChuChi
        );
        lvwKhoanChi.setAdapter(adapter);

        lvwKhoanChi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), EditExpenseActivity.class);
                intent.putExtra("MaKhoanChi", maKhoanChi[i]);
                intent.putExtra("TenKhoanChi", tenKhoanChi[i]);
                intent.putExtra("SoTienChi", soTienChi[i]);
                intent.putExtra("NgayChi", ngayChi[i]);
                intent.putExtra("GhiChuChi", ghiChuChi[i]);
                startActivity(intent);
            }
        });

        return view;
    }

}
