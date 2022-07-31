package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DangKy;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.R;

public class DangKyAdapter extends ArrayAdapter {

    Activity context;
    int resource;
    List<DangKy> objects;

    public DangKyAdapter(Activity context, int resource, List<DangKy> objects){
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

        TextView txtMa = item.findViewById(R.id.txtId_dangki);
        TextView txtTenKhach = item.findViewById(R.id.txt_tenkhachhang);
        TextView txtEmail = item.findViewById(R.id.txt_email);
        TextView txtSDT = item.findViewById(R.id.txt_phone);
        final DangKy dangKy = this.objects.get(position);
        //cac thuoc tính hien thi ra man hinh duoi dang
        txtMa.setText("ID"+dangKy.getId_dangky());
        txtTenKhach.setText(""+dangKy.getTenkhachhang());
        txtEmail.setText(""+dangKy.getEmail());
        txtSDT.setText(""+ dangKy.getSdt());
        return item;
    }
}
