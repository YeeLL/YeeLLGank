package yeell.yeellgank.presenter.home;

import android.app.Activity;
import android.util.Log;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import yeell.yeellgank.https.ApiManager;
import yeell.yeellgank.model.TypeModel;
import yeell.yeellgank.model.TypeModels;
import yeell.yeellgank.views.home.MainActivity;
import yeell.yeellgank.views.home.TypeFragment;


/**
 * Created by yee on 11/22/16.
 */

public class HomePresenter {

     MainActivity homeActivity;

    public HomePresenter(Activity activity) {
        this.homeActivity = (MainActivity) activity;
    }


    public void getTopicData(String type, final int page, final TypeFragment typeFragment) {
        ApiManager.getApiService().getTypeData(type, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<TypeModels>() {
                    @Override
                    public void call(TypeModels typeModels) {
                        int currentpage = page;
                        currentpage++;
                        typeFragment.setupData(typeModels.results, currentpage);
                    }
                });
    }
}
