package ir.zarjame.haftrang.AlarmManager;

/**
 * Created by bSherafati on 2/7/2018.
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import ir.zarjame.haftrang.Activity.SplashActivity;
import ir.zarjame.haftrang.Models.Requests.Request_SearchBuses;
import ir.zarjame.haftrang.Models.Responses.Response_Bus;
import ir.zarjame.haftrang.Models.Responses.Response_SearchBuses;
import ir.zarjame.haftrang.NetworkServices.ApiCallbacks;
import ir.zarjame.haftrang.NetworkServices.ApiHandler;
import ir.zarjame.haftrang.R;
import ir.zarjame.haftrang.Tools.PublicTools;
import ir.zarjame.haftrang.Tools.SharedPref;

import static ir.zarjame.haftrang.Tools.SharedPref.getBusAmount;
import static ir.zarjame.haftrang.Tools.SharedPref.getBusArrivalCode;
import static ir.zarjame.haftrang.Tools.SharedPref.getBusArrivalName;
import static ir.zarjame.haftrang.Tools.SharedPref.getBusDepartureCode;
import static ir.zarjame.haftrang.Tools.SharedPref.getBusDepartureDate;
import static ir.zarjame.haftrang.Tools.SharedPref.getBusDepartureName;
import static ir.zarjame.haftrang.Tools.SharedPref.getBusSourceDestination;

public class BusBackgroundService extends Service {

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

            if (getBusDepartureCode().equals("")
                    || getBusArrivalCode().equals("")
                    || getBusDepartureName().equals("")
                    || getBusArrivalName().equals("")
                    || getBusDepartureDate().equals("")
                    || getBusSourceDestination().equals("")
                    || getBusAmount() == 0)
                return;


            Request_SearchBuses request = new Request_SearchBuses(getBusDepartureCode(),
                    getBusArrivalCode(),
                    getBusDepartureName(),
                    getBusArrivalName(),
                    getBusDepartureDate());


            ApiHandler.searchBuses(context, request, new ApiCallbacks.searchBusesInterface() {
                @Override
                public void onSearchBusesFailed(String message) {

                }

                @Override
                public void onSearchBusesSucceeded(Response_SearchBuses response) {
                    int counter = 0;

                    for (Response_Bus bus : response.getItems()
                            ) {
                        if (bus.getPrice() < getBusAmount())
                            counter++;
                    }

                    if (counter != 0) {
                        Intent intent = new Intent(context, SplashActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1410,
                                intent, PendingIntent.FLAG_ONE_SHOT);

                        NotificationCompat.Builder notificationBuilder = new
                                NotificationCompat.Builder(context)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle(getBusSourceDestination())
                                .setContentText(counter + " سرویس اتوبوس با قیمت کمتر از " + PublicTools.getThousandSeperated(getBusAmount()) + " ریال یافت شد ")
                                .setAutoCancel(true)
                                .setContentIntent(pendingIntent);

                        NotificationManager notificationManager =
                                (NotificationManager)
                                        getSystemService(Context.NOTIFICATION_SERVICE);

                        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        notificationBuilder.setSound(alarmSound);

                        notificationManager.notify(1403, notificationBuilder.build());
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
