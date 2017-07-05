package in.singhalenterprises.singhalenterprise;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BlogActivity extends AppCompatActivity {
    WebView webView;
    private ProgressDialog pd;
    RelativeLayout relativeLayout;
    TextView textView;
    private String[] urlsEcatalogue=new String[]{"https://drive.google.com/open?id=0BzgVitO8_6J4T0pzS1BFY1FlNW8","https://drive.google.com/open?id=0BzgVitO8_6J4N05KMU43eWZTRVE","https://drive.google.com/open?id=0BzgVitO8_6J4Tzh5YjdFcGtHcG8"};
    private String[] urlsBlog= new String[]{ "http://singhalenterprises.in/2016/10/07/cyber-security-charts/",
    "http://singhalenterprises.in/2016/12/31/happy-new-year/","http://singhalenterprises.in/2016/12/25/merry-christmas/","http://singhalEnterprises.in"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_blog);
        int id=getIntent().getIntExtra("ITEM_CLICKED",3);
        String action=getIntent().getStringExtra("ACTION_NAME");
        webView=(WebView)findViewById(R.id.webview);
        textView=(TextView)findViewById(R.id.tt_blog);
        relativeLayout=(RelativeLayout)findViewById(R.id.layout_blog);
        pd = new ProgressDialog(BlogActivity.this);
        pd.setMessage("Please wait Loading...");
        pd.setCancelable(false);
        pd.show();

        webView.setWebViewClient(new MyBrowser());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        if(action.equals("blog")) {
            relativeLayout.removeView(textView);
            getSupportActionBar().hide();
            webView.loadUrl(urlsBlog[id]);
        }
        if (action.equals("ecatalogue")){
            getSupportActionBar().setTitle(R.string.ecatalogue);
            webView.loadUrl(urlsEcatalogue[id]);
            textView.setText(R.string.extra_tag);

        }

    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            if (!pd.isShowing()) {
                pd.show();
            }

            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (pd.isShowing()) {
                pd.dismiss();
                relativeLayout.removeView(textView);
            }

        }
    }
}

