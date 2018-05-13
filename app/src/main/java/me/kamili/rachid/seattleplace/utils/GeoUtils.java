package me.kamili.rachid.seattleplace.utils;

import android.location.Location;

import java.text.DecimalFormat;

public class GeoUtils {
    private static double CENTER_LAT = 47.606200;
    private static double CENTER_LNG = -122.332100;

    //Distance between the center of Seattle and (lat, lng)
    public static float getDistance(double lat, double lng){
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
    public static float getMilesFromMeters(float meters){
        return Float.valueOf(new DecimalFormat("#.#")
                .format((float) (meters*0.000621371192)));
    }
}
