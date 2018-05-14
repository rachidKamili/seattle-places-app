package me.kamili.rachid.seattleplace.view.map_pins;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import me.kamili.rachid.seattleplace.R;
import me.kamili.rachid.seattleplace.injection.module.ContextModule;
import me.kamili.rachid.seattleplace.model.Venue;
import me.kamili.rachid.seattleplace.utils.GeoUtils;
import me.kamili.rachid.seattleplace.view.base.BasePresenter;

public class MapPinsPresenter extends BasePresenter<MapPinsView> {

    private Context mContext;

    @Inject
    public MapPinsPresenter(Context context) {
        mContext = context;
    }

    public Map<String, Venue> addMarkers(GoogleMap mMap, List<Venue> mPlaces, List<LatLng> listLocations) {
        //Map to identify every venue: for click listener ( Map<markerId,place> )
        Map<String, Venue> placesMap = new HashMap<>();

        //Add all markers for each venue
        for (Venue place : mPlaces) {
            LatLng locationVenue = new LatLng(
                    place.getLocation().getLat(), place.getLocation().getLng()
            );
            listLocations.add(locationVenue);
            Marker marker = GeoUtils.addLatLngToMap(
                    mContext, mMap, locationVenue,
                    place.getName(), R.drawable.ic_marker
            );
            placesMap.put(marker.getId(),place);
        }
        return placesMap;
    }
}
