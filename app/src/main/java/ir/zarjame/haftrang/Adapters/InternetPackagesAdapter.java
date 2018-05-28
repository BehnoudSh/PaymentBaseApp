package ir.zarjame.haftrang.Adapters;

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

import ir.zarjame.haftrang.Models.Responses.Response_Internet_FinalPackage;
import ir.zarjame.haftrang.Models.Responses.Response_Others_Result;
import ir.zarjame.haftrang.R;
import ir.zarjame.haftrang.Tools.PublicTools;

/**
 * Created by tinabehnoud on 8/4/17.
 */

public class InternetPackagesAdapter extends RecyclerView.Adapter<InternetPackagesAdapter.MyViewHolder> {


    private List<Response_Internet_FinalPackage> categoriesList;
    private Context context;
    OnItemClickListener clickListener;

    public InternetPackagesAdapter(List<Response_Internet_FinalPackage> categoriesList, Context context, OnItemClickListener listener) {
        this.categoriesList = categoriesList;
        this.context = context;
        this.clickListener = listener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_internetpackage, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {

        Response_Internet_FinalPackage category = this.categoriesList.get(i);
        myViewHolder.bind(category, clickListener);

        myViewHolder.tv_price.setText(PublicTools.getThousandSeperated(category.getPrice()));
        myViewHolder.tv_persianName.setText(category.getName());

        myViewHolder.iv_img.setBackgroundResource(R.drawable.irancelllogo);


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


        public void bind(final Response_Internet_FinalPackage item, final InternetPackagesAdapter.OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    listener.onItemClick(item, getAdapterPosition());

                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Response_Internet_FinalPackage item, int position);
    }

}
