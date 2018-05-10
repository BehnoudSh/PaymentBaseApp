package ir.zarjame.haftrang.Activity;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.zarjame.haftrang.Fragments.ChargeCategoriesFragment;
import ir.zarjame.haftrang.Models.Operators;
import ir.zarjame.haftrang.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ChargeActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar
            toolbar;

    @BindView(R.id.iv_actionbar_back)
    ImageView iv_actionbar_back;

    @BindView(R.id.tv_actionbar_title)
    TextView tv_actionbar_title;

    @BindView(R.id.toolbaricon)
    ImageView toolbaricon;

    @BindView(R.id.card_view_irancell)
    CardView irancell_cardview;

    @BindView(R.id.card_view_mci)
    CardView mci_cardview;

    @BindView(R.id.card_view_rightel)
    CardView rightel_cardview;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge);

        ButterKnife.bind(this);

        int color = getIntent().getIntExtra("bg_color", getResources().getColor(R.color.colorPrimary));

        String title = getIntent().getStringExtra("toolbar_title");

        setupactionbar(color, title);

        irancell_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(Operators.IRANCELL);

            }
        });
        mci_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(Operators.MCI);
            }
        });
        rightel_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(Operators.RIGHTEL);
            }
        });


    }


    public void addFragment(Operators operator) {

        Bundle bundle = new Bundle();

        bundle.putString(operator.getStringValueEnglish(), "selectedoperator");

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.fragmentholder, ChargeCategoriesFragment.newInstance(operator.getStringValueEnglish()));

        fragmentTransaction.addToBackStack("chargecategoriesfragment");

        fragmentTransaction.commit();
    }

    void setupactionbar(int bg_color, String toolbar_title) {
        {
            tv_actionbar_title.setText(toolbar_title);
            toolbar.setBackgroundColor(bg_color);
            iv_actionbar_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ChargeActivity.this.finish();
                }
            });
        }
    }
}
