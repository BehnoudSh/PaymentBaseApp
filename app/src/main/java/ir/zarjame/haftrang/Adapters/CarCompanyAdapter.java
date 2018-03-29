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

import ir.zarjame.haftrang.Models.Responses.Response_AllCars;
import ir.zarjame.haftrang.Models.Responses.Response_CarList;
import ir.zarjame.haftrang.Models.Responses.Response_Others_Result;
import ir.zarjame.haftrang.R;
import ir.zarjame.haftrang.Tools.PublicTools;

/**
 * Created by tinabehnoud on 8/4/17.
 */

public class CarCompanyAdapter extends RecyclerView.Adapter<CarCompanyAdapter.MyViewHolder> {


    private List<Response_CarList> carPricesList;
    private Context context;
    OnItemClickListener clickListener;


    public CarCompanyAdapter(List<Response_CarList> carPricesList, Context context, OnItemClickListener listener) {
        this.carPricesList = carPricesList;
        this.context = context;
        this.clickListener = listener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_simple_elevation, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {

        Response_CarList companyCarList = this.carPricesList.get(i);
        myViewHolder.bind(companyCarList, clickListener);


        myViewHolder.tv_persianName.setText(companyCarList.getCar_company());


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


        public void bind(final Response_CarList item, final CarCompanyAdapter.OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    listener.onItemClick(item, getAdapterPosition());

                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Response_CarList item, int position);
    }


}
