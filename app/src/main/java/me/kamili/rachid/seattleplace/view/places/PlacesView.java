package me.kamili.rachid.seattleplace.view.places;

import java.util.List;

import me.kamili.rachid.seattleplace.model.Venue;
import me.kamili.rachid.seattleplace.view.base.BaseView;

public interface PlacesView extends BaseView {

    void onSuggestionsLoaded(List<String> suggestions);

    void onClearSuggestions();

    void onPlacesLoaded(List<Venue> venueList);

    void onClearPlaces();

    void onHidePinFab();

    void onShowPinFab();
}
