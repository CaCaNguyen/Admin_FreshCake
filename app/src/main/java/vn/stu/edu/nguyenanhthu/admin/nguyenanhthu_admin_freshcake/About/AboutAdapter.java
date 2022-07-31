package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.About;

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

public class AboutAdapter extends ArrayAdapter {
    Activity context;
    int resource;
    List<About> objects;

    public AboutAdapter(Activity context, int resource, List<About> objects){
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


        TextView txtName = item.findViewById(R.id.txtnamestore);
        TextView txtslogan = item.findViewById(R.id.txtslogan);
        TextView txtphone = item.findViewById(R.id.txthotline);
        TextView txtaddress = item.findViewById(R.id.txtaddress);
        TextView txtsince = item.findViewById(R.id.txtsince);

        final About about = this.objects.get(position);
        //cac thuoc tính hien thi ra man hinh duoi dang
        txtName.setText(""+about.getName_store());
        txtslogan.setText(""+about.getSlogan());
        txtphone.setText(""+about.getPhone());
        txtaddress.setText(""+about.getAddress());
        txtsince.setText("SINCE " +about.getSince());

        return item;
    }
}
