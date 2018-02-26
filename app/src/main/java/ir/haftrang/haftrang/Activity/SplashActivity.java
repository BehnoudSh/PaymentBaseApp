package ir.haftrang.haftrang.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        setScreenUtils();
        SharedPref.getInstance().initSharedPref(getApplicationContext());


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

                            Intent i = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(i);
                          //  overridePendingTransition(R.anim.come_in, R.anim.go_out);
                            finish();

                        } else {
                            Intent i = new Intent(SplashActivity.this, SplashActivity.class);
                            startActivity(i);
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


    void getConfig() {





        ApiHandler.getConfig(this, new ApiCallbacks.getConfigInterface()

                {
                    @Override
                    public void onGetConfigFailed() {

                    }

                    @Override
                    public void onGetConfigSucceeded(final Response_Config response) {

//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {


                        Intent i = new Intent(SplashActivity.this, MainActivity.class);
                        i.putExtra("daily_quote", response.getQuote());
                        startActivity(i);
                     //   overridePendingTransition(R.anim.come_in, R.anim.go_out);
                        finish();


//                    }
//                }, SPLASH_TIME_OUT);
                    }
                }

        );
    }
}
