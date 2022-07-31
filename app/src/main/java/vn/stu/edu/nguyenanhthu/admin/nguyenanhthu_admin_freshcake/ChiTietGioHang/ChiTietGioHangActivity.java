package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ChiTietGioHang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DonHang.DonHang;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.KichThuoc.KichThuocActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.R;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityChiTietGioHangBinding;

public class ChiTietGioHangActivity extends AppCompatActivity {

    private ActivityChiTietGioHangBinding binding;
    public ArrayList<ChiTietGioHang> chiTietGioHangArrayList;
    private ChiTietGioHangAdapter chiTietGioHangAdapter;
    private DonHang donHangChon = null;
//    private SanPhamAdapter sanPhamAdapter;
//    private DanhMuc danhMucChon = null;
//    private SanPham sanPhamChon = null;
    //private ArrayList<SanPham> sanPhamArrayList = new ArrayList<>();
    public ArrayList<DonHang> donHangArrayList = new ArrayList<>();
    public ArrayAdapter arrayAdapterDonHang;

    ChiTietGioHangDAO chiTietGioHangDAO;
    MainControllerView_ChiTietGioHang mainControllerViewChiTietGioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityChiTietGioHangBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //goi danh muc load de co the lay ten danh muc trong tung id san pham
        donHangChon =(DonHang) getIntent().getSerializableExtra("DONHANG");

        addControls();
        loadSanPham();
    }
    private void loadSanPham(){
        chiTietGioHangDAO.getsChiTietDonHangByIdGioHang(donHangChon.getId_giohang());
    }


    private void addControls(){
        mainControllerViewChiTietGioHang = new MainControllerView_ChiTietGioHang() {
            @Override
            public void showMessage(String message){
                Toast.makeText(ChiTietGioHangActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showListView(ArrayList<ChiTietGioHang> chiTietGioHangsUpdate){
                chiTietGioHangArrayList = chiTietGioHangsUpdate;
                chiTietGioHangAdapter = new ChiTietGioHangAdapter(ChiTietGioHangActivity.this, R.layout.layout_item_chitietgiohang, chiTietGioHangArrayList);
                binding.lvChiTietDonHang.setAdapter(chiTietGioHangAdapter);
                chiTietGioHangAdapter.notifyDataSetChanged();
            }

            @Override
            public void showDialog(){

            }

            @Override
            public void updateListView(){
                chiTietGioHangArrayList.clear();
            }
        };
        chiTietGioHangDAO = new ChiTietGioHangDAO(ChiTietGioHangActivity.this, mainControllerViewChiTietGioHang);
    }

    //option menu
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_chitietgiohang, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if (id == R.id.menu_about) {
            Toast.makeText(ChiTietGioHangActivity.this, "Màn hình hiển thị about ", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(ChiTietGioHangActivity.this, "Đã đăng xuất!", Toast.LENGTH_SHORT).show();

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