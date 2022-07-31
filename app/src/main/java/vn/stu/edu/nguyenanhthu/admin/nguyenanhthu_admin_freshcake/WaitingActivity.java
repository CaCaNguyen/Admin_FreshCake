package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class WaitingActivity extends AppCompatActivity {
    //khoi tao thoi gian cho heu ung
    private static int SPLASH_TIMER = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);

        addControls();
        addEvent();
    }

    private void addControls(){

        //tạo đối tượng view
        ImageView imgLogo = (ImageView) findViewById(R.id.imgLogo);
        TextView txtFreshCake = (TextView)findViewById(R.id.txtCakeshop);
        TextView txtPowered = (TextView)findViewById(R.id.txtPowered);


        //lấy đối tượng animation
        Animation sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim);
        Animation bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        //thiết lập animation cho component
        imgLogo.setAnimation(sideAnim);
        txtFreshCake.setAnimation(sideAnim);
        txtPowered.setAnimation(bottomAnim);
    }

    private void addEvent(){
        //dieu huong chuyen trang
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish(); //destroy activity khi back sẽ ko về splash
            }
        },SPLASH_TIMER);
    }
}