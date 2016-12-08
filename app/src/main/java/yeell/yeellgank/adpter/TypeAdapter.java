package yeell.yeellgank.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import yeell.yeellgank.R;
import yeell.yeellgank.https.ApiManager;
import yeell.yeellgank.model.TypeModel;
import yeell.yeellgank.utils.ImageLoader;

/**
 * Created by YeeLL on 11/24/16.
 */

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.viewHolder> {

    private List<TypeModel> mList;
    private Context mContext;
    private ImageLoader mImageLoader;
    private boolean isShowLoadMore = false;

    public interface onItemClickListener {
        void onItemClickListener(TypeModel typeModel);
    }

    private onItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public TypeAdapter(List<TypeModel> list, Context context) {
        this.mList = list;
        this.mContext = context;
        mImageLoader = ImageLoader.getInstance();
    }

    public List<TypeModel> getList() {
        return mList;
    }

    public void showLoadMore(boolean isShow) {
        if (isShow) {
            isShowLoadMore = true;
            mList.add(new TypeModel());
        } else {
            isShowLoadMore = false;
            mList.remove(mList.size());
        }
        this.notifyItemChanged(mList.size());
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_typedata, parent, false));
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        if (!isShowLoadMore) {
            final TypeModel typeModel = mList.get(position);
            holder.name.setText(typeModel.who);
            holder.type.setText("· " + typeModel.type);
            holder.time.setText(typeModel.publishedAt);
            if (typeModel.type.equals(ApiManager.API_DATA_TYPE_FULI)) {
                holder.des.setVisibility(View.GONE);
                holder.descImg.setVisibility(View.VISIBLE);
                ImageLoader.getInstance().loadImage(holder.descImg, typeModel.url, mContext);
            } else {
                holder.des.setVisibility(View.VISIBLE);
                holder.des.setText(typeModel.desc);
                if (typeModel.images != null && typeModel.images.size() != 0) {
                    ImageLoader.getInstance().loadImage(holder.descImg, typeModel.images.get(0), mContext);
                    holder.descImg.setVisibility(View.VISIBLE);
                } else {
                    holder.descImg.setVisibility(View.GONE);
                }
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClickListener(typeModel);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_home_topic_desc_img)
        ImageView descImg;
        @BindView(R.id.item_home_topic_name_txt)
        TextView name;
        @BindView(R.id.item_home_topic_time_txt)
        TextView time;
        @BindView(R.id.item_home_topic_title_txt)
        TextView des;
        @BindView(R.id.item_home_topic_type)
        TextView type;

        public viewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
