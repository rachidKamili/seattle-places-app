package me.kamili.rachid.seattleplace.view.places;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import me.kamili.rachid.seattleplace.R;
import me.kamili.rachid.seattleplace.injection.component.DaggerPlacesComponent;
import me.kamili.rachid.seattleplace.injection.module.PlacesModule;
import me.kamili.rachid.seattleplace.view.base.BaseActivity;

public class PlacesActivity extends BaseActivity implements PlacesView {

    @Inject
    protected PlacesPresenter mPresenter;

    @BindView(R.id.search_autocomplete)
    AutoCompleteTextView mSearchAutoComplete;

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
                (adapterView, view, position, id) -> Toast.makeText(
                        PlacesActivity.this,
                        "Clicked item " + adapterView.getItemAtPosition(position)
                        , Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public void onSuggestionsLoaded(List<String> suggestions) {
        mAutoCompleteAdapter.addAll(suggestions);
        mAutoCompleteAdapter.getFilter().filter(null);
        mAutoCompleteAdapter.notifyDataSetChanged();
    }

    //Clear suggestion list
    @Override
    public void onClearItems() {
        mAutoCompleteAdapter.clear();
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
}
