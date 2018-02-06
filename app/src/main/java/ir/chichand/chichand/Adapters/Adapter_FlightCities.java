package ir.chichand.chichand.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ir.chichand.chichand.Models.Responses.Response_BusCity;
import ir.chichand.chichand.Models.Responses.Response_FlightCity;
import ir.chichand.chichand.R;
import ir.chichand.chichand.Tools.PublicTools;


public class Adapter_FlightCities extends RecyclerView.Adapter<Adapter_FlightCities.MyViewHolder> {


    private List<Response_FlightCity> cities_all;
    private List<Response_FlightCity> cities_filter;
    private Context context;
    OnItemClickListener _listener;
    String lastFilter;

    public Adapter_FlightCities(List<Response_FlightCity> cities, Context context, OnItemClickListener listener) {
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
        Response_FlightCity city = this.cities_filter.get(i);
        myViewHolder.bind(city, _listener);
        myViewHolder.tv_title.setText(city.getCity() + " (" + city.getIata() + ")");
        myViewHolder.seperator.setVisibility(View.VISIBLE);
        if (i == cities_filter.size() - 1)
            myViewHolder.seperator.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return this.cities_filter.size();
    }


    public void filter(String filter) {
        lastFilter = filter;
        filter = PublicTools.fa2en(filter);
        cities_filter.clear();
        if (filter.trim().length() == 0) {
            cities_filter.addAll(cities_all);
        } else {
            for (Response_FlightCity city : cities_all) {
                if (city.getCity().contains(filter))
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

        public void bind(final Response_FlightCity item, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Response_FlightCity city, int position);

        void onListEmptied();
    }

}
