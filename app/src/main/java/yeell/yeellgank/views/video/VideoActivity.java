package yeell.yeellgank.views.video;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.widget.IjkVideoView;
import yeell.yeellgank.R;
import yeell.yeellgank.base.BaseActivity;
import yeell.yeellgank.model.TypeModel;
import yeell.yeellgank.presenter.VideoPresenter;

/**
 * 视频播放详情页
 * Created by YeeLL on 12/8/16.
 */

public class VideoActivity extends BaseActivity {

    public final static String EXTRA_VIDEO_DATA = "video_data";

    @BindView(R.id.ijkVideo)
    IjkVideoView mVideoView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.video_name)
    TextView mName;
    @BindView(R.id.video_time)
    TextView mTime;
    @BindView(R.id.video_type)
    TextView mType;
    @BindView(R.id.video_desc)
    TextView mDesc;


    private TypeModel mData;
    private VideoPresenter mPresenter;

    public static void start(Context context, TypeModel typeModel) {
        Intent starter = new Intent(context, VideoActivity.class);
        starter.putExtra(EXTRA_VIDEO_DATA, typeModel);
        context.startActivity(starter);
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_video;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mData = (TypeModel) getIntent().getSerializableExtra(EXTRA_VIDEO_DATA);
        mPresenter = new VideoPresenter(this, mVideoView, mData);
        initToolBar();
        initView();

        mVideoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mVideoView.isPlaying()) {
                    mPresenter.pauseVideo();
                } else {
                    mPresenter.startVideo();
                }
            }
        });
        mPresenter.startVideo();
    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);

        ActionBar actionBar =  getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initView() {
        mName.setText(mData.who);
        mTime.setText(mData.publishedAt);
        mType.setText(mData.type);
        mDesc.setText(mData.desc);
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
