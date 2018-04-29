package com.hht.myfi.Debt;

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

class DebtListViewAdapter extends ArrayAdapter<String>
{
    Context context;

    final String maKhoanVayTitle = "Mã khoản vay: ";
    final String tenKhoanVayTitle = "Tên khoản vay: ";
    final String soTienVayTitle = "Số tiền: ";
    final String laiSuatVayTitle = "Lãi suất: ";
    final String ngayVayTitle = "Ngày vay: ";
    final String ngayTraVayTitle = "Ngày trả: ";
    final String ghiChuVayTitle = "Ghi chú: ";

    String maKhoanVay[];
    String tenKhoanVay[];
    String soTienVay[];
    String laiSuatVay[];
    String ngayVay[];
    String ngayTraVay[];
    String ghiChuVay[];

    DebtListViewAdapter(
            Context c,
            String[] maKhoanVay,
            String[] tenKhoanVay,
            String[] soTienVay,
            String[] laiSuatVay,
            String[] ngayVay,
            String[] ngayTraVay,
            String[] ghiChuVay)
    {
        super(c, R.layout.row_debt, R.id.tvwMaKhoanVay, maKhoanVay);

        this.context = c;
        this.maKhoanVay = maKhoanVay;
        this.tenKhoanVay = tenKhoanVay;
        this.soTienVay = soTienVay;
        this.laiSuatVay = laiSuatVay;
        this.ngayVay = ngayVay;
        this.ngayTraVay = ngayTraVay;
        this.ghiChuVay = ghiChuVay;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row_khoan_vay = layoutInflater.inflate(R.layout.row_debt, parent, false);

        TextView tvwMaKhoanVay = (TextView) row_khoan_vay.findViewById(R.id.tvwMaKhoanVay);
        TextView tvwTenKhoanVay = (TextView) row_khoan_vay.findViewById(R.id.tvwTenKhoanVay);
        TextView tvwSoTienVay = (TextView) row_khoan_vay.findViewById(R.id.tvwSoTienVay);
        TextView tvwLaiSuatVay = (TextView) row_khoan_vay.findViewById(R.id.tvwLaiSuatVay);
        TextView tvwNgayVay = (TextView) row_khoan_vay.findViewById(R.id.tvwNgayVay);
        TextView tvwNgayTraVay = (TextView) row_khoan_vay.findViewById(R.id.tvwNgayTraVay);
        TextView tvwGhiChuVay = (TextView) row_khoan_vay.findViewById(R.id.tvwGhiChuVay);

        TextView tvwMaKhoanVayTitle = (TextView) row_khoan_vay.findViewById(R.id.tvwMaKhoanVayTitle);
        TextView tvwTenKhoanVayTitle = (TextView) row_khoan_vay.findViewById(R.id.tvwTenKhoanVayTitle);
        TextView tvwSoTienVayTitle = (TextView) row_khoan_vay.findViewById(R.id.tvwSoTienVayTitle);
        TextView tvwLaiSuatVayTitle = (TextView) row_khoan_vay.findViewById(R.id.tvwLaiSuatVayTitle);
        TextView tvwNgayVayTitle = (TextView) row_khoan_vay.findViewById(R.id.tvwNgayVayTitle);
        TextView tvwNgayTraVayTitle = (TextView) row_khoan_vay.findViewById(R.id.tvwNgayTraVayTitle);
        TextView tvwGhiChuVayTitle = (TextView) row_khoan_vay.findViewById(R.id.tvwGhiChuVayTitle);

        tvwMaKhoanVay.setText(maKhoanVay[position]);
        tvwTenKhoanVay.setText(tenKhoanVay[position]);
        tvwSoTienVay.setText(soTienVay[position]);
        tvwLaiSuatVay.setText(laiSuatVay[position]);
        tvwNgayVay.setText(formatDate(ngayVay[position]));
        tvwNgayTraVay.setText(formatDate(ngayTraVay[position]));
        tvwGhiChuVay.setText(ghiChuVay[position]);

        tvwMaKhoanVayTitle.setText(maKhoanVayTitle);
        tvwTenKhoanVayTitle.setText(tenKhoanVayTitle);
        tvwSoTienVayTitle.setText(soTienVayTitle);
        tvwLaiSuatVayTitle.setText(laiSuatVayTitle);
        tvwNgayVayTitle.setText(ngayVayTitle);
        tvwNgayTraVayTitle.setText(ngayTraVayTitle);
        tvwGhiChuVayTitle.setText(ghiChuVayTitle);

        return row_khoan_vay;
    }
}