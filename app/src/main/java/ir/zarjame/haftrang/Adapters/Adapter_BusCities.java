package ir.zarjame.haftrang.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ir.zarjame.haftrang.Models.Responses.Response_BusCity;
import ir.zarjame.haftrang.R;
import ir.zarjame.haftrang.Tools.PublicTools;


public class Adapter_BusCities extends RecyclerView.Adapter<Adapter_BusCities.MyViewHolder> {


    private List<Response_BusCity> cities_all;
    private List<Response_BusCity> cities_filter;
    private Context context;
    OnItemClickListener _listener;
    String lastFilter;

    public Adapter_BusCities(List<Response_BusCity> cities, Context context, OnItemClickListener listener) {
        this.cities_filter = cities;
        cities_all = new ArrayList<>();
        cities_all.addAll(cities);
        this.context = context;
        this._listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_bus_city, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        Response_BusCity city = this.cities_filter.get(i);
        myViewHolder.bind(city, _listener);
        myViewHolder.tv_title.setText(city.getPersianName());
        myViewHolder.seperator.setVisibility(View.VISIBLE);
        if(i==cities_filter.size()-1)
            myViewHolder.seperator.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return this.cities_filter.size();
    }



    public void filter(String filter){
        lastFilter = filter;
        filter = PublicTools.fa2en(filter);
        cities_filter.clear();
        if(filter.trim().length()==0) {
            cities_filter.addAll(cities_all);
        }
        else {
            for(Response_BusCity city : cities_all){
                if(city.getPersianName().contains(filter))
                    cities_filter.add(city);
            }
        }
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public View seperator;

        public MyViewHolder(View view) {
            super(view);
            tv_title = (TextView) view.findViewById(R.id.tv_item_bus_city);
            seperator = view.findViewById(R.id.seperator);
        }

        public void bind(final Response_BusCity item, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Response_BusCity city, int position);
        void onListEmptied();
    }

}
