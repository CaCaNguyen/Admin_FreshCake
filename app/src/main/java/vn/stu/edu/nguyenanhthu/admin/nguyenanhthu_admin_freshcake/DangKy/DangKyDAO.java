package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DangKy;

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

public class DangKyDAO {

    private ArrayList<DangKy> dangKyArrayList = new ArrayList<>();
    private Context context;
    private RequestQueue requestQueue;
    MainControllerView_DangKy mainControllerViewDangKy;
    //private  DanhMucRepo danhMucRepo = new DanhMucRepo();

    public DangKyDAO(Context context, MainControllerView_DangKy mainControllerViewDangKy){
        this.context = context;
        this.mainControllerViewDangKy = mainControllerViewDangKy;
        requestQueue = Volley.newRequestQueue(this.context);
    }

    public void onLoad(){
        getAllDangKy();
    }

    //Hàm lấy danh sách của tất cả danh mục
    private void getAllDangKy(){
        Response.Listener<String> stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){

                try {
                    JSONObject jsonObj = new JSONObject(response);
                    String message = jsonObj.getString("results");
                    JSONArray jsonArray = new JSONArray(message);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        int id = jsonObject.getInt("id_dangky");
                        String tenkhachhang = jsonObject.getString("tenkhachhang");
                        String email = jsonObject.getString("email");
                        String sdt = jsonObject.getString("sdt");
                        DangKy dk = new DangKy();
                        dk.setId_dangky(id);
                        dk.setTenkhachhang(tenkhachhang);
                        dk.setEmail(email);
                        dk.setSdt(sdt);
                        dangKyArrayList.add(dk);
                    }
                    mainControllerViewDangKy.showListView(dangKyArrayList);
                } catch (JSONException e) {
                    mainControllerViewDangKy.showMessage(e.getMessage());
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error){

                mainControllerViewDangKy.showMessage(error.getMessage());
            }
        };

        Uri.Builder builder = Uri.parse(utils.URL_GetAllDangKy).buildUpon();

        String url = builder.build().toString();

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                stringListener,
                errorListener
        );
        requestQueue.add(request);

    }
}
