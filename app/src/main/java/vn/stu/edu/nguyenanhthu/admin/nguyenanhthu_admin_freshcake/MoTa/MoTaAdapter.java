package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.MoTa;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.R;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham.SanPham;

public class MoTaAdapter extends ArrayAdapter {

    Activity context;
    int resource;
    List<MoTa> objects;

    public MoTaAdapter(@NonNull Activity context, int resource, @NonNull List objects){
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
        TextView txtIdMT = item .findViewById(R.id.txtIdmota);
        TextView txtTenSp = item.findViewById(R.id.txtTensanpham);
        TextView txtSize = item.findViewById(R.id.txtSize);
        TextView txtGiasp = item.findViewById(R.id.txtGiasp);

        final MoTa mota = this.objects.get(position);
        txtIdMT.setText("ID"+ mota.getId_mota());
        txtTenSp.setText(""+mota.getTensanpham());
        txtSize.setText("Size "+mota.getSize());
        txtGiasp.setText(""+mota.getGiasp());

        return item;
    }
}
