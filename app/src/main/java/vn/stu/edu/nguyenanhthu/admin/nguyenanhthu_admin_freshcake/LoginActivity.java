package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.ActivityOptions;
import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.databinding.ActivityLoginBinding;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.utils.utils;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //token notification
        //them vo
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        System.out.println("TOKEN LA:"+token);
                    }
                });

        binding.txtUserName.getText().toString();
        binding.txtPassword.getText().toString();
        binding.btnLoginDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                login(utils.URL_CheckAdmin);
                //notification qua user
                FCMSend.pushNotification(LoginActivity.this,
                        "c6fl54c-Sq-dWJIJKXQwdb:APA91bEbOR0q3PskPhuIDSbypEKa3hvmTc8MjJOA7VoqdGLTq83N0Q4wQhWXi8Qu44WO9M_n6jrAhAx-5785V_Wd0XmODno0W8tlt7373E4gWkzSzOsxd8e_NB0NwgjgTDgC6Wz4nKvQ\n",
                        "Check Order",
                        "Don dat hang cua ban da xac nhan!"
                );
//                sendNotification();
            }
        });
    }

    private void login(String url_checkAdmin){
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean result = Boolean.parseBoolean(jsonObject.getString("result"));
                    if (result == true) {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Đăng nhập không thành công", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(LoginActivity.this, "Lỗi\n", Toast.LENGTH_LONG).show();
                Log.d("AAA", "Lỗi\n" + error.toString());
            }
        };
        Uri.Builder builder = Uri.parse(utils.URL_CheckAdmin).buildUpon();
        builder.appendQueryParameter("username", binding.txtUserName.getText().toString().trim());
        builder.appendQueryParameter("password", binding.txtPassword.getText().toString().trim());
        url_checkAdmin = builder.build().toString();
        StringRequest request = new StringRequest(Request.Method.GET, url_checkAdmin,
                listener,
                errorListener
        );
        request.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    //Goi ve trang welcome khi nhan nut mui ten tro ve tren activity dang ky
    public void backFromLogin(View view){
        Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
        //tạo animation cho thành phần
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.activityLogin), "transition_login");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    //Send notification
    public void sendNotification(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        Notification notification = new NotificationCompat.Builder(this, MyNotification.CHANNEL_ID)
                .setContentTitle("Check the order")
                .setContentText("Có đơn đặt hàng đang chờ hãy gọi điện và xác nhận")
                .setSmallIcon(R.drawable.logo)
                .setColor(getResources().getColor(R.color.teal_200))
                .setLargeIcon(bitmap)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getNotificationId(),notification);
    }

    private int getNotificationId(){
        return (int) new Date().getTime();
    }
}