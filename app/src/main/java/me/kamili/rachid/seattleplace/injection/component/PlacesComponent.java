package me.kamili.rachid.seattleplace.injection.component;

import dagger.Component;
import me.kamili.rachid.seattleplace.injection.module.PlacesModule;
import me.kamili.rachid.seattleplace.injection.scope.PerActivity;
import me.kamili.rachid.seattleplace.view.places.PlacesActivity;

@PerActivity
@Component(modules = PlacesModule.class, dependencies = ApplicationComponent.class)
public interface PlacesComponent {
    void inject(PlacesActivity activity);
}
