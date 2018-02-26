package ir.chichand.chichand.Tools;

import android.content.Context;
import android.graphics.Typeface;
import android.util.LruCache;

/**
 * Created by bSherafati on 2/26/2018.
 */


public class TypeFaceUtil {
    private LruCache<String, Typeface> typefaceLruCache;
    private Context context;


    public TypeFaceUtil(Context context) {
        typefaceLruCache = new LruCache<>(2);
        this.context = context;
    }

    public Typeface getTypeFaceForPath(Context context, String fontAssetPath) {
        if (typefaceLruCache.get(fontAssetPath) != null) {
            return typefaceLruCache.get(fontAssetPath);
        }
        final Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontAssetPath);
        if (typeface != null) {
            typefaceLruCache.put(fontAssetPath, typeface);
        }
        return typeface;
    }



    public Typeface getSansFont() {
        return getTypeFaceForPath(context, "fonts/IRANYekanRegular.ttf");
    }

}