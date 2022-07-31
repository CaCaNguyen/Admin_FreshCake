package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThongKeDoanhThu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.R;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThongKeMua.MainControllerView_ThongKe;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThongKeMua.ThongKe;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThongKeMua.ThongKeAdapter;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThongKeMua.ThongKeDAO;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThongKeMua.ThongKeMuaActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityDoanhThuBinding;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityThongKeMuaBinding;

public class ThongKeDoanhThuActivity extends AppCompatActivity {

    private ActivityDoanhThuBinding binding;

    private ArrayList<DoanhThu> doanhThuArrayList;
    private DoanhThuAdapter doanhThuAdapter;
    DoanhThuDAO doanhThuDAO;
    MainControllerView_DoanhThu mainControllerViewDoanhThu;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityDoanhThuBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        addControls();
        doanhThuDAO.onLoad();
    }

    private void addControls(){
        mainControllerViewDoanhThu = new MainControllerView_DoanhThu() {
            @Override
            public void showMessage(String message){
                Toast.makeText(ThongKeDoanhThuActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showListView(ArrayList<DoanhThu> doanhThuUpdate){
                doanhThuArrayList = doanhThuUpdate;
                //adapterDanhMuc
                doanhThuAdapter = new DoanhThuAdapter(ThongKeDoanhThuActivity.this, R.layout.layout_item_thongkedoanhthu, doanhThuArrayList);
                binding.lvThongKeDoanhThu.setAdapter(doanhThuAdapter);
                doanhThuAdapter.notifyDataSetChanged();
            }

            @Override
            public void showDialog(){

            }

            @Override
            public void updateListView(){
                doanhThuArrayList.clear();
                doanhThuDAO.onLoad();
            }
        };
        doanhThuDAO = new DoanhThuDAO(ThongKeDoanhThuActivity.this, mainControllerViewDoanhThu);
    }
}