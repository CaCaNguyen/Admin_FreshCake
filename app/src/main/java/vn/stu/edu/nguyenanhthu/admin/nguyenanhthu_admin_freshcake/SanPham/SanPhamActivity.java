package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.About.AboutActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMucActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.MenuActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.R;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SuaSanPham.SuaSanPhamActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThemSanPham.ThemSanPhamActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivitySanPhamBinding;

public class SanPhamActivity extends AppCompatActivity {


    private ActivitySanPhamBinding binding;




    public ArrayList<SanPham> sanPhamArrayList;
    private SanPhamAdapter sanPhamAdapter;
    private SanPham sanPhamChon = null;
    //private ArrayList<SanPham> sanPhamArrayList = new ArrayList<>();
    public ArrayList<DanhMuc> danhMucArrayList = new ArrayList<>();
    public ArrayAdapter arrayAdapterDanhMuc;

    SanPhamDAO sanPhamDAO;
    MainControllerView_SanPham mainControllerViewSanpham;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivitySanPhamBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        addControls();
        sanPhamDAO.onLoad();
        addEvents();

    }
//    private void loadSanPham(){
//        sanPhamDAO = new SanPhamDAO(SanPhamActivity.this, mainControllerViewSanpham);
//        sanPhamDAO.onLoad();
//    }


    private void addControls(){
        binding.txtSearch.getText();
        mainControllerViewSanpham = new MainControllerView_SanPham() {
            @Override
            public void showMessage(String message){
                Toast.makeText(SanPhamActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateUserModel(SanPham sanpham){

            }

            @Override
            public void showListView(ArrayList<SanPham> sanPhamsUpdate){
                sanPhamArrayList = sanPhamsUpdate;
                sanPhamAdapter = new SanPhamAdapter(SanPhamActivity.this, R.layout.layout_item_sanpham, sanPhamArrayList);
                binding.lvSanPham.setAdapter(sanPhamAdapter);
                sanPhamAdapter.notifyDataSetChanged();
            }

            @Override
            public void showDialog(){

            }

            @Override
            public void updateListView(){
                sanPhamArrayList.clear();
                sanPhamDAO.onLoad();
            }
        };
        sanPhamDAO = new SanPhamDAO(SanPhamActivity.this, mainControllerViewSanpham);
    }

    private void addEvents(){
        binding.lvSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                sanPhamChon = sanPhamArrayList.get(i);
                Intent intent = new Intent(SanPhamActivity.this, SuaSanPhamActivity.class);
                intent.putExtra("DULIEU", sanPhamChon);
                startActivity(intent);
            }
        });

        //ham xoa khi nhan giu lau item
        binding.lvSanPham.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                AlertDialog.Builder builder = new AlertDialog.Builder(SanPhamActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xoá sản phẩm này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sanPhamDAO.deleteSanPham(sanPhamArrayList.get(position).getId_sanpham());
                        Intent intent = new Intent(SanPhamActivity.this, MenuActivity.class);
                        startActivity(intent);
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
        getMenuInflater().inflate(R.menu.menu_sanpham, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if (id == R.id.menu_themsp) {
            Toast.makeText(SanPhamActivity.this, "Màn hình thêm sản phẩm", Toast.LENGTH_LONG).show();
            startActivity(new Intent(SanPhamActivity.this, ThemSanPhamActivity.class));
        }
        if (id == R.id.menu_about) {
            Toast.makeText(SanPhamActivity.this, "Màn hình hiển thị about ", Toast.LENGTH_LONG).show();
            startActivity(new Intent(SanPhamActivity.this, AboutActivity.class));
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
                    Toast.makeText(SanPhamActivity.this, "Đã đăng xuất!", Toast.LENGTH_SHORT).show();

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