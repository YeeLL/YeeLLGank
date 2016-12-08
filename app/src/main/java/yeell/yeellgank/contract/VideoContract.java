package yeell.yeellgank.contract;

/**
 * 视频详情页
 * Created by YeeLL on 12/8/16.
 */

public class VideoContract {

    public interface IVideoPresenter{
            void StartVideo();
            void stopVideo();
            void pauseVideo();
    }

    public interface IVideoView{

    }
}
