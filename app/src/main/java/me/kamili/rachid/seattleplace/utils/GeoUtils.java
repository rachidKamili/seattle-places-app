package me.kamili.rachid.seattleplace.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
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
    public static Marker addLatLngToMap(Context context, GoogleMap map, LatLng latLng, String title,@DrawableRes int markerVector){
        return map.addMarker(new MarkerOptions()
                .position(latLng)
                .title(title)
                .icon(bitmapDescriptorFromVector(context,markerVector)));
    }

    //decode the image to Bitmap - 100x100px
    private static BitmapDescriptor bitmapDescriptorFromVector(Context context,@DrawableRes int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, 100, 100);
        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    public static LatLngBounds getBoundsFromLocations(List<LatLng> list){
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LatLng location : list) {
            builder.include(location);
        }
        return builder.build();
    }
}
