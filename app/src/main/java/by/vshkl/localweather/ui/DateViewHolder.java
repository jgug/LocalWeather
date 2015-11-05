package by.vshkl.localweather.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import by.vshkl.localweather.R;

public class DateViewHolder extends RecyclerView.ViewHolder {
    TextView date;

    public DateViewHolder(final View itemView) {
        super(itemView);
        date = (TextView) itemView.findViewById(R.id.weather_date_dection);
    }
}
