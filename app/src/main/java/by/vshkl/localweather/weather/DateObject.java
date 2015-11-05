package by.vshkl.localweather.weather;

import android.os.Parcel;
import android.os.Parcelable;

public class DateObject extends BaseObject {
    private String date;

    public DateObject() {
        super();
    }

    public DateObject(Parcel parcel) {
        super(parcel);
        date = parcel.readString();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DateObject{" +
                "date='" + date + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
    }

    public static final Parcelable.Creator<DateObject> CREATOR =
            new Parcelable.Creator<DateObject>() {

                @Override
                public DateObject createFromParcel(Parcel source) {
                    return new DateObject(source);
                }

                @Override
                public DateObject[] newArray(int size) {
                    return new DateObject[size];
                }
            };
}
