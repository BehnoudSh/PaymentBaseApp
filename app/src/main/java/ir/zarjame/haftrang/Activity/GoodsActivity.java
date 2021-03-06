package ir.zarjame.haftrang.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.zarjame.haftrang.Adapters.GoodsAdapter;
import ir.zarjame.haftrang.Dialog.GoodsImageDialog;
import ir.zarjame.haftrang.Models.Requests.Request_Inquiry;
import ir.zarjame.haftrang.Models.Responses.Response_Inquiry;
import ir.zarjame.haftrang.Models.Responses.Response_Others;
import ir.zarjame.haftrang.Models.Responses.Response_Others_Result;
import ir.zarjame.haftrang.NetworkServices.ApiCallbacks;
import ir.zarjame.haftrang.NetworkServices.ApiHandler;
import ir.zarjame.haftrang.R;
import ir.zarjame.haftrang.Tools.PublicTools;

public class GoodsActivity extends AppCompatActivity {

    int cat_id = 0;

    @BindView(R.id.rv_activity_cat_level2_List)
    RecyclerView rv_goodsList;

    @BindView(R.id.et_activity_cat_level2_search)
    EditText et_search;

    @BindView(R.id.iv_actionbar_back)
    ImageView iv_actionbar_back;

    @BindView(R.id.tv_actionbar_title)
    TextView tv_actionbar_title;

    @BindView(R.id.toolbaricon)
    ImageView toolbaricon;

    @BindView(R.id.toolbar)
    Toolbar
            toolbar;

    @BindView(R.id.pb_activity_cat_level2_loader)
    ProgressBar
            pb_loader;


    GoodsAdapter GoodsAdapter;
    LinearLayoutManager mLayoutManager = new LinearLayoutManager(GoodsActivity.this);
    List<Response_Others_Result> responseList = new ArrayList<>();

    String base_url = "";

    int page = 0;

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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_level2);
        ButterKnife.bind(this);
        cat_id = getIntent().getIntExtra("cat_id", 0);
        getInquiry("");
        rv_goodsList.requestFocus();

        populateRecycler();

        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    getInquiry(v.getText().toString());
                }
                return false;
            }
        });

        setupactionbar(getIntent().getIntExtra("bg_color", getResources().getColor(R.color.colorPrimary)), getIntent().getStringExtra("toolbar_title"));
    }


    void getInquiry(String searchword) {
        PublicTools.hideKeyboard(this);
        final ProgressDialog dialog = PublicTools.ProgressDialogInstance(this, "???? ?????? ???????????? ???????? ????????????");
        dialog.show();
        if (responseList.size() != 0) {
            responseList.clear();
            GoodsAdapter.notifyDataSetChanged();
        }

        ApiHandler.getInquiry(this, new Request_Inquiry(cat_id, searchword), new ApiCallbacks.getInquiryInterface() {
            @Override
            public void onGetInquiryFailed() {
                dialog.dismiss();
                Toast.makeText(GoodsActivity.this, "???????? ?????? ???? ?????????????? ???????????? ???????? ????????", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onGetInquirySucceeded(Response_Inquiry response) {
                dialog.dismiss();
                if (response.getHas_url() == 1) {
                    getTorob(response.getUrl());
                    base_url = response.getUrl();
                }
            }
        });


    }

    void getTorob(String url) {
        PublicTools.hideKeyboard(this);
        pb_loader.setVisibility(View.VISIBLE);
        ApiHandler.getCatLevel1_Goods(GoodsActivity.this, url, new ApiCallbacks.getCatLevel1GoodsInterface() {
            @Override
            public void onGetCatLevel0_GoodsFailed() {
                pb_loader.setVisibility(View.GONE);

            }

            @Override
            public void onGetCatLevel0_GoodsSucceeded(Response_Others response) {
                pb_loader.setVisibility(View.GONE);

                if (response.getResult().size() != 0) {
                    responseList.addAll(response.getResult());

                    GoodsAdapter.notifyDataSetChanged();

                    base_url = base_url.replace("page=" + page, "page=" + String.valueOf(page + 1));
                    page++;
                } else {

                    Toast.makeText(GoodsActivity.this, "???????????? ?????? ????????????????? ???? ???? ??????????", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    void populateRecycler() {
        GoodsAdapter = new GoodsAdapter(responseList, GoodsActivity.this, new GoodsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Response_Others_Result item, int position) {


                GoodsImageDialog dialog = new GoodsImageDialog(GoodsActivity.this, item.getImage_url());
                dialog.show();


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
}
