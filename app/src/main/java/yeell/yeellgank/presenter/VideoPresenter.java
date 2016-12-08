package yeell.yeellgank.presenter;

import android.net.Uri;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.widget.IjkVideoView;
import yeell.yeellgank.model.TypeModel;
import yeell.yeellgank.views.video.VideoActivity;

/**
 * Created by YeeLL on 12/8/16.
 */

public class VideoPresenter {

    public VideoActivity mContext;
    public IjkVideoView mVideoView;
    public TypeModel model;

    public VideoPresenter(VideoActivity mContext, IjkVideoView videoView, TypeModel model) {
        this.mContext = mContext;
        this.mVideoView = videoView;
        this.model = model;
    }

    public void startVideo() {

        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        mVideoView.setVideoURI(Uri.parse(model.url));

        mVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer mp) {
                mVideoView.start();
            }
        });
    }

    public void stopVideo() {
        mVideoView.stopPlayback();
    }

    public void pauseVideo() {
        mVideoView.pause();
    }
}
