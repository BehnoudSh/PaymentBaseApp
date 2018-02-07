package ir.chichand.chichand.AlarmManager;

/**
 * Created by bSherafati on 2/7/2018.
 */

import android.app.*;
import android.content.*;
import android.os.*;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent background = new Intent(context, BackgroundService.class);
        context.startService(background);
    }

}