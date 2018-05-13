package me.kamili.rachid.seattleplace.application;

import android.app.Application;

import me.kamili.rachid.seattleplace.injection.component.ApplicationComponent;
import me.kamili.rachid.seattleplace.injection.component.DaggerApplicationComponent;
import me.kamili.rachid.seattleplace.injection.module.ContextModule;
import me.kamili.rachid.seattleplace.injection.module.NetworkModule;
import me.kamili.rachid.seattleplace.injection.module.SharedPreferencesModule;

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
                .contextModule(new ContextModule(this))
                .networkModule(new NetworkModule(BASE_URL))
                .sharedPreferencesModule(new SharedPreferencesModule(FILE_NAME))
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
