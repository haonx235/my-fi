package com.hht.myfi.Income;

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

class IncomeListViewAdapter extends ArrayAdapter<String>
{
    Context context;

    final String maKhoanThuTitle = "Mã khoản thu: ";
    final String tenKhoanThuTitle = "Tên khoản thu: ";
    final String soTienThuTitle = "Số tiền: ";
    final String ngayThuTitle = "Ngày: ";
    final String ghiChuThuTitle = "Ghi chú: ";

    String maKhoanThu[];
    String tenKhoanThu[];
    String soTienThu[];
    String ngayThu[];
    String ghiChuThu[];

    IncomeListViewAdapter(
            Context c,
            String[] maKhoanThu,
            String[] tenKhoanThu,
            String[] soTienThu,
            String[] ngayThu,
            String[] ghiChuThu)
    {
        super(c, R.layout.row_income, R.id.tvwMaKhoanThu, maKhoanThu);

        this.context = c;
        this.maKhoanThu = maKhoanThu;
        this.tenKhoanThu = tenKhoanThu;
        this.soTienThu = soTienThu;
        this.ngayThu = ngayThu;
        this.ghiChuThu = ghiChuThu;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row_khoan_thu = layoutInflater.inflate(R.layout.row_income, parent, false);

        TextView tvwMaKhoanThu = (TextView) row_khoan_thu.findViewById(R.id.tvwMaKhoanThu);
        TextView tvwTenKhoanThu = (TextView) row_khoan_thu.findViewById(R.id.tvwTenKhoanThu);
        TextView tvwSoTienThu = (TextView) row_khoan_thu.findViewById(R.id.tvwSoTienThu);
        TextView tvwNgayThu = (TextView) row_khoan_thu.findViewById(R.id.tvwNgayThu);
        TextView tvwGhiChuThu = (TextView) row_khoan_thu.findViewById(R.id.tvwGhiChuThu);

        TextView tvwMaKhoanThuTitle = (TextView) row_khoan_thu.findViewById(R.id.tvwMaKhoanThuTitle);
        TextView tvwTenKhoanThuTitle = (TextView) row_khoan_thu.findViewById(R.id.tvwTenKhoanThuTitle);
        TextView tvwSoTienThuTitle = (TextView) row_khoan_thu.findViewById(R.id.tvwSoTienThuTitle);
        TextView tvwNgayThuTitle = (TextView) row_khoan_thu.findViewById(R.id.tvwNgayThuTitle);
        TextView tvwGhiChuThuTitle = (TextView) row_khoan_thu.findViewById(R.id.tvwGhiChuThuTitle);

        tvwMaKhoanThu.setText(maKhoanThu[position]);
        tvwTenKhoanThu.setText(tenKhoanThu[position]);
        tvwSoTienThu.setText(soTienThu[position]);
        tvwNgayThu.setText(formatDate(ngayThu[position]));
        tvwGhiChuThu.setText(ghiChuThu[position]);

        tvwMaKhoanThuTitle.setText(maKhoanThuTitle);
        tvwTenKhoanThuTitle.setText(tenKhoanThuTitle);
        tvwSoTienThuTitle.setText(soTienThuTitle);
        tvwNgayThuTitle.setText(ngayThuTitle);
        tvwGhiChuThuTitle.setText(ghiChuThuTitle);

        return row_khoan_thu;
    }
}