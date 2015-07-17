package co.infinum.phonebook;

import com.raizlabs.android.dbflow.config.FlowManager;

import android.app.Application;

/**
 * Created by ivan on 17/07/15.
 */
public class PhoneBookApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        FlowManager.destroy();
    }
}
