package ir.chichand.chichand.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.chichand.chichand.Adapters.Adapter_BusCities;
import ir.chichand.chichand.Adapters.Adapter_SearchBuses;
import ir.chichand.chichand.Models.Responses.Response_BusCity;
import ir.chichand.chichand.Models.Responses.Response_SearchBuses;
import ir.chichand.chichand.R;
import ir.chichand.chichand.Tools.PublicVariables;

public class BusSearchResultDialog extends Dialog {

    @BindView(R.id.rv_actBusSelectCity)
    RecyclerView recyclerView;

    Response_SearchBuses searchresult;

    RecyclerView.LayoutManager layoutManager;

    private Unbinder unbinder;

    Context context;

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


        layoutManager = new LinearLayoutManager(context);

        Adapter_SearchBuses adapter = new Adapter_SearchBuses(this.searchresult.getItems(), context);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);


    }


    public BusSearchResultDialog(Context context, Response_SearchBuses searchresult) {
        super(context);
        this.context = context;
        this.searchresult = searchresult;
    }

}
