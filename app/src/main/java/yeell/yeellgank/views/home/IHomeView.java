package yeell.yeellgank.views.home;


import java.util.List;

/**
 * Created by yee on 11/21/16.
 */

public interface IHomeView {

     <T> void setupData(List<T> topicModel,int page);

}
