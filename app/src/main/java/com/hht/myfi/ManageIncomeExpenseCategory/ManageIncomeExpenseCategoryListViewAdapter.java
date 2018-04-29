package com.hht.myfi.ManageIncomeExpenseCategory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hht.myfi.R;
import static com.hht.myfi.Helper.formatDate;

/**
 * Created by streetguard on 27-Dec-17.
 */

class ManageIncomeExpenseCategoryListViewAdapter extends ArrayAdapter<String>
{
    Context context;

    final String maQuanLyTheLoaiThuChiTitle = "Mã thể loại: ";
    final String tenQuanLyTheLoaiThuChiTitle = "Tên thể loại: ";

    String maQuanLyTheLoaiThuChi[];
    String tenQuanLyTheLoaiThuChi[];

    ManageIncomeExpenseCategoryListViewAdapter(
            Context c,
            String[] maQuanLyTheLoaiThuChi,
            String[] tenQuanLyTheLoaiThuChi)
    {
        super(c, R.layout.row_manage_category, R.id.tvwMaQuanLyTheLoaiThuChi, maQuanLyTheLoaiThuChi);

        this.context = c;
        this.maQuanLyTheLoaiThuChi = maQuanLyTheLoaiThuChi;
        this.tenQuanLyTheLoaiThuChi = tenQuanLyTheLoaiThuChi;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row_quan_ly_the_loai = layoutInflater.inflate(R.layout.row_manage_category, parent, false);

        TextView tvwMaQuanLyTheLoaiThuChi = (TextView) row_quan_ly_the_loai.findViewById(R.id.tvwMaQuanLyTheLoaiThuChi);
        TextView tvwTenQuanLyTheLoaiThuChi = (TextView) row_quan_ly_the_loai.findViewById(R.id.tvwTenQuanLyTheLoaiThuChi);


        TextView tvwMaQuanLyTheLoaiThuChiTitle = (TextView) row_quan_ly_the_loai.findViewById(R.id.tvwMaQuanLyTheLoaiThuChiTitle);
        TextView tvwTenQuanLyTheLoaiThuChiTitle = (TextView) row_quan_ly_the_loai.findViewById(R.id.tvwTenQuanLyTheLoaiThuChiTitle);

        tvwMaQuanLyTheLoaiThuChi.setText(maQuanLyTheLoaiThuChi[position]);
        tvwTenQuanLyTheLoaiThuChi.setText(tenQuanLyTheLoaiThuChi[position]);

        tvwMaQuanLyTheLoaiThuChiTitle.setText(maQuanLyTheLoaiThuChiTitle);
        tvwTenQuanLyTheLoaiThuChiTitle.setText(tenQuanLyTheLoaiThuChiTitle);

        return row_quan_ly_the_loai;
    }
}