package ir.chichand.chichand.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ir.chichand.chichand.Models.Responses.Response_Bus;
import ir.chichand.chichand.Models.Responses.Response_Flight;
import ir.chichand.chichand.R;
import ir.chichand.chichand.Tools.PublicTools;


public class Adapter_AvailableFlights extends RecyclerView.Adapter<Adapter_AvailableFlights.MyViewHolder> {


    private List<Response_Flight> flightsList;
    private Context context;


    public Adapter_AvailableFlights(List<Response_Flight> busesList, Context context) {
        this.flightsList = busesList;
        this.context = context;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_flight_search, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {

        Response_Flight flight = this.flightsList.get(i);
        //  myViewHolder.bind(bus, _listener);
        myViewHolder.tv_flightAirline.setText(flight.getAirline());
        myViewHolder.tv_flightPrice.setText(PublicTools.getThousandSeperated(flight.getPrice()));
        myViewHolder.tv_flightArrivalTime.setText(flight.getArrival_time());
        myViewHolder.tv_flightDepartureTime.setText(flight.getDeparture_time());
        myViewHolder.tv_flightType.setText(flight.getFlight_type());

        if (flight.getFlight_type().trim().equals("چارتر"))
            myViewHolder.tv_flightType.setBackgroundResource(R.drawable.bg_green_rounded);
        else if (flight.getFlight_type().trim().equals("سیستمی"))
            myViewHolder.tv_flightType.setBackgroundResource(R.drawable.bg_blue_rounded);


    }

    @Override
    public int getItemCount() {
        return this.flightsList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_flightType;
        public TextView tv_flightPrice;
        public TextView tv_flightAirline;
        public TextView tv_flightDepartureTime;
        public TextView tv_flightArrivalTime;


        public MyViewHolder(View view) {
            super(view);
            tv_flightType = (TextView) view.findViewById(R.id.tv_item_flight_search_flight_type);
            tv_flightPrice = (TextView) view.findViewById(R.id.tv_item_flight_search_flight_price);
            tv_flightAirline = (TextView) view.findViewById(R.id.tv_item_flight_search_flight_airline);
            tv_flightDepartureTime = (TextView) view.findViewById(R.id.tv_item_flight_search_flight_departureTime);
            tv_flightArrivalTime = (TextView) view.findViewById(R.id.tv_item_flight_search_flight_arrivalTime);


        }


        public void bind(final Response_Flight item, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    listener.onItemClick(item, getAdapterPosition());

                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Response_Flight item, int position);
    }

}
