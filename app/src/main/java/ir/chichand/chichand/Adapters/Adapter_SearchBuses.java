package ir.chichand.chichand.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ir.chichand.chichand.Models.Responses.Response_Bus;
import ir.chichand.chichand.Models.Responses.Response_BusCity;
import ir.chichand.chichand.Models.Responses.Response_SearchBuses;
import ir.chichand.chichand.R;
import ir.chichand.chichand.Tools.PublicTools;


public class Adapter_SearchBuses extends RecyclerView.Adapter<Adapter_SearchBuses.MyViewHolder> {


    private List<Response_Bus> allResults;

    private Context context;


    public Adapter_SearchBuses(List<Response_Bus> allResults, Context context) {

        this.allResults = allResults;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_bus_city, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        Response_Bus city = this.allResults.get(i);

        myViewHolder.tv_title.setText(city.getBusType());

    }

    @Override
    public int getItemCount() {
        return this.allResults.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title;


        public MyViewHolder(View view) {
            super(view);
            tv_title = (TextView) view.findViewById(R.id.tv_item_bus_city);

        }


    }


}
