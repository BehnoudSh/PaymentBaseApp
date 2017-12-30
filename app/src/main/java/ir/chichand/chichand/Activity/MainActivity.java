package ir.chichand.chichand.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.chichand.chichand.Adapters.CategoriesAdapter;
import ir.chichand.chichand.Model.Response_Categories;
import ir.chichand.chichand.NetworkServices.ApiCallbacks;
import ir.chichand.chichand.NetworkServices.ApiHandler;
import ir.chichand.chichand.R;
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
                StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2,
                        StaggeredGridLayoutManager.HORIZONTAL);
                CategoriesAdapter userSuggestionAdapter = new CategoriesAdapter(temp, MainActivity.this, new CategoriesAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Response_Categories item, int position) {

                        Toast.makeText(MainActivity.this, item.getPersian_title(), Toast.LENGTH_LONG).show();

                    }

                });
                rv_categoriesList.setLayoutManager(mLayoutManager);
                rv_categoriesList.setItemAnimator(new DefaultItemAnimator());
                rv_categoriesList.setAdapter(userSuggestionAdapter);

                rv_categoriesList.setItemViewCacheSize(200);
                rv_categoriesList.setDrawingCacheEnabled(true);
                rv_categoriesList.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);


            }
        });


    }
}
