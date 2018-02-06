package ir.chichand.chichand.AlarmManager;

import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.Toast;

import ir.chichand.chichand.Models.Requests.Request_Inquiry;
import ir.chichand.chichand.Models.Responses.Response_Inquiry;
import ir.chichand.chichand.NetworkServices.ApiCallbacks;
import ir.chichand.chichand.NetworkServices.ApiHandler;

import static android.app.Service.START_STICKY;

/**
 * Created by tinabehnoud on 2/7/18.
 */

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

                    Toast.makeText(context, response.getData().get(0).getName() + " " + response.getData().get(0).getPrice(), Toast.LENGTH_SHORT).show();

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