package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham;

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
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMucDAO;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.MainControllerView_DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.utils.utils;

public class SanPhamDAO {
    private ArrayList<SanPham> sanPhamArrayList = new ArrayList<>();
    private ArrayList<DanhMuc> danhMucArrayList = new ArrayList<>();
    private Context context;
    private RequestQueue requestQueue;
    MainControllerView_SanPham mainControllerViewSanPham;
    MainControllerView_DanhMuc mainControllerViewDanhMuc;
    DanhMucDAO danhMucDAO;

    String dmName = "";

    public SanPhamDAO(Context context, MainControllerView_SanPham mainControllerViewSanPham){
        this.context = context;
        this.mainControllerViewSanPham = mainControllerViewSanPham;
        requestQueue = Volley.newRequestQueue(this.context);
    }

    //goi danh sach san pham ra man hinh
    public void onLoad(){
        getAllSanPham(utils.URL_GetAllSanPham);
    }

    //dong ham nay k xuat dc chu danh muc trong item

    private String getDanhmucName(int idInput){
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (DanhMuc dm: danhMucArrayList) {
            if (dm.getId_danhmuc() == idInput){
                return dm.getTendanhmuc();
            }
        }
        return "";
    }

//        AppData appData = AppData.getInstance();
//        if(!appData.danhMucs.isEmpty()){
//            for (DanhMuc dm:
//                 appData.danhMucs) {
//                if(dm.getId_danhmuc()==idInput) return dm.getTendanhmuc();
//            }
//        }
//        return "";
//    }

    //Hàm lấy danh sách của tất cả san pham
    private void getAllSanPham(String sanPham){
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
                        String masp = jsonObject.getString("masp");
                        int soluong = jsonObject.getInt("soluong");
                        String hinh = jsonObject.getString("hinhanh");
                        String noidung = jsonObject.getString("noidung");
                        String danhmuc = jsonObject.optString("tendanhmuc","");


                        SanPham sp = new SanPham();
                        sp.setId_sanpham(ma);
                        sp.setMasp(masp);
                        sp.setTensanpham(ten);
                        sp.setSoluong(soluong);
                        sp.setTendanhmuc(danhmuc);
                        sp.setHinhanh(hinh);
                        sp.setNoidung(noidung);
                        sanPhamArrayList.add(sp);
                    }
                    mainControllerViewSanPham.showListView(sanPhamArrayList);
                } catch (JSONException e) {
                    mainControllerViewSanPham.showMessage(e.getMessage());
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error){

                mainControllerViewSanPham.showMessage(error.getMessage());
            }
        };

        Uri.Builder builder = Uri.parse(utils.URL_GetAllSanPham).buildUpon();

        String url = builder.build().toString();

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                stringListener,
                errorListener
        );
        requestQueue.add(request);
    }

    //Hàm xoá một san pham có trong list
    public void deleteSanPham(int id_sanpham){
        // khai báo lớp dữ liệu nhận response
        Response.Listener<String> stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String result = jsonObject.getString("result");
                    if (result != null) {
                        mainControllerViewDanhMuc.showMessage("Xoa thanh cong");
                        mainControllerViewSanPham.updateListView();
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
        Uri.Builder builder = Uri.parse(utils.URL_DeleteSanPham(id_sanpham)).buildUpon();
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
