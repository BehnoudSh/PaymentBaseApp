package ir.chichand.chichand.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.chichand.chichand.Fragments.Fragment_BusSearch;
import ir.chichand.chichand.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BusActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.tv_actionbar_title)
    TextView tv_actionbar_title;


    @BindView(R.id.actionbarholder)
    RelativeLayout actionbarholder;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        ButterKnife.bind(this);


//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        Fragment_BusSearch busSearchFragment = Fragment_BusSearch.newInstance();
//        fragmentTransaction.add(R.id.frame, busSearchFragment, "busSearchFragment");
//        fragmentTransaction.addToBackStack("busSearchFragment");
//        fragmentTransaction.commit();

        tv_actionbar_title.setText("بلیت اتوبوس");

        actionbarholder.setBackgroundColor(getResources().getColor(R.color.holder3));

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}
