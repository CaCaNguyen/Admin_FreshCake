package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DonHang;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.R;

public class DonHangAdapter extends ArrayAdapter {

    Activity context;
    int resource;
    List<DonHang> objects;

    public DonHangAdapter(Activity context, int resource, List<DonHang> objects){
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


        TextView txtMa = item.findViewById(R.id.txtIdgiohang);
        TextView txtTenKH = item.findViewById(R.id.txtTenkhachhang);
        TextView txtTrangThai = item.findViewById(R.id.txtTrangthai);
        TextView txtNgayDat = item.findViewById(R.id.txtNgaylap);
        TextView txtNgayNhan = item.findViewById(R.id.txtNgaynhan);

        final DonHang donHang = this.objects.get(position);

        //cac thuoc tính hien thi ra man hinh duoi dang
        txtMa.setText("ID"+donHang.getId_giohang());
        txtTenKH.setText(""+donHang.getTenkhachhang());
        txtTrangThai.setText(""+donHang.getTrangthai());
        txtNgayDat.setText(""+donHang.getNgaylap());
        txtNgayNhan.setText(""+donHang.getNgaynhan());

        return item;
    }
}
