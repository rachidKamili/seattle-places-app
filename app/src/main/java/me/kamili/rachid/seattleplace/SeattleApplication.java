package me.kamili.rachid.seattleplace;

import android.app.Application;

import me.kamili.rachid.seattleplace.injection.component.ApplicationComponent;
import me.kamili.rachid.seattleplace.injection.component.DaggerApplicationComponent;
import me.kamili.rachid.seattleplace.injection.module.ApplicationModule;

public class SeattleApplication extends Application {

    private static final String BASE_URL = "https://api.foursquare.com";
    private static final String FILE_NAME = "app.seattle.favorites";

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this, BASE_URL, FILE_NAME))
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
