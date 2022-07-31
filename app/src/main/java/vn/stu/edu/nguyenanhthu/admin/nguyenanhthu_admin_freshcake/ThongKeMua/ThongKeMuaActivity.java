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
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityThongKeMuaBinding;

public class ThongKeMuaActivity extends AppCompatActivity {

    private ActivityThongKeMuaBinding binding;

    private ArrayList<ThongKe> thongKeArrayList;
    private ThongKeAdapter thongKeAdapter;
    ThongKeDAO thongKeDAO;
    MainControllerView_ThongKe mainControllerViewThongKeMua;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityThongKeMuaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        addControls();
        thongKeDAO.onLoad();
    }

    private void addControls(){
        mainControllerViewThongKeMua = new MainControllerView_ThongKe() {
            @Override
            public void showMessage(String message){
                Toast.makeText(ThongKeMuaActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showListView(ArrayList<ThongKe> thongKeMuasUpdate){
                thongKeArrayList = thongKeMuasUpdate;
                //adapterDanhMuc
                thongKeAdapter = new ThongKeAdapter(ThongKeMuaActivity.this, R.layout.layout_item_thongkemua, thongKeArrayList);
                binding.lvThongKeMua.setAdapter(thongKeAdapter);
                thongKeAdapter.notifyDataSetChanged();
            }

            @Override
            public void showDialog(){

            }

            @Override
            public void updateListView(){
                thongKeArrayList.clear();
                thongKeDAO.onLoad();

            }
        };
        thongKeDAO = new ThongKeDAO(ThongKeMuaActivity.this, mainControllerViewThongKeMua);
    }

    //option menu
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_thongkemua, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if (id == R.id.menu_about) {
            Toast.makeText(ThongKeMuaActivity.this, "Màn hình hiển thị about ", Toast.LENGTH_LONG).show();
            startActivity(new Intent(ThongKeMuaActivity.this, AboutActivity.class));
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
                    Toast.makeText(ThongKeMuaActivity.this, "Đã đăng xuất!", Toast.LENGTH_SHORT).show();
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