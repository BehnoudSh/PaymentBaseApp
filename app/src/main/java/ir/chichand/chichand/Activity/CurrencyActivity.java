package ir.chichand.chichand.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    @BindView(R.id.tv_actionbar_title)
    TextView tv_actionbar_title;

    @BindView(R.id.actionbarholder)
    RelativeLayout actionbarholder;
    @BindView(R.id.iv_actionbar_back)
    ImageView iv_actionbar_back;
    @BindView(R.id.parent)
    LinearLayout parent;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    void setupactionbar() {
        {
            tv_actionbar_title.setText("طلا و ارز");

            actionbarholder.setBackgroundColor(getResources().getColor(R.color.holder2));
            iv_actionbar_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        ButterKnife.bind(this);

        setupactionbar();


        Request_Inquiry request = new Request_Inquiry(1000, "");


        final ProgressDialog dialog = ProgressDialog.show(this, "",
                "در حال دریافت اطلاعات ...", true);


        ApiHandler.getInquiry(this, request, new ApiCallbacks.getInquiryInterface() {
            @Override
            public void onGetInquiryFailed() {
                dialog.dismiss();
                Toast.makeText(CurrencyActivity.this, "بروز خطا در ارتباط", Toast.LENGTH_LONG).show();
                finish();

            }

            @Override
            public void onGetInquirySucceeded(Response_Inquiry response) {

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
                    TextView groupUnit = (TextView) groupView.findViewById(R.id.tv_activity_currency_groups_unitName);

                    int currentGroup = 0;

                    for (Response_Inquiry_Data item : group) {

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
                                groupUnit.setText("واحد: " + groupSpec.getGroup_currency());
                            }
                        }
                    }

                    parent.addView(linear);

                }

            }
        });
    }
}
