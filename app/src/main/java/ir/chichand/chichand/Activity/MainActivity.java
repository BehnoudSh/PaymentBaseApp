package ir.chichand.chichand.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.chichand.chichand.Model.Response_Categories;
import ir.chichand.chichand.NetworkServices.ApiCallbacks;
import ir.chichand.chichand.NetworkServices.ApiHandler;
import ir.chichand.chichand.R;
import ir.chichand.chichand.Tools.ScreenUtils;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rl_activity_main_adHolder)
    RelativeLayout rl_adHolder;

    @BindView(R.id.cat1)
    ImageView cat1;

    @BindView(R.id.cat2)
    ImageView cat2;

    @BindView(R.id.cat1text)
    TextView cat1text;

    @BindView(R.id.cat2text)
    TextView cat2text;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setInitialSizes();
        getCategories();


    }


    void setInitialSizes() {
        int hieght = (int) ((9 * ScreenUtils.ScreenSizesInPixel.x) / 16);
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams((int) ScreenUtils.ScreenSizesInPixel.x, (int) hieght);
        rl_adHolder.setLayoutParams(layoutParams);
    }

    void getCategories() {
        final List<Response_Categories> temp = new ArrayList<>();

        ApiHandler.getAllCategories(this, new ApiCallbacks.getCategoriesInterface() {
            @Override
            public void getAllCategoriesFailed() {

            }

            @Override
            public void getAllCategoriesSucceeded(List<Response_Categories> response) {

                for (Response_Categories resp : response
                        ) {
                    if (resp.getCat_level() != null)
                        if (resp.getCat_level().equals("0")) {
                            temp.add(resp);
                        }

                }


                RequestOptions requestOptions = new RequestOptions();
                requestOptions.placeholder(R.mipmap.ic_launcher);
                requestOptions.fitCenter();


                Glide.with(MainActivity.this)
                        .applyDefaultRequestOptions(requestOptions)
                        .load(temp.get(0).getCat_icon())
                        .apply(requestOptions)
                        .into(cat1);

                Glide.with(MainActivity.this)
                        .applyDefaultRequestOptions(requestOptions)
                        .load(temp.get(1).getCat_icon())
                        .apply(requestOptions)
                        .into(cat2);

                cat1text.setText(temp.get(0).getPersian_title());
                cat2text.setText(temp.get(1).getPersian_title());


            }
        });


    }
}
