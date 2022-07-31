package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThemMoTa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.KichThuoc.KichThuoc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.MenuActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.MoTa.MainControllerView_MoTa;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.MoTa.MoTa;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.MoTa.MoTaActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.MoTa.MoTaDAO;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.R;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham.SanPham;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham.SanPhamActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham.SanPhamDAO;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThemSanPham.MainControllerView_ThemSanPham;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThemSanPham.ThemSanPhamActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThemSanPham.ThemSanPhamDAO;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityMoTaBinding;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityThemMoTaBinding;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityThemSanPhamBinding;

public class ThemMoTaActivity extends AppCompatActivity {

    ActivityThemMoTaBinding binding;

    //
    private KichThuoc kichThuocChon = null;
    private TenSanPham sanPhamChon = null;

    private static final String[] paths = {"item 1", "item 2", "item 3"};
//    Button btnThem;

    private ArrayList<MoTa> moTaArrayList = new ArrayList<>();
    private ArrayList<TenSanPham> sanPhamArrayList = new ArrayList<>();
    public ArrayList<KichThuoc> kichThuocArrayList = new ArrayList<>();
    public ArrayAdapter arrayAdapterKichThuoc;
    public ArrayAdapter arrayAdapterSanPham;
    // private DanhMucAdapter adapter;

    ThemMoTaDAO themMoTaDAO;
    MoTaDAO moTaDAO;
    //DanhMucDAO danhMucDAO;
    MainControllerView_ThemMoTa mainControllerView_themMoTa;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityThemMoTaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        addControl();
        loadKichThuoc();
        loadSanPham();
        addEvent();
    }

    private void loadKichThuoc(){
        themMoTaDAO = new ThemMoTaDAO(ThemMoTaActivity.this, mainControllerView_themMoTa);
        themMoTaDAO.onLoadSize();
    }

    private void loadSanPham(){
        themMoTaDAO = new ThemMoTaDAO(ThemMoTaActivity.this, mainControllerView_themMoTa);
        themMoTaDAO.onLoadSanPham();
    }

    private void addControl(){

        mainControllerView_themMoTa = new MainControllerView_ThemMoTa() {
            @Override
            public void showMessage(String message){
                Toast.makeText(ThemMoTaActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateUserModel(SanPham sanpham){

            }

            @Override
            public void showSpinerKichThuoc(ArrayList<KichThuoc> ArrayListKichThuoc){
                kichThuocArrayList = ArrayListKichThuoc;
                addSpinerKichThuoc();
            }

            @Override
            public void showSpinerSanPham(ArrayList<TenSanPham> ArrayListSanPham){
                sanPhamArrayList = ArrayListSanPham;
                addSpinerSanPham();
            }

            @Override
            public void showDialog(){

            }

            @Override
            public void updateListView(){
                moTaArrayList.clear();
                themMoTaDAO.onLoadSize();
                themMoTaDAO.onLoadSanPham();
            }
        };

    }


    private void addSpinerKichThuoc(){

        //sanPhams = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (int i = 0; i < kichThuocArrayList.size(); i++) {
            stringArrayList.add(kichThuocArrayList.get(i).getSize());
        }

        arrayAdapterKichThuoc = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, kichThuocArrayList);
        binding.sizeSpinner.setAdapter(arrayAdapterKichThuoc);
        arrayAdapterKichThuoc.notifyDataSetChanged();

        binding.sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
                kichThuocChon = kichThuocArrayList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){

            }
        });

    }

    private void addSpinerSanPham(){

        //sanPhams = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (int i = 0; i < sanPhamArrayList.size(); i++) {
            stringArrayList.add(sanPhamArrayList.get(i).getTensanpham());
        }

        arrayAdapterSanPham = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sanPhamArrayList);
        binding.spSpinner.setAdapter(arrayAdapterSanPham);
        arrayAdapterSanPham.notifyDataSetChanged();

        binding.spSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
                sanPhamChon = sanPhamArrayList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){

            }
        });

    }


    private void addEvent(){

        binding.btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                themMoTaDAO.insertMoTa(
                        sanPhamChon.getId_sanpham(),
                        kichThuocChon.getId_size(),
                        Double.parseDouble(binding.txtGia.getText().toString())
                );
                Toast.makeText(ThemMoTaActivity.this, " ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ThemMoTaActivity.this, MenuActivity.class));

            }

        });

    }
}