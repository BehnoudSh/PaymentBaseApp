package ir.zarjame.haftrang.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.zarjame.haftrang.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_News_Category extends Fragment {


    public Fragment_News_Category() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news__category, container, false);
    }

}
