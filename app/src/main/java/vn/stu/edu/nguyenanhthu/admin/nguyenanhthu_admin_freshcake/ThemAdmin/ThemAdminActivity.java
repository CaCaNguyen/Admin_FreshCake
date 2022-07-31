package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThemAdmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.Admin.AdminActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.Admin.MainControllerView_Admin;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DonHang.DonHangActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.LoginActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.MenuActivity;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.R;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityThemAdminBinding;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.utils.utils;

public class ThemAdminActivity extends AppCompatActivity {

    private ActivityThemAdminBinding binding;
    MainControllerView_Admin mainControllerViewAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityThemAdminBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.txtuser.getText().toString();
        binding.txtpassword.getText().toString();
        //addControls();

        binding.btnSignupNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                registerAdmin(
                        binding.txtuser.getText().toString(),
                        binding.txtpassword.getText().toString()
                );

            }

        });
    }

    //Ham them mot tai khoan moi
    public void registerAdmin(String user, String pass){
        // khai báo lớp dữ liệu nhận response
        RequestQueue requestQueue = Volley.newRequestQueue(ThemAdminActivity.this);
        Response.Listener<String> stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){
                // khi dữ liệu bên api trả về thì nó vào đây
                //response = [{"email":"","matkhau":""}]
                // lấy dữ liệu trả về và đưa vào list
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean result = Boolean.parseBoolean(jsonObject.getString("result"));
                    if (result == true) {
//                        mainControllerViewDangKy.showMessage("Tạo tài khoản thành công");
                        Toast.makeText(ThemAdminActivity.this, "Tạo admin thành công!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(ThemAdminActivity.this, AdminActivity.class));
                    } else {
//                        mainControllerViewDangKy.showMessage("Tạo tài khoản thất bại");
                        Toast.makeText(ThemAdminActivity.this, "Tạo admin thất bại", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                // nếu api bị lỗi thì nó vào đây
                // lỗi mạng
                // dịa chỉ ip
                //
                mainControllerViewAdmin.showMessage(error.getMessage());
            }
        };

        // lấy url buid từ constant
        Uri.Builder builder = Uri.parse(utils.URL_CreateAdmin).buildUpon();
        // bui parameter action = insert
        //builder.appendQueryParameter("action", "insert");
        builder.appendQueryParameter("username", user);
        builder.appendQueryParameter("password", pass);
        // chuyển giá trị builder thành string
        String url = builder.build().toString();

        StringRequest request = new StringRequest(
                Request.Method.POST, // phương thức get
                url,// đường dẫn Get
                stringListener,
                errorListener
        );

        requestQueue.add(request);
    }

    //Goi ve trang welcome khi nhan nut mui ten tro ve tren activity dang ky
    public void backFromRegister(View view){

        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        //tạo animation cho thành phần
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.activityThemAdmin), "transition_register");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ThemAdminActivity.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }
    //option menu
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if (id == R.id.menu_about) {
            Toast.makeText(ThemAdminActivity.this, "Màn hình hiển thị about ", Toast.LENGTH_LONG).show();
//            startActivity(new Intent(SanPhamActivity.this, AboutActivity.class));
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
                    Toast.makeText(ThemAdminActivity.this, "Đã đăng xuất!", Toast.LENGTH_SHORT).show();

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