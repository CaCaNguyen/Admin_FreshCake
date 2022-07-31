package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc;

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

public class DanhMucDAO {
    private ArrayList<DanhMuc> danhMucArrayList = new ArrayList<>();
    private Context context;
    private RequestQueue requestQueue;
    MainControllerView_DanhMuc mainControllerViewDanhMuc;
    //private  DanhMucRepo danhMucRepo = new DanhMucRepo();

    public DanhMucDAO(Context context, MainControllerView_DanhMuc mainControllerViewDanhMuc){
        this.context = context;
        this.mainControllerViewDanhMuc = mainControllerViewDanhMuc;
        requestQueue = Volley.newRequestQueue(this.context);
    }

    public void onLoad(){
        getAllDanhMuc();
    }

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
                    mainControllerViewDanhMuc.showListView(danhMucArrayList);
                } catch (JSONException e) {
                    mainControllerViewDanhMuc.showMessage(e.getMessage());
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error){

                mainControllerViewDanhMuc.showMessage(error.getMessage());
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

    //Hàm insert thêm một danh mục mới cho danh sách
    public void insertDanhMuc(String tendanhmuc){
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
                    if (result != null) {
                        mainControllerViewDanhMuc.showMessage("Thêm thành công");
                        mainControllerViewDanhMuc.updateListView();
                    } else {
                        mainControllerViewDanhMuc.showMessage("Không thành công");
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
                mainControllerViewDanhMuc.showMessage(error.getMessage());
            }
        };

        // lấy url buid từ constant
        Uri.Builder builder = Uri.parse(utils.URL_InsertDanhMuc).buildUpon();
        // bui parameter action = insert
//        builder.appendQueryParameter("action", "insert");
        builder.appendQueryParameter("tendanhmuc", tendanhmuc);
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


    //Ham update mot danh muc co trong list
    public void updateDanhMuc(int ma, String tendanhmucnew){
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
                    if (result != null) {
                        mainControllerViewDanhMuc.showMessage("Sửa thành công");
                        mainControllerViewDanhMuc.updateListView();
                    } else {
                        mainControllerViewDanhMuc.showMessage("Không thành công");
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
                mainControllerViewDanhMuc.showMessage(error.getMessage());
            }
        };

        // lấy url buid từ constant
        Uri.Builder builder = Uri.parse(utils.URL_UpdateDanhMuc).buildUpon();
        // bui parameter action = insert
        builder.appendQueryParameter("id", ma + "");
        builder.appendQueryParameter("tendanhmuc", tendanhmucnew);
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


    //Hàm xoá một danh mục có trong list
    public void deleteDanhMuc(int id_danhmuc){
        // khai báo lớp dữ liệu nhận response
        Response.Listener<String> stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String result = jsonObject.getString("result");
                    if (result != null) {
                        mainControllerViewDanhMuc.showMessage("Xoa thanh cong");
                    } else {
                        mainControllerViewDanhMuc.showMessage("Xoa khong thanh cong!");
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
                mainControllerViewDanhMuc.showMessage(error.getMessage());
            }
        };

        // lấy url buid từ constant
        Uri.Builder builder = Uri.parse(utils.URL_DeleteDanhMuc(id_danhmuc)).buildUpon();
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
