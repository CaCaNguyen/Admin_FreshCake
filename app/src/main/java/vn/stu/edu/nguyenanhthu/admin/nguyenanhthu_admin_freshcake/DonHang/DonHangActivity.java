package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DonHang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.About.AboutActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ChiTietGioHang.ChiTietGioHang;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ChiTietGioHang.ChiTietGioHangActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMucActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMucAdapter;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMucDAO;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.MainControllerView_DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.R;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham.SanPhamActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThemSanPham.ThemSanPhamActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityDanhMucBinding;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityDonHangBinding;

public class DonHangActivity extends AppCompatActivity {

    private ActivityDonHangBinding binding;

    private ArrayList<DonHang> donHangArrayList;
    private DonHangAdapter donHangAdapter;
    private DonHang donHangChon = null;
    DonHangDAO donHangDAO;
    MainControllerView_DonHang mainControllerViewDonHang;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityDonHangBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        addControls();
        donHangDAO.onLoad();
        addEvents();
    }

    private void addControls(){
        binding.txtSearch.getText();

        mainControllerViewDonHang = new MainControllerView_DonHang(){
            @Override
            public void showMessage(String message){
                Toast.makeText(DonHangActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showListView(ArrayList<DonHang> donHangsUpdate){
                donHangArrayList = donHangsUpdate;
                //adapterDanhMuc
                donHangAdapter = new DonHangAdapter(DonHangActivity.this, R.layout.layout_item_donhang, donHangArrayList);
                binding.lvDonHang.setAdapter(donHangAdapter);
                donHangAdapter.notifyDataSetChanged();
            }

            @Override
            public void showDialog(){

            }

            @Override
            public void updateListView(){
                donHangArrayList.clear();
                donHangDAO.onLoad();
            }
        };
        donHangDAO = new DonHangDAO(DonHangActivity.this, mainControllerViewDonHang);
    }
    public void addEvents(){
        binding.lvDonHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                donHangChon = donHangArrayList.get(i);

                Intent intent = new Intent(DonHangActivity.this, ChiTietGioHangActivity.class);
                intent.putExtra("DONHANG", donHangChon);
                startActivity(intent);
            }
        });

        //ham xoa khi nhan giu lau item
        binding.lvDonHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                AlertDialog.Builder builder = new AlertDialog.Builder(DonHangActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xác nhận đơn hàng này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("SELECTED INDEX", String.valueOf(position));
                        try {
                            donHangDAO.updateDonHang(donHangArrayList.get(position).getId_giohang());
                            donHangArrayList.clear();
                            donHangDAO.onLoad();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        donHangDAO.onLoad();
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
        getMenuInflater().inflate(R.menu.menu_giohang, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if (id == R.id.menu_about) {
            Toast.makeText(DonHangActivity.this, "Màn hình hiển thị about ", Toast.LENGTH_LONG).show();
            startActivity(new Intent(DonHangActivity.this, AboutActivity.class));
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
                    Toast.makeText(DonHangActivity.this, "Đã đăng xuất!", Toast.LENGTH_SHORT).show();

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