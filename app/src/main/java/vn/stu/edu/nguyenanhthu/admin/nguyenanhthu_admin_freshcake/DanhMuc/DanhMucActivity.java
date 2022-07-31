package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc;

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

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.About.AboutActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DonHang.DonHangActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.R;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityDanhMucBinding;

public class DanhMucActivity extends AppCompatActivity {
    private ActivityDanhMucBinding binding;

    private ArrayList<DanhMuc> danhMucArrayList;
    private DanhMucAdapter danhMucAdapter;
    private DanhMuc danhMucChon = null;
    DanhMucDAO danhMucDAO;
    MainControllerView_DanhMuc mainControllerViewDanhMuc;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityDanhMucBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        addControls();
        danhMucDAO.onLoad();
        addEvents();
    }

    private void addControls(){
        binding.txtNamedanhmuc.getText();

        mainControllerViewDanhMuc = new MainControllerView_DanhMuc() {
            @Override
            public void showMessage(String message){
                Toast.makeText(DanhMucActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showListView(ArrayList<DanhMuc> danhMucsUpdate){
                danhMucArrayList = danhMucsUpdate;
                //adapterDanhMuc
                danhMucAdapter = new DanhMucAdapter(DanhMucActivity.this, R.layout.layout_item_danhmuc, danhMucArrayList);
                binding.lvDanhmuc.setAdapter(danhMucAdapter);
                danhMucAdapter.notifyDataSetChanged();
            }

            @Override
            public void showDialog(){

            }

            @Override
            public void updateListView(){
                danhMucArrayList.clear();
                danhMucDAO.onLoad();
            }
        };
        danhMucDAO = new DanhMucDAO(DanhMucActivity.this, mainControllerViewDanhMuc);
    }

    private void addEvents(){

        //nut them danh muc
        binding.btnAdddanhmuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                danhMucDAO.insertDanhMuc(binding.txtNamedanhmuc.getText().toString());
                binding.txtNamedanhmuc.setText("");
            }
        });


        //ham sua khi nhap vao mot cai cap nhat
        binding.lvDanhmuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                binding.txtNamedanhmuc.setText(danhMucArrayList.get(i).getTendanhmuc());
                binding.btnUpdatedanhmuc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view){
                        danhMucDAO.updateDanhMuc(danhMucArrayList.get(i).getId_danhmuc(), binding.txtNamedanhmuc.getText().toString());
                        binding.txtNamedanhmuc.setText("");
                    }
                });
            }
        });

        //ham xoa khi nhan giu lau item
        binding.lvDanhmuc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                AlertDialog.Builder builder = new AlertDialog.Builder(DanhMucActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xoá danh mục này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        danhMucDAO.deleteDanhMuc(danhMucArrayList.get(position).getId_danhmuc());
                        danhMucDAO.onLoad();
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
        getMenuInflater().inflate(R.menu.menu_danhmuc, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if (id == R.id.menu_about) {
            Toast.makeText(DanhMucActivity.this, "Màn hình hiển thị about ", Toast.LENGTH_LONG).show();
            startActivity(new Intent(DanhMucActivity.this, AboutActivity.class));
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
                    Toast.makeText(DanhMucActivity.this, "Đã đăng xuất!", Toast.LENGTH_SHORT).show();

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