package ir.zarjame.haftrang.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ir.zarjame.haftrang.BuildConfig;
import ir.zarjame.haftrang.Models.Responses.Response_Categories;
import ir.zarjame.haftrang.R;
import ir.zarjame.haftrang.Tools.ScreenUtils;

public class CatLevel0PagerAdapter extends PagerAdapter {

    Context _context;
    LayoutInflater mLayoutInflater;
    HashMap<Integer, List<Response_Categories>> hashMap;
    List<Response_Categories> dasteNtayi;
    int hashmapIndex = 0;
    OnItemClickListener _listener;
    int n = 0;


    public CatLevel0PagerAdapter(Context context, List<Response_Categories> allitems, OnItemClickListener _listener) {

        _context = context;

        mLayoutInflater = LayoutInflater.from(_context);

        this._listener = _listener;

        hashMap = new HashMap<Integer, List<Response_Categories>>();

        if (getScreenSizeInInch() > 5)
            n = 6;
        else
            n = 4;


        for (int i = 0; i < allitems.size(); i++) {

            if ((i % n) == 0) {
                dasteNtayi = new ArrayList<>();
            }

            dasteNtayi.add(allitems.get(i));

            if ((i % n) == (n - 1)) {
                hashMap.put(hashmapIndex, dasteNtayi);
                hashmapIndex++;
            }

            if (dasteNtayi.size() < n) {
                hashMap.put(hashmapIndex, dasteNtayi);
            }


        }
    }

