package com.vibhunorby.totalpaisa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //     I was noticed this after 5 months and then searched about this;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setNavigationBarColor(Color.parseColor("#1da1f3"));
//        }



        // It is used to prevent app to intent multiple activity in the stack . It happens after user press home button and then open app again.
        if(!isTaskRoot()) {
            final Intent intent = getIntent();
            if(intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(intent.getAction())) {
                finish();
                return;
            }
        }


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#1da1f3"));
        }


                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

    }

}