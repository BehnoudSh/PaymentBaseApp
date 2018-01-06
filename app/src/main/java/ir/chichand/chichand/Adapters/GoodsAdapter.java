package ir.chichand.chichand.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import ir.chichand.chichand.Models.Responses.Response_Categories;
import ir.chichand.chichand.Models.Responses.Response_Others;
import ir.chichand.chichand.Models.Responses.Response_Others_Result;
import ir.chichand.chichand.R;
import ir.chichand.chichand.Tools.ScreenUtils;

/**
 * Created by tinabehnoud on 8/4/17.
 */

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.MyViewHolder> {


    private List<Response_Others_Result> categoriesList;
    private Context context;
    OnItemClickListener _listener;

    public GoodsAdapter(List<Response_Others_Result> categoriesList, Context context, OnItemClickListener listener) {
        this.categoriesList = categoriesList;
        this.context = context;
        this._listener = listener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_category, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {

        Response_Others_Result category = this.categoriesList.get(i);
        myViewHolder.bind(category, _listener);

        RelativeLayout.LayoutParams layoutParams1 =
                new RelativeLayout.LayoutParams((int) (ScreenUtils.ScreenSizesInPixel.x / 2), ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams1.setMargins((int) ScreenUtils.convertDpToPixel(5, context),
                (int) ScreenUtils.convertDpToPixel(5, context),
                (int) ScreenUtils.convertDpToPixel(5, context),
                (int) ScreenUtils.convertDpToPixel(5, context));
        myViewHolder.parent.setLayoutParams(layoutParams1);


        RelativeLayout.LayoutParams layoutParams2 =
                new RelativeLayout.LayoutParams((int) (ScreenUtils.ScreenSizesInPixel.x / 2), (int) (ScreenUtils.ScreenSizesInPixel.x / 2));
        myViewHolder.img.setLayoutParams(layoutParams2);


        myViewHolder.title.setText(category.getPersian_name());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.ic_launcher);
        requestOptions.fitCenter();

        Glide.with(context)
                .applyDefaultRequestOptions(requestOptions)
                .load(category.getImage_url())
                .apply(requestOptions)
                .into(myViewHolder.img);

    }

    @Override
    public int getItemCount() {
        return this.categoriesList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView img;
        public RelativeLayout parent;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_item_categories_title);
            img = (ImageView) view.findViewById(R.id.iv_item_categories_icon);
            parent = (RelativeLayout) view.findViewById(R.id.rl_item_categories_parent);

        }


        public void bind(final Response_Others_Result item, final GoodsAdapter.OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    listener.onItemClick(item, getAdapterPosition());

                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Response_Others_Result item, int position);
    }

}
