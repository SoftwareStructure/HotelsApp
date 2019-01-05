package ify.com.hotelsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dataHolder.UserDataHolder;
import models.Vacation;

public class VacationAdapter extends RecyclerView.Adapter<VacationAdapter.MyViewHolder> {

    private List<Vacation> vacationList=new ArrayList<>();

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        public TextView countryView;
        public TextView checkInView;
        public TextView checkOutView;
        public TextView priceView;
        public TextView hotelNmaeView;

        public MyViewHolder(View v) {
            super(v);

            countryView= (TextView)(v.findViewById(R.id.countryXml));
            checkInView=(TextView)(v.findViewById(R.id.checkInXml));
            checkOutView=(TextView)(v.findViewById(R.id.checkOutXml));
            hotelNmaeView =(TextView)(v.findViewById(R.id.priceXml));
            priceView=(TextView)(v.findViewById(R.id.hotelNameXml));
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public VacationAdapter(List<Vacation> vacationList) {
        this.vacationList = vacationList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public VacationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.vacation_row, parent, false);

        // Return a new holder instance
        MyViewHolder viewHolder = new MyViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(VacationAdapter.MyViewHolder viewHolder, int position) {


        // Get the data model based on position
        Vacation vacation = vacationList.get(position);

            // Set item views based on your views and data model
            TextView country = viewHolder.countryView;
            country.setText("Country: " + vacation.getCountry());

            TextView checkIn = viewHolder.checkInView;
            checkIn.setText("Check in: " + vacation.getCheckIn());

            TextView checkOut = viewHolder.checkOutView;
            checkOut.setText("Check Out:" + vacation.getCheckOut());

            TextView price = viewHolder.priceView;
            price.setText("Price: " + vacation.getPrice());

            TextView hotelName = viewHolder.hotelNmaeView;
            hotelName.setText("Hotel name: " + vacation.getHotelName());

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return vacationList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}