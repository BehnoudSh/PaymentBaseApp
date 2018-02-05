package ir.chichand.chichand.Fragments;


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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.chichand.chichand.Activity.BusActivity;
import ir.chichand.chichand.Activity.FlightActivity;
import ir.chichand.chichand.Activity.GoodsActivity;
import ir.chichand.chichand.Adapters.CatLevel1CategoriesAdapter;
import ir.chichand.chichand.Models.Responses.Response_Categories;
import ir.chichand.chichand.R;
import ir.chichand.chichand.Tools.PublicClass;
import ir.chichand.chichand.Tools.PublicVariables;


public class CatLevel1CategoriesFragment extends Fragment {

    @BindView(R.id.rv_fragment_cat_level1_categoriesList)
    RecyclerView rv_categoriesList;

    private Unbinder unbinder;


    public static CatLevel1CategoriesFragment newInstance(int catleve0_id) {

        CatLevel1CategoriesFragment fragment = new CatLevel1CategoriesFragment();
        Bundle args = new Bundle();
        args.putInt("catlevel0_id", catleve0_id);
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

        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);

        List<Response_Categories> temp = new ArrayList<>();

        for (Response_Categories resp : PublicVariables.allCategories
                ) {
            if (resp.getCat_level() != null)

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

        }


        CatLevel1CategoriesAdapter categories_level_1Adapter = new CatLevel1CategoriesAdapter(temp, getActivity(), new CatLevel1CategoriesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Response_Categories item, int position) {

                int cat_id = Integer.parseInt(item.getCat_id());

                if (0 < cat_id && cat_id < 1000) {
                    Intent intent = new Intent(getActivity(), GoodsActivity.class);
                    intent.putExtra("cat_id", cat_id);
                    startActivity(intent);
                } else if (cat_id == 2007) {
                    Intent intent = new Intent(getActivity(), BusActivity.class);
                    startActivity(intent);
                } else if (cat_id == 2001) {
                    Intent intent = new Intent(getActivity(), FlightActivity.class);
                    startActivity(intent);
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
