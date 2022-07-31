package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThongKeMua;

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

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.utils.utils;

public class ThongKeDAO {
    private ArrayList<ThongKe> thongKeArrayList = new ArrayList<>();
    private Context context;
    private RequestQueue requestQueue;
    MainControllerView_ThongKe mainControllerViewThongKe;

    //private  DanhMucRepo danhMucRepo = new DanhMucRepo();

    public ThongKeDAO(Context context, MainControllerView_ThongKe mainControllerViewThongKe){
        this.context = context;
        this.mainControllerViewThongKe = mainControllerViewThongKe;
        requestQueue = Volley.newRequestQueue(this.context);
    }

    public void onLoad(){
        getThongKeMua();
    }

    //Hàm lấy danh sách của tất cả danh mục
    private void getThongKeMua(){
        Response.Listener<String> stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){

                try {
                    JSONObject jsonObj = new JSONObject(response);
                    String message = jsonObj.getString("results");
                    JSONArray jsonArray = new JSONArray(message);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String tensanpham  = jsonObject.getString("tensanpham");
                        int soluongmua = jsonObject.getInt("soluong");

                        ThongKe tkm = new ThongKe();
                        tkm.setTensanpham(tensanpham);
                        tkm.setSoluong(soluongmua);
                        thongKeArrayList.add(tkm);
                    }
                    mainControllerViewThongKe.showListView(thongKeArrayList);
                } catch (JSONException e) {
                    mainControllerViewThongKe.showMessage(e.getMessage());
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error){
                mainControllerViewThongKe.showMessage(error.getMessage());
            }
        };

        Uri.Builder builder = Uri.parse(utils.URL_GetThongKeMua).buildUpon();

        String url = builder.build().toString();

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                stringListener,
                errorListener
        );
        requestQueue.add(request);

    }

    public void onLoadThongKe(){
        getThongKeBan();
    }

    //Hàm lấy danh sách của tất cả danh mục
    private void getThongKeBan(){
        Response.Listener<String> stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){

                try {
                    JSONObject jsonObj = new JSONObject(response);
                    String message = jsonObj.getString("results");
                    JSONArray jsonArray = new JSONArray(message);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String tensanpham  = jsonObject.getString("tensanpham");
                        int soluongmua = jsonObject.getInt("soluong");

                        ThongKe tkm = new ThongKe();
                        tkm.setTensanpham(tensanpham);
                        tkm.setSoluong(soluongmua);
                        thongKeArrayList.add(tkm);
                    }
                    mainControllerViewThongKe.showListView(thongKeArrayList);
                } catch (JSONException e) {
                    mainControllerViewThongKe.showMessage(e.getMessage());
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error){
                mainControllerViewThongKe.showMessage(error.getMessage());
            }
        };

        Uri.Builder builder = Uri.parse(utils.URL_GetThongKeBan).buildUpon();

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
