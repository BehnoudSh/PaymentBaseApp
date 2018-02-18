package ir.chichand.chichand.AlarmManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/**
 * Created by tinabehnoud on 2/19/18.
 */

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //  execute when Boot Completed

        Intent alarm = new Intent(context, CurrencyAlarmReceiver.class);
        boolean alarmRunning = (PendingIntent.getBroadcast(context, 0, alarm, PendingIntent.FLAG_NO_CREATE) != null);
        if (alarmRunning == false) {
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarm, 0);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(),   5000, pendingIntent);
        }


    }
}
