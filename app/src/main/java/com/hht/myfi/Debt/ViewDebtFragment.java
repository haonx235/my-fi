package com.hht.myfi.Debt;


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
public class ViewDebtFragment extends Fragment
{

    ListView lvwKhoanVay;
    String[] maKhoanVay;
    String[] tenKhoanVay;
    String[] soTienVay;
    String[] laiSuatVay;
    String[] ngayVay;
    String[] ngayTraVay;
    String[] ghiChuVay;

    DatabaseHelper db = DatabaseHelper.getInstance(getActivity());

    public ViewDebtFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_debt, container, false);

        lvwKhoanVay = (ListView) view.findViewById(R.id.lvwKhoanVay);
        int size = db.getDebtID().size();

        maKhoanVay = db.getDebtID().toArray(new String[size]);
        tenKhoanVay = db.getDebtName().toArray(new String[size]);
        soTienVay = db.getDebtAmount().toArray(new String[size]);
        laiSuatVay = db.getDebtInterestRate().toArray(new String[size]);
        ngayVay = db.getDebtDate().toArray(new String[size]);
        ngayTraVay = db.getDebtExpirationDate().toArray(new String[size]);
        ghiChuVay = db.getDebtNote().toArray(new String[size]);

        DebtListViewAdapter adapter = new DebtListViewAdapter(
                getContext(),
                maKhoanVay,
                tenKhoanVay,
                soTienVay,
                laiSuatVay,
                ngayVay,
                ngayTraVay,
                ghiChuVay
        );
        lvwKhoanVay.setAdapter(adapter);

        lvwKhoanVay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), EditDebtActivity.class);
                intent.putExtra("MaKhoanVay", maKhoanVay[i]);
                intent.putExtra("TenKhoanVay", tenKhoanVay[i]);
                intent.putExtra("SoTienVay", soTienVay[i]);
                intent.putExtra("LaiSuatVay", laiSuatVay[i]);
                intent.putExtra("NgayVay", ngayVay[i]);
                intent.putExtra("NgayTraVay", ngayTraVay[i]);
                intent.putExtra("GhiChuVay", ghiChuVay[i]);
                startActivity(intent);
            }
        });

        return view;
    }



}
