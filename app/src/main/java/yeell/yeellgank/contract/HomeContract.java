package yeell.yeellgank.contract;

import java.util.List;

/**
 * Created by YeeLL on 12/8/16.
 */

public class HomeContract {

    public interface IhomePresenter{

    }

    public interface IHomeView {

        <T> void setupData(List<T> topicModel, int page);

    }
}
