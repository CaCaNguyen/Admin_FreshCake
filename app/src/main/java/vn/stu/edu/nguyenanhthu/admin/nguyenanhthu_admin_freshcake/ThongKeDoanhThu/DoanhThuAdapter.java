package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThongKeDoanhThu;

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
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThongKeMua.ThongKe;

public class DoanhThuAdapter extends ArrayAdapter {
    Activity context;
    int resource;
    List<DoanhThu> objects;

    public DoanhThuAdapter(Activity context, int resource, List<DoanhThu> objects){
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
        TextView txtThang = item.findViewById(R.id.txt_thang);
        TextView txtNam= item.findViewById(R.id.txt_nam);
        TextView txtTien= item.findViewById(R.id.txt_tien);

        final DoanhThu doanhThu = this.objects.get(position);
        //cac thuoc tính hien thi ra man hinh duoi dang
        txtThang.setText(""+ doanhThu.getThang());
        txtNam.setText(""+ doanhThu.getNam());
        txtTien.setText(""+doanhThu.getTien());

        return item;
    }
}
