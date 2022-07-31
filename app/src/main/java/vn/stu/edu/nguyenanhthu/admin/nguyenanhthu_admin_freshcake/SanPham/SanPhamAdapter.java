package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.R;

public class SanPhamAdapter extends ArrayAdapter {
    Activity context;
    int resource;
    List<SanPham> objects;

    public SanPhamAdapter(@NonNull Activity context, int resource, @NonNull List objects){
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
        TextView txtIdSp = item.findViewById(R.id.txtIdsanpham);
        TextView txtTenSp = item.findViewById(R.id.txtTensanpham);
        TextView txtMasp = item.findViewById(R.id.txtMasp);
        TextView txtSoluong = item.findViewById(R.id.txtSoluong);
        TextView txtNoidung=item.findViewById(R.id.txtNoidung);
        TextView txtIdDm= item.findViewById(R.id.txtDanhMuc);
        ImageView imgAnhSP = item.findViewById(R.id.imgAnhSP);

        //hinhanh
        final SanPham sanpham = this.objects.get(position);
        txtIdSp.setText("ID"+sanpham.getId_sanpham());
        txtMasp.setText("MASP"+sanpham.getMasp());
        txtTenSp.setText(""+sanpham.getTensanpham());
        txtSoluong.setText("Soluong:"+sanpham.getSoluong());
        txtIdDm.setText(""+sanpham.getTendanhmuc());
        //txtMasp.setText("Masp:"+sanpham.getMasp());
        txtNoidung.setText(""+sanpham.getNoidung());

        //hinhanh
        Picasso.with(context).load(sanpham.getHinhanh()).into(imgAnhSP);
        return item;
    }

    public File saveFile(String data) throws RuntimeException {
        File dir = new File(context.getFilesDir(), "mydir");
        if(!dir.exists()){
            dir.mkdir();
        }
        File gpxfile = null;
        try {
            gpxfile = new File(dir, "temp.jpg");
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(data);
            writer.flush();
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return gpxfile;
    }
}
