package ir.zarjame.haftrang.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.zarjame.haftrang.R;

public class WebViewActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar
            toolbar;

    @BindView(R.id.iv_actionbar_back)
    ImageView iv_actionbar_back;

    @BindView(R.id.tv_actionbar_title)
    TextView tv_actionbar_title;

    @BindView(R.id.toolbaricon)
    ImageView toolbaricon;

    private myWebChromeClient mWebChromeClient;
    @BindView(R.id.wv_activity_webview_webView)
    WebView webView;

    String redirectUrl = "";

    private ProgressDialog progressBar;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        ButterKnife.bind(this);

        int color = getIntent().getIntExtra("bg_color", getResources().getColor(R.color.colorPrimary));

        String title = getIntent().getStringExtra("toolbar_title");

        setupactionbar(color, title);

        redirectUrl = getIntent().getStringExtra("url");

        progressBar = ProgressDialog.show(WebViewActivity.this, "", "در حال دریافت اطلاعات ...");

        webView.clearCache(true);
        webView.loadUrl(redirectUrl);


        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.clearCache(true);
                webView.loadUrl(url);

                return true;
            }

            public void onPageFinished(WebView view, String url) {

                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                webView.loadUrl("about:blank");
//                Dialog_Message message = new Dialog_Message(Activity_WebView.this,
//                        "لطفا دوباره تلاش کنید",
//                        "تایید",
//                        false,
//                        new Dialog_Message.MessageDialogCallback() {
//                            @Override
//                            public void onMessageDialogConfirm() {
//                                finish();
//                                overridePendingTransition(R.anim.come_out, R.anim.go_in);
//                            }
//
//                            @Override
//                            public void onMessageDialogCancel() {
//                                finish();
//                                overridePendingTransition(R.anim.come_out, R.anim.go_in);
//                            }
//                        });
//                message.show();

            }
        });

        mWebChromeClient = new myWebChromeClient();

        this.webView.setWebChromeClient(mWebChromeClient);

        WebSettings ws = this.webView.getSettings();

        ws.setAllowFileAccess(true);
        ws.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= 19) {
            webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);


    }

    void setupactionbar(int bg_color, String toolbar_title) {
        {
            tv_actionbar_title.setText(toolbar_title);
            toolbar.setBackgroundColor(bg_color);
            iv_actionbar_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                        getSupportFragmentManager().popBackStack();
                    else
                        finish();

                }
            });
        }
    }

    @Override
    protected void onPause() {
        super.onPause();    //To change body of overridden methods use File | Settings | File Templates.
        webView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
        webView.onResume();
    }

    class myWebChromeClient extends WebChromeClient {


        public void onProgressChanged(WebView view, int progress) {
            progressBar.setProgress(progress);
            if (progress == 100) {
                progressBar.dismiss();

            } else {
                progressBar.show();

            }
        }


    }
}
