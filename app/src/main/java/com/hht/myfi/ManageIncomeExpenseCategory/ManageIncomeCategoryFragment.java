package com.hht.myfi.ManageIncomeExpenseCategory;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hht.myfi.DTO.DTO_IncomeCategory;
import com.hht.myfi.DatabaseHelper;
import com.hht.myfi.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ManageIncomeCategoryFragment extends Fragment
{

    ListView lvwTheLoaiThu;
    String[] maQuanLyTheLoaiThu;
    String[] tenQuanLyTheLoaiThu;

    DatabaseHelper db = DatabaseHelper.getInstance(getActivity());

    public ManageIncomeCategoryFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manage_income_category, container, false);

        lvwTheLoaiThu = (ListView) view.findViewById(R.id.lvwTheLoaiThu);
        int size = db.getIncomeCategoryID().size();

        maQuanLyTheLoaiThu = db.getIncomeCategoryID().toArray(new String[size]);
        tenQuanLyTheLoaiThu = db.getIncomeCategory().toArray(new String[size]);

        ManageIncomeExpenseCategoryListViewAdapter adapter = new ManageIncomeExpenseCategoryListViewAdapter(
                getContext(),
                maQuanLyTheLoaiThu,
                tenQuanLyTheLoaiThu
        );
        lvwTheLoaiThu.setAdapter(adapter);

        lvwTheLoaiThu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Show dialog
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View mView = getLayoutInflater().inflate(R.layout.dialog_income_category, null);
                final TextView tvwSuaMaTheLoaiThu = (TextView) mView.findViewById(R.id.tvwSuaMaTheLoaiThu);
                final EditText etxSuaTenTheLoaiThu = (EditText) mView.findViewById(R.id.etxSuaTenTheLoaiThu);
                Button btnSuaTheLoaiThuCapNhat = (Button) mView.findViewById(R.id.btnSuaTheLoaiThuCapNhat);
                Button btnSuaTheLoaiThuXoa = (Button) mView.findViewById(R.id.btnSuaTheLoaiThuXoa);

                tvwSuaMaTheLoaiThu.setText(maQuanLyTheLoaiThu[i]);
                etxSuaTenTheLoaiThu.setText(tenQuanLyTheLoaiThu[i]);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                btnSuaTheLoaiThuCapNhat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DTO_IncomeCategory theLoaiThu = new DTO_IncomeCategory(etxSuaTenTheLoaiThu.getText().toString());
                        db.updateIncomeCategory(theLoaiThu, tvwSuaMaTheLoaiThu.getText().toString());
                        Toast toast = Toast.makeText(getContext(), "Cập nhật thành công. \nVui lòng bấm trở về để xem thay đổi.", Toast.LENGTH_LONG);
                        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                        if (v != null)
                            v.setGravity(Gravity.CENTER);
                        toast.show();                        dialog.dismiss();
                    }
                });

                btnSuaTheLoaiThuXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db.deleteIncomeCategory(tvwSuaMaTheLoaiThu.getText().toString());
                        Toast toast = Toast.makeText(getContext(), "Xoá thành công. \nVui lòng bấm trở về để xem thay đổi.", Toast.LENGTH_LONG);
                        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                        if (v != null)
                            v.setGravity(Gravity.CENTER);
                        toast.show();                        dialog.dismiss();
                    }
                });
            }
        });

        return view;
    }



}
