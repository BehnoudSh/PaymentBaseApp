package ir.chichand.chichand.Activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import ir.chichand.chichand.Fragments.Fragment_BusSearch;
import ir.chichand.chichand.R;

public class BusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        ButterKnife.bind(this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment_BusSearch busSearchFragment = Fragment_BusSearch.newInstance();
        fragmentTransaction.add(R.id.frame, busSearchFragment, "busSearchFragment");
        fragmentTransaction.addToBackStack("busSearchFragment");
        fragmentTransaction.commit();
    }
}
