package ahsan_malik.cvmakermvvm.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;

import com.airbnb.lottie.LottieAnimationView;

import ahsan_malik.cvmakermvvm.R;

public class SplashScreen extends AppCompatActivity {

    LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        animationView = findViewById(R.id.animlogo);
        //animationView.animate().translationY(-1400).setDuration(1000).setStartDelay(4000);
        animationView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.logo_anim));

        new Handler().postDelayed(() -> {
           startActivity(new Intent(SplashScreen.this, MainActivity.class));
           finish();
        }, 3000);


    }
}