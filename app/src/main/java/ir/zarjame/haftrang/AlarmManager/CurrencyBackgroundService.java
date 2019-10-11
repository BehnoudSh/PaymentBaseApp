package ir.zarjame.haftrang.AlarmManager;

/**
 * Created by bSherafati on 2/7/2018.
 */

import android.app.*;
import android.content.*;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.*;
import android.support.v4.app.NotificationCompat;

import ir.zarjame.haftrang.Activity.SplashActivity;
import ir.zarjame.haftrang.Models.Requests.Request_Inquiry;
import ir.zarjame.haftrang.Models.Responses.Response_Inquiry;
import ir.zarjame.haftrang.Models.Responses.Response_Inquiry_Data;
import ir.zarjame.haftrang.NetworkServices.ApiCallbacks;
import ir.zarjame.haftrang.NetworkServices.ApiHandler;
import ir.zarjame.haftrang.R;
import ir.zarjame.haftrang.Tools.SharedPref;

import static ir.zarjame.haftrang.Tools.SharedPref.getCurrencyAmount;
import static ir.zarjame.haftrang.Tools.SharedPref.getCurrencyName;
import static ir.zarjame.haftrang.Tools.SharedPref.getCurrencyType;

public class CurrencyBackgroundService extends Service {

    private boolean isRunning;
    private Context context;
    private Thread backgroundThread;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        this.context = this;
        this.isRunning = false;
        this.backgroundThread = new Thread(myTask);
        SharedPref.getInstance().initSharedPref(this.context);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(1, new Notification());
        }

    }

    private Runnable myTask = new Runnable() {
        public void run() {


            if (getCurrencyType().equals("")
                    || getCurrencyAmount() == 0
                    || getCurrencyName().equals(""))
                return;


            ApiHandler.getInquiry(context, new Request_Inquiry(1000, ""), new ApiCallbacks.getInquiryInterface() {
                @Override
                public void onGetInquiryFailed() {

                }

                @Override
                public void onGetInquirySucceeded(Response_Inquiry response) {
                    if (response != null && response.getData() != null && response.getData().size() > 0) {
                        for (Response_Inquiry_Data data : response.getData()) {

                            if (data.getName().contains(getCurrencyName())) {

                                Intent intent = new Intent(context, SplashActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                PendingIntent pendingIntent = PendingIntent.getActivity(context, 1410,
                                        intent, PendingIntent.FLAG_ONE_SHOT);

                                NotificationCompat.Builder notificationBuilder;
                                NotificationManager notificationManager = null;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                                    // Sets an ID for the notification, so it can be updated.
                                    int notifyID = 1;
                                    String CHANNEL_ID = "my_channel_01";// The id of the channel.
                                    CharSequence name = getString(R.string.app_name);// The user-visible name of the channel.
                                    int importance = NotificationManager.IMPORTANCE_HIGH;
                                    NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                                    notificationBuilder = new
                                            NotificationCompat.Builder(context)
                                            .setSmallIcon(R.mipmap.ic_launcher)
                                            .setContentTitle(getCurrencyName())
                                            .setContentText(data.getPrice().toString() + " ریال")
                                            .setAutoCancel(true)
                                            .setChannelId(CHANNEL_ID)
                                            .setContentIntent(pendingIntent);

                                    notificationManager =
                                            (NotificationManager)
                                                    getSystemService(Context.NOTIFICATION_SERVICE);


                                    notificationManager.createNotificationChannel(mChannel);
                                } else {
                                    notificationBuilder = new
                                            NotificationCompat.Builder(context)
                                            .setSmallIcon(R.mipmap.ic_launcher)
                                            .setContentTitle(getCurrencyName())
                                            .setContentText(data.getPrice().toString() + " ریال")
                                            .setAutoCancel(true)
                                            .setContentIntent(pendingIntent);

                                    notificationManager =
                                            (NotificationManager)
                                                    getSystemService(Context.NOTIFICATION_SERVICE);

                                }


                                Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                notificationBuilder.setSound(alarmSound);


                                if (getCurrencyType().equals("بیشتر از")) {

                                    if (Long.valueOf(data.getPrice().replace(",", "")) > getCurrencyAmount())
                                        notificationManager.notify(1410, notificationBuilder.build());

                                } else if (getCurrencyType().equals("کمتر از")) {
                                    if (Long.valueOf(data.getPrice().replace(",", "")) < getCurrencyAmount())

                                        notificationManager.notify(1410, notificationBuilder.build());

                                }
                            }
                        }
                    }
                }
            });

            stopSelf();

        }
    };

    @Override
    public void onDestroy() {
        this.isRunning = false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!this.isRunning) {
            this.isRunning = true;
            this.backgroundThread.start();
        }
        return START_STICKY;
    }

}
