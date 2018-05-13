package me.kamili.rachid.seattleplace.injection.component;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;
import me.kamili.rachid.seattleplace.injection.module.ContextModule;
import me.kamili.rachid.seattleplace.injection.module.NetworkModule;
import me.kamili.rachid.seattleplace.injection.module.SharedPreferencesModule;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {ContextModule.class, NetworkModule.class, SharedPreferencesModule.class})
public interface ApplicationComponent {

    Retrofit exposeRetrofit();

    SharedPreferences exposeSharedPreferences();

    Context exposeContext();
}
