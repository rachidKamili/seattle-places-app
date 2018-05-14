package me.kamili.rachid.seattleplace.injection.module;

import dagger.Module;
import dagger.Provides;
import me.kamili.rachid.seattleplace.injection.scope.PerActivity;
import me.kamili.rachid.seattleplace.view.map_pins.MapPinsView;

@Module
public class MapPinsModule {
    private MapPinsView mView;

    public MapPinsModule(MapPinsView view) {
        mView = view;
    }

    @PerActivity
    @Provides
    MapPinsView provideView() {
        return mView;
    }
}
