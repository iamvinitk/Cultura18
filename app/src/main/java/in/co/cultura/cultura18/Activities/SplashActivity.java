package in.co.cultura.cultura18.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import in.co.cultura.cultura18.R;


public class SplashActivity extends AppCompatActivity {
    public static final int STARTUP_DELAY = 500;
    public static final int ANIM_ITEM_DURATION = 1000;
    public static final int ITEM_DELAY = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.SplashTheme);
        setContentView(R.layout.activity_splash);
        if (Build.VERSION.SDK_INT >= 21) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        animator();
    }

    private void animator() {
        ImageView imageView = findViewById(R.id.splash_img_logo);
        ViewGroup container = findViewById(R.id.splash_container);
        final TextView textView = findViewById(R.id.splash_date_text);
//
//        Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.animate);
//        animation.reset();
//        textView.clearAnimation();
//        textView.setAnimation(animation);
        ViewCompat.animate(imageView).translationY(+150)
                .setStartDelay(STARTUP_DELAY)
                .setDuration(ANIM_ITEM_DURATION).setInterpolator(
                new DecelerateInterpolator(1.2f)).start();
        Display display = getWindowManager().getDefaultDisplay();
        int height = display.getHeight();

        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);
            ViewPropertyAnimatorCompat viewAnimator;

            viewAnimator = ViewCompat.animate(v)
                    .translationY(-height / (2.8f)).alpha(1)
                    .setStartDelay((ITEM_DELAY * i) + 500)
                    .setDuration(500);
            viewAnimator.setInterpolator(new DecelerateInterpolator()).start();
        }
        ViewCompat.animate(imageView)
                .scaleXBy(-0.2f)
                .scaleYBy(-0.2f)
                .setStartDelay(STARTUP_DELAY)
                .setDuration(ANIM_ITEM_DURATION).setInterpolator(new DecelerateInterpolator(1.2f))
                .start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.animate);
                animation.reset();
                textView.setVisibility(View.VISIBLE);
                textView.clearAnimation();
                textView.setAnimation(animation);
            }
        }, 1500);
         Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
            startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
            }
        }, 4500);
    }
}
