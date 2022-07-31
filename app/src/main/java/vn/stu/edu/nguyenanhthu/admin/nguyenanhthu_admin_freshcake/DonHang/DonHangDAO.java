package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DonHang;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.utils.utils;

public class DonHangDAO {
    private ArrayList<DonHang> donHangArrayList = new ArrayList<>();
    private Context context;
    private RequestQueue requestQueue;
    MainControllerView_DonHang mainControllerViewDonHang;
    //private  DanhMucRepo danhMucRepo = new DanhMucRepo();

    public DonHangDAO(Context context, MainControllerView_DonHang mainControllerViewDonHang){
        this.context = context;
        this.mainControllerViewDonHang = mainControllerViewDonHang;
        requestQueue = Volley.newRequestQueue(this.context);
    }

    public void onLoad(){
        getAllDonHang();
    }

    public String trangThai(int x){
        if (x == 1) {
            return "da xac nhan";
        } else {
            return "chua xac nhan";
        }
    }

    //Hàm lấy danh sách của tất cả danh mục
    private void getAllDonHang(){
        Response.Listener<String> stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){

                try {
                    JSONObject jsonObj = new JSONObject(response);
                    String message = jsonObj.getString("results");
                    JSONArray jsonArray = new JSONArray(message);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id_giohang");
                        String ten = jsonObject.getString("tenkhachhang");
                        int x = jsonObject.getInt("trangthai");
                        String trangthai = String.valueOf(trangThai(x));


                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        //ngay khong lay duoc;
                        String ngaylap = jsonObject.getString("ngaylap");
                        String ngaynhanhang = jsonObject.getString("ngaynhanhang");

//                        Date datengaylap  = df.parse(ngaylap);
//                        Date datengaynhan = df.parse(ngaynhanhang);

                        DonHang dh = new DonHang();
                        dh.setId_giohang(id);
                        dh.setTenkhachhang(ten);
                        dh.setTrangthai(trangthai);
                        dh.setNgaylap(ngaylap);
                        dh.setNgaynhan(ngaynhanhang);

                        donHangArrayList.add(dh);

                    }
                    mainControllerViewDonHang.showListView(donHangArrayList);
                } catch (JSONException e) {
                    mainControllerViewDonHang.showMessage(e.getMessage());
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error){

                mainControllerViewDonHang.showMessage(error.getMessage());
            }
        };

        Uri.Builder builder = Uri.parse(utils.URL_GetAllGioHang).buildUpon();

        String url = builder.build().toString();

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                stringListener,
                errorListener
        );
        requestQueue.add(request);

    }

    public void updateDonHang(int ma) throws JSONException{
        JSONObject reqData = new JSONObject();
        reqData.put("id",ma);
        reqData.put("trangthai",1);
        Log.d("REQUEST",reqData.toString());
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.PUT,
                utils.URL_UpdateGioHang,
                reqData,
                response -> {
                    try {
                         JSONObject result = response.getJSONObject("result");
                         donHangArrayList.clear();
                         getAllDonHang();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("RESPONSE",response.toString());
        }, error -> {
                    Log.d("ERROR",error.getMessage());
        }
        );

        requestQueue.add(request);
    }
}
