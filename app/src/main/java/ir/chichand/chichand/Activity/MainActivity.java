package ir.chichand.chichand.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.chichand.chichand.Adapters.CategoriesAdapter;
import ir.chichand.chichand.Fragments.CatLevel1CategoriesFragment;
import ir.chichand.chichand.Models.Responses.Response_Categories;
import ir.chichand.chichand.NetworkServices.ApiCallbacks;
import ir.chichand.chichand.NetworkServices.ApiHandler;
import ir.chichand.chichand.R;
import ir.chichand.chichand.Tools.PublicClass;
import ir.chichand.chichand.Tools.ScreenUtils;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rl_activity_main_adHolder)
    RelativeLayout rl_adHolder;

    @BindView(R.id.rv_activity_main_categoriesList)
    RecyclerView rv_categoriesList;


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


        PublicClass.category_item_hieght = (int) ScreenUtils.ScreenSizesInPixel.y - hieght - 150;
    }

    void getCategories() {
        final List<Response_Categories> temp = new ArrayList<>();

        ApiHandler.getAllCategories(this, new ApiCallbacks.getCategoriesInterface() {
            @Override
            public void getAllCategoriesFailed() {

            }

            @Override
            public void getAllCategoriesSucceeded(List<Response_Categories> response) {

                PublicClass.allCategories = response;

                for (Response_Categories resp : response
                        ) {
                    if (resp.getCat_level() != null)
                        if (resp.getCat_level().equals("0")) {
                            temp.add(resp);


                        }

                }
                StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2,
                        StaggeredGridLayoutManager.HORIZONTAL);
                CategoriesAdapter categories_level_zeroAdapter = new CategoriesAdapter(temp, MainActivity.this, new CategoriesAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Response_Categories item, int position) {

                        int cat_id = Integer.parseInt(item.getCat_id());

                        if (cat_id == 1000) {

                            Intent intent = new Intent(MainActivity.this, CurrencyActivity.class);
                            startActivity(intent);

                        } else if (cat_id == 0) {

                            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                            fragmentTransaction.add(R.id.fl_activity_main_fragmentHolder, CatLevel1CategoriesFragment.newInstance());

                            fragmentTransaction.addToBackStack("CatLevel1CategoriesFragment");

                            fragmentTransaction.commit();


                        }
                    }

                });
                rv_categoriesList.setLayoutManager(mLayoutManager);
                rv_categoriesList.setItemAnimator(new DefaultItemAnimator());
                rv_categoriesList.setAdapter(categories_level_zeroAdapter);

                rv_categoriesList.setItemViewCacheSize(200);
                rv_categoriesList.setDrawingCacheEnabled(true);
                rv_categoriesList.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);


            }
        });


    }


}
