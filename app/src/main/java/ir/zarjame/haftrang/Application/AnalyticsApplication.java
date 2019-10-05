package ir.zarjame.haftrang.Application;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import ir.zarjame.haftrang.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class AnalyticsApplication extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/IRANYekanRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}