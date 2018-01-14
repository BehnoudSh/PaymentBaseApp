package ir.chichand.chichand.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.chichand.chichand.Adapters.GoodsAdapter;
import ir.chichand.chichand.Models.Requests.Request_Inquiry;
import ir.chichand.chichand.Models.Responses.Response_Inquiry;
import ir.chichand.chichand.Models.Responses.Response_Others;
import ir.chichand.chichand.Models.Responses.Response_Others_Result;
import ir.chichand.chichand.NetworkServices.ApiCallbacks;
import ir.chichand.chichand.NetworkServices.ApiHandler;
import ir.chichand.chichand.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class GoodsActivity extends AppCompatActivity {

    int cat_id = 0;

    @BindView(R.id.rv_activity_cat_level2_List)
    RecyclerView rv_goodsList;

    GoodsAdapter GoodsAdapter;
    LinearLayoutManager mLayoutManager = new LinearLayoutManager(GoodsActivity.this);
    List<Response_Others_Result> food = new ArrayList<>();

    String base_url = "";

    int page = 0;

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

        getInquiry();


        GoodsAdapter = new GoodsAdapter(food, GoodsActivity.this, new GoodsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Response_Others_Result item, int position) {

            }
        }, new GoodsAdapter.OnListEndedListener() {
            @Override
            public void onListEnded() {
                getTorob(base_url);
            }
        });

        rv_goodsList.setLayoutManager(mLayoutManager);
        rv_goodsList.setItemAnimator(new DefaultItemAnimator());
        rv_goodsList.setAdapter(GoodsAdapter);

        rv_goodsList.setItemViewCacheSize(200);
        rv_goodsList.setDrawingCacheEnabled(true);
        rv_goodsList.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);


    }


    void getInquiry() {


        ApiHandler.getInquiry(this, new Request_Inquiry(cat_id, ""), new ApiCallbacks.getInquiryInterface() {
            @Override
            public void getInquiryFailed() {

            }

            @Override
            public void getInquirySucceeded(Response_Inquiry response) {

                if (response.getHas_url() == 1) {
                    getTorob(response.getUrl());
                    base_url = response.getUrl();
                }
            }
        });


    }

    void getTorob(String url) {

        ApiHandler.getCatLevel1_Goods(GoodsActivity.this, url, new ApiCallbacks.getCatLevel1_Goods() {
            @Override
            public void getCatLevel0_GoodsFailed() {

            }

            @Override
            public void getCatLevel0_GoodsSucceeded(Response_Others response) {

                food.addAll(response.getResult());

                GoodsAdapter.notifyDataSetChanged();


                base_url = base_url.replace("page=" + page, "page=" + String.valueOf(page + 1));
                page++;
            }
        });


    }
}
