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
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.zarjame.haftrang.Adapters.CarAdapter;
import ir.zarjame.haftrang.Models.Responses.Response_Car;
import ir.zarjame.haftrang.R;

public class GoodsImageDialog extends Dialog {

    private Unbinder unbinder;
    Context context;

    @BindView(R.id.iv_dialog_image_img)
    ImageView img;

    @BindView(R.id.iv_dialog_currency_alarm_closeDialog)
    ImageView closeDialog;

    String imageAddress = "";


    public GoodsImageDialog(@NonNull Context context, String imageaddress) {
        super(context);
        this.imageAddress = imageaddress;
        this.context = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_image);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(this.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER;
        this.getWindow().setAttributes(lp);

        this.setCancelable(true);
        unbinder = ButterKnife.bind(this);


        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.playstoreicon);
        requestOptions.fitCenter();

        Glide.with(context)
                .applyDefaultRequestOptions(requestOptions)
                .load(imageAddress)
                .apply(requestOptions)
                .into(img);


        closeDialog.setOnClickListener(new View.OnClickListener()

                                       {
                                           @Override
                                           public void onClick(View v) {
                                               dismiss();
                                           }
                                       }

        );


    }


}


