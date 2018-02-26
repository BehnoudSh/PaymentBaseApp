package ir.haftrang.haftrang.Application;

import android.app.Application;

import ir.haftrang.haftrang.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class AnalyticsApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/IRANYekanRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}