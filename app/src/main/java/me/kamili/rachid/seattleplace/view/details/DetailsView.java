package me.kamili.rachid.seattleplace.view.details;

import me.kamili.rachid.seattleplace.model.Venue;
import me.kamili.rachid.seattleplace.view.base.BaseView;

public interface DetailsView extends BaseView {
    void setFavoriteImageChecked();
    void setFavoriteImageUnChecked();
    void setVenueDetails(Venue venue);
}
