package ir.chichand.chichand.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import butterknife.BindView;
import butterknife.ButterKnife;
import ir.chichand.chichand.Models.Requests.Request_Inquiry;
import ir.chichand.chichand.Models.Responses.Response_Inquiry;
import ir.chichand.chichand.Models.Responses.Response_Inquiry_Data;
import ir.chichand.chichand.Models.Responses.Response_Inquiry_Data_Group;
import ir.chichand.chichand.NetworkServices.ApiCallbacks;
import ir.chichand.chichand.NetworkServices.ApiHandler;
import ir.chichand.chichand.R;
import ir.chichand.chichand.Tools.ScreenUtils;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CurrencyActivity extends AppCompatActivity {

    @BindView(R.id.parent)
    LinearLayout parent;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        ButterKnife.bind(this);
        Request_Inquiry request = new Request_Inquiry(1000, "");


        final ProgressDialog dialog = ProgressDialog.show(this, "",
                "در حال دریافت اطلاعات ...", true);


        ApiHandler.getInquiry(this, request, new ApiCallbacks.getInquiryInterface() {
            @Override
            public void getInquiryFailed() {
                dialog.dismiss();
                Toast.makeText(CurrencyActivity.this, "بروز خطا در ارتباط", Toast.LENGTH_LONG).show();
                finish();

            }

            @Override
            public void getInquirySucceeded(Response_Inquiry response) {

                dialog.dismiss();

                Map<String, List<Response_Inquiry_Data>> map = new HashMap<String, List<Response_Inquiry_Data>>();
                for (Response_Inquiry_Data data : response.getData()) {
                    String key = String.valueOf(data.getGroup());
                    if (map.get(key) == null) {
                        map.put(key, new ArrayList<Response_Inquiry_Data>());

                    }

                    map.get(key).add(data);
                }


                for (List<Response_Inquiry_Data> group : map.values()
                        ) {

                    View groupView = CurrencyActivity.this.getLayoutInflater().inflate(R.layout.activity_currency_groups, null);

                    LinearLayout linear = (LinearLayout) groupView.findViewById(R.id.parent);
                    TextView groupTitle = (TextView) groupView.findViewById(R.id.tv_activity_currency_groups_title);

                    int currentGroup = 0;
                    for (Response_Inquiry_Data item : group
                            ) {

                        View layout = CurrencyActivity.this.getLayoutInflater().inflate(R.layout.item_currency_group, null);

                        ((TextView) (layout.findViewById(R.id.tv_item_currency_group_price))).setText(item.getPrice());
                        ((TextView) (layout.findViewById(R.id.tv_item_currency_group_title))).setText(item.getName());

                        currentGroup = item.getGroup();

                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins((int) ScreenUtils.convertDpToPixel(5, CurrencyActivity.this),
                                (int) ScreenUtils.convertDpToPixel(5, CurrencyActivity.this),
                                (int) ScreenUtils.convertDpToPixel(5, CurrencyActivity.this),
                                (int) ScreenUtils.convertDpToPixel(5, CurrencyActivity.this));
                        linear.setLayoutParams(params);
                        linear.addView(layout);

                    }

                    for (Response_Inquiry_Data_Group groupSpec : response.getData_group()
                            ) {

                        if (groupSpec != null) {
                            if (groupSpec.getGroup() == currentGroup) {
                                groupTitle.setText(groupSpec.getGroup_title());
                            }
                        }
                    }

                    parent.addView(linear);

                }

            }
        });
    }
}
