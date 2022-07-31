package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThongKeDoanhThu;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
import java.util.List;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.R;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThongKeMua.MainControllerView_ThongKe;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThongKeMua.ThongKe;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.utils.utils;

public class DoanhThuDAO {
    private ArrayList<DoanhThu> doanhThuArrayList = new ArrayList<>();
    private Context context;
    private RequestQueue requestQueue;
    MainControllerView_DoanhThu mainControllerViewDoanhThu;

    //private  DanhMucRepo danhMucRepo = new DanhMucRepo();

    public DoanhThuDAO(Context context, MainControllerView_DoanhThu mainControllerViewDoanhThu){
        this.context = context;
        this.mainControllerViewDoanhThu = mainControllerViewDoanhThu;
        requestQueue = Volley.newRequestQueue(this.context);
    }

    public void onLoad(){
        getThongKeDoanhThu();
    }

    //Hàm lấy danh sách của tất cả danh mục
    private void getThongKeDoanhThu(){
        Response.Listener<String> stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){

                try {
                    JSONObject jsonObj = new JSONObject(response);
                    String message = jsonObj.getString("results");
                    JSONArray jsonArray = new JSONArray(message);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int thang  = jsonObject.getInt("Tháng");
                        int nam = jsonObject.getInt("Năm");
                        Double tien =jsonObject.getDouble("Doanh thu");

                        DoanhThu doanhThu = new DoanhThu();
                        doanhThu.setThang(thang);
                        doanhThu.setNam(nam);
                        doanhThu.setTien(tien);
                        doanhThuArrayList.add(doanhThu);

                    }
                    mainControllerViewDoanhThu.showListView(doanhThuArrayList);
                } catch (JSONException e) {
                    mainControllerViewDoanhThu.showMessage(e.getMessage());
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error){
                mainControllerViewDoanhThu.showMessage(error.getMessage());
            }
        };

        Uri.Builder builder = Uri.parse(utils.URL_GetThongKeDoanhThu).buildUpon();

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
