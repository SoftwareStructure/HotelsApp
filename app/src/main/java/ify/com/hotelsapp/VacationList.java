package ify.com.hotelsapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class VacationList extends ArrayAdapter<Vacation> {

    private Activity context;
    private List<Vacation> vacationList;

    public VacationList(Activity context, List<Vacation> vacationList) {
        super(context, R.layout.list_layout, vacationList);
        this.context = context;
        this.vacationList = vacationList;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);
        TextView textViewCountry = (TextView) listViewItem.findViewById(R.id.textViewCountry);
        TextView textViewCheckIn = (TextView) listViewItem.findViewById(R.id.textViewCheckIn);
        TextView textViewCheckOut = (TextView) listViewItem.findViewById(R.id.textViewCheckOut);
        TextView textViewHotelName = (TextView) listViewItem.findViewById(R.id.textViewHotelName);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.textViewPrice);

        Vacation vac = vacationList.get(position);
        textViewCountry.setText(vac.getCounrty());
        textViewCheckIn.setText(vac.getCheckIn());
        textViewCheckOut.setText(vac.getCheckOut());
        textViewHotelName.setText(vac.getHotelName());
        textViewPrice.setText(vac.getPrice());

        return listViewItem;
    }

}
