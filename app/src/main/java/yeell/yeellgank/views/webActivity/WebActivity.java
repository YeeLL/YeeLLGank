package yeell.yeellgank.views.webActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;

import butterknife.BindView;
import yeell.yeellgank.R;
import yeell.yeellgank.base.BaseActivity;
import yeell.yeellgank.model.TypeModel;

/**
 * Created by YeeLL on 12/9/16.
 */

public class WebActivity extends BaseActivity {

    public final static String EXTRA_DATA_WEB = "web";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.webView)
    WebView mWebView;


    private TypeModel mData;


    public static void start(Context context, TypeModel typeModel) {
        Intent starter = new Intent(context, WebActivity.class);
        starter.putExtra(EXTRA_DATA_WEB, typeModel);
        context.startActivity(starter);
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_web;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mData = (TypeModel) getIntent().getSerializableExtra(EXTRA_DATA_WEB);
        initToolBar();
        initWebView();
    }

    private void initWebView() {
        mWebView.loadUrl(mData.url);
    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
