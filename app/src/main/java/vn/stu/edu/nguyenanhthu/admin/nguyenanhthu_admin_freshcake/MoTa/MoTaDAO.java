package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.MoTa;

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
import java.util.Calendar;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.KichThuoc.KichThuoc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.KichThuoc.KichThuocDAO;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.KichThuoc.MainControllerView_KichThuoc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham.MainControllerView_SanPham;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham.SanPham;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham.SanPhamDAO;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.utils.utils;

public class MoTaDAO {

    private ArrayList<MoTa> moTaArrayList = new ArrayList<>();
    private ArrayList<SanPham> sanPhamArrayList = new ArrayList<>();
    private ArrayList<KichThuoc> kichThuocArrayList = new ArrayList<>();

    private Context context;
    private RequestQueue requestQueue;

    MainControllerView_MoTa mainControllerViewMoTa;
    MainControllerView_SanPham mainControllerViewSanPham;
    MainControllerView_KichThuoc mainControllerViewKichThuoc;

    KichThuocDAO kichThuocDAO;
    SanPhamDAO sanPhamDAO;


    public MoTaDAO(Context context, MainControllerView_MoTa mainControllerViewMoTa){
        this.context = context;
        this.mainControllerViewMoTa = mainControllerViewMoTa;
        requestQueue = Volley.newRequestQueue(this.context);

    }

    public void onLoad(){
        getAllMoTa(utils.URL_GetAllMoTa);
    }

    //H??m l???y danh s??ch c???a t???t c??? san pham
    private void getAllMoTa(String moTa){
        Response.Listener<String> stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){

                try {
                    JSONObject jsonObj = new JSONObject(response);
                    String message = jsonObj.getString("results");
                    JSONArray jsonArray = new JSONArray(message);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        int id = jsonObject.getInt("id_mota");
                        String ten = jsonObject.getString("tensanpham");
                        String size = jsonObject.getString("size");
                        Double gia = jsonObject.getDouble("gia_sp");


                        MoTa mota = new MoTa();
                        mota.setId_mota(id);
                        mota.setTensanpham(ten);
                        mota.setSize(size);
                        mota.setGiasp(gia);
                        moTaArrayList.add(mota);

                    }
                    mainControllerViewMoTa.showListView(moTaArrayList);
                } catch (JSONException e) {
                    mainControllerViewMoTa.showMessage(e.getMessage());
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error){

                mainControllerViewMoTa.showMessage(error.getMessage());
            }
        };

        Uri.Builder builder = Uri.parse(utils.URL_GetAllMoTa).buildUpon();

        String url = builder.build().toString();

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                stringListener,
                errorListener
        );
        requestQueue.add(request);
    }

    //H??m xo?? m???t danh m???c c?? trong list
    //H??m xo?? m???t danh m???c c?? trong list
    public void deleteMoTa(int id_mota){
        // khai b??o l???p d??? li???u nh???n response
        Response.Listener<String> stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String result = jsonObject.getString("result");
                    if (result != null) {
                        mainControllerViewMoTa.showMessage("Xoa thanh cong");
                    } else {
                        mainControllerViewMoTa.showMessage("Xoa khong thanh cong!");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                // n???u api b??? l???i th?? n?? v??o ????y
                // l???i m???ng
                // d???a ch??? ip
                //
                mainControllerViewMoTa.showMessage(error.getMessage());
            }
        };

        // l???y url buid t??? constant
        Uri.Builder builder = Uri.parse(utils.URL_DeleteMoTa(id_mota)).buildUpon();
        // bui parameter action = insert
        //builder.appendQueryParameter("",id_danhmuc + "");
//        builder.appendQueryParameter("ma",ma);
        // chuy???n gi?? tr??? builder th??nh string
        String url = builder.build().toString();

        StringRequest request = new StringRequest(
                Request.Method.DELETE, // ph????ng th???c get
                url,// ???????ng d???n Get
                stringListener,
                errorListener
        );

        requestQueue.add(request);
    }

}
