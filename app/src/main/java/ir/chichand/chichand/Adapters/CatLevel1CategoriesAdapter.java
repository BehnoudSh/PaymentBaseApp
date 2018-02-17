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
import ir.chichand.chichand.R;
import ir.chichand.chichand.Tools.ScreenUtils;

/**
 * Created by tinabehnoud on 8/4/17.
 */

public class CatLevel1CategoriesAdapter extends RecyclerView.Adapter<CatLevel1CategoriesAdapter.MyViewHolder> {


    private List<Response_Categories> categoriesList;
    private Context context;
    OnItemClickListener _listener;
    int bg_color;

    public CatLevel1CategoriesAdapter(List<Response_Categories> categoriesList, int bg_color, Context context, OnItemClickListener listener) {
        this.categoriesList = categoriesList;
        this.context = context;
        this._listener = listener;
        this.bg_color = bg_color;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_category, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        if (bg_color == context.getResources().getColor(R.color.holder1))
            myViewHolder.parent.setBackgroundResource(R.drawable.bg_rounded_holder1);
        else if (bg_color == context.getResources().getColor(R.color.holder2))
            myViewHolder.parent.setBackgroundResource(R.drawable.bg_rounded_holder2);
        else if (bg_color == context.getResources().getColor(R.color.holder3))
            myViewHolder.parent.setBackgroundResource(R.drawable.bg_rounded_holder3);
        else if (bg_color == context.getResources().getColor(R.color.holder4))
            myViewHolder.parent.setBackgroundResource(R.drawable.bg_rounded_holder4);
        else if (bg_color == context.getResources().getColor(R.color.holder5))
            myViewHolder.parent.setBackgroundResource(R.drawable.bg_rounded_holder5);
        else if (bg_color == context.getResources().getColor(R.color.holder6))
            myViewHolder.parent.setBackgroundResource(R.drawable.bg_rounded_holder6);



        Response_Categories category = this.categoriesList.get(i);
        myViewHolder.bind(category, _listener);

        RelativeLayout.LayoutParams layoutParams1 =
                new RelativeLayout.LayoutParams((int) (ScreenUtils.ScreenSizesInPixel.x / 2), (int) (ScreenUtils.ScreenSizesInPixel.x / 2));
        layoutParams1.setMargins((int) ScreenUtils.convertDpToPixel(5, context),
                (int) ScreenUtils.convertDpToPixel(5, context),
                (int) ScreenUtils.convertDpToPixel(5, context),
                (int) ScreenUtils.convertDpToPixel(5, context));
        myViewHolder.parent.setLayoutParams(layoutParams1);


        myViewHolder.title.setText(category.getPersian_title());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.playstoreicon);
        requestOptions.fitCenter();

        Glide.with(context)
                .applyDefaultRequestOptions(requestOptions)
                .load(category.getCat_icon())
                .apply(requestOptions)
                .into(myViewHolder.img);




        if (category.getIsenabled().equals("0")) {

            myViewHolder.parent.setAlpha(0.3f);

        }
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


        public void bind(final Response_Categories item, final CatLevel1CategoriesAdapter.OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    listener.onItemClick(item, getAdapterPosition());

                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Response_Categories item, int position);
    }

}
