package ir.chichand.chichand.AlarmManager;

/**
 * Created by bSherafati on 2/7/2018.
 */

import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v4.app.NotificationCompat;

import ir.chichand.chichand.Activity.SplashActivity;
import ir.chichand.chichand.Models.Requests.Request_Inquiry;
import ir.chichand.chichand.Models.Responses.Response_Inquiry;
import ir.chichand.chichand.Models.Responses.Response_Inquiry_Data;
import ir.chichand.chichand.NetworkServices.ApiCallbacks;
import ir.chichand.chichand.NetworkServices.ApiHandler;
import ir.chichand.chichand.R;
import ir.chichand.chichand.Tools.SharedPref;

import static ir.chichand.chichand.Tools.SharedPref.getCurrencyAmount;
import static ir.chichand.chichand.Tools.SharedPref.getCurrencyName;
import static ir.chichand.chichand.Tools.SharedPref.getCurrencyType;

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

    }

    private Runnable myTask = new Runnable() {
        public void run() {

            ApiHandler.getInquiry(context, new Request_Inquiry(1000, ""), new ApiCallbacks.getInquiryInterface() {
                @Override
                public void onGetInquiryFailed() {

                }

                @Override
                public void onGetInquirySucceeded(Response_Inquiry response) {

                    for (Response_Inquiry_Data data : response.getData()) {

                        if (data.getName().contains(getCurrencyName())) {

                            Intent intent = new Intent(context, SplashActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            PendingIntent pendingIntent = PendingIntent.getActivity(context, 1410,
                                    intent, PendingIntent.FLAG_ONE_SHOT);

                            NotificationCompat.Builder notificationBuilder = new
                                    NotificationCompat.Builder(context)
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle(getCurrencyName())
                                    .setContentText(data.getPrice().toString() + " ریال")
                                    .setAutoCancel(true)
                                    .setContentIntent(pendingIntent);

                            NotificationManager notificationManager =
                                    (NotificationManager)
                                            getSystemService(Context.NOTIFICATION_SERVICE);

                            if (getCurrencyType().equals("بیشتر از")) {

                                if (Long.valueOf(data.getPrice().replace(",", "")) > getCurrencyAmount())
                                    notificationManager.notify(1410, notificationBuilder.build());

                            } else if (getCurrencyType().equals("کمتر از")) {
                                if (Long.valueOf(data.getPrice().replace(",", "")) < getCurrencyAmount())

                                    notificationManager.notify(1410, notificationBuilder.build());

                            } else if (getCurrencyType().equals("برابر با")) {
                                if (Long.valueOf(data.getPrice().replace(",", "")) == getCurrencyAmount())

                                    notificationManager.notify(1402, notificationBuilder.build());

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
