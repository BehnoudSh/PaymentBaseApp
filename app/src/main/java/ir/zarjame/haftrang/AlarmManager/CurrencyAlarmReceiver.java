package ir.zarjame.haftrang.AlarmManager;

/**
 * Created by bSherafati on 2/7/2018.
 */

import android.content.*;
import android.os.Build;

public class CurrencyAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        Intent background = new Intent(context, CurrencyBackgroundService.class);
//        context.startService(background);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(new Intent(context, CurrencyBackgroundService.class));
        } else {
            context.startService(new Intent(context, CurrencyBackgroundService.class));
        }
    }

}