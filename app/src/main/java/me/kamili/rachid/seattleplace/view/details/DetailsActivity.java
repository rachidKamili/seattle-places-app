package me.kamili.rachid.seattleplace.view.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.kamili.rachid.seattleplace.R;
import me.kamili.rachid.seattleplace.injection.component.DaggerDetailsComponent;
import me.kamili.rachid.seattleplace.injection.module.DetailsModule;
import me.kamili.rachid.seattleplace.model.BestPhoto;
import me.kamili.rachid.seattleplace.model.Category;
import me.kamili.rachid.seattleplace.model.Icon;
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
    @BindView(R.id.vanue_best_photo)
    ImageView bestPhoto;
    @BindView(R.id.vanue_category_icon)
    ImageView categoryIcon;
    @BindView(R.id.vanue_category_name)
    TextView categoryName;
    @BindView(R.id.vanue_rating)
    RatingBar vanueRating;
    @BindView(R.id.vanue_url)
    TextView vanueUrl;
    @BindView(R.id.vanue_description)
    TextView vanueDescription;
    @BindView(R.id.vanue_address)
    TextView vanueAddress;

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

            //init the img for the favorite btn
            mPresenter.setImageToBtn(mPlace.getId());

            //Set the venue name to the toolbar's title
            getSupportActionBar().setTitle(mPlace.getName());

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.toolbar_map);
            mapFragment.getMapAsync(this);

            mPresenter.getVenueDetails(mPlace.getId());
        }
    }

    @OnClick(R.id.fab_favorite)
    public void onClickFavoritePlace(FloatingActionButton button) {
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
    public void setVenueDetails(Venue venue) {
        //Photo
        if (venue.getBestPhoto() != null) {
            Glide.with(this)
                    .load(getBestPhotoUrl(venue.getBestPhoto()))
                    .into(bestPhoto);
        }

        //Category icon
        if (venue.getCategories().get(0).getIcon() != null) {
            Glide.with(this)
                    .load(getCategoryUrlFromIcon(venue.getCategories().get(0).getIcon()))
                    .into(categoryIcon);
        }

        //Category name
        categoryName.setText(venue.getCategories().get(0).getName());

        //Rating
        vanueRating.setRating((float) (venue.getRating()/2.0));

        //Url
        if (venue.getUrl() != null) {
            vanueUrl.setText(venue.getUrl());
        }else
            vanueUrl.setText(venue.getCanonicalUrl());

        //Address
        vanueAddress.setText(TextUtils.join("\n", venue.getLocation().getFormattedAddress()));

        //Description
        if (venue.getDescription() != null) {
            vanueDescription.setText(venue.getDescription());
        }else
            vanueDescription.setText("There is no description at this time");
    }

    private String getBestPhotoUrl(BestPhoto bestPhoto){
        return  bestPhoto.getPrefix() +
                bestPhoto.getWidth() + "x" + bestPhoto.getHeight() +
                bestPhoto.getSuffix();
    }

    private String getCategoryUrlFromIcon(Icon icon) {
        return icon.getPrefix() + "bg_88" + icon.getSuffix();
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

        //To prevent bugs
        mMap.setOnMapLoadedCallback(() -> {
            int width = appBarLayout.getWidth();
            int height = appBarLayout.getHeight();
            // offset from edges of the map 30% of the collapsing toolbar
            int padding = (int) (width * 0.3);

            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding));
        });
    }

    @Override
    public void onShowDialog(String message) {
        showDialog(message);
    }

    @Override
    public void onHideDialog() {
        hideDialog();
    }

    @Override
    public void onShowToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
