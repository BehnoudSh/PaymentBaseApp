package ir.zarjame.haftrang.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import butterknife.ButterKnife;
import ir.zarjame.haftrang.BuildConfig;
import ir.zarjame.haftrang.Models.Responses.Response_Config;
import ir.zarjame.haftrang.NetworkServices.ApiCallbacks;
import ir.zarjame.haftrang.NetworkServices.ApiHandler;
import ir.zarjame.haftrang.R;
import ir.zarjame.haftrang.Tools.PublicTools;
import ir.zarjame.haftrang.Tools.ScreenUtils;
import ir.zarjame.haftrang.Tools.SharedPref;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends AppCompatActivity {


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
                ApiHandler.getConfig(SplashActivity.this, new ApiCallbacks.getConfigInterface() {
                            @Override
                            public void onGetConfigFailed() {
                                _dialogConfigError.show();
                            }

                            @Override
                            public void onGetConfigSucceeded(final Response_Config response) {

                                PublicTools.bill_url = response.getBill_url();
                                PublicTools.charge_url = response.getCharge_url();
                                PublicTools.internet_url = response.getInternet_url();

                                //force update
                                if (response.getForced_update() == 1 && BuildConfig.VERSION_CODE < response.getVersion_code()) {
                                    _dialogForceUpdate = makeCafeBazaarDialog(response.getUpdate_url(), false, response);
                                    _dialogForceUpdate.show();
                                }

                                //update ekhtiari
                                else if (response.getForced_update() == 0 && BuildConfig.VERSION_CODE < response.getVersion_code()) {
                                    _dialogForceUpdate = makeCafeBazaarDialog(response.getUpdate_url(), true, response);
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

    //https://play.google.com/store/apps/details?id=ir.zarjame.haftrang&hl=en
    //"http://cafebazaar.ir/app/?id=ir.zarjame.haftrang"
    AlertDialog makeCafeBazaarDialog(final String url, final boolean isForceUpdate, final Response_Config response) {

        String message = "";
        if (!TextUtils.isEmpty(response.getChangelog())) {
            message = "نسخه جدید هفت‌رنگ را دریافت نمایید" + "\n \n" + "تغییرات این نسخه:" + "\n" + response.getChangelog();
        } else {
            message = "نسخه جدید هفت‌رنگ را دریافت نمایید";

        }

        AlertDialog _dialog = new AlertDialog.Builder(this)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("نسخه جدید هفت‌رنگ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Intent i = new Intent(Intent.ACTION_VIEW);
//                        if (!TextUtils.isEmpty(response.getUpdate_url()))
//                            i.setData(Uri.parse(url));
//                        else
//                            i.setData(Uri.parse("https://play.google.com/store/apps/details?id=ir.zarjame.haftrang&hl=en"));
//                        startActivity(i);


                        final String appPackageName = getPackageName();
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                        }


                    }
                })
                .setNegativeButton("فعلا بیخیال", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (isForceUpdate) {
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            intent.putExtra("daily_quote", response.getQuote());
                            startActivity(intent);
                            finish();
                        } else {
                            finish();
                        }
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


                        if (PublicTools.checkNetworkStatus(SplashActivity.this)) {

                            getConfig();

                        } else {

                            makeOfflineDialog();
                            _dialogOffline.show();

                        }

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
