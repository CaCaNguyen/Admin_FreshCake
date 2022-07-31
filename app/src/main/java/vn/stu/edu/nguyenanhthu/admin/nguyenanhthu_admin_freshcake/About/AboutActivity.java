package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.About;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMucActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMucAdapter;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMucDAO;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.MainControllerView_DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.R;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityAboutBinding;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityDanhMucBinding;

public class AboutActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ActivityAboutBinding binding;
    private ArrayList<About> aboutArrayList;
    private AboutAdapter aboutAdapter;
    private About aboutChon = null;
    AboutDAO aboutDAO;
    MainControllerView_About mainControllerViewAbout;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        addControls();
        aboutDAO.onLoad();
        addEvents();

    }


    private void addControls(){
        mainControllerViewAbout = new MainControllerView_About() {
            @Override
            public void showMessage(String message){
                Toast.makeText(AboutActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showListView(ArrayList<About> aboutsUpdate){
                aboutArrayList = aboutsUpdate;
                aboutAdapter = new AboutAdapter(AboutActivity.this, R.layout.layout_item_about, aboutArrayList);
                binding.lvAbout.setAdapter(aboutAdapter);
                aboutAdapter.notifyDataSetChanged();
            }

            @Override
            public void showDialog(){

            }

            @Override
            public void updateListView(){
                aboutArrayList.clear();
                aboutDAO.onLoad();
            }

        };
        aboutDAO = new AboutDAO(AboutActivity.this, mainControllerViewAbout);
    }

    private void addEvents(){
        binding.btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse("tel:" + "0377226657"));
                startActivity(intent);
            }
        });

        binding.btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(
                        Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/thuhuynh2020"));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng stu = new LatLng(10.794181145489253, 106.67241103914846);
        mMap.addMarker(new MarkerOptions().position(stu).title("T&T Fresh Cake"));

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(stu);
        LatLngBounds bounds = builder.build();
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(
                bounds,
                width,
                height,
                150
        );
        mMap.moveCamera(cameraUpdate);
    }
}