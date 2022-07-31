package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.Admin;

import android.content.Context;
import android.net.Uri;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.MainControllerView_DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.utils.utils;

public class AdminDAO {
    private ArrayList<Admin> adminArrayList = new ArrayList<>();
    private Context context;
    private RequestQueue requestQueue;
    MainControllerView_Admin mainControllerViewAdmin;

    //private  DanhMucRepo danhMucRepo = new DanhMucRepo();

    public AdminDAO(Context context, MainControllerView_Admin mainControllerViewAdmin){
        this.context = context;
        this.mainControllerViewAdmin = mainControllerViewAdmin;
        requestQueue = Volley.newRequestQueue(this.context);
    }

    public void onLoad(){
        getAllAdmin();
    }

    //Hàm lấy danh sách của tất cả danh mục
    private void getAllAdmin(){
        Response.Listener<String> stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){

                try {
                    JSONObject jsonObj = new JSONObject(response);
                    String message = jsonObj.getString("results");
                    JSONArray jsonArray = new JSONArray(message);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id_admin");
                        String ten = jsonObject.getString("username");
                        String pass = jsonObject.getString("password");

                        Admin admin = new Admin();
                        admin.setId_admin(id);
                        admin.setUsername(ten);
                        admin.setPassword(pass);
                        adminArrayList.add(admin);

                    }
                    mainControllerViewAdmin.showListView(adminArrayList);
                } catch (JSONException e) {
                    mainControllerViewAdmin.showMessage(e.getMessage());
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error){

                mainControllerViewAdmin.showMessage(error.getMessage());
            }
        };

        Uri.Builder builder = Uri.parse(utils.URL_GetAllAdmin).buildUpon();

        String url = builder.build().toString();

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                stringListener,
                errorListener
        );
        requestQueue.add(request);

    }

    //Hàm xoá một danh mục có trong list
    public void deleteAdmin(int id_admin){
        // khai báo lớp dữ liệu nhận response
        Response.Listener<String> stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String result = jsonObject.getString("result");
                    if (result != null) {
                        mainControllerViewAdmin.showMessage("Xoa thanh cong");
                    } else {
                        mainControllerViewAdmin.showMessage("Xoa khong thanh cong!");
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
        Uri.Builder builder = Uri.parse(utils.URL_DeleteAdmin(id_admin)).buildUpon();
        // bui parameter action = insert
        //builder.appendQueryParameter("",id_danhmuc + "");
//        builder.appendQueryParameter("ma",ma);
        // chuyển giá trị builder thành string
        String url = builder.build().toString();

        StringRequest request = new StringRequest(
                Request.Method.DELETE, // phương thức get
                url,// đường dẫn Get
                stringListener,
                errorListener
        );

        requestQueue.add(request);
    }
}
