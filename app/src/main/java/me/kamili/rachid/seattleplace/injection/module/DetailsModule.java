package me.kamili.rachid.seattleplace.injection.module;

import dagger.Module;
import dagger.Provides;
import me.kamili.rachid.seattleplace.injection.scope.PerActivity;
import me.kamili.rachid.seattleplace.view.details.DetailsView;

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
}
