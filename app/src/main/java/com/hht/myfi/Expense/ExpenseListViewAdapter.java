package com.hht.myfi.Expense;

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

class ExpenseListViewAdapter extends ArrayAdapter<String>
{
    Context context;

    final String maKhoanChiTitle = "Mã khoản chi: ";
    final String tenKhoanChiTitle = "Tên khoản chi: ";
    final String soTienChiTitle = "Số tiền: ";
    final String ngayChiTitle = "Ngày: ";
    final String ghiChuChiTitle = "Ghi chú: ";

    String maKhoanChi[];
    String tenKhoanChi[];
    String soTienChi[];
    String ngayChi[];
    String ghiChuChi[];

    ExpenseListViewAdapter(
            Context c,
            String[] maKhoanChi,
            String[] tenKhoanChi,
            String[] soTienChi,
            String[] ngayChi,
            String[] ghiChuChi)
    {
        super(c, R.layout.row_expense, R.id.tvwMaKhoanChi, maKhoanChi);

        this.context = c;
        this.maKhoanChi = maKhoanChi;
        this.tenKhoanChi = tenKhoanChi;
        this.soTienChi = soTienChi;
        this.ngayChi = ngayChi;
        this.ghiChuChi = ghiChuChi;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row_khoan_Chi = layoutInflater.inflate(R.layout.row_expense, parent, false);

        TextView tvwMaKhoanChi = (TextView) row_khoan_Chi.findViewById(R.id.tvwMaKhoanChi);
        TextView tvwTenKhoanChi = (TextView) row_khoan_Chi.findViewById(R.id.tvwTenKhoanChi);
        TextView tvwSoTienChi = (TextView) row_khoan_Chi.findViewById(R.id.tvwSoTienChi);
        TextView tvwNgayChi = (TextView) row_khoan_Chi.findViewById(R.id.tvwNgayChi);
        TextView tvwGhiChuChi = (TextView) row_khoan_Chi.findViewById(R.id.tvwGhiChuChi);

        TextView tvwMaKhoanChiTitle = (TextView) row_khoan_Chi.findViewById(R.id.tvwMaKhoanChiTitle);
        TextView tvwTenKhoanChiTitle = (TextView) row_khoan_Chi.findViewById(R.id.tvwTenKhoanChiTitle);
        TextView tvwSoTienChiTitle = (TextView) row_khoan_Chi.findViewById(R.id.tvwSoTienChiTitle);
        TextView tvwNgayChiTitle = (TextView) row_khoan_Chi.findViewById(R.id.tvwNgayChiTitle);
        TextView tvwGhiChuChiTitle = (TextView) row_khoan_Chi.findViewById(R.id.tvwGhiChuChiTitle);

        tvwMaKhoanChi.setText(maKhoanChi[position]);
        tvwTenKhoanChi.setText(tenKhoanChi[position]);
        tvwSoTienChi.setText(soTienChi[position]);
        tvwNgayChi.setText(formatDate(ngayChi[position]));
        tvwGhiChuChi.setText(ghiChuChi[position]);

        tvwMaKhoanChiTitle.setText(maKhoanChiTitle);
        tvwTenKhoanChiTitle.setText(tenKhoanChiTitle);
        tvwSoTienChiTitle.setText(soTienChiTitle);
        tvwNgayChiTitle.setText(ngayChiTitle);
        tvwGhiChuChiTitle.setText(ghiChuChiTitle);

        return row_khoan_Chi;
    }
}