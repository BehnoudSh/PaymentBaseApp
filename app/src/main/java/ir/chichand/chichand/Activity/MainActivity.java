package ir.chichand.chichand.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.chichand.chichand.Adapters.CatLevel0PagerAdapter;
import ir.chichand.chichand.BuildConfig;
import ir.chichand.chichand.Fragments.CatLevel1CategoriesFragment;
import ir.chichand.chichand.Models.Responses.Response_Categories;
import ir.chichand.chichand.NetworkServices.ApiCallbacks;
import ir.chichand.chichand.NetworkServices.ApiHandler;
import ir.chichand.chichand.R;
import ir.chichand.chichand.Tools.PublicTools;
import ir.chichand.chichand.Tools.PublicVariables;
import ir.chichand.chichand.Tools.ScreenUtils;
import me.relex.circleindicator.CircleIndicator;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.iv_actionbar_back)
    ImageView iv_actionbar_back;

    @BindView(R.id.tv_actionbar_title)
    TextView tv_actionbar_title;

    @BindView(R.id.actionbarholder)
    RelativeLayout actionbarholder;

    @BindView(R.id.toolbaricon)
    ImageView toolbaricon;

    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar
            toolbar;


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


    Animation.AnimationListener listener;

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
        setupactionbar();

        listener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                System.out.println("End Animation!");
                //load_animations();
            }
        };

//        toolbaricon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Creating the instance of PopupMenu
//                PopupMenu popup = new PopupMenu(MainActivity.this, toolbaricon);
//                //Inflating the Popup using xml file
//                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
//
//                //registering popup with OnMenuItemClickListener
//                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    public boolean onMenuItemClick(MenuItem item) {
//                        Toast.makeText(MainActivity.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
//                        return true;
//                    }
//                });
//
//                popup.show();//showing popup menu
//            }
//        });

        load_animations();

    }

    void load_animations() {
        new AnimationUtils();
        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        rotation.setAnimationListener(listener);
        toolbaricon.startAnimation(rotation);
    }


    void setInitialSizes() {
        int hieght = (int) ((3 * ScreenUtils.ScreenSizesInPixel.x) / 4);
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams((int) ScreenUtils.ScreenSizesInPixel.x, (int) hieght);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rl_adHolder.setLayoutParams(layoutParams);

    }

    void getCategories() {

        final ProgressDialog dialog = PublicTools.ProgressDialogInstance(this, "در حال دریافت لیست هفت‌رنگ");
        dialog.show();

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
                        if (resp.getCat_level().equals("0") ) {
                            temp.add(resp);


                        }
                }

                CatLevel0PagerAdapter pagerAdapter = new CatLevel0PagerAdapter(MainActivity.this, temp, new CatLevel0PagerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Response_Categories item) {

                        int cat_id = Integer.parseInt(item.getCat_id());

                        if (cat_id == 1000) {

                            Intent intent = new Intent(MainActivity.this, CurrencyActivity.class);
                            intent.putExtra("bg_color", item.getBg_color());
                            intent.putExtra("toolbar_title", item.getPersian_title());
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

                            fragmentTransaction.add(R.id.fl_activity_main_fragmentHolder, CatLevel1CategoriesFragment.newInstance(cat_id, item.getPersian_title(), item.getBg_color()));

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

    void setupactionbar() {

        tv_actionbar_title.setText("هفت‌رنگ");

        toolbar.setBackgroundColor(getResources().getColor(R.color.transparent_black_percent_60));

        iv_actionbar_back.setVisibility(View.GONE);


    }

}
