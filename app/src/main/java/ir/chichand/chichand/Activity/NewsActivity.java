package ir.chichand.chichand.Activity;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;

import ir.chichand.chichand.Adapters.NewsPagerAdapter;
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
        setupViewPager(pager);
        pager.setOffscreenPageLimit(2);
        tabs.setupWithViewPager(pager);
    }

    private void setupViewPager(ViewPager viewPager) {
        pager_adapter = new NewsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pager_adapter);
    }

}
