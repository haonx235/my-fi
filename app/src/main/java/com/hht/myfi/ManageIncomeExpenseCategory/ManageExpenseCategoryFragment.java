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

import com.hht.myfi.DTO.DTO_ExpenseCategory;
import com.hht.myfi.DatabaseHelper;
import com.hht.myfi.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ManageExpenseCategoryFragment extends Fragment
{

    ListView lvwTheLoaiChi;
    String[] maQuanLyTheLoaiChi;
    String[] tenQuanLyTheLoaiChi;

    DatabaseHelper db = DatabaseHelper.getInstance(getActivity());

    public ManageExpenseCategoryFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manage_expense_category, container, false);

        lvwTheLoaiChi = (ListView) view.findViewById(R.id.lvwTheLoaiChi);
        int size = db.getExpenseCategoryID().size();

        maQuanLyTheLoaiChi = db.getExpenseCategoryID().toArray(new String[size]);
        tenQuanLyTheLoaiChi = db.getExpenseCategory().toArray(new String[size]);

        ManageIncomeExpenseCategoryListViewAdapter adapter = new ManageIncomeExpenseCategoryListViewAdapter(
                getContext(),
                maQuanLyTheLoaiChi,
                tenQuanLyTheLoaiChi
        );
        lvwTheLoaiChi.setAdapter(adapter);

        lvwTheLoaiChi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Show dialog
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View mView = getLayoutInflater().inflate(R.layout.dialog_expense_category, null);
                final TextView tvwSuaMaTheLoaiChi = (TextView) mView.findViewById(R.id.tvwSuaMaTheLoaiChi);
                final EditText etxSuaTenTheLoaiChi = (EditText) mView.findViewById(R.id.etxSuaTenTheLoaiChi);
                Button btnSuaTheLoaiChiCapNhat = (Button) mView.findViewById(R.id.btnSuaTheLoaiChiCapNhat);
                Button btnSuaTheLoaiChiXoa = (Button) mView.findViewById(R.id.btnSuaTheLoaiChiXoa);

                tvwSuaMaTheLoaiChi.setText(maQuanLyTheLoaiChi[i]);
                etxSuaTenTheLoaiChi.setText(tenQuanLyTheLoaiChi[i]);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                btnSuaTheLoaiChiCapNhat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DTO_ExpenseCategory theLoaiChi = new DTO_ExpenseCategory(etxSuaTenTheLoaiChi.getText().toString());
                        db.updateExpenseCategory(theLoaiChi, tvwSuaMaTheLoaiChi.getText().toString());
                        Toast toast = Toast.makeText(getContext(), "Cập nhật thành công. \nVui lòng bấm trở về để xem thay đổi.", Toast.LENGTH_LONG);
                        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                        if (v != null)
                            v.setGravity(Gravity.CENTER);
                        toast.show();                        dialog.dismiss();
                    }
                });

                btnSuaTheLoaiChiXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db.deleteExpenseCategory(tvwSuaMaTheLoaiChi.getText().toString());
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
