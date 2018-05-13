package me.kamili.rachid.seattleplace.view.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.kamili.rachid.seattleplace.R;
import me.kamili.rachid.seattleplace.injection.component.DaggerDetailsComponent;
import me.kamili.rachid.seattleplace.injection.module.DetailsModule;
import me.kamili.rachid.seattleplace.model.Venue;
import me.kamili.rachid.seattleplace.view.base.BaseActivity;

public class DetailsActivity extends BaseActivity implements DetailsView{

    public static final String PLACE = "PLACE_OBJECT";

    private Venue mPlace;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mPlace = intent.getParcelableExtra(PLACE);
        if (mPlace != null) {
            mPresenter.setImageToBtn(mPlace.getId());
            getSupportActionBar().setTitle(mPlace.getName());
        }
    }

    @OnClick(R.id.fab_favorite)
    public void onClickFavoritePlace(FloatingActionButton button){
        mPresenter.handleFavEvent(mPlace);
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
}
