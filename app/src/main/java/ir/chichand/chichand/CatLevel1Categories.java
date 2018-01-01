package ir.chichand.chichand;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class CatLevel1Categories extends Fragment {

    public static CatLevel1Categories newInstance() {

        CatLevel1Categories fragment = new CatLevel1Categories();
        Bundle args = new Bundle();
//        args.putString("searchword", searchword);
        fragment.setArguments(args);
        return fragment;

    }

    public CatLevel1Categories() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cat_level1_categories, container, false);
    }

}
