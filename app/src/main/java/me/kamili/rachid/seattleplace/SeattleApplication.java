package me.kamili.rachid.seattleplace;

import android.app.Application;

import me.kamili.rachid.seattleplace.injection.component.ApplicationComponent;
import me.kamili.rachid.seattleplace.injection.component.DaggerApplicationComponent;
import me.kamili.rachid.seattleplace.injection.module.ApplicationModule;

public class SeattleApplication extends Application {

    private static final String BASE_URL = "https://api.foursquare.com";

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this, BASE_URL))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
