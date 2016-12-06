package yeell.yeellgank.https;

import java.util.List;

import dagger.Component;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import yeell.yeellgank.model.TypeModel;
import yeell.yeellgank.model.TypeModels;

/**
 * Created by YeeLL on 12/6/16.
 */

public interface ApiService {

    //获取gank 不同类型的数据 默认一页有20个数据
    @GET("data/{type}/20/{page}")
    Observable<TypeModels> getTypeData(@Path("type")String type, @Path("page") int page);
}
