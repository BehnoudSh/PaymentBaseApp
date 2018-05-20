package ir.zarjame.haftrang.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.zarjame.haftrang.Models.Responses.Response_Internet_FinalPackage;
import ir.zarjame.haftrang.Models.Responses.Response_initializedata;
import ir.zarjame.haftrang.NetworkServices.ApiCallbacks;
import ir.zarjame.haftrang.NetworkServices.ApiHandler;
import ir.zarjame.haftrang.R;
import ir.zarjame.haftrang.Tools.PublicTools;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class InternetActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar
            toolbar;

    @BindView(R.id.iv_actionbar_back)
    ImageView iv_actionbar_back;

    @BindView(R.id.tv_actionbar_title)
    TextView tv_actionbar_title;

    @BindView(R.id.toolbaricon)
    ImageView toolbaricon;

    Response_initializedata allPackages;

    @BindView(R.id.card_view_irancell_roozane)
    CardView roozane;
    @BindView(R.id.card_view_irancell_haftegi)
    CardView haftegi;
    @BindView(R.id.card_view_irancell_mahane)
    CardView mahane;
    @BindView(R.id.card_view_irancell_shegeftangiz)
    CardView shgeftangiz;
    @BindView(R.id.card_view_irancell_saati)
    CardView saati;
    @BindView(R.id.card_view_irancell_tdlte)
    CardView tdlte;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);
        ButterKnife.bind(this);

        int color = getIntent().getIntExtra("bg_color", getResources().getColor(R.color.colorPrimary));

        String title = getIntent().getStringExtra("toolbar_title");

        setupactionbar(color, title);

        getInternetAvailablePackages();


        roozane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Response_Internet_FinalPackage> list = allPackages.getProducts().getInternetPackage().getMtn().getRoozane();
            }
        });
        haftegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Response_Internet_FinalPackage> list = allPackages.getProducts().getInternetPackage().getMtn().getHaftegi();

            }
        });
        mahane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Response_Internet_FinalPackage> list = allPackages.getProducts().getInternetPackage().getMtn().getMahane();

            }
        });
        shgeftangiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Response_Internet_FinalPackage> list = allPackages.getProducts().getInternetPackage().getMtn().getShegeftangiz();

            }
        });
        saati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Response_Internet_FinalPackage> list = allPackages.getProducts().getInternetPackage().getMtn().getSaati();

            }
        });
        tdlte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Response_Internet_FinalPackage> list = allPackages.getProducts().getInternetPackage().getMtn().getTdlte();

            }
        });


    }

    private void getInternetAvailablePackages() {

        final ProgressDialog dialog = PublicTools.ProgressDialogInstance(this, "در حال دریافت بسته‌های اینترنت ...");
        dialog.show();

        ApiHandler.initializeData(this, new ApiCallbacks.initializeDataInterface() {
            @Override
            public void oninitializeDataFailed(String message) {
                dialog.dismiss();
                Toast.makeText(InternetActivity.this, message, Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void oninitializeDataSucceeded(Response_initializedata response) {
                dialog.dismiss();

                allPackages = response;

            }
        });


    }

    void setupactionbar(int bg_color, String toolbar_title) {
        {
            tv_actionbar_title.setText(toolbar_title);
            toolbar.setBackgroundColor(bg_color);
            iv_actionbar_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                        getSupportFragmentManager().popBackStack();
                    else
                        finish();

                }
            });
        }
    }
}
