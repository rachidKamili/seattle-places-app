package me.kamili.rachid.seattleplace.injection.component;

import dagger.Component;
import me.kamili.rachid.seattleplace.injection.module.ContextModule;
import me.kamili.rachid.seattleplace.injection.module.MapPinsModule;
import me.kamili.rachid.seattleplace.injection.scope.PerActivity;
import me.kamili.rachid.seattleplace.view.map_pins.MapPinsActivity;

@PerActivity
@Component(modules = MapPinsModule.class, dependencies = ApplicationComponent.class)
public interface MapPinsComponent {
    void inject(MapPinsActivity activity);
}