package by.vshkl.localweather.services;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class FetchWeatherResultReceiver extends ResultReceiver {
    private Receiver receiver;

    public FetchWeatherResultReceiver(Handler handler) {
        super(handler);
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public interface Receiver {
        void onReceiverResult(int resultCode, Bundle resultData);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (receiver != null) {
            receiver.onReceiverResult(resultCode, resultData);
        }
    }
}
