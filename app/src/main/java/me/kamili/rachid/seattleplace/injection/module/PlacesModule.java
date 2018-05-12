package me.kamili.rachid.seattleplace.injection.module;

import dagger.Module;
import dagger.Provides;
import me.kamili.rachid.seattleplace.api.ApiService;
import me.kamili.rachid.seattleplace.injection.scope.PerActivity;
import me.kamili.rachid.seattleplace.view.places.PlacesView;
import retrofit2.Retrofit;

@Module
public class PlacesModule {

    private PlacesView mView;

    public PlacesModule(PlacesView view) {
        mView = view;
    }

    @PerActivity
    @Provides
    PlacesView provideView() {
        return mView;
    }

    @PerActivity
    @Provides
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
