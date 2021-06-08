package ir.zarjame.haftrang.Dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.zarjame.haftrang.R;

/**
 * Created by tinabehnoud on 5/24/18.
 */

public class Dialog_Barcode_Camera extends DialogFragment implements BarcodeCallback {


    AppCompatActivity activity;
    private Unbinder unbinder;
    Callback callback;

    @BindView(R.id.barcode_scanner)
    DecoratedBarcodeView barcodeView;

    public Dialog_Barcode_Camera() {
    }

    public static Dialog_Barcode_Camera newInstance(int position, boolean isParentActivity) {
        Dialog_Barcode_Camera frag = new Dialog_Barcode_Camera();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putBoolean("isParentActivity", isParentActivity);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_barcode_camera, container);
        unbinder = ButterKnife.bind(this, view);
        activity = (AppCompatActivity) getActivity();
        barcodeView.decodeContinuous(this);
        barcodeView.setStatusText("بارکد قبض را به درستی روبروی دوربین قرار دهید");
        barcodeView.resume();


        if (getArguments().getBoolean("isParentActivity"))
            callback = (Callback) getActivity();
        else
            callback = (Callback) getParentFragment();
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // paymentRequest a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        // make dialog's background transparent
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        barcodeView.resume();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onPause() {
        super.onPause();
        barcodeView.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        callback.BarcodeCamera_onCanceled();
        unbinder.unbind();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        callback.BarcodeCamera_onCanceled();
    }

    @Override
    public void barcodeResult(BarcodeResult result) {
        if (result.getText() == null) {
            return;
        }
        barcodeView.pause();
        callback.BarcodeCamera_onSuccess(getArguments().getInt("position", 0), result.getText());
        dismiss();
    }

    @Override
    public void possibleResultPoints(List<ResultPoint> resultPoints) {

    }

    public interface Callback {
        void BarcodeCamera_onSuccess(int position, String text);

        void BarcodeCamera_onCanceled();
    }


}
