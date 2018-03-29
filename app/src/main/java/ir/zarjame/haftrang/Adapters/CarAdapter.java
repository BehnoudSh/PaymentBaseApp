package ir.zarjame.haftrang.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ir.zarjame.haftrang.Models.Responses.Response_Car;

import ir.zarjame.haftrang.R;

/**
 * Created by tinabehnoud on 8/4/17.
 */

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {


    private List<Response_Car> carPricesList;
    private Context context;


    public CarAdapter(List<Response_Car> carPricesList, Context context) {
        this.carPricesList = carPricesList;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_simple_elevation, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {

        Response_Car companyCarList = this.carPricesList.get(i);

        String full = companyCarList.getCar_name();
        full += "\n";
        full += "\t";
        full += "قیمت بازار: ";
        full += companyCarList.getPrice_bazar();
        full += "\n";
        full += "\t";
        full += "قیمت کارخانه: ";
        full += companyCarList.getPrice();

        myViewHolder.tv_persianName.setText(full);

    }

    @Override
    public int getItemCount() {
        return this.carPricesList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_persianName;


        public MyViewHolder(View view) {
            super(view);
            tv_persianName = (TextView) view.findViewById(R.id.tv_item_simple_elevation_title);


        }


    }


}
