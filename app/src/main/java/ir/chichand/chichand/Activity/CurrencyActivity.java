package ir.chichand.chichand.Activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.chichand.chichand.Models.Reqeusts.Request_Inquiry;
import ir.chichand.chichand.Models.Responses.Response_Inquiry;
import ir.chichand.chichand.Models.Responses.Response_Inquiry_Data;
import ir.chichand.chichand.NetworkServices.ApiCallbacks;
import ir.chichand.chichand.NetworkServices.ApiHandler;
import ir.chichand.chichand.R;

public class CurrencyActivity extends AppCompatActivity {

    @BindView(R.id.parent)
    LinearLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        ButterKnife.bind(this);
        Request_Inquiry request = new Request_Inquiry(1000, "");

        ApiHandler.getInquiry(this, request, new ApiCallbacks.getInquiryInterface() {
            @Override
            public void getInquiryFailed() {
                Toast.makeText(CurrencyActivity.this, "failed", Toast.LENGTH_LONG).show();


            }

            @Override
            public void getInquirySucceeded(Response_Inquiry response) {

                for (Response_Inquiry_Data data : response.getData()
                        ) {

                    TextView tv = new TextView(CurrencyActivity.this);
                    tv.setText(data.getName() + ": " + data.getPrice());
                    tv.setTextColor(Color.BLACK);
                    parent.addView(tv);

                }


            }
        });
    }
}
