package ir.zarjame.haftrang.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.zarjame.haftrang.Activity.BusActivity;
import ir.zarjame.haftrang.Activity.FlightActivity;
import ir.zarjame.haftrang.Activity.GoodsActivity;
import ir.zarjame.haftrang.Adapters.CatLevel1CategoriesAdapter;
import ir.zarjame.haftrang.Dialog.EstelamPhoneBillDialog;
import ir.zarjame.haftrang.Models.Responses.Response_Categories;
import ir.zarjame.haftrang.R;
import ir.zarjame.haftrang.Tools.PublicVariables;


public class CatLevel1CategoriesFragment extends Fragment {

    @BindView(R.id.rv_fragment_cat_level1_categoriesList)
    RecyclerView rv_categoriesList;

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

    void setupactionbar(String toolbartitle, int toolbar_bg_color) {

        tv_actionbar_title.setText(toolbartitle);

        toolbar.setBackgroundColor(toolbar_bg_color);

        iv_actionbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

    }

    private Unbinder unbinder;

    public static CatLevel1CategoriesFragment newInstance(int catleve0_id, String toolbar_title, int toolbar_bg_color) {

        CatLevel1CategoriesFragment fragment = new CatLevel1CategoriesFragment();
        Bundle args = new Bundle();
        args.putInt("catlevel0_id", catleve0_id);
        args.putString("toolbarTitle", toolbar_title);
        args.putInt("toolbar_bg_color", toolbar_bg_color);
        fragment.setArguments(args);
        return fragment;

    }

    public CatLevel1CategoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int catlevel0_id = getArguments().getInt("catlevel0_id");
        String toolbar_title = getArguments().getString("toolbarTitle");
        final int toolbar_bg_color = getArguments().getInt("toolbar_bg_color");

        setupactionbar(toolbar_title, toolbar_bg_color);

        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);

        List<Response_Categories> temp = new ArrayList<>();

        for (Response_Categories resp : PublicVariables.allCategories
                ) {
            if (resp.getCat_level() != null)
                if (resp.getVisibility().equals("1")) {
                    if (catlevel0_id == 0) {
                        if (0 < Integer.parseInt(resp.getCat_id()) && Integer.parseInt(resp.getCat_id()) < 1000) {
                            if (resp.getCat_level().equals("1")) {
                                temp.add(resp);
                            }
                        }
                    }

                    if (catlevel0_id == 2000) {
                        if (2000 < Integer.parseInt(resp.getCat_id()) && Integer.parseInt(resp.getCat_id()) < 3000) {
                            if (resp.getCat_level().equals("1")) {
                                temp.add(resp);
                            }
                        }
                    }
                    //    }
                }
        }
        CatLevel1CategoriesAdapter categories_level_1Adapter = new CatLevel1CategoriesAdapter(temp, toolbar_bg_color, getActivity(), new CatLevel1CategoriesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Response_Categories item, int position) {

                if (item.getIsenabled().equals("1")) {
                    int cat_id = Integer.parseInt(item.getCat_id());

                    if (0 < cat_id && cat_id < 1000) {
                        Intent intent = new Intent(getActivity(), GoodsActivity.class);
                        intent.putExtra("cat_id", cat_id);
                        intent.putExtra("toolbar_title", item.getPersian_title());
                        intent.putExtra("bg_color", toolbar_bg_color);
                        startActivity(intent);
                    } else if (cat_id == 2007) {
                        Intent intent = new Intent(getActivity(), BusActivity.class);
                        intent.putExtra("toolbar_title", item.getPersian_title());
                        intent.putExtra("bg_color", toolbar_bg_color);
                        startActivity(intent);
                    } else if (cat_id == 2001) {
                        Intent intent = new Intent(getActivity(), FlightActivity.class);
                        intent.putExtra("toolbar_title", item.getPersian_title());
                        intent.putExtra("bg_color", toolbar_bg_color);
                        startActivity(intent);
                    } else if (cat_id == 2004) {


                        EstelamPhoneBillDialog dialog = new EstelamPhoneBillDialog(getActivity(), toolbar_bg_color, item.getPersian_title());
                        dialog.show();


                    }
                } else {

                    Toast.makeText(getActivity(), "به زودی ...", Toast.LENGTH_LONG).show();

                }

            }

        });


        rv_categoriesList.setLayoutManager(mLayoutManager);
        rv_categoriesList.setItemAnimator(new DefaultItemAnimator());
        rv_categoriesList.setAdapter(categories_level_1Adapter);

        rv_categoriesList.setItemViewCacheSize(200);
        rv_categoriesList.setDrawingCacheEnabled(true);
        rv_categoriesList.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_cat_level1_categories, container, false);
        unbinder = ButterKnife.bind(this, layout);
        return layout;
    }
}
