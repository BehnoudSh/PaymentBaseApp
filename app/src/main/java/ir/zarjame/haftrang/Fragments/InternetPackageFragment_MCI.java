package ir.zarjame.haftrang.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.zarjame.haftrang.Activity.InternetActivity;
import ir.zarjame.haftrang.Adapters.InternetPackagesAdapter_MCI;
import ir.zarjame.haftrang.Adapters.InternetPackagesAdapter_MTN;
import ir.zarjame.haftrang.Models.Responses.Response_Internet_FinalPackage;
import ir.zarjame.haftrang.R;


public class InternetPackageFragment_MCI extends Fragment {

    @BindView(R.id.rv_internetPackages)
    RecyclerView rv_internetPackages;

    List<Response_Internet_FinalPackage> packages;

    private Unbinder unbinder;

    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());


    public InternetPackageFragment_MCI() {
    }

    public static InternetPackageFragment_MCI newInstance(List<Response_Internet_FinalPackage> packages) {

        InternetPackageFragment_MCI fragment = new InternetPackageFragment_MCI();
        Bundle args = new Bundle();
        args.putSerializable("packages", (Serializable) packages);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        packages = (List<Response_Internet_FinalPackage>) getArguments().getSerializable("packages");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (packages != null) {
            populateRecycler(packages);
        } else {
            getActivity().getSupportFragmentManager().popBackStack();
            Toast.makeText(getActivity(), "در حال حاضر بسته‌ای موجود نیست", Toast.LENGTH_LONG).show();
        }
    }


    void populateRecycler(List<Response_Internet_FinalPackage> packages) {


        InternetPackagesAdapter_MCI adapter = new InternetPackagesAdapter_MCI(packages, getActivity(), new InternetPackagesAdapter_MCI.OnItemClickListener() {
            @Override
            public void onItemClick(Response_Internet_FinalPackage item, int position) {
                addFragment(item.getId(), item.getName(), item.getPrice());
            }
        });

        rv_internetPackages.setLayoutManager(mLayoutManager);
        rv_internetPackages.setItemAnimator(new DefaultItemAnimator());
        rv_internetPackages.setAdapter(adapter);
        rv_internetPackages.setItemViewCacheSize(200);
        rv_internetPackages.setDrawingCacheEnabled(true);
        rv_internetPackages.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);

    }

    public void addFragment(String packageId, String packagedesc, String packageamount) {

        InternetConfirmFragment confirmFragment = InternetConfirmFragment.newInstance(
                packageId,
                packagedesc,
                packageamount);

        confirmFragment.show(((InternetActivity) getActivity()).getSupportFragmentManager(), "");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.internetpackage, container, false);
        unbinder = ButterKnife.bind(this, layout);

        return layout;
    }
}
