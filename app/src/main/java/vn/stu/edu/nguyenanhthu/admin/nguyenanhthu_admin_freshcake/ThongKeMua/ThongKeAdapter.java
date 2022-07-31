package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThongKeMua;

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

public class ThongKeAdapter extends ArrayAdapter {
    Activity context;
    int resource;
    List<ThongKe> objects;

    public ThongKeAdapter(Activity context, int resource, List<ThongKe> objects){
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
        TextView txtTensanpham = item.findViewById(R.id.txt_sanphammua);
        TextView txtSoluong = item.findViewById(R.id.txt_soluongmua);

        final ThongKe thongKe = this.objects.get(position);
        //cac thuoc tính hien thi ra man hinh duoi dang
        txtTensanpham.setText(""+ thongKe.getTensanpham());
        txtSoluong.setText(""+ thongKe.getSoluong());

        return item;
    }
}
