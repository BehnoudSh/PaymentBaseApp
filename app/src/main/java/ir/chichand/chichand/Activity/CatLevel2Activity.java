package ir.chichand.chichand.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.chichand.chichand.Adapters.CategoriesAdapter;
import ir.chichand.chichand.Adapters.GoodsAdapter;
import ir.chichand.chichand.Models.Reqeusts.Request_Inquiry;
import ir.chichand.chichand.Models.Responses.Response_Categories;
import ir.chichand.chichand.Models.Responses.Response_Inquiry;
import ir.chichand.chichand.Models.Responses.Response_Others;
import ir.chichand.chichand.Models.Responses.Response_Others_Result;
import ir.chichand.chichand.NetworkServices.ApiCallbacks;
import ir.chichand.chichand.NetworkServices.ApiHandler;
import ir.chichand.chichand.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CatLevel2Activity extends AppCompatActivity {

    int cat_id = 0;

    @BindView(R.id.rv_activity_cat_level2_List)
    RecyclerView rv_goodsList;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_level2);
        ButterKnife.bind(this);
        cat_id = getIntent().getIntExtra("cat_id", 0);


        ApiHandler.getInquiry(this, new Request_Inquiry(cat_id, ""), new ApiCallbacks.getInquiryInterface() {
            @Override
            public void getInquiryFailed() {

            }

            @Override
            public void getInquirySucceeded(Response_Inquiry response) {

                if (response.getHas_url() == 1) {
                    ApiHandler.getCatLevel1_Goods(CatLevel2Activity.this, response.getUrl(), new ApiCallbacks.getCatLevel1_Goods() {
                        @Override
                        public void getCatLevel0_GoodsFailed() {

                        }

                        @Override
                        public void getCatLevel0_GoodsSucceeded(Response_Others response) {


                            GoodsAdapter GoodsAdapter = new GoodsAdapter(response.getResult(), CatLevel2Activity.this, new GoodsAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(Response_Others_Result item, int position) {

                                }
                            });


                            LinearLayoutManager mLayoutManager = new LinearLayoutManager(CatLevel2Activity.this);
                            rv_goodsList.setLayoutManager(mLayoutManager);
                            rv_goodsList.setItemAnimator(new DefaultItemAnimator());
                            rv_goodsList.setAdapter(GoodsAdapter);

                            rv_goodsList.setItemViewCacheSize(200);
                            rv_goodsList.setDrawingCacheEnabled(true);
                            rv_goodsList.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);

                        }
                    });
                }
            }
        });
    }
}
