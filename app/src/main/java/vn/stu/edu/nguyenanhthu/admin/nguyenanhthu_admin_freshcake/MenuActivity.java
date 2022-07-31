package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake;

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

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.About.AboutActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.Admin.AdminActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DangKy.DangKyActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMucActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DonHang.DonHangActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.KichThuoc.KichThuocActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.MoTa.MoTaActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham.SanPhamActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThongKeDoanhThu.ThongKeDoanhThuActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThongKeMua.ThongKeBanActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThongKeMua.ThongKeMuaActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {

    private ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //Buttion cho danh muc
        binding.btnDanhmuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(MenuActivity.this, "", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MenuActivity.this, DanhMucActivity.class));
            }
        });

        //Button cho san pham
        binding.btnSanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(MenuActivity.this,"",Toast.LENGTH_LONG).show();
                startActivity(new Intent(MenuActivity.this, SanPhamActivity.class));
            }
        });

        //Button cho kich thuoc
        binding.btnKichThuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(MenuActivity.this, "",Toast.LENGTH_LONG).show();
                startActivity(new Intent(MenuActivity.this, KichThuocActivity.class));
            }
        });

        //Bution cho mo ta
        binding.btnMoTa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(MenuActivity.this, "",Toast.LENGTH_LONG).show();
                startActivity(new Intent(MenuActivity.this, MoTaActivity.class));
            }
        });

        //Bution cho gio hang
        binding.btnGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(MenuActivity.this, "",Toast.LENGTH_LONG).show();
                startActivity(new Intent(MenuActivity.this, DonHangActivity.class));
            }
        });

        binding.btnKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(MenuActivity.this, "",Toast.LENGTH_LONG).show();
                startActivity(new Intent(MenuActivity.this, DangKyActivity.class));
            }
        });

        binding.btnCTGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(MenuActivity.this, "",Toast.LENGTH_LONG).show();
                startActivity(new Intent(MenuActivity.this, ThongKeMuaActivity.class));
            }
        });

        binding.btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(MenuActivity.this, "",Toast.LENGTH_LONG).show();
                startActivity(new Intent(MenuActivity.this, ThongKeBanActivity.class));
            }
        });

        binding.btnDoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(MenuActivity.this, "",Toast.LENGTH_LONG).show();
                startActivity(new Intent(MenuActivity.this, ThongKeDoanhThuActivity.class));
            }
        });

    }

    //option menu
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if (id == R.id.menu_about){
            Toast.makeText(MenuActivity.this, "Màn hình hiển thị about ", Toast.LENGTH_LONG).show();
            startActivity(new Intent(MenuActivity.this, AboutActivity.class));
        }
        if (id == R.id.menu_exit){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Thông báo");
            builder.setMessage("Bạn có muốn đăng xuất ra màn hình chính?");
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //intentDanhMucActivity
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(intent.CATEGORY_HOME);
                    startActivity(intent);
                    finish();
                    Toast.makeText(MenuActivity.this, "Đã đăng xuất!", Toast.LENGTH_SHORT).show();

                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
        }

        if (id == R.id.mnuAdmin){
            Toast.makeText(MenuActivity.this, "Màn hình hiển thị danh sách Admin ", Toast.LENGTH_LONG).show();
            startActivity(new Intent(MenuActivity.this,AdminActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}