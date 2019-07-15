package ir.zarjame.haftrang.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
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
import ir.zarjame.haftrang.Fragments.InternetPackageFragment_MCI;
import ir.zarjame.haftrang.Fragments.InternetPackageFragment_MTN;
import ir.zarjame.haftrang.Fragments.InternetPackageFragment_RTL;
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
    CardView mtn_roozane;
    @BindView(R.id.card_view_irancell_haftegi)
    CardView mtn_haftegi;
    @BindView(R.id.card_view_irancell_mahane)
    CardView mtn_mahane;
    @BindView(R.id.card_view_irancell_shegeftangiz)
    CardView mtn_shgeftangiz;
    @BindView(R.id.card_view_irancell_saati)
    CardView mtn_saati;
    @BindView(R.id.card_view_irancell_tdlte)
    CardView mtn_tdlte;

    @BindView(R.id.card_view_mci_haftegi)
    CardView mci_haftegi;

    @BindView(R.id.card_view_mci_mahane)
    CardView mci_mahane;

    @BindView(R.id.card_view_mci_roozane)
    CardView mci_roozane;

    @BindView(R.id.card_view_rtl_haftegi)
    CardView rtl_haftegi;

    @BindView(R.id.card_view_rtl_mahane)
    CardView rtl_mahane;

    @BindView(R.id.card_view_rtl_roozane)
    CardView rtl_roozane;

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

        mtn_roozane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Response_Internet_FinalPackage> list = allPackages.getProducts().getInternetPackage().getMtn().getRoozane();
                addFragment_mtn(list);
            }
        });
        mtn_haftegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Response_Internet_FinalPackage> list = allPackages.getProducts().getInternetPackage().getMtn().getHaftegi();
                addFragment_mtn(list);
            }
        });
        mtn_mahane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Response_Internet_FinalPackage> list = allPackages.getProducts().getInternetPackage().getMtn().getMahane();
                addFragment_mtn(list);
            }
        });
        mtn_shgeftangiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Response_Internet_FinalPackage> list = allPackages.getProducts().getInternetPackage().getMtn().getShegeftangiz();
                addFragment_mtn(list);
            }
        });
        mtn_saati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Response_Internet_FinalPackage> list = allPackages.getProducts().getInternetPackage().getMtn().getSaati();
                addFragment_mtn(list);
            }
        });
        mtn_tdlte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Response_Internet_FinalPackage> list = allPackages.getProducts().getInternetPackage().getMtn().getTdlte();
                addFragment_mtn(list);
            }
        });


        mci_haftegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Response_Internet_FinalPackage> list = allPackages.getProducts().getInternetPackage().getMci().getHaftegi();
                addFragment_mci(list);
            }
        });
        mci_roozane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Response_Internet_FinalPackage> list = allPackages.getProducts().getInternetPackage().getMci().getRoozane();
                addFragment_mci(list);
            }
        });
        mci_mahane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Response_Internet_FinalPackage> list = allPackages.getProducts().getInternetPackage().getMci().getMahane();
                addFragment_mci(list);
            }
        });


        rtl_haftegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Response_Internet_FinalPackage> list = allPackages.getProducts().getInternetPackage().getRtl().getHaftegi();
                addFragment_rtl(list);
            }
        });
        rtl_mahane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Response_Internet_FinalPackage> list = allPackages.getProducts().getInternetPackage().getRtl().getMahane();
                addFragment_rtl(list);
            }
        });
        rtl_roozane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Response_Internet_FinalPackage> list = allPackages.getProducts().getInternetPackage().getRtl().getRoozane();
                addFragment_rtl(list);
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
            toolbar.setBackgroundColor(getResources().getColor(R.color.transparent_black_percent_60));

//            toolbar.setBackgroundColor(bg_color);
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

    public void addFragment_mtn(List<Response_Internet_FinalPackage> internetpackages) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.fragmentholder, InternetPackageFragment_MTN.newInstance(internetpackages));

        fragmentTransaction.addToBackStack("internetcategoriesfragment");

        fragmentTransaction.commit();
    }


    public void addFragment_mci(List<Response_Internet_FinalPackage> internetpackages) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.fragmentholder, InternetPackageFragment_MCI.newInstance(internetpackages));

        fragmentTransaction.addToBackStack("internetcategoriesfragment");

        fragmentTransaction.commit();
    }

    public void addFragment_rtl(List<Response_Internet_FinalPackage> internetpackages) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.fragmentholder, InternetPackageFragment_RTL.newInstance(internetpackages));

        fragmentTransaction.addToBackStack("internetcategoriesfragment");

        fragmentTransaction.commit();
    }

}
