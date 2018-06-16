package ir.zarjame.haftrang.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ir.zarjame.haftrang.R;

/**
 * Created by tinabehnoud on 2/17/18.
 */

public class Adapter_simpleSpinner extends BaseAdapter {

    Context context;

    List<String> innerList;

    LayoutInflater inflter;

    public Adapter_simpleSpinner(Context context, List<String> innerList) {
        this.context = context;
        this.innerList = innerList;
        this.inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return innerList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflter.inflate(R.layout.item_spinner_simple, null);
        TextView names = (TextView) convertView.findViewById(R.id.text1);
        names.setText(innerList.get(position));


        if (position == 0) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, (int) context.getResources().getDimension(R.dimen.very_large_gap), 0);
            names.setLayoutParams(params);

        }

        return convertView;
    }
}
