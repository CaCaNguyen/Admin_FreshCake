package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.KichThuoc;

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

public class KichThuocAdapter extends ArrayAdapter {

    Activity context;
    int resource;
    List<KichThuoc> objects;

    public KichThuocAdapter(Activity context, int resource, List<KichThuoc> objects){
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
        //lay ben layout item
        TextView txtMa = item.findViewById(R.id.txtMasize);
        TextView txtTenDM = item.findViewById(R.id.txtSize);
        final KichThuoc danhMuc = this.objects.get(position);
        //cac thuoc tính hien thi ra man hinh duoi dang
        txtMa.setText("ID"+danhMuc.getId_size());
        txtTenDM.setText(""+danhMuc.getSize());
        return item;
    }
}
