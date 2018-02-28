package ir.haftrang.haftrang.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import butterknife.ButterKnife;
import ir.haftrang.haftrang.Models.Responses.Response_Config;
import ir.haftrang.haftrang.NetworkServices.ApiCallbacks;
import ir.haftrang.haftrang.NetworkServices.ApiHandler;
import ir.haftrang.haftrang.R;
import ir.haftrang.haftrang.Tools.PublicTools;
import ir.haftrang.haftrang.Tools.ScreenUtils;
import ir.haftrang.haftrang.Tools.SharedPref;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1500;
    AlertDialog _dialogOffline;
    AlertDialog _dialogForceUpdate;
    AlertDialog _dialogConfigError;
    Handler handler = new Handler();
    Animation.AnimationListener listener;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        load_animations();
        setScreenUtils();
        SharedPref.getInstance().initSharedPref(getApplicationContext());
        _dialogConfigError = makeErrorDialog();
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
    }

    void load_animations() {
        new AnimationUtils();
        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        rotation.setAnimationListener(listener);
        (findViewById(R.id.haftrangicon)).startAnimation(rotation);
    }

    void setScreenUtils() {
        final ScreenUtils screenUtils = new ScreenUtils();
        screenUtils.Metrics = getResources().getDisplayMetrics();
        screenUtils.ScreenSizesInDP.x = screenUtils.ConvertPixelsToDp(screenUtils.Metrics.widthPixels, this);
        screenUtils.ScreenSizesInDP.y = screenUtils.ConvertPixelsToDp(screenUtils.Metrics.heightPixels, this);
        screenUtils.ScreenSizesInPixel.x = screenUtils.Metrics.widthPixels;
        screenUtils.ScreenSizesInPixel.y = screenUtils.Metrics.heightPixels;
    }

    void makeOfflineDialog() {
        _dialogOffline = new AlertDialog.Builder(this)
                .setMessage("اتصال به اینترنت برقرار نیست")
                .setCancelable(false)
                .setPositiveButton("تلاش دوباره", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (PublicTools.checkNetworkStatus(SplashActivity.this)) {

                            getConfig();

                        } else {
                            Intent i = new Intent(SplashActivity.this, SplashActivity.class);
                            startActivity(i);
                            dialog.dismiss();
                            finish();
                        }
                    }
                })
                .setNegativeButton("تنظیمات اتصال به اینترنت", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));

                    }
                })

                .create();
    }

    @Override
    protected void onStart() {

        super.onStart();

        if (PublicTools.checkNetworkStatus(SplashActivity.this)) {

            getConfig();

        } else {

            makeOfflineDialog();
            _dialogOffline.show();

        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (_dialogOffline != null && _dialogOffline.isShowing())
            _dialogOffline.dismiss();

        if (_dialogForceUpdate != null && _dialogForceUpdate.isShowing())
            _dialogForceUpdate.dismiss();
    }

    void getConfig() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ApiHandler.getConfig(SplashActivity.this, new ApiCallbacks.getConfigInterface()

                        {
                            @Override
                            public void onGetConfigFailed() {
                                _dialogConfigError.show();
                            }

                            @Override
                            public void onGetConfigSucceeded(final Response_Config response) {


                                if (response.getForced_update() == 1) {

                                    _dialogForceUpdate = makeCafeBazaarDialog(response.getUpdate_url());
                                    _dialogForceUpdate.show();
                                } else {
                                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                                    i.putExtra("daily_quote", response.getQuote());
                                    startActivity(i);
                                    finish();
                                }

                            }
                        }

                );
            }
        }, 1500);

    }

    AlertDialog makeCafeBazaarDialog(final String url) {
        AlertDialog _dialog = new AlertDialog.Builder(this)
                .setMessage("نسخه جدید هفت‌رنگ را دریافت نمایید")
                .setCancelable(false)
                .setPositiveButton("دریافت نسخه جدید", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse("http://cafebazaar.ir/app/?id=ir.haftrang.haftrang"));
                        startActivity(i);

                    }
                })
                .create();
        return _dialog;
    }

    AlertDialog makeErrorDialog() {
        AlertDialog _dialog = new AlertDialog.Builder(this)
                .setMessage("بروز خطا در دریافت اطلاعات از هفت‌رنگ")
                .setCancelable(false)
                .setPositiveButton("تلاش دوباره", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        getConfig();

                    }
                })
                .setNegativeButton("خروج", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })

                .create();
        return _dialog;
    }
}
