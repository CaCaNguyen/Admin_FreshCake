package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMucActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMucAdapter;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMucDAO;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.MainControllerView_DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.R;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham.SanPhamActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThemAdmin.ThemAdminActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThemSanPham.ThemSanPhamActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityAdminBinding;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityDanhMucBinding;

public class AdminActivity extends AppCompatActivity {

    private ActivityAdminBinding binding;

    private ArrayList<Admin> adminArrayList;
    private AdminAdapter adminAdapter;
    private Admin adminChon = null;
   AdminDAO adminDAO;
   MainControllerView_Admin mainControllerViewAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        addControls();
        adminDAO.onLoad();
        addEvents();
    }

    private void addControls(){
        mainControllerViewAdmin = new MainControllerView_Admin() {
            @Override
            public void showMessage(String message){
                Toast.makeText(AdminActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showListView(ArrayList<Admin> adminsUpdate){
                adminArrayList = adminsUpdate;
                adminAdapter = new AdminAdapter(AdminActivity.this, R.layout.layout_item_admin, adminArrayList);
                binding.lvAdmin.setAdapter(adminAdapter);
                adminAdapter.notifyDataSetChanged();
            }

            @Override
            public void showDialog(){

            }

            @Override
            public void updateListView(){
               adminArrayList.clear();
                adminDAO.onLoad();
            }
        };
        adminDAO = new AdminDAO(AdminActivity.this, mainControllerViewAdmin);
    }

    public void addEvents(){
        //ham xoa khi nhan giu lau item
        binding.lvAdmin.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xoá danh mục này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adminDAO.deleteAdmin(adminArrayList.get(position).getId_admin());
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
                return true;
            }
        });
    }

    //option menu
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if (id == R.id.menu_them_admin) {
            Toast.makeText(AdminActivity.this, "Màn hình thêm Admin", Toast.LENGTH_LONG).show();
            startActivity(new Intent(AdminActivity.this, ThemAdminActivity.class));
        }
        if (id == R.id.menu_about) {
//            Toast.makeText(SanPhamActivity.this, "Màn hình hiển thị about ", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(AdminActivity.this, "Đã đăng xuất!", Toast.LENGTH_SHORT).show();

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