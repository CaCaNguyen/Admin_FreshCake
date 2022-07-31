package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.KichThuoc;

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

public class KichThuocDAO {
    private ArrayList<KichThuoc> kichThuocArrayList = new ArrayList<>();
    private Context context;
    private RequestQueue requestQueue;
    MainControllerView_KichThuoc mainControllerViewKichThuoc;

    public KichThuocDAO(Context context, MainControllerView_KichThuoc mainControllerViewKichThuoc){
        this.context = context;
        this.mainControllerViewKichThuoc = mainControllerViewKichThuoc;
        requestQueue = Volley.newRequestQueue(this.context);
    }

    public void onLoad(){
        getAllKichThuoc();
    }

    private void getAllKichThuoc(){
        Response.Listener<String> stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){
                try{
                    JSONObject jsonObj = new JSONObject(response);
                    String message =  jsonObj.getString("results");
                    JSONArray jsonArray = new JSONArray(message);
                    for (int i = 0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id_size");
                        String ten = jsonObject.getString("size");
                        KichThuoc kt = new KichThuoc();
                        kt.setId_size(id);
                        kt.setSize(ten);
                        kichThuocArrayList.add(kt);
                    }
                    mainControllerViewKichThuoc.showListView(kichThuocArrayList);
                }catch (JSONException e){
                    mainControllerViewKichThuoc.showMessage(e.getMessage());
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                mainControllerViewKichThuoc .showMessage(error.getMessage());
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

    //Hàm insert thêm một danh mục mới cho danh sách
    public void insertKichThuoc(String size){
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
                        mainControllerViewKichThuoc.showMessage("Thêm thành công");
                        mainControllerViewKichThuoc.updateListView();
                    } else {
                        mainControllerViewKichThuoc.showMessage("Không thành công");
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
                mainControllerViewKichThuoc.showMessage(error.getMessage());
            }
        };

        // lấy url buid từ constant
        Uri.Builder builder = Uri.parse(utils.URL_InsertSize).buildUpon();
        // bui parameter action = insert
//        builder.appendQueryParameter("action", "insert");
        builder.appendQueryParameter("size", size);
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
    public void updateKichThuoc(int ma, String size_new){
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
                        mainControllerViewKichThuoc.showMessage("Sửa thành công");
                        mainControllerViewKichThuoc.updateListView();
                    } else {
                        mainControllerViewKichThuoc.showMessage("Không thành công");
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
                mainControllerViewKichThuoc.showMessage(error.getMessage());
            }
        };

        // lấy url buid từ constant
        Uri.Builder builder = Uri.parse(utils.URL_UpdateSize).buildUpon();
        // bui parameter action = insert
        builder.appendQueryParameter("id", ma + "");
        builder.appendQueryParameter("size", size_new);
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
    public void deleteKichThuoc(int id_size){
        // khai báo lớp dữ liệu nhận response
        Response.Listener<String> stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String result = jsonObject.getString("result");
                    if (result != null) {
                        mainControllerViewKichThuoc.showMessage("Xoa thanh cong");
                    } else {
                        mainControllerViewKichThuoc.showMessage("Xoa khong thanh cong!");
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
                mainControllerViewKichThuoc.showMessage(error.getMessage());
            }
        };

        // lấy url buid từ constant
        Uri.Builder builder = Uri.parse(utils.URL_DeleteSize(id_size)).buildUpon();
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
