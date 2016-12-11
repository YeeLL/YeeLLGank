package yeell.yeellgank.views.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.webkit.WebView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import yeell.yeellgank.R;
import yeell.yeellgank.adpter.TypeAdapter;
import yeell.yeellgank.base.BaseFragment;
import yeell.yeellgank.contract.HomeContract;
import yeell.yeellgank.https.ApiManager;
import yeell.yeellgank.model.TypeModel;
import yeell.yeellgank.presenter.home.HomePresenter;
import yeell.yeellgank.views.video.VideoActivity;
import yeell.yeellgank.views.webActivity.WebActivity;

/**
 * Created by yee on 11/22/16.
 */

public class TypeFragment extends BaseFragment implements HomeContract.IHomeView,
                                                            TypeAdapter.onItemClickListener {

    public static String TYPE_DATA = "type";

    @BindView(R.id.topic_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.topic_refresh)
    SwipeRefreshLayout mRefresh;

    private String mType;
    private TypeAdapter mAdapter;
    private List<TypeModel> mList = new ArrayList<>();
    private HomePresenter mHomePresenter;
    private int mPage = 0;
    private LinearLayout mLoadMore;

    private Handler mLoadMoreHandler = new Handler();

    @Override
    protected int getContentId() {
        return R.layout.fragment_topic;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mType = this.getArguments().getString(TYPE_DATA, "all");
        mHomePresenter = new HomePresenter(this.getActivity());
        initRecyclerView();
        loadData();
    }

    private void initRecyclerView() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new TypeAdapter(mList,this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefresh.setRefreshing(true);
                mPage = 0;
                loadData();
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();

                    // 判断是否滚动到底部，并且是向右滚动
                    if (lastVisibleItem == (totalItemCount - 1)) {
                        //加载更多功能的代码
                       View view = recyclerView.getChildAt(manager.getChildCount()-1);
                        if (view != null){
                            mLoadMore = (LinearLayout) view.findViewById(R.id.item_home_loadmore);
                            mLoadMore.setVisibility(View.VISIBLE);
                            recyclerView.smoothScrollToPosition(lastVisibleItem);
                            mLoadMoreHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    loadData();
                                }
                            },500);

                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private void loadData() {
        mHomePresenter.getTopicData(mType, mPage,this);
    }

    @Override
    public <T> void setupData(List<T> topicModel,int page) {
        if (mLoadMore!=null){
            mLoadMore.setVisibility(View.GONE);
        }
        mRefresh.setRefreshing(false);
        if (mPage == 0) {
            mList.clear();
        }
        mPage += 1;
        List<TypeModel> modelList = (List<TypeModel>) topicModel;
        mList.addAll(modelList);
        if (mList != null && mList.size() != 0) {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClickListener(TypeModel typeModel) {
        switch (typeModel.type){
            case ApiManager.API_DATA_TYPE_XIUXI:
                VideoActivity.start(getActivity(),typeModel);
                break;
            case ApiManager.API_DATA_TYPE_ANDROID:
            case ApiManager.API_DATA_TYPE_APP:
            case ApiManager.API_DATA_TYPE_iOS:
            case ApiManager.API_DATA_TYPE_QIANDUAN:
            case ApiManager.API_DATA_TYPE_TUOZHAN:
                WebActivity.start(getContext(),typeModel);
                break;
        }
    }
}
