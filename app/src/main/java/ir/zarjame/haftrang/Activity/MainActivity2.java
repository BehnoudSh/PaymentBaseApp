package ir.zarjame.haftrang.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import ir.zarjame.haftrang.Models.Responses.Response_Image;
import ir.zarjame.haftrang.NetworkServices.ApiCallbacks;
import ir.zarjame.haftrang.NetworkServices.ApiHandler;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import butterknife.ButterKnife;

import butterknife.BindView;
import ir.zarjame.haftrang.R;

public class MainActivity2 extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;

    @BindView(R.id.toolbaricon)
    ImageView toolbaricon;

    @BindView(R.id.tv_actionbar_title)
    TextView tv_actionbar_title;

    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar
            toolbar;

    @BindView(R.id.iv_actionbar_back)
    ImageView iv_actionbar_back;

    @BindView(R.id.chargeHolder)
    CardView chargeHolder;

    @BindView(R.id.ghabzHolder)
    CardView ghabzHolder;

    @BindView(R.id.internetHolder)
    CardView internetHolder;

    @BindView(R.id.bgbgbg)
    ImageView bgbgbg;

    Animation.AnimationListener listener;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        setupactionbar();
//        ApiHandler.getImage(this, new ApiCallbacks.imageInterface() {
//            @Override
//            public void ongetImageFailed() {
//
//            }
//
//            @Override
//            public void ongetImageSucceeded(Response_Image response) {
//
//
//                RequestOptions requestOptions = new RequestOptions();
//                requestOptions.placeholder(0);
//                requestOptions.fitCenter();
//
//                Glide.with(MainActivity2.this)
//                        .applyDefaultRequestOptions(requestOptions)
//                        .load(response.getUrls().getRegular())
//                        .apply(requestOptions)
//                        .into(bgbgbg);
//
//
//            }
//        });

        listener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                System.out.println("End Animation!");
                //load_animations();
            }
        };

        load_animations();
        chargeHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, ChargeActivity.class);
//                intent.putExtra("bg_color", item.getBg_color());
                intent.putExtra("toolbar_title", "خرید شارژ");
                intent.putExtra("bg_color", "");
                startActivity(intent);
            }
        });

        ghabzHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, BillActivity.class);
//                intent.putExtra("bg_color", item.getBg_color());
                intent.putExtra("toolbar_title", "پرداخت قبض");
                intent.putExtra("bg_color", "");
                startActivity(intent);
            }
        });

        internetHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, InternetActivity.class);
//                intent.putExtra("bg_color", item.getBg_color());
                intent.putExtra("toolbar_title", "خرید بسته‌ی اینترنت");
                intent.putExtra("bg_color", "");
                startActivity(intent);
            }
        });
    }

    void setupactionbar() {

        tv_actionbar_title.setText("هفت‌رنگ");

        toolbar.setBackgroundColor(getResources().getColor(R.color.transparent_black_percent_60));

        iv_actionbar_back.setVisibility(View.GONE);

    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            return;
        }

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "برای خروج، دوباره دکمه‌ی بازگشت را لمس نمایید", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    void load_animations() {
        new AnimationUtils();
        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        rotation.setAnimationListener(listener);
        toolbaricon.startAnimation(rotation);
    }
}
