package com.hht.myfi.Income;


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
public class ViewIncomeFragment extends Fragment
{

    ListView lvwKhoanThu;
    String[] maKhoanThu;
    String[] tenKhoanThu;
    String[] soTienThu;
    String[] ngayThu;
    String[] ghiChuThu;

    DatabaseHelper db = DatabaseHelper.getInstance(getActivity());

    public ViewIncomeFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_income, container, false);

        lvwKhoanThu = (ListView) view.findViewById(R.id.lvwKhoanThu);
        int size = db.getIncomeID().size();

        maKhoanThu = db.getIncomeID().toArray(new String[size]);
        tenKhoanThu = db.getIncomeName().toArray(new String[size]);
        soTienThu = db.getIncomeAmount().toArray(new String[size]);
        ngayThu = db.getIncomeDate().toArray(new String[size]);
        ghiChuThu = db.getIncomeNote().toArray(new String[size]);

        IncomeListViewAdapter adapter = new IncomeListViewAdapter(
                getContext(),
                maKhoanThu,
                tenKhoanThu,
                soTienThu,
                ngayThu,
                ghiChuThu
        );
        lvwKhoanThu.setAdapter(adapter);

        lvwKhoanThu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), EditIncomeActivity.class);
                intent.putExtra("MaKhoanThu", maKhoanThu[i]);
                intent.putExtra("TenKhoanThu", tenKhoanThu[i]);
                intent.putExtra("SoTienThu", soTienThu[i]);
                intent.putExtra("NgayThu", ngayThu[i]);
                intent.putExtra("GhiChuThu", ghiChuThu[i]);
                startActivity(intent);
            }
        });

        return view;
    }
}
