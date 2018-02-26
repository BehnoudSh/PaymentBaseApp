package ir.haftrang.haftrang.Tools;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.DecimalFormat;


public class PublicTools {


    public static void hideKeyboard(Activity context) {

        View view = context.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }


    public static String getThousandSeperated(String input) {
        return getDecimalFormat().format(removeThousandSeparated(input));
    }

    public static String getThousandSeperated(double input) {
        return getDecimalFormat().format(input);
    }

    public static DecimalFormat getDecimalFormat() {
        return new DecimalFormat("#,###");
    }

    public static long removeThousandSeparated(String value) {
        if (TextUtils.isEmpty(value))
            return 0;
        char separator = getDecimalFormat().getDecimalFormatSymbols().getGroupingSeparator();
        value = removePersianNumbers(value.replace(String.valueOf(separator), ""));
        return Long.parseLong(value);
    }

    public static String removePersianNumbers(String value) {
        char[] charMap = new char[]{'۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹'};
        for (int i = 0; i < charMap.length; i++) {
            value = value.replace(charMap[i], String.valueOf(i).charAt(0));
        }
        return value;
    }

    public static boolean checkNetworkStatus(Context activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean internetStatus = false;
        if (connectivityManager != null) {
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null) {
                for (int i = 0; i < networkInfo.length; i++) {
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        internetStatus = true;
                    }
                }
            }
        }

        return internetStatus;
    }

    public static String fa2en(String input) {
        if (input == null) {
            return null;
        }
        return input
                .replace('\u06F0', '0')
                .replace('\u06F1', '1')
                .replace('\u06F2', '2')
                .replace('\u06F3', '3')
                .replace('\u06F4', '4')
                .replace('\u06F5', '5')
                .replace('\u06F6', '6')
                .replace('\u06F7', '7')
                .replace('\u06F8', '8')
                .replace('\u06F9', '9')

                .replace('\u0660', '0')
                .replace('\u0661', '1')
                .replace('\u0662', '2')
                .replace('\u0663', '3')
                .replace('\u0664', '4')
                .replace('\u0665', '5')
                .replace('\u0666', '6')
                .replace('\u0667', '7')
                .replace('\u0668', '8')
                .replace('\u0669', '9')
                ;
    }

    public static String en2fa(String input) {
        if (input == null) {
            return null;
        }
        return input
                .replace('0', '\u06F0')
                .replace('1', '\u06F1')
                .replace('2', '\u06F2')
                .replace('3', '\u06F3')
                .replace('4', '\u06F4')
                .replace('5', '\u06F5')
                .replace('6', '\u06F6')
                .replace('7', '\u06F7')
                .replace('8', '\u06F8')
                .replace('9', '\u06F9')

                .replace('\u0660', '\u06F0')
                .replace('\u0661', '\u06F1')
                .replace('\u0662', '\u06F2')
                .replace('\u0663', '\u06F3')
                .replace('\u0664', '\u06F4')
                .replace('\u0665', '\u06F5')
                .replace('\u0666', '\u06F6')
                .replace('\u0667', '\u06F7')
                .replace('\u0668', '\u06F8')
                .replace('\u0669', '\u06F9')
                ;
    }

    public static ProgressDialog ProgressDialogInstance(Context context, String message) {
        final ProgressDialog dialog = new ProgressDialog(context, ProgressDialog.THEME_HOLO_LIGHT);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(message);
        return dialog;
    }
}
