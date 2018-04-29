package com.hht.myfi.Loan;

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

class LoanListViewAdapter extends ArrayAdapter<String>
{
    Context context;

    final String maKhoanChoVayTitle = "Mã khoản cho vay: ";
    final String tenKhoanChoVayTitle = "Tên khoản cho vay: ";
    final String soTienChoVayTitle = "Số tiền: ";
    final String laiSuatChoVayTitle = "Lãi suất: ";
    final String ngayChoVayTitle = "Ngày cho vay: ";
    final String ngayThuLaiTitle = "Ngày thu lại: ";
    final String ghiChuChoVayTitle = "Ghi chú: ";

    String maKhoanChoVay[];
    String tenKhoanChoVay[];
    String soTienChoVay[];
    String laiSuatChoVay[];
    String ngayChoVay[];
    String ngayThuLai[];
    String ghiChuChoVay[];

    LoanListViewAdapter(
            Context c,
            String[] maKhoanChoVay,
            String[] tenKhoanChoVay,
            String[] soTienChoVay,
            String[] laiSuatChoVay,
            String[] ngayChoVay,
            String[] ngayThuLai,
            String[] ghiChuChoVay)
    {
        super(c, R.layout.row_loan, R.id.tvwMaKhoanChoVay, maKhoanChoVay);

        this.context = c;
        this.maKhoanChoVay = maKhoanChoVay;
        this.tenKhoanChoVay = tenKhoanChoVay;
        this.soTienChoVay = soTienChoVay;
        this.laiSuatChoVay = laiSuatChoVay;
        this.ngayChoVay = ngayChoVay;
        this.ngayThuLai = ngayThuLai;
        this.ghiChuChoVay = ghiChuChoVay;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row_khoan_cho_vay = layoutInflater.inflate(R.layout.row_loan, parent, false);

        TextView tvwMaKhoanChoVay = (TextView) row_khoan_cho_vay.findViewById(R.id.tvwMaKhoanChoVay);
        TextView tvwTenKhoanChoVay = (TextView) row_khoan_cho_vay.findViewById(R.id.tvwTenKhoanChoVay);
        TextView tvwSoTienChoVay = (TextView) row_khoan_cho_vay.findViewById(R.id.tvwSoTienChoVay);
        TextView tvwLaiSuatChoVay = (TextView) row_khoan_cho_vay.findViewById(R.id.tvwLaiSuatChoVay);
        TextView tvwNgayChoVay = (TextView) row_khoan_cho_vay.findViewById(R.id.tvwNgayChoVay);
        TextView tvwNgayThuLai = (TextView) row_khoan_cho_vay.findViewById(R.id.tvwNgayThuLai);
        TextView tvwGhiChuChoVay = (TextView) row_khoan_cho_vay.findViewById(R.id.tvwGhiChuChoVay);

        TextView tvwMaKhoanChoVayTitle = (TextView) row_khoan_cho_vay.findViewById(R.id.tvwMaKhoanChoVayTitle);
        TextView tvwTenKhoanChoVayTitle = (TextView) row_khoan_cho_vay.findViewById(R.id.tvwTenKhoanChoVayTitle);
        TextView tvwSoTienChoVayTitle = (TextView) row_khoan_cho_vay.findViewById(R.id.tvwSoTienChoVayTitle);
        TextView tvwLaiSuatChoVayTitle = (TextView) row_khoan_cho_vay.findViewById(R.id.tvwLaiSuatChoVayTitle);
        TextView tvwNgayChoVayTitle = (TextView) row_khoan_cho_vay.findViewById(R.id.tvwNgayChoVayTitle);
        TextView tvwNgayThuLaiTitle = (TextView) row_khoan_cho_vay.findViewById(R.id.tvwNgayThuLaiTitle);
        TextView tvwGhiChuChoVayTitle = (TextView) row_khoan_cho_vay.findViewById(R.id.tvwGhiChuChoVayTitle);

        tvwMaKhoanChoVay.setText(maKhoanChoVay[position]);
        tvwTenKhoanChoVay.setText(tenKhoanChoVay[position]);
        tvwSoTienChoVay.setText(soTienChoVay[position]);
        tvwLaiSuatChoVay.setText(laiSuatChoVay[position]);
        tvwNgayChoVay.setText(formatDate(ngayChoVay[position]));
        tvwNgayThuLai.setText(formatDate(ngayThuLai[position]));
        tvwGhiChuChoVay.setText(ghiChuChoVay[position]);

        tvwMaKhoanChoVayTitle.setText(maKhoanChoVayTitle);
        tvwTenKhoanChoVayTitle.setText(tenKhoanChoVayTitle);
        tvwSoTienChoVayTitle.setText(soTienChoVayTitle);
        tvwLaiSuatChoVayTitle.setText(laiSuatChoVayTitle);
        tvwNgayChoVayTitle.setText(ngayChoVayTitle);
        tvwNgayThuLaiTitle.setText(ngayThuLaiTitle);
        tvwGhiChuChoVayTitle.setText(ghiChuChoVayTitle);

        return row_khoan_cho_vay;
    }
}