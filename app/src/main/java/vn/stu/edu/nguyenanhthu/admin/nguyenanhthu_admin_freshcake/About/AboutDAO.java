package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.About;

import android.content.Context;
import android.net.Uri;
import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.MainControllerView_DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.utils.utils;

public class AboutDAO {

    private ArrayList<About> aboutArrayList = new ArrayList<>();
    private Context context;
    private RequestQueue requestQueue;
    MainControllerView_About mainControllerViewAbout;

    public AboutDAO(Context context, MainControllerView_About mainControllerViewAbout){
        this.context = context;
        this.mainControllerViewAbout = mainControllerViewAbout;
        requestQueue = Volley.newRequestQueue(this.context);
    }

    public void onLoad(){
        getAbout();
    }

    private void getAbout(){
        Response.Listener<String> stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){

                try {
                    JSONObject jsonObj = new JSONObject(response);
                    String message = jsonObj.getString("results");
                    JSONArray jsonArray = new JSONArray(message);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name_store = jsonObject.getString("name_store");
                        String slogan = jsonObject.getString("slogan");
                        String phone = jsonObject.getString("phone");
                        String address = jsonObject.getString("address");
                        int since = jsonObject.getInt("since");
                        About about = new About();
                        about.setName_store(name_store);
                        about.setSlogan(slogan);
                        about.setPhone(phone);
                        about.setAddress(address);
                        about.setSince(since);
                        aboutArrayList.add(about);
                    }
                    mainControllerViewAbout.showListView(aboutArrayList);
                } catch (JSONException e) {
                    mainControllerViewAbout.showMessage(e.getMessage());
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error){

                mainControllerViewAbout.showMessage(error.getMessage());
            }
        };

        Uri.Builder builder = Uri.parse(utils.URL_GetAbout).buildUpon();

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
