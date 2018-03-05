package ir.zarjame.haftrang.Tools;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.PointF;
import android.util.DisplayMetrics;

/**
 * Created by Behnoud on 18/10/2016.
 */

public class ScreenUtils {

    public static DisplayMetrics Metrics;

    /// <summary>
    /// where X is WidthInDp and Y is HeightInDp
    /// </summary>
    public static PointF ScreenSizesInDP = new PointF();

    /// <summary>
    /// where X is WidthInPixel and Y is HeightInPixel
    /// </summary>
    public static PointF ScreenSizesInPixel = new PointF();


    public static int ConvertPixelsToDp(float pixelValue, Context context) {
        int dp = (int) ((pixelValue) / context.getResources().getDisplayMetrics().density);
        return dp;
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / 160);
        return px;
    }

    public static boolean isTablet(Context ctx) {
        return (ctx.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

}