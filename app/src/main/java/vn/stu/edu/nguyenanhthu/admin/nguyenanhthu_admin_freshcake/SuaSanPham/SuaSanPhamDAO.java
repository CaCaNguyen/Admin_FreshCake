package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SuaSanPham;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

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
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham.SanPham;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.utils.utils;

public class SuaSanPhamDAO {
    private ArrayList<DanhMuc> danhMucArrayList = new ArrayList<>();
    private ArrayList<SanPham> sanPhamArrayList = new ArrayList<>();
    private Context context;
    private RequestQueue requestQueue;
    MainControllerView_SuaSanPham mainControllerView_suaSanPham;

    public SuaSanPhamDAO (Context context, MainControllerView_SuaSanPham mainControllerView_suaSanPham){
        this.context = context;
        this.mainControllerView_suaSanPham = mainControllerView_suaSanPham;
        requestQueue = Volley.newRequestQueue(this.context);
        //onLoad();
    }

    public void onLoad(){
        getAllDanhMuc();
    }

    //Hàm lấy danh sách của tất cả danh mục
    //Hàm lấy danh sách của tất cả danh mục
    private void getAllDanhMuc(){
        Response.Listener<String> stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){

                try {
                    JSONObject jsonObj = new JSONObject(response);
                    String message = jsonObj.getString("results");
                    JSONArray jsonArray = new JSONArray(message);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id_danhmuc");
                        String ten = jsonObject.getString("tendanhmuc");
                        DanhMuc dm = new DanhMuc();
                        dm.setId_danhmuc(id);
                        dm.setTendanhmuc(ten);
                        danhMucArrayList.add(dm);
                    }
                    mainControllerView_suaSanPham.showSpiner(danhMucArrayList);
                } catch (JSONException e) {
                    mainControllerView_suaSanPham.showMessage(e.getMessage());
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error){

                mainControllerView_suaSanPham.showMessage(error.getMessage());
            }
        };

        Uri.Builder builder = Uri.parse(utils.URL_GetAllDanhMuc).buildUpon();

        String url = builder.build().toString();

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                stringListener,
                errorListener
        );
        requestQueue.add(request);

    }

    //Ham update mot danh muc co trong list
    //ham them san pham
    public void updateSanPham(int ma, String ten, String masp, int soluong, String hinhanh,
                              String noidung, int id_danhmuc){
        // khai báo lớp dữ liệu nhận response
        Response.Listener<String> stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){
                // khi dữ liệu bên api trả về thì nó vào đây
                //response = [{"id":"1","tendanhmuc":"abc"}]
                // lấy dữ liệu trả về và đưa vào list
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String result = jsonObject.getString("result");
                    if ( result != null) {
                        mainControllerView_suaSanPham.showMessage("Cập nhật thành công");
                        mainControllerView_suaSanPham.updateListView();
                    } else {
                        mainControllerView_suaSanPham.showMessage("Cập nhật không thành công");
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
                mainControllerView_suaSanPham.showMessage(error.getMessage());
            }
        };

        // lấy url buid từ constant
        //String ten, int masp, double giasp, int soluong, String hinhanh,
        //                              String tomtat, String noidung, int tinhtrang, int id_danhmuc
        Uri.Builder builder = Uri.parse(utils.URL_UpdateSanPham).buildUpon();
        // bui parameter action = insert
        builder.appendQueryParameter("id", ma + "");
        builder.appendQueryParameter("tensanpham", ten);
        builder.appendQueryParameter("masp", masp);
        builder.appendQueryParameter("soluong", String.valueOf(soluong));
        builder.appendQueryParameter("hinhanh", hinhanh);
        builder.appendQueryParameter("noidung", noidung);
        builder.appendQueryParameter("id_danhmuc", String.valueOf(id_danhmuc));


        // chuyển giá trị builder thành string
        String url = builder.build().toString();

        StringRequest request = new StringRequest(
                Request.Method.PUT, // phương thức get
                url,// đường dẫn Get
                stringListener,
                errorListener
        );

        requestQueue.add(request);
    }
}
