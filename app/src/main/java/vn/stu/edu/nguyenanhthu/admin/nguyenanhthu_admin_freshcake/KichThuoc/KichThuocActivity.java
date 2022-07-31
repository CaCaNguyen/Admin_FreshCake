package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.KichThuoc;

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
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMucActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMucAdapter;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMucDAO;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.MainControllerView_DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DonHang.DonHangActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.R;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityDanhMucBinding;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityKichThuocBinding;

public class KichThuocActivity extends AppCompatActivity {
    private ActivityKichThuocBinding binding;

    private ArrayList<KichThuoc> kichThuocArrayList;
    private KichThuocAdapter kichThuocAdapter;
    private KichThuoc kichThuocChon = null;
    KichThuocDAO kichThuocDAO;
    MainControllerView_KichThuoc mainControllerViewKichThuoc;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityKichThuocBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        addControls();
        kichThuocDAO.onLoad();
        addEvents();
    }


    private void addControls(){
        binding.txtSize.getText();
        mainControllerViewKichThuoc = new MainControllerView_KichThuoc() {
            @Override
            public void showMessage(String message){
                Toast.makeText(KichThuocActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showListView(ArrayList<KichThuoc> kichThuocsUpdate){

                kichThuocArrayList = kichThuocsUpdate;
                kichThuocAdapter = new KichThuocAdapter(KichThuocActivity.this, R.layout.layout_item_kichthuoc, kichThuocArrayList);
                binding.lvSize.setAdapter(kichThuocAdapter);
                kichThuocAdapter.notifyDataSetChanged();
            }

            @Override
            public void showDialog(){

            }

            @Override
            public void updateListView(){
                kichThuocArrayList.clear();
                kichThuocDAO.onLoad();
            }
        };
        kichThuocDAO = new KichThuocDAO(KichThuocActivity.this, mainControllerViewKichThuoc);
    }

    public void addEvents(){

        binding.btnAddkichthuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                kichThuocDAO.insertKichThuoc(binding.txtSize.getText().toString());
                binding.txtSize.setText("");
            }
        });

        //ham sua khi nhap vao mot cai cap nhat
        binding.lvSize.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                binding.txtSize.setText(kichThuocArrayList.get(i).getSize());
                binding.btnUpdatekichthuoc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view){
                        kichThuocDAO.updateKichThuoc(kichThuocArrayList.get(i).getId_size(), binding.txtSize.getText().toString());
                        binding.txtSize.setText("");
                    }
                });
            }
        });


        //ham xoa khi nhan giu lau item
        binding.lvSize.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                AlertDialog.Builder builder = new AlertDialog.Builder(KichThuocActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xoá Size này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        kichThuocDAO.deleteKichThuoc(kichThuocArrayList.get(position).getId_size());
                        kichThuocDAO.onLoad();
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
        getMenuInflater().inflate(R.menu.menu_kichthuoc, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if (id == R.id.menu_about) {
            Toast.makeText(KichThuocActivity.this, "Màn hình hiển thị about ", Toast.LENGTH_LONG).show();
            startActivity(new Intent(KichThuocActivity.this, AboutActivity.class));
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
                    Toast.makeText(KichThuocActivity.this, "Đã đăng xuất!", Toast.LENGTH_SHORT).show();

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