package ir.haftrang.haftrang.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import ir.haftrang.haftrang.R;


/**
 * Created by bSherafati on 4/30/2017.
 */

public class Dialog_LoadingWithMessage extends Dialog {

    public Context context;
    TextView tv_message;
    String text;

    public Dialog_LoadingWithMessage(Context context, String text) {

        super(context);

        this.context = context;
        this.text = text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_loading_withmessage);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        tv_message = (TextView) findViewById(R.id.tv_dlgLoadingMessage);
        tv_message.setText(text);
        if(text==null || text.trim().length()==0){
            tv_message.setVisibility(View.GONE);
        }
        else {
            tv_message.setVisibility(View.VISIBLE);
        }
        this.getWindow().setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.transparent_black_hex_7)));

        this.setCancelable(false);

    }

    public void setMessage(String text){
        if(tv_message!=null) {
            tv_message.setVisibility(View.VISIBLE);
            tv_message.setText(text);
        }
    }

    public void clearMessage(){
        if(tv_message!=null) {
            tv_message.setText(null);
            tv_message.setVisibility(View.GONE);
        }
    }
}


