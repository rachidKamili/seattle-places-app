package me.kamili.rachid.seattleplace.view.places;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.kamili.rachid.seattleplace.R;
import me.kamili.rachid.seattleplace.injection.component.DaggerPlacesComponent;
import me.kamili.rachid.seattleplace.injection.module.PlacesModule;
import me.kamili.rachid.seattleplace.model.Venue;
import me.kamili.rachid.seattleplace.view.base.BaseActivity;
import me.kamili.rachid.seattleplace.view.details.DetailsActivity;
import me.kamili.rachid.seattleplace.view.places.adapter.PlacesAdapter;

public class PlacesActivity extends BaseActivity implements PlacesView {

    @Inject
    protected PlacesPresenter mPresenter;

    @BindView(R.id.search_autocomplete)
    AutoCompleteTextView mSearchAutoComplete;

    @BindView(R.id.rvPlaceList)
    RecyclerView mRecyclerView;
    private PlacesAdapter mAdapter;

    private List<String> mSearchList = new ArrayList<>();
    private ArrayAdapter<String> mAutoCompleteAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_places;
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerPlacesComponent.builder()
                .applicationComponent(getApplicationComponent())
                .placesModule(new PlacesModule(this))
                .build().inject(this);
    }

    @Override
    protected void onActivityReady(Bundle savedInstanceState, Intent intent) {
        initiateAutocomplete();
        initiateRecyclerView();
    }

    private void initiateAutocomplete() {
        mAutoCompleteAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, mSearchList);
        mSearchAutoComplete.setAdapter(mAutoCompleteAdapter);

        mSearchAutoComplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = mSearchAutoComplete.getText().toString().trim();

                //Get suggestions only if there is more than 2 chars and clear the list if not
                mPresenter.getSuggestions(query);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSearchAutoComplete.setOnItemClickListener(
                (adapterView, view, position, id) -> mPresenter.getPlaces(
                        adapterView.getItemAtPosition(position).toString()
                )
        );
    }

    private void initiateRecyclerView() {
        mRecyclerView.setHasFixedSize(true);

        // using a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify the adapter
        mAdapter = new PlacesAdapter(getLayoutInflater());
        mAdapter.setPlaceClickListener(mPlaceClickListener);
        mAdapter.setFavoritePlaceClickListener(mFavPlaceClickListener);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onSuggestionsLoaded(List<String> suggestions) {
        mAutoCompleteAdapter.addAll(suggestions);
        mAutoCompleteAdapter.getFilter().filter(null);
        mAutoCompleteAdapter.notifyDataSetChanged();
    }

    //Clear suggestion list
    @Override
    public void onClearSuggestions() {
        mAutoCompleteAdapter.clear();
    }

    @Override
    public void onPlacesLoaded(List<Venue> venueList) {
        mAdapter.addPlaces(venueList);
    }

    @Override
    public void onClearPlaces() {
        mAdapter.clearPlaces();
    }

    @OnClick(R.id.search_btn)
    public void onPlacesSearch(Button button) {
        hideKeyboard();
        mPresenter.getPlaces(mSearchAutoComplete.getText().toString());
    }

    public void hideKeyboard(){
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
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
        Toast.makeText(PlacesActivity.this, message
                , Toast.LENGTH_SHORT).show();
    }

    private void goToDetailsActivity(Venue place){
        Intent intent = new Intent(PlacesActivity.this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.PLACE, place);
        startActivity(intent);
    }

    private PlacesAdapter.OnPlaceClickListener mPlaceClickListener = (place, position) -> {
        goToDetailsActivity(place);
    };

    private PlacesAdapter.OnFavoritePlaceClickListener mFavPlaceClickListener = new PlacesAdapter.OnFavoritePlaceClickListener() {
        @Override
        public void onFavoritePlaceClick(Venue place) {
            mPresenter.handleFavEvent(place);
        }

        @Override
        public List<String> getFavoritePlace() {
            return mPresenter.getFavPlacesIds();
        }
    };

    //To update the favorite icon
    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }
}
