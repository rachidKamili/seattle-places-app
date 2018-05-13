package me.kamili.rachid.seattleplace.view.details;

import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import me.kamili.rachid.seattleplace.model.Venue;
import me.kamili.rachid.seattleplace.view.base.BasePresenter;

public class DetailsPresenter extends BasePresenter<DetailsView> {

    private static final String LIST_FAV_IDS = "LIST_FAV_IDS";

    private List<String> favIds;

    private SharedPreferences mSharedPreferences;

    @Inject
    public DetailsPresenter(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
        favIds = new ArrayList<>( getFavPlacesIds() );
    }

    //return a list of ids of favorite places
    public List<String> getFavPlacesIds(){
        String listString = mSharedPreferences.getString(LIST_FAV_IDS, "");
        return Arrays.asList(listString.trim().split(","));
    }

    public void setImageToBtn(String id) {
        if (favIds.contains(id)) {
            getView().setFavoriteImageChecked();
        } else {
            getView().setFavoriteImageUnChecked();
        }
    }

    //Add or remove a place and save changes
    public void handleFavEvent(Venue place) {
        if (favIds.contains(place.getId())) {
            favIds.remove(place.getId());
            getView().setFavoriteImageUnChecked();
        } else {
            favIds.add(place.getId());
            getView().setFavoriteImageChecked();
        }
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(LIST_FAV_IDS, TextUtils.join(",", favIds));
        editor.apply();
    }
}
