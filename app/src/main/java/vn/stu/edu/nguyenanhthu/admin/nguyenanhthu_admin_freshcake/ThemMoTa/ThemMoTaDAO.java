package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThemMoTa;

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
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.KichThuoc.KichThuoc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.MoTa.MoTa;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham.SanPham;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThemSanPham.MainControllerView_ThemSanPham;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.utils.utils;

public class ThemMoTaDAO {
    private ArrayList<MoTa> moTaArrayList = new ArrayList<>();
    private ArrayList<KichThuoc> kichThuocArrayList = new ArrayList<>();
    private ArrayList<TenSanPham> tenSanPhamArrayList = new ArrayList<>();
    private Context context;
    private RequestQueue requestQueue;
    MainControllerView_ThemMoTa mainControllerView_themMoTa;

    public ThemMoTaDAO(Context context, MainControllerView_ThemMoTa mainControllerView_themMoTa){
        this.context = context;
        this.mainControllerView_themMoTa =mainControllerView_themMoTa;
        requestQueue = Volley.newRequestQueue(this.context);
        //onLoad();
    }

    public void onLoadSize(){
        getAllSize();
    }

    public void onLoadSanPham(){
        getAllTenSanPham();
    }

    //Hàm lấy danh sách của tất cả danh mục
    private void getAllSize(){
        Response.Listener<String> stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){

                try {
                    JSONObject jsonObj = new JSONObject(response);
                    String message = jsonObj.getString("results");
                    JSONArray jsonArray = new JSONArray(message);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id_size");
                        String ten = jsonObject.getString("size");
                        KichThuoc kt = new KichThuoc();
                        kt.setId_size(id);
                        kt.setSize(ten);
                        kichThuocArrayList.add(kt);

                    }
                    mainControllerView_themMoTa.showSpinerKichThuoc(kichThuocArrayList);
                } catch (JSONException e) {
                    mainControllerView_themMoTa.showMessage(e.getMessage());
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error){

                mainControllerView_themMoTa.showMessage(error.getMessage());
            }
        };

        Uri.Builder builder = Uri.parse(utils.URL_GetAllSize).buildUpon();

        String url = builder.build().toString();

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                stringListener,
                errorListener
        );
        requestQueue.add(request);

    }

    //Hàm lấy danh sách của tất cả san pham
    private void getAllTenSanPham(){
        Response.Listener<String> stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){

                try {
                    JSONObject jsonObj = new JSONObject(response);
                    String message = jsonObj.getString("results");
                    JSONArray jsonArray = new JSONArray(message);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        int ma = jsonObject.getInt("id_sanpham");
                        String ten = jsonObject.getString("tensanpham");

                        TenSanPham tsp = new TenSanPham();

                        tsp.setId_sanpham(ma);
                        tsp.setTensanpham(ten);

                        tenSanPhamArrayList.add(tsp);
                    }
                    mainControllerView_themMoTa.showSpinerSanPham(tenSanPhamArrayList);
                } catch (JSONException e) {
                    mainControllerView_themMoTa.showMessage(e.getMessage());
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error){

                mainControllerView_themMoTa.showMessage(error.getMessage());
            }
        };

        Uri.Builder builder = Uri.parse(utils.URL_GetAllTenSanPham).buildUpon();

        String url = builder.build().toString();

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                stringListener,
                errorListener
        );
        requestQueue.add(request);
    }

    //ham them san pham
    public void insertMoTa(int id_sanpham, int id_size, double giasp){
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
                        mainControllerView_themMoTa.showMessage("Thêm thành công");
                        mainControllerView_themMoTa.updateListView();
                    } else {
                        mainControllerView_themMoTa.showMessage("Không thành công");
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
               mainControllerView_themMoTa.showMessage(error.getMessage());
            }
        };

        // lấy url buid từ constant
        //String ten, int masp, double giasp, int soluong, String hinhanh,
        //                              String tomtat, String noidung, int tinhtrang, int id_danhmuc
        Uri.Builder builder = Uri.parse(utils.URL_InsertMoTa).buildUpon();
        // bui parameter action = insert
        builder.appendQueryParameter("id_sanpham", String.valueOf(id_sanpham));
        builder.appendQueryParameter("id_size", String.valueOf(id_size));
        builder.appendQueryParameter("gia_sp", String.valueOf(giasp));


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


}
