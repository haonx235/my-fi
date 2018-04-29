package com.hht.myfi.Loan;


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
public class ViewLoanFragment extends Fragment
{

    ListView lvwKhoanChoVay;
    String[] maKhoanChoVay;
    String[] tenKhoanChoVay;
    String[] soTienChoVay;
    String[] laiSuatChoVay;
    String[] ngayChoVay;
    String[] ngayThuLai;
    String[] ghiChuChoVay;

    DatabaseHelper db = DatabaseHelper.getInstance(getActivity());

    public ViewLoanFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_loan, container, false);

        lvwKhoanChoVay = (ListView) view.findViewById(R.id.lvwKhoanChoVay);
        int size = db.getLoanID().size();

        maKhoanChoVay = db.getLoanID().toArray(new String[size]);
        tenKhoanChoVay = db.getLoanName().toArray(new String[size]);
        soTienChoVay = db.getLoanAmount().toArray(new String[size]);
        laiSuatChoVay = db.getLoanInterestRate().toArray(new String[size]);
        ngayChoVay = db.getLoanDate().toArray(new String[size]);
        ngayThuLai = db.getLoanExpirationDate().toArray(new String[size]);
        ghiChuChoVay = db.getLoanNote().toArray(new String[size]);

        LoanListViewAdapter adapter = new LoanListViewAdapter(
                getContext(),
                maKhoanChoVay,
                tenKhoanChoVay,
                soTienChoVay,
                laiSuatChoVay,
                ngayChoVay,
                ngayThuLai,
                ghiChuChoVay
        );
        lvwKhoanChoVay.setAdapter(adapter);

        lvwKhoanChoVay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), EditLoanActivity.class);
                intent.putExtra("MaKhoanChoVay", maKhoanChoVay[i]);
                intent.putExtra("TenKhoanChoVay", tenKhoanChoVay[i]);
                intent.putExtra("SoTienChoVay", soTienChoVay[i]);
                intent.putExtra("LaiSuatChoVay", laiSuatChoVay[i]);
                intent.putExtra("NgayChoVay", ngayChoVay[i]);
                intent.putExtra("NgayThuLai", ngayThuLai[i]);
                intent.putExtra("GhiChuChoVay", ghiChuChoVay[i]);
                startActivity(intent);
            }
        });

        return view;
    }



}
