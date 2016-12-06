package yeell.yeellgank.https;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by YeeLL on 12/6/16.
 */

public class ApiManager {
    public static ApiService apiService;
    public final static String BASE_URL = "http://gank.io/api/";
    public final static String API_DATA_TYPE_FULI = "福利";
    public final static String API_DATA_TYPE_ANDROID = "Android";
    public final static String API_DATA_TYPE_iOS = "iOS";
    public final static String API_DATA_TYPE_XIUXI = "休息视频";
    public final static String API_DATA_TYPE_TUOZHAN = "拓展资源";
    public final static String API_DATA_TYPE_QIANDUAN = "前端";
    public final static String API_DATA_TYPE_ALL = "all";
    public final static String API_DATA_TYPE_XIATUIJIAN = "瞎推荐";
    public final static String API_DATA_TYPE_APP = "App";

    public static ApiService getApiService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        if (apiService == null) {
            apiService = retrofit.create(ApiService.class);
        }

        return apiService;
    }

}
