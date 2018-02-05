package ir.chichand.chichand.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.chichand.chichand.Adapters.CatLevel0PagerAdapter;
import ir.chichand.chichand.Fragments.CatLevel1CategoriesFragment;
import ir.chichand.chichand.Models.Responses.Response_Categories;
import ir.chichand.chichand.NetworkServices.ApiCallbacks;
import ir.chichand.chichand.NetworkServices.ApiHandler;
import ir.chichand.chichand.R;
import ir.chichand.chichand.Tools.PublicVariables;
import ir.chichand.chichand.Tools.ScreenUtils;
import me.relex.circleindicator.CircleIndicator;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rl_activity_main_adHolder)
    RelativeLayout rl_adHolder;

    @BindView(R.id.rv_activity_main_categoriesList)
    RecyclerView rv_categoriesList;


    @BindView(R.id.catlevel0pager)
    ViewPager pager;

    @BindView(R.id.indicator)
    CircleIndicator indicator;

    @BindView(R.id.tv_activity_main_quote)
    TextView tv_quote;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getCategories();

        tv_quote.setText(getIntent().getStringExtra("daily_quote"));
        tv_quote.setSelected(true);
        //setInitialSizes();


    }


    void setInitialSizes() {
        int hieght = (int) ((3 * ScreenUtils.ScreenSizesInPixel.x) / 4);
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams((int) ScreenUtils.ScreenSizesInPixel.x, (int) hieght);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rl_adHolder.setLayoutParams(layoutParams);

    }

    void getCategories() {

        final ProgressDialog dialog = ProgressDialog.show(this, "",
                "در حال دریافت اطلاعات ...", true);

        final List<Response_Categories> temp = new ArrayList<>();

        ApiHandler.getAllCategories(this, new ApiCallbacks.getCategoriesInterface() {
            @Override
            public void onGetAllCategoriesFailed() {
                dialog.dismiss();
            }

            @Override
            public void onGetAllCategoriesSucceeded(List<Response_Categories> response) {
                dialog.dismiss();
                PublicVariables.allCategories = response;

                for (Response_Categories resp : response
                        ) {
                    if (resp.getCat_level() != null)
                        if (resp.getCat_level().equals("0")) {
                            temp.add(resp);
//                            temp.add(resp);
//                            temp.add(resp);
//                            temp.add(resp);

                        }
                }

                CatLevel0PagerAdapter pagerAdapter = new CatLevel0PagerAdapter(MainActivity.this, temp, new CatLevel0PagerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Response_Categories item) {

                        int cat_id = Integer.parseInt(item.getCat_id());

                        if (cat_id == 1000) {

                            Intent intent = new Intent(MainActivity.this, CurrencyActivity.class);
                            startActivity(intent);

                        } else if (cat_id == 3000) {


                            List<Response_Categories> news_cat_level1_list = new ArrayList<Response_Categories>();
                            for (Response_Categories resp : PublicVariables.allCategories) {

                                if (3000 < Integer.parseInt(resp.getCat_id()) && Integer.parseInt(resp.getCat_id()) < 4000) {
                                    if (resp.getCat_level().equals("1")) {
                                        news_cat_level1_list.add(resp);
                                    }
                                }

                            }

                            Intent intent = new Intent(MainActivity.this, NewsActivity.class);

                            intent.putExtra("news_cat_level1_list", (Serializable) news_cat_level1_list);

                            startActivity(intent);

                        } else {

                            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                            fragmentTransaction.add(R.id.fl_activity_main_fragmentHolder, CatLevel1CategoriesFragment.newInstance(cat_id));

                            fragmentTransaction.addToBackStack("CatLevel1CategoriesFragment");

                            fragmentTransaction.commit();

                        }
                    }
                });

                pager.setOffscreenPageLimit(10);

                pager.setAdapter(pagerAdapter);

                indicator.setViewPager(pager);


            }
        });


    }


}
