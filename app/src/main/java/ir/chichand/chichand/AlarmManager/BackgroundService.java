package ir.chichand.chichand.AlarmManager;

/**
 * Created by bSherafati on 2/7/2018.
 */

import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import ir.chichand.chichand.Activity.SplashActivity;
import ir.chichand.chichand.Models.Requests.Request_Inquiry;
import ir.chichand.chichand.Models.Responses.Response_Inquiry;
import ir.chichand.chichand.Models.Responses.Response_Inquiry_Data;
import ir.chichand.chichand.NetworkServices.ApiCallbacks;
import ir.chichand.chichand.NetworkServices.ApiHandler;
import ir.chichand.chichand.R;

public class BackgroundService extends Service {

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
    }

    private Runnable myTask = new Runnable() {
        public void run() {

            ApiHandler.getInquiry(context, new Request_Inquiry(1000, ""), new ApiCallbacks.getInquiryInterface() {
                @Override
                public void onGetInquiryFailed() {

                }

                @Override
                public void onGetInquirySucceeded(Response_Inquiry response) {

                    for (Response_Inquiry_Data data : response.getData()
                            ) {

                        if (data.getName().contains("دلار امریکا")) {
                            Intent intent = new Intent(context, SplashActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            PendingIntent pendingIntent = PendingIntent.getActivity(context, 1410,
                                    intent, PendingIntent.FLAG_ONE_SHOT);

                            NotificationCompat.Builder notificationBuilder = new
                                    NotificationCompat.Builder(context)
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle("قیمت دلار امریکا")
                                    .setContentText(data.getPrice().toString() + " ریال")
                                    .setAutoCancel(true)
                                    .setContentIntent(pendingIntent);

                            NotificationManager notificationManager =
                                    (NotificationManager)
                                            getSystemService(Context.NOTIFICATION_SERVICE);

                            notificationManager.notify(1410, notificationBuilder.build());

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
