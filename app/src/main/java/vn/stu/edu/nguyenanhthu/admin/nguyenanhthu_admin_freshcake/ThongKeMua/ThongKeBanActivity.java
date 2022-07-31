package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThongKeMua;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.About.AboutActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.R;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityThongKeBanBinding;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityThongKeMuaBinding;

public class ThongKeBanActivity extends AppCompatActivity {

    private ActivityThongKeBanBinding binding;

    private ArrayList<ThongKe> thongKeArrayList;
    private ThongKeAdapter thongKeAdapter;
    ThongKeDAO thongKeDAO;
    MainControllerView_ThongKe mainControllerViewThongKeBan;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityThongKeBanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        addControls();
        thongKeDAO.onLoadThongKe();
    }

    private void addControls(){
        mainControllerViewThongKeBan = new MainControllerView_ThongKe() {
            @Override
            public void showMessage(String message){
                Toast.makeText(ThongKeBanActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showListView(ArrayList<ThongKe> thongKesUpdate){
                thongKeArrayList = thongKesUpdate;
                //adapterDanhMuc
                thongKeAdapter = new ThongKeAdapter(ThongKeBanActivity.this, R.layout.layout_item_thongkeban, thongKeArrayList);
                binding.lvThongKeBan.setAdapter(thongKeAdapter);
                thongKeAdapter.notifyDataSetChanged();
            }

            @Override
            public void showDialog(){

            }

            @Override
            public void updateListView(){
                thongKeArrayList.clear();
                thongKeDAO.onLoadThongKe();

            }
        };
        thongKeDAO = new ThongKeDAO(ThongKeBanActivity.this, mainControllerViewThongKeBan);
    }

    //option menu
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_thongkeban, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if (id == R.id.menu_about) {
            Toast.makeText(ThongKeBanActivity.this, "Màn hình hiển thị about ", Toast.LENGTH_LONG).show();
            startActivity(new Intent(ThongKeBanActivity.this, AboutActivity.class));
        }
        if (id == R.id.menu_exit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Thông báo");
            builder.setMessage("Bạn có muốn đăng xuất ra màn hình chính?");
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i){
                    Intent intent1 = new Intent(Intent.ACTION_MAIN);
                    intent1.addCategory(intent1.CATEGORY_HOME);
                    startActivity(intent1);
                    finish();
                    Toast.makeText(ThongKeBanActivity.this, "Đã đăng xuất!", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i){

                }
            });
            builder.show();
        }
        return super.onOptionsItemSelected(item);
    }
}