package me.kamili.rachid.seattleplace.utils;

import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;
import java.util.List;

public class GeoUtils {
    private static double CENTER_LAT = 47.606200;
    private static double CENTER_LNG = -122.332100;

    //return the location of the center of seattle
    public static LatLng getCenterSeattleLocation() {
        return new LatLng(CENTER_LAT, CENTER_LNG);
    }

    //Distance between the center of Seattle and (lat, lng)
    public static float getDistance(double lat, double lng) {
        Location loc1 = new Location("");
        loc1.setLatitude(CENTER_LAT);
        loc1.setLongitude(CENTER_LNG);

        Location loc2 = new Location("");
        loc2.setLatitude(lat);
        loc2.setLongitude(lng);

        float distanceInMeters = loc1.distanceTo(loc2);
        return getMilesFromMeters(distanceInMeters);
    }

    //Get formatted value
    public static float getMilesFromMeters(float meters) {
        return Float.valueOf(new DecimalFormat("#.#")
                .format((float) (meters * 0.000621371192)));
    }

    //Add a marker to the map
    public static void addLatLngToMap(GoogleMap map, LatLng latLng, String title){
        map.addMarker(new MarkerOptions().position(latLng).title( title )).showInfoWindow();
    }

    public static LatLngBounds getBoundsFromLocations(List<LatLng> list){
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LatLng location : list) {
            builder.include(location);
        }
        return builder.build();
    }
}
