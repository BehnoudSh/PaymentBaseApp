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
                .inflate(R.layout.item_bus_search, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {

        Response_Flight flight = this.flightsList.get(i);
        //  myViewHolder.bind(bus, _listener);

        // Date date = Statics.dateConvertor(bus.getDepartureDate(),true);

        myViewHolder.tv_companyName.setText(flight.getAirline());
//        myViewHolder.tv_busModel.setText(flight.getBusType());
//        myViewHolder.tv_availableSeatsCount.setText(flight.getAvailableSeatCount() + "");
//        myViewHolder.tv_sourceCity.setText(flight.getOriginTerminalPersianName());
//
//        myViewHolder.tv_destinationCity.setText(flight.getDestinationTerminalPersianName());
//
//        myViewHolder.tv_time.setText(flight.getDepartureTime());
        myViewHolder.tv_price.setText(flight.getPrice() + " ریال");

    }

    @Override
    public int getItemCount() {
        return this.flightsList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_companyName;
        public TextView tv_busModel;
        public TextView tv_availableSeatsCount;
        public TextView tv_sourceCity;
        public TextView tv_sourceTerminal;
        public TextView tv_destinationCity;
        public TextView tv_destinationTerminal;
        public TextView tv_time;
        public TextView tv_price;
        public LinearLayout ll_additionalInfo;
        public Button bt_seats;
        public Button bt_ways;


        public MyViewHolder(View view) {
            super(view);
            tv_companyName = (TextView) view.findViewById(R.id.tv_item_available_bus_companyName);
            tv_busModel = (TextView) view.findViewById(R.id.tv_item_available_bus_busModel);
            tv_availableSeatsCount = (TextView) view.findViewById(R.id.tv_item_available_bus_availableSeatsCountValue);
            tv_sourceCity = (TextView) view.findViewById(R.id.tv_item_available_bus_sourceCity);
            tv_sourceTerminal = (TextView) view.findViewById(R.id.tv_item_available_bus_sourceTerminal);
            tv_destinationCity = (TextView) view.findViewById(R.id.tv_item_available_bus_destinationCity);
            tv_destinationTerminal = (TextView) view.findViewById(R.id.tv_item_available_bus_destinationTerminal);
            tv_time = (TextView) view.findViewById(R.id.tv_item_available_bus_departureTime);
            tv_price = (TextView) view.findViewById(R.id.tv_item_available_bus_price);


        }


        public void bind(final Response_Flight item, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    listener.onItemClick(item, getAdapterPosition());

                }
            });


            bt_seats.setOnClickListener(new View.OnClickListener() {
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
