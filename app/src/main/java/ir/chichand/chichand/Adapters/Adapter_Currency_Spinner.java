package ir.chichand.chichand.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Currency;
import java.util.List;

import ir.chichand.chichand.Models.Responses.Response_Inquiry_Data;
import ir.chichand.chichand.R;

/**
 * Created by tinabehnoud on 2/9/18.
 */


public class Adapter_Currency_Spinner extends ArrayAdapter<Response_Inquiry_Data> {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<Response_Inquiry_Data> items;
    private final int mResource;

    public Adapter_Currency_Spinner(@NonNull Context context, @LayoutRes int resource,
                                    @NonNull List objects) {
        super(context, resource, 0, objects);

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public
    @NonNull
    View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        final View view = mInflater.inflate(mResource, parent, false);

        TextView title = (TextView) view.findViewById(R.id.tv_item_spinner_simple_title);

        Response_Inquiry_Data item = items.get(position);

        title.setText(item.getName());

        return view;
    }


}
