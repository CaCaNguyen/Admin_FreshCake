package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.MoTa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.About.AboutActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.MenuActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.R;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham.SanPhamActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SuaSanPham.SuaSanPhamActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThemMoTa.ThemMoTaActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThemSanPham.ThemSanPhamActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityMoTaBinding;

public class MoTaActivity extends AppCompatActivity {

    private ActivityMoTaBinding binding;
    public ArrayList<MoTa> moTaArrayList;
    private MoTaAdapter moTaAdapter;

    private MoTa moTaChon = null;

    MoTaDAO moTaDAO;
    MainControllerView_MoTa mainControllerViewMoTa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityMoTaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        addControls();
        moTaDAO.onLoad();
        addEvents();
    }

    private void addControls(){
        binding.txtSearch.getText();
        mainControllerViewMoTa = new MainControllerView_MoTa() {
            @Override
            public void showMessage(String message){
                Toast.makeText(MoTaActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateUserModel(MoTa mota){

            }

            @Override
            public void showListView(ArrayList<MoTa> moTasUpdate){

                moTaArrayList = moTasUpdate;
                moTaAdapter = new MoTaAdapter(MoTaActivity.this, R.layout.layout_item_mota, moTaArrayList);
                binding.lvMoTa.setAdapter(moTaAdapter);
                moTaAdapter.notifyDataSetChanged();
            }

            @Override
            public void showDialog(){

            }

            @Override
            public void updateListView(){
                moTaArrayList.clear();
                moTaDAO.onLoad();
            }
        };
        moTaDAO = new MoTaDAO(MoTaActivity.this, mainControllerViewMoTa);
    }

    private void addEvents(){

        //ham xoa khi nhan giu lau item
        binding.lvMoTa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                AlertDialog.Builder builder = new AlertDialog.Builder(MoTaActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xoá mô tả này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moTaDAO.deleteMoTa(moTaArrayList.get(position).getId_mota());
                        Intent intent = new Intent(MoTaActivity.this, MenuActivity.class);
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
        getMenuInflater().inflate(R.menu.menu_mota, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if (id == R.id.menu_themmt) {
            Toast.makeText(MoTaActivity.this, "Màn hình thêm mô tả", Toast.LENGTH_LONG).show();
            startActivity(new Intent(MoTaActivity.this, ThemMoTaActivity.class));
        }
        if (id == R.id.menu_about) {
            Toast.makeText(MoTaActivity.this, "Màn hình hiển thị about ", Toast.LENGTH_LONG).show();
            startActivity(new Intent(MoTaActivity.this, AboutActivity.class));
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
                    Toast.makeText(MoTaActivity.this, "Đã đăng xuất!", Toast.LENGTH_SHORT).show();

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