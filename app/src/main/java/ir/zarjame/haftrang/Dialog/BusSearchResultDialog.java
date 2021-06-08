package ir.zarjame.haftrang.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.zarjame.haftrang.Adapters.Adapter_AvailableBuses;
import ir.zarjame.haftrang.Models.Responses.Response_SearchBuses;
import ir.zarjame.haftrang.R;

public class BusSearchResultDialog extends Dialog {

    @BindView(R.id.rv_actBusSelectCity)
    RecyclerView recyclerView;

    @BindView(R.id.iv_actionbar_back)
    ImageView iv_actionbar_back;
    @BindView(R.id.tv_actionbar_title)
    TextView tv_actionbar_title;

    @BindView(R.id.actionbarholder)
    RelativeLayout actionbarholder;
    @BindView(R.id.toolbar)
    Toolbar
            toolbar;


    Response_SearchBuses searchresult;

    RecyclerView.LayoutManager layoutManager;

    private Unbinder unbinder;

    Context context;
    int bg_color;
    String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.fragment_bus_searchresult);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(this.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER;

        this.getWindow().setAttributes(lp);

        this.setCancelable(true);
        unbinder = ButterKnife.bind(this);

        setupactionbar(bg_color, title);

        layoutManager = new LinearLayoutManager(context);

        Adapter_AvailableBuses adapter = new Adapter_AvailableBuses(this.searchresult.getItems(), context);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        // back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
    }


    public BusSearchResultDialog(Context context, Response_SearchBuses searchresult, int bgcolor, String title) {
        super(context);
        this.context = context;
        this.searchresult = searchresult;
        this.bg_color = bgcolor;
        this.title = title;
    }

    void setupactionbar(int color, String title) {

        tv_actionbar_title.setText(title);

        actionbarholder.setBackgroundColor(color);

        iv_actionbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
