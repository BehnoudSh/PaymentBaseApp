package ir.chichand.chichand.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import ir.chichand.chichand.Models.Responses.Response_Others_Result;
import ir.chichand.chichand.R;
import ir.chichand.chichand.Tools.PublicTools;

/**
 * Created by tinabehnoud on 8/4/17.
 */

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.MyViewHolder> {


    private List<Response_Others_Result> categoriesList;
    private Context context;
    OnItemClickListener clickListener;
    OnListEndedListener listEndedListener;

    public GoodsAdapter(List<Response_Others_Result> categoriesList, Context context, OnItemClickListener listener, OnListEndedListener listendedlistener) {
        this.categoriesList = categoriesList;
        this.context = context;
        this.clickListener = listener;
        this.listEndedListener = listendedlistener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_goods, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {

        Response_Others_Result category = this.categoriesList.get(i);
        myViewHolder.bind(category, clickListener);

        myViewHolder.tv_storeName.setText(category.getShop_name());
        myViewHolder.tv_price.setText(PublicTools.getThousandSeperated(category.getPrice()) + " ریال");
        myViewHolder.tv_persianName.setText(category.getPersian_name());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.playstoreicon);
        requestOptions.fitCenter();

        Glide.with(context)
                .applyDefaultRequestOptions(requestOptions)
                .load(category.getImage_url())
                .apply(requestOptions)
                .into(myViewHolder.iv_img);

        if (i == this.categoriesList.size() - 1) {

            listEndedListener.onListEnded();
        }
    }

    @Override
    public int getItemCount() {
        return this.categoriesList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_persianName;
        public ImageView iv_img;
        public TextView tv_storeName;
        public TextView tv_price;

        public MyViewHolder(View view) {
            super(view);
            tv_persianName = (TextView) view.findViewById(R.id.tv_item_goods_persianName);
            iv_img = (ImageView) view.findViewById(R.id.iv_item_goods_img);
            tv_storeName = (TextView) view.findViewById(R.id.tv_item_goods_storeName);
            tv_price = (TextView) view.findViewById(R.id.tv_item_goods_price);

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

    public interface OnListEndedListener {
        void onListEnded();
    }


}
