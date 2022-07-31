package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThemSanPham;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.MenuActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.R;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham.SanPham;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham.SanPhamActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham.SanPhamDAO;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityThemSanPhamBinding;

public class ThemSanPhamActivity extends AppCompatActivity {

    ActivityThemSanPhamBinding binding;

    //goi thu lay hinh anh tu duong link url
    Handler mainHandler = new Handler();
    ProgressDialog progressDialog;

    //
    private DanhMuc danhMucChon = null;
    private static final String[] paths = {"item 1", "item 2", "item 3"};
//    Button btnThem;

    private ArrayList<SanPham> sanPhamArrayList = new ArrayList<>();
    public ArrayList<DanhMuc> danhMucArrayList = new ArrayList<>();
    public ArrayAdapter arrayAdapterDanhMuc;
    // private DanhMucAdapter adapter;

    ThemSanPhamDAO themSanPhamDAO;
    SanPhamDAO sanPhamDAO;
    //DanhMucDAO danhMucDAO;
    MainControllerView_ThemSanPham mainControllerView_themSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityThemSanPhamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //BUTION CLEAR
        binding.clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                binding.txtUrlHinhAnh.setText("");
                binding.imageView.setImageBitmap(null);

            }
        });

        //BUTION LAY ANH
        binding.fetchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                String url = binding.txtUrlHinhAnh.getText().toString();
                new FetchImage(url).start();

            }
        });

        addControl();
        loadDanhMuc();
        addEvent();
    }

    private void loadDanhMuc(){
        themSanPhamDAO = new ThemSanPhamDAO(ThemSanPhamActivity.this, mainControllerView_themSanPham);
        themSanPhamDAO.onLoad();
    }

    private void addControl(){

        mainControllerView_themSanPham = new MainControllerView_ThemSanPham() {
            @Override
            public void showMessage(String message){
                Toast.makeText(ThemSanPhamActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateUserModel(SanPham sanpham){

            }

            @Override
            public void showSpiner(ArrayList<DanhMuc> ArayListDanhMuc){
                danhMucArrayList = ArayListDanhMuc;
                addSpiner();
                // this.danhMucArrayList =
            }


            @Override
            public void showDialog(){

            }

            @Override
            public void updateListView(){
                sanPhamArrayList.clear();
                themSanPhamDAO.onLoad();
                //sanPhamDAO.onLoad();
            }
        };

    }


    private void addSpiner(){

        //sanPhams = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (int i = 0; i < danhMucArrayList.size(); i++) {
            stringArrayList.add(danhMucArrayList.get(i).getTendanhmuc());
        }

        arrayAdapterDanhMuc = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, danhMucArrayList);
        binding.themspinner.setAdapter(arrayAdapterDanhMuc);
        arrayAdapterDanhMuc.notifyDataSetChanged();

        binding.themspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
                danhMucChon = danhMucArrayList.get(i);
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
                themSanPhamDAO.insertSanPham(
                        binding.txtTensp.getText().toString(), //dong nay
                        binding.txtMasanpham.getText().toString(),
                        Integer.parseInt(binding.txtSoluong.getText().toString()),
                        binding.txtUrlHinhAnh.getText().toString(),
                        binding.txtNoidungsanpham.getText().toString(),
                        danhMucChon.getId_danhmuc());
               // Toast.makeText(ThemSanPhamActivity.this, " ", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ThemSanPhamActivity.this, MenuActivity.class));
            }

        });

    }



    //XU LY KHI NHAN LAY ANH
    //tham khao tai link: https://stackoverflow.com/questions/13954611/android-when-should-i-use-a-handler-and-when-should-i-use-a-thread
    class FetchImage extends Thread {

        String URL;
        Bitmap bitmap;

        FetchImage(String URL){

            this.URL = URL;

        }

        @Override
        public void run(){

            mainHandler.post(new Runnable() {
                @Override
                public void run(){

                    progressDialog = new ProgressDialog(ThemSanPhamActivity.this);
                    progressDialog.setMessage("Getting your pic....");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
            });

            InputStream inputStream = null;
            try {
                inputStream = new URL(URL).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            mainHandler.post(new Runnable() {
                @Override
                public void run(){

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    binding.imageView.setImageBitmap(bitmap);

                }
            });


        }
    }
}