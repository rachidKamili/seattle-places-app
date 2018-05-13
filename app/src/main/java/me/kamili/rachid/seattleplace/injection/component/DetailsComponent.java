package me.kamili.rachid.seattleplace.injection.component;

import dagger.Component;
import me.kamili.rachid.seattleplace.injection.module.DetailsModule;
import me.kamili.rachid.seattleplace.injection.scope.PerActivity;
import me.kamili.rachid.seattleplace.view.details.DetailsActivity;

@PerActivity
@Component(modules = DetailsModule.class, dependencies = ApplicationComponent.class)
public interface DetailsComponent {
    void inject(DetailsActivity activity);
}