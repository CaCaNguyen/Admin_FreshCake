package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.R;

public class DanhMucAdapter extends ArrayAdapter {
    Activity context;
    int resource;
    List<DanhMuc> objects;

    public DanhMucAdapter(Activity context, int resource, List<DanhMuc> objects){
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
        TextView txtMa = item.findViewById(R.id.txtMadanhmuc);
        TextView txtTenDM = item.findViewById(R.id.txtTendanhmuc);
        final DanhMuc danhMuc = this.objects.get(position);
        //cac thuoc tính hien thi ra man hinh duoi dang
        txtMa.setText("ID"+danhMuc.getId_danhmuc());
        txtTenDM.setText(""+danhMuc.getTendanhmuc());
        return item;
    }

}
