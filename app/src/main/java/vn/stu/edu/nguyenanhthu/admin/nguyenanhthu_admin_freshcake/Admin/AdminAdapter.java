package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.Admin;

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

public class AdminAdapter extends ArrayAdapter {
    Activity context;
    int resource;
    List<Admin> objects;

    public AdminAdapter(Activity context, int resource, List<Admin> objects){
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
        TextView txtMa = item.findViewById(R.id.txt_id_admin);
        TextView txtUser = item.findViewById(R.id.txt_username);
        TextView txtPass = item.findViewById(R.id.txt_password);

        final Admin admin = this.objects.get(position);
        txtMa.setText("ID"+admin.getId_admin());
        txtUser.setText(""+admin.getUsername());
        txtPass.setText(""+admin.getPassword());

        return item;
    }
}
