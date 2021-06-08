package ir.zarjame.haftrang.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.zarjame.haftrang.Dialog.CurrencyAlarmDialog;
import ir.zarjame.haftrang.Models.Requests.Request_Inquiry;
import ir.zarjame.haftrang.Models.Responses.Response_Inquiry;
import ir.zarjame.haftrang.Models.Responses.Response_Inquiry_Data;
import ir.zarjame.haftrang.Models.Responses.Response_Inquiry_Data_Group;
import ir.zarjame.haftrang.NetworkServices.ApiCallbacks;
import ir.zarjame.haftrang.NetworkServices.ApiHandler;
import ir.zarjame.haftrang.R;
import ir.zarjame.haftrang.Tools.PublicTools;
import ir.zarjame.haftrang.Tools.ScreenUtils;


public class CurrencyActivity extends AppCompatActivity {

    @BindView(R.id.parent)
    LinearLayout parent;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.iv_actionbar_back)
    ImageView iv_actionbar_back;

    @BindView(R.id.tv_actionbar_title)
    TextView tv_actionbar_title;

    @BindView(R.id.toolbaricon)
    ImageView toolbaricon;

    @BindView(R.id.toolbar)
    Toolbar
            toolbar;
    @BindView(R.id.datelastupdated)
    TextView tv_datelastupdated;

    @BindView(R.id.rl_activity_currency_bottomHelp)
    RelativeLayout rl_bottomHelp;

    @BindView(R.id.tv_activity_currency_help)
    TextView tv_help;

    @BindView(R.id.iv_activity_currency_closeHelp)
    ImageView iv_closeHelp;


    List<Response_Inquiry_Data> currencyList;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    void setupactionbar(int bg_color, String toolbar_title) {
        {
            tv_actionbar_title.setText(toolbar_title);
            toolbar.setBackgroundColor(bg_color);
            iv_actionbar_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CurrencyActivity.this.finish();
                }
            });
        }
    }

//    void showHelp(Activity activity, View view, String title, String description) {
//
//        TypeFaceUtil typeface = new TypeFaceUtil(activity);
//
//        TapTargetView.showFor(activity,
//                TapTarget.forView(view, title, description)
//                        .outerCircleColor(R.color.transparent_black_hex_1)      // Specify a color for the outer circle
//                        .outerCircleAlpha(0.85f)            // Specify the alpha amount for the outer circle
//                        .targetCircleColor(R.color.air_force_blue)   // Specify a color for the target circle
//                        .titleTextSize(20)                  // Specify the size (in sp) of the title text
//                        .titleTextColor(R.color.red_error)      // Specify the color of the title text
//                        .descriptionTextSize(12)            // Specify the size (in sp) of the description text
//                        .textColor(R.color.white)            // Specify a color for both the title and description text
//                        .textTypeface(typeface.getSansFont())  // Specify a typeface for the text
//                        .dimColor(R.color.black)            // If set, will dim behind the view with 30% opacity of the given color
//                        .drawShadow(true)                   // Whether to draw a drop shadow or not
//                        .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
//                        .transparentTarget(true)           // Specify whether the target is transparent (displays the content underneath)
//                        .targetRadius(60),                  // Specify the target radius (in dp)
//                new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
//                    @Override
//                    public void onTargetClick(TapTargetView view) {
//                        super.onTargetClick(view);      // This call is optional
//                        setHelpState();
//                    }
//                });
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_currency);

        ButterKnife.bind(this);

        int color = getIntent().getIntExtra("bg_color", getResources().getColor(R.color.colorPrimary));

        String title = getIntent().getStringExtra("toolbar_title");

        setupactionbar(color, title);

        Request_Inquiry request = new Request_Inquiry(1000, "");

        final ProgressDialog dialog = PublicTools.ProgressDialogInstance(this, "در حال دریافت آخرین نرخ طلا و ارز");

        dialog.show();

        ApiHandler.getInquiry(this, request, new ApiCallbacks.getInquiryInterface() {
            @Override
            public void onGetInquiryFailed() {
                dialog.dismiss();
                Toast.makeText(CurrencyActivity.this, "بروز خطا در ارتباط، دوباره تلاش کنید", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onGetInquirySucceeded(final Response_Inquiry response) {

                currencyList = response.getData();

                dialog.dismiss();

                tv_datelastupdated.setText("تاریخ آخرین به‌روزرسانی: " + response.getData_last_update());

                Map<String, List<Response_Inquiry_Data>> map = new HashMap<String, List<Response_Inquiry_Data>>();
                for (Response_Inquiry_Data data : response.getData()) {
                    String key = String.valueOf(data.getGroup());
                    if (map.get(key) == null) {
                        map.put(key, new ArrayList<Response_Inquiry_Data>());

                    }

                    if (data.getPrice() != null && !data.getPrice().equals("0"))
                        map.get(key).add(data);
                }

                for (final List<Response_Inquiry_Data> group : map.values()) {

                    if (group.size() > 0) {
                        View groupView = CurrencyActivity.this.getLayoutInflater().inflate(R.layout.activity_currency_groups, null);

                        final LinearLayout linear = (LinearLayout) groupView.findViewById(R.id.parent);
                        final TextView groupTitle = (TextView) groupView.findViewById(R.id.tv_activity_currency_groups_title);
                        final TextView groupUnit = (TextView) groupView.findViewById(R.id.tv_activity_currency_groups_unitName);
                        ImageView iv_share = (ImageView) groupView.findViewById(R.id.sharecurrency);
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


                        iv_share.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                shareCurrencyData(
                                        groupTitle.getText().toString(),
                                        groupUnit.getText().toString(),
                                        group);
                            }
                        });

                        parent.addView(linear);
                    }


                }


                rl_bottomHelp.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideInLeft)
                        .duration(2000)
                        .playOn(rl_bottomHelp);
                tv_help.setSelected(true);
                iv_closeHelp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        YoYo.with(Techniques.SlideOutLeft)
                                .duration(1000)
                                .playOn(rl_bottomHelp);
                        rl_bottomHelp.setVisibility(View.GONE);

                    }
                });

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrencyAlarmDialog alarmDialog = new CurrencyAlarmDialog(CurrencyActivity.this, CurrencyActivity.this, currencyList);
                alarmDialog.show();
            }
        });

    }


    void shareCurrencyData(String title, String unit, List<Response_Inquiry_Data> data) {

        String shareBody = "";

        shareBody += "* " + title + " *" + "\n";
        shareBody += unit + "\n";
        for (Response_Inquiry_Data item : data) {

            shareBody += item.getName() + ": " + item.getPrice();

            shareBody += "\n";

        }
        shareBody += "\n";
        shareBody += "هفت‌رنگ دنیایی رو به رشد از سرویس‌های مورد نیاز هر ایرانی";
        shareBody += "\n";


        final String appPackageName = getPackageName();
//        try {
//            shareBody += "market://details?id=" + appPackageName;
//        } catch (android.content.ActivityNotFoundException anfe) {

        shareBody += "https://play.google.com/store/apps/details?id=" + appPackageName;
        // }

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "اشتراک‌گذاری اطلاعات با"));

    }
}
