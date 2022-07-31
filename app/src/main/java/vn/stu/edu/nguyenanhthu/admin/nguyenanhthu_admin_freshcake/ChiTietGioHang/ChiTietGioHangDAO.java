package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ChiTietGioHang;

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

public class ChiTietGioHangDAO {

    private ArrayList<ChiTietGioHang> chiTietGioHangArrayList = new ArrayList<>();

    private Context context;
    private RequestQueue requestQueue;
    MainControllerView_ChiTietGioHang mainControllerViewChiTietGioHang;
    ChiTietGioHangDAO chiTietGioHangDAO;

    String dmName = "";

    public ChiTietGioHangDAO(Context context, MainControllerView_ChiTietGioHang mainControllerViewChiTietGioHang){
        this.context = context;
        this.mainControllerViewChiTietGioHang = mainControllerViewChiTietGioHang;
        requestQueue = Volley.newRequestQueue(this.context);
    }


    public void getsChiTietDonHangByIdGioHang(int id_giohang){
        Response.Listener<String> stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){

                try {
                    JSONObject jsonObj = new JSONObject(response);
                    String message = jsonObj.getString("results");
                    if (message != null){
                        JSONArray jsonArray = new JSONArray(message);
                        for (int i = 0; i<jsonArray.length();i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);


                            int ma = jsonObject.getInt("id_chitietgiohang");
                            int magiohang = jsonObject.getInt("magiohang");
                            String tensp = jsonObject.getString("tensanpham");
                            int soluong = jsonObject.getInt("soluongmua");
                            String size = jsonObject.getString("size");

                            ChiTietGioHang ctgh = new ChiTietGioHang();
                            ctgh.setId_chitietgiohang(ma);
                            ctgh.setMagiohang(magiohang);
                            ctgh.setTensanpham(tensp);
                            ctgh.setSoluong(soluong);
                            ctgh.setSize(size);

                            chiTietGioHangArrayList.add(ctgh);
                        }
                    }
                    mainControllerViewChiTietGioHang.showListView(chiTietGioHangArrayList);
                }catch (JSONException e){
                    mainControllerViewChiTietGioHang.showMessage(e.getMessage());
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error){

                mainControllerViewChiTietGioHang.showMessage(error.getMessage());
            }
        };

        Uri.Builder builder = Uri.parse(utils.URL_GetChiTietGioHangByIdGioHang(id_giohang)).buildUpon();

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
