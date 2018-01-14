package ir.chichand.chichand.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import ir.chichand.chichand.Models.Responses.Response_Categories;
import ir.chichand.chichand.R;

public class CatLevel0PagerAdapter extends PagerAdapter {

    Context _context;
    LayoutInflater mLayoutInflater;
    HashMap<Integer, List<Response_Categories>> hashMap;
    List<Response_Categories> daste4tayi;
    int hashmapIndex = 0;
    OnItemClickListener _listener;


    public CatLevel0PagerAdapter(Context context, List<Response_Categories> allitems, OnItemClickListener _listener) {

        _context = context;

        mLayoutInflater = LayoutInflater.from(_context);

        this._listener = _listener;

        hashMap = new HashMap<Integer, List<Response_Categories>>();

        for (int i = 0; i < allitems.size(); i++) {

            if ((i % 4) == 0) {
                daste4tayi = new ArrayList<>();
            }

            daste4tayi.add(allitems.get(i));

            if ((i % 4) == 3) {
                hashMap.put(hashmapIndex, daste4tayi);
                hashmapIndex++;
            }

            if (i == allitems.size() - 1)
                hashMap.put(hashmapIndex, daste4tayi);
        }
    }

    @Override
    public Object instantiateItem(final ViewGroup collection, final int position) {

        ViewGroup itemView = (ViewGroup) mLayoutInflater.inflate(R.layout.item_catlevel0_pager, collection, false);

//        itemView.bind(hashMap.get(position).get(0), clickListener);
//
//
//
//
//        clickListener.onItemClick(hashMap.get(position).get(0), getAdapterPosition());


        RelativeLayout Holder1 = (RelativeLayout) itemView.findViewById(R.id.holder1);
        RelativeLayout Holder2 = (RelativeLayout) itemView.findViewById(R.id.holder2);
        RelativeLayout Holder3 = (RelativeLayout) itemView.findViewById(R.id.holder3);
        RelativeLayout Holder4 = (RelativeLayout) itemView.findViewById(R.id.holder4);


        TextView Title1 = (TextView) itemView.findViewById(R.id.item1Title);
        ImageView Image1 = (ImageView) itemView.findViewById(R.id.item1Img);
        TextView Title2 = (TextView) itemView.findViewById(R.id.item2Title);
        ImageView Image2 = (ImageView) itemView.findViewById(R.id.item2Img);
        TextView Title3 = (TextView) itemView.findViewById(R.id.item3Title);
        ImageView Image3 = (ImageView) itemView.findViewById(R.id.item3Img);
        TextView Title4 = (TextView) itemView.findViewById(R.id.item4Title);
        ImageView Image4 = (ImageView) itemView.findViewById(R.id.item4Img);

        try {

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.mipmap.ic_launcher);
            requestOptions.fitCenter();


            Title1.setText(hashMap.get(position).get(0).getPersian_title());
            Glide.with(_context)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(hashMap.get(position).get(0).getCat_icon())
                    .apply(requestOptions)
                    .into(Image1);
            Holder1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _listener.onItemClick(hashMap.get(position).get(0));
                }
            });
            Holder1.setBackgroundResource(R.drawable.bg_rounded_holder1);


            Title2.setText(hashMap.get(position).get(1).getPersian_title());
            Glide.with(_context)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(hashMap.get(position).get(1).getCat_icon())
                    .apply(requestOptions)
                    .into(Image2);
            Holder2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _listener.onItemClick(hashMap.get(position).get(1));
                }
            });
            Holder2.setBackgroundResource(R.drawable.bg_rounded_holder2);


            Title3.setText(hashMap.get(position).get(2).getPersian_title());
            Glide.with(_context)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(hashMap.get(position).get(2).getCat_icon())
                    .apply(requestOptions)
                    .into(Image3);
            Holder3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _listener.onItemClick(hashMap.get(position).get(2));
                }
            });
            Holder3.setBackgroundResource(R.drawable.bg_rounded_holder3);


            Title4.setText(hashMap.get(position).get(3).getPersian_title());
            Glide.with(_context)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(hashMap.get(position).get(3).getCat_icon())
                    .apply(requestOptions)
                    .into(Image4);
            Holder4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _listener.onItemClick(hashMap.get(position).get(3));
                }
            });
            Holder4.setBackgroundResource(R.drawable.bg_rounded_holder4);

        } catch (Exception ex) {
        }


        collection.addView(itemView);

        return itemView;
    }

    @Override
    public int getCount() {
        return hashMap.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {

        collection.removeView((View) view);
    }

    public interface OnItemClickListener {
        void onItemClick(Response_Categories item);
    }

//    public void bind(final Response_Categories item, final CatLevel1CategoriesAdapter.OnItemClickListener listener) {
//
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                listener.onItemClick(item, getAdapterPosition());
//
//            }
//        });
//    }

}

