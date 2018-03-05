package ir.zarjame.haftrang.AlarmManager;

/**
 * Created by bSherafati on 2/7/2018.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class FlightAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent background = new Intent(context, FlightBackgroundService.class);
        context.startService(background);
    }

}