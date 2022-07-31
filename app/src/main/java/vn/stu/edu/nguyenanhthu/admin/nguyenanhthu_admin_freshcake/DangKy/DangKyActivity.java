package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DangKy;

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

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMucActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMucAdapter;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMucDAO;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.MainControllerView_DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.R;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityDangKyBinding;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityDanhMucBinding;

public class DangKyActivity extends AppCompatActivity {
    private ActivityDangKyBinding binding;

    private ArrayList<DangKy> dangKyArrayList;
    private DangKyAdapter dangKyAdapter;
    private DanhMuc danhMucChon = null;
    DangKyDAO dangKyDAO;
    MainControllerView_DangKy mainControllerViewDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityDangKyBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        addControls();
        dangKyDAO.onLoad();
    }

    private void addControls(){
        binding.txtSearch.getText();

        mainControllerViewDangKy = new MainControllerView_DangKy() {
            @Override
            public void showMessage(String message){
                Toast.makeText(DangKyActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showListView(ArrayList<DangKy> dangKysUpdate){
                dangKyArrayList =dangKysUpdate;
                //adapterDanhMuc
                dangKyAdapter = new DangKyAdapter(  DangKyActivity.this, R.layout.layout_item_dangky, dangKyArrayList);
                binding.lvDangKy.setAdapter(dangKyAdapter);
                dangKyAdapter.notifyDataSetChanged();
            }

            @Override
            public void showDialog(){

            }

            @Override
            public void updateListView(){
                dangKyArrayList.clear();
                dangKyDAO.onLoad();
            }
        };
        dangKyDAO = new DangKyDAO(DangKyActivity.this, mainControllerViewDangKy);
    }


    //option menu
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_dangky, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if (id == R.id.menu_about) {
            Toast.makeText(DangKyActivity.this, "Màn hình hiển thị about ", Toast.LENGTH_LONG).show();
//            startActivity(new Intent(SanPhamActivity.this, AboutActivity.class));
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
                    Toast.makeText(DangKyActivity.this, "Đã đăng xuất!", Toast.LENGTH_SHORT).show();

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