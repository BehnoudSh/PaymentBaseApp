package ir.chichand.chichand.Activity;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import ir.chichand.chichand.Adapters.NewsPagerAdapter;
import ir.chichand.chichand.Models.Responses.Response_Categories;
import ir.chichand.chichand.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NewsActivity extends AppCompatActivity {

    @BindView(R.id.tl_activity_news_tabs)
    TabLayout tabs;

    @BindView(R.id.vp_news_pager)
    ViewPager pager;

    NewsPagerAdapter pager_adapter;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);


        setupViewPager(pager, (List<Response_Categories>) getIntent().getSerializableExtra("news_cat_level1_list"));
        pager.setOffscreenPageLimit(2);
        tabs.setupWithViewPager(pager);


        ViewGroup vg = (ViewGroup) tabs.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/IRANYekanRegular.ttf"));
                }
            }
        }
    }

    private void setupViewPager(ViewPager viewPager, List<Response_Categories> news_cat_level1_list) {
        pager_adapter = new NewsPagerAdapter(getSupportFragmentManager(), news_cat_level1_list);
        viewPager.setAdapter(pager_adapter);
    }

}
