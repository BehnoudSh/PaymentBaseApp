package ir.zarjame.haftrang.Activity;

import android.app.ProgressDialog;
import android.content.Context;
 import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.zarjame.haftrang.Adapters.CarCompanyAdapter;
import ir.zarjame.haftrang.Dialog.CarListDialog;
import ir.zarjame.haftrang.Models.Responses.Response_AllCars;
import ir.zarjame.haftrang.Models.Responses.Response_CarList;
import ir.zarjame.haftrang.NetworkServices.ApiCallbacks;
import ir.zarjame.haftrang.NetworkServices.ApiHandler;
import ir.zarjame.haftrang.R;
import ir.zarjame.haftrang.Tools.PublicTools;

public class CarPricesActivity extends AppCompatActivity {

    @BindView(R.id.iv_actionbar_back)
    ImageView iv_actionbar_back;

    @BindView(R.id.tv_actionbar_title)
    TextView tv_actionbar_title;

    @BindView(R.id.toolbaricon)
    ImageView toolbaricon;

    @BindView(R.id.toolbar)
    Toolbar
            toolbar;


    @BindView(R.id.rv_activity_car_prices_companyList)
    RecyclerView
            carCompanyList;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_prices);

        ButterKnife.bind(this);
        int color = getIntent().getIntExtra("bg_color", getResources().getColor(R.color.colorPrimary));

        String title = getIntent().getStringExtra("toolbar_title");
        setupactionbar(color, title);


        final ProgressDialog dialog = PublicTools.ProgressDialogInstance(this, "در حال دریافت آخرین قیمت خودرو");

        dialog.show();


        ApiHandler.getCarPrices(this, new ApiCallbacks.getCarsInterface() {
            @Override
            public void onGetCarPricesFailed(String message) {
                dialog.dismiss();
                Toast.makeText(CarPricesActivity.this, "بروز خطا در ارتباط، دوباره تلاش کنید", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onGetCarPricesSucceeded(Response_AllCars response) {
                dialog.dismiss();


                StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2,
                        StaggeredGridLayoutManager.VERTICAL);
                CarCompanyAdapter carCompanyAdapter = new CarCompanyAdapter(response.getCompany_list(), CarPricesActivity.this, new CarCompanyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Response_CarList item, int position) {

                        CarListDialog dialog = new CarListDialog(CarPricesActivity.this, item.getCar_list(), item.getCar_company());
                        dialog.show();
                    }
                });

                carCompanyList.setLayoutManager(mLayoutManager);
                carCompanyList.setItemAnimator(new DefaultItemAnimator());
                carCompanyList.setAdapter(carCompanyAdapter);

                carCompanyList.setItemViewCacheSize(200);
                carCompanyList.setDrawingCacheEnabled(true);
                carCompanyList.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);

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
                    finish();
                }
            });
        }
    }
}
