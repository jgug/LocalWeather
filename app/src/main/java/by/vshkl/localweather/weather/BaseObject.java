package by.vshkl.localweather.weather;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseObject implements Parcelable {
    public BaseObject() {

    }

    public BaseObject(Parcel parcel) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Parcelable.Creator<BaseObject> CREATOR =
            new Parcelable.Creator<BaseObject>() {

                @Override
                public BaseObject createFromParcel(Parcel source) {
                    return new BaseObject(source);
                }

                @Override
                public BaseObject[] newArray(int size) {
                    return new BaseObject[size];
                }
            };
}
