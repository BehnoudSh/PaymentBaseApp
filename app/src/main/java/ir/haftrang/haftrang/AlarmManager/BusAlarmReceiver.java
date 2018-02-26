package ir.haftrang.haftrang.AlarmManager;

/**
 * Created by bSherafati on 2/7/2018.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BusAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent background = new Intent(context, BusBackgroundService.class);
        context.startService(background);
    }

}