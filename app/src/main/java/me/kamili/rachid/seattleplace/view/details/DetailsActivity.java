package me.kamili.rachid.seattleplace.view.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.kamili.rachid.seattleplace.R;
import me.kamili.rachid.seattleplace.injection.component.DaggerDetailsComponent;
import me.kamili.rachid.seattleplace.injection.module.DetailsModule;
import me.kamili.rachid.seattleplace.model.Venue;
import me.kamili.rachid.seattleplace.utils.GeoUtils;
import me.kamili.rachid.seattleplace.view.base.BaseActivity;

public class DetailsActivity extends BaseActivity implements DetailsView, OnMapReadyCallback {

    public static final String PLACE = "PLACE_OBJECT";

    private Venue mPlace;
    private GoogleMap mMap;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    //to get the height and width
    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;

    @BindView(R.id.fab_favorite)
    FloatingActionButton btnFav;

    @Inject
    protected DetailsPresenter mPresenter;

    @Override
    protected int getContentView() {
        return R.layout.activity_details;
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerDetailsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .detailsModule(new DetailsModule(this))
                .build().inject(this);
    }

    @Override
    protected void onActivityReady(Bundle savedInstanceState, Intent intent) {
        setSupportActionBar(toolbar);

        //Back support
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mPlace = intent.getParcelableExtra(PLACE);
        if (mPlace != null) {
            mPresenter.setImageToBtn(mPlace.getId());
            getSupportActionBar().setTitle(mPlace.getName());

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.toolbar_map);
            mapFragment.getMapAsync(this);
        }
    }

    @OnClick(R.id.fab_favorite)
    public void onClickFavoritePlace(FloatingActionButton button){
        mPresenter.handleFavEvent(mPlace);
    }

    @Override
    public void setFavoriteImageChecked() {
        btnFav.setImageResource(R.drawable.ic_favorite_red);
    }

    @Override
    public void setFavoriteImageUnChecked() {
        btnFav.setImageResource(R.drawable.ic_favorite_grey);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Changing the map style to : Aubergine
        boolean success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        this, R.raw.map_aubergine));

        if (!success) {
            onShowToast("Style parsing failed.");
        }

        List<LatLng> listLocations = new ArrayList<>();

        // Add a marker in the center of Seattle and move the camera
        LatLng seattle = GeoUtils.getCenterSeattleLocation();
        listLocations.add(seattle);
        GeoUtils.addLatLngToMap(this, mMap, seattle, "Seattle center", R.drawable.ic_marker);

        // Add the venue marker
        LatLng locationVenue = new LatLng(
                mPlace.getLocation().getLat(), mPlace.getLocation().getLng()
        );
        listLocations.add(locationVenue);
        GeoUtils.addLatLngToMap(this, mMap, locationVenue, mPlace.getName(), R.drawable.ic_marker)
                .showInfoWindow()
        ;

        // Get bounds from existing markers
        LatLngBounds bounds = GeoUtils.getBoundsFromLocations(listLocations);

        int width = appBarLayout.getWidth();
        int height = appBarLayout.getHeight();
        // offset from edges of the map 30% of the collapsing toolbar
        int padding = (int) (width * 0.3);

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding));
    }

    @Override
    public void onShowDialog(String message) {

    }

    @Override
    public void onHideDialog() {

    }

    @Override
    public void onShowToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
