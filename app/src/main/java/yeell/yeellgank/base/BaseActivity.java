package yeell.yeellgank.base;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by YeeLL on 12/6/16.
 */

public class BaseActivity extends AppCompatActivity {

    protected Application mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentId());
        mContext = getApplication();
        ButterKnife.bind(this);
        init(savedInstanceState);
    }

    protected void init(Bundle savedInstanceState) {
    }

    protected int getContentId() {
        return 0;
    }
}