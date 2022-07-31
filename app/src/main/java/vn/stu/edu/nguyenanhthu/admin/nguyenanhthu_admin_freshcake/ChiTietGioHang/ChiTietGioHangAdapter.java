package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ChiTietGioHang;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DonHang.DonHang;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.R;

public class ChiTietGioHangAdapter extends ArrayAdapter {

    Activity context;
    int resource;
    List<ChiTietGioHang> objects;

    public ChiTietGioHangAdapter(Activity context, int resource, List<ChiTietGioHang> objects){
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);


        TextView txtCTGH = item.findViewById(R.id.txt_id_chitietgiohang);
        TextView txtIdGioHang = item.findViewById(R.id.txt_id_giohang);
        TextView txtTenSanPham = item.findViewById(R.id.txt_tensanpham);
        TextView txtSoLuong = item.findViewById(R.id.txt_soluong);
        TextView txtSize = item.findViewById(R.id.txt_size);

        final  ChiTietGioHang  chiTietGioHang = this.objects.get(position);
        txtCTGH.setText("ID"+ chiTietGioHang.getId_chitietgiohang());
        txtIdGioHang.setText("Mã Giỏ Hàng : "+chiTietGioHang.getMagiohang());
        txtTenSanPham.setText(""+chiTietGioHang.getTensanpham());
        txtSoLuong.setText("SL : " + chiTietGioHang.getSoluong());
        txtSize.setText(""+chiTietGioHang.getSize());

        return item;
    }
}
