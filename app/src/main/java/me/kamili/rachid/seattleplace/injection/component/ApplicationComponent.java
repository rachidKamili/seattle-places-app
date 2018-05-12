package me.kamili.rachid.seattleplace.injection.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import me.kamili.rachid.seattleplace.injection.module.ApplicationModule;
import retrofit2.Retrofit;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Retrofit exposeRetrofit();

    Context exposeContext();
}