    @Override
    public Object instantiateItem(final ViewGroup collection, final int position) {

        ViewGroup itemView = (ViewGroup) mLayoutInflater.inflate(R.layout.item_catlevel0_pager, collection, false);

        RelativeLayout Holder1 = (RelativeLayout) itemView.findViewById(R.id.holder1);
        RelativeLayout Holder2 = (RelativeLayout) itemView.findViewById(R.id.holder2);
        RelativeLayout Holder3 = (RelativeLayout) itemView.findViewById(R.id.holder3);
        RelativeLayout Holder4 = (RelativeLayout) itemView.findViewById(R.id.holder4);
        RelativeLayout Holder5 = (RelativeLayout) itemView.findViewById(R.id.holder5);
        RelativeLayout Holder6 = (RelativeLayout) itemView.findViewById(R.id.holder6);
        LinearLayout Holder5_6Holder = (LinearLayout) itemView.findViewById(R.id.holder5_6_holder);

        TextView Title1 = (TextView) itemView.findViewById(R.id.item1Title);
        ImageView Image1 = (ImageView) itemView.findViewById(R.id.item1Img);
        TextView Title2 = (TextView) itemView.findViewById(R.id.item2Title);
        ImageView Image2 = (ImageView) itemView.findViewById(R.id.item2Img);
        TextView Title3 = (TextView) itemView.findViewById(R.id.item3Title);
        ImageView Image3 = (ImageView) itemView.findViewById(R.id.item3Img);
        TextView Title4 = (TextView) itemView.findViewById(R.id.item4Title);
        ImageView Image4 = (ImageView) itemView.findViewById(R.id.item4Img);
        TextView Title5 = (TextView) itemView.findViewById(R.id.item5Title);
        ImageView Image5 = (ImageView) itemView.findViewById(R.id.item5Img);
        TextView Title6 = (TextView) itemView.findViewById(R.id.item6Title);
        ImageView Image6 = (ImageView) itemView.findViewById(R.id.item6Img);

        try {

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.playstoreicon);
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
            Holder1.setBackgroundResource(R.drawable.bg_rounded_color1);
            //  handleEnabled(Holder1, hashMap.get(position).get(0));
            handleUpdateNedded(Holder1, hashMap.get(position).get(0));
            hashMap.get(position).get(0).setBg_color(_context.getResources().getColor(R.color.holder1));

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
            Holder2.setBackgroundResource(R.drawable.bg_rounded_color2);
            //   handleEnabled(Holder2, hashMap.get(position).get(1));
            handleUpdateNedded(Holder2, hashMap.get(position).get(1));

            hashMap.get(position).get(1).setBg_color(_context.getResources().getColor(R.color.holder2));

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
            Holder3.setBackgroundResource(R.drawable.bg_rounded_color3);
            //   handleEnabled(Holder3, hashMap.get(position).get(2));
            handleUpdateNedded(Holder3, hashMap.get(position).get(2));

            hashMap.get(position).get(2).setBg_color(_context.getResources().getColor(R.color.holder3));

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
            Holder4.setBackgroundResource(R.drawable.bg_rounded_color4);
            //   handleEnabled(Holder4, hashMap.get(position).get(3));
            handleUpdateNedded(Holder4, hashMap.get(position).get(3));

            hashMap.get(position).get(3).setBg_color(_context.getResources().getColor(R.color.holder4));

            if (n == 6) {

                Holder5_6Holder.setVisibility(View.VISIBLE);


                Title5.setText(hashMap.get(position).get(4).getPersian_title());
                Glide.with(_context)
                        .applyDefaultRequestOptions(requestOptions)
                        .load(hashMap.get(position).get(4).getCat_icon())
                        .apply(requestOptions)
                        .into(Image5);
                Holder5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        _listener.onItemClick(hashMap.get(position).get(4));
                    }
                });
                Holder5.setBackgroundResource(R.drawable.bg_rounded_color5);
                //  handleEnabled(Holder5, hashMap.get(position).get(4));
                handleUpdateNedded(Holder5, hashMap.get(position).get(4));

                hashMap.get(position).get(4).setBg_color(_context.getResources().getColor(R.color.holder5));


                Title6.setText(hashMap.get(position).get(5).getPersian_title());
                Glide.with(_context)
                        .applyDefaultRequestOptions(requestOptions)
                        .load(hashMap.get(position).get(5).getCat_icon())
                        .apply(requestOptions)
                        .into(Image6);
                Holder6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        _listener.onItemClick(hashMap.get(position).get(5));
                    }
                });
                Holder6.setBackgroundResource(R.drawable.bg_rounded_color6);
                //  handleEnabled(Holder6, hashMap.get(position).get(5));
                handleUpdateNedded(Holder6, hashMap.get(position).get(5));

                hashMap.get(position).get(5).setBg_color(_context.getResources().getColor(R.color.holder6));


            } else {

                Holder5_6Holder.setVisibility(View.GONE);

            }

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

    void makeCafeBazaarDialog() {
        final AlertDialog _dialogOffline = new AlertDialog.Builder(_context)
                .setMessage("برای استفاده از این قسمت لطفا هفت\u200Cرنگ را به\u200Cروزرسانی کنید")
                .setCancelable(true)
                .setPositiveButton("دریافت نسخه جدید", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String url = "http://cafebazaar.ir/app/?id=ir.zarjame.haftrang";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        _context.startActivity(i);

                    }
                })
                .setNegativeButton("فعلا بیخیال", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })

                .create();
        _dialogOffline.show();
    }

    void handleUpdateNedded(View view, Response_Categories category) {

        if (Integer.parseInt(category.getMinversion()) > BuildConfig.VERSION_CODE) {
            view.setAlpha(0.3f);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    makeCafeBazaarDialog();

                }
            });

            return;
        }

        if (Integer.parseInt(category.getMinversion()) <= BuildConfig.VERSION_CODE) {

            if (category.getIsenabled().equals("0")) {
                view.setAlpha(0.3f);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(_context, "در حال حاضر غیر فعال ...", Toast.LENGTH_LONG).show();
                    }
                });

                return;
            }
        }

        view.setAlpha(1f);

    }

    double getScreenSizeInInch() {

        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) _context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        double x = Math.pow(ScreenUtils.ScreenSizesInPixel.x / dm.xdpi, 2);
        double y = Math.pow(ScreenUtils.ScreenSizesInPixel.y / dm.ydpi, 2);
        return Math.sqrt(x + y);

    }
}

