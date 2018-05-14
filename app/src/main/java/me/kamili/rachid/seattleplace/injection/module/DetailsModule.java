package me.kamili.rachid.seattleplace.injection.module;

import dagger.Module;
import dagger.Provides;
import me.kamili.rachid.seattleplace.api.ApiService;
import me.kamili.rachid.seattleplace.injection.scope.PerActivity;
import me.kamili.rachid.seattleplace.view.details.DetailsView;
import retrofit2.Retrofit;

@Module
public class DetailsModule {
    private DetailsView mView;

    public DetailsModule(DetailsView view) {
        mView = view;
    }

    @PerActivity
    @Provides
    DetailsView provideView() {
        return mView;
    }

    @PerActivity
    @Provides
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

}
