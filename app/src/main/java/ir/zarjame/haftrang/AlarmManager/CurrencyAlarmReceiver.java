package ir.zarjame.haftrang.AlarmManager;

/**
 * Created by bSherafati on 2/7/2018.
 */

import android.content.*;

public class CurrencyAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent background = new Intent(context, CurrencyBackgroundService.class);
        context.startService(background);
    }

}