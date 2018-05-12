package me.kamili.rachid.seattleplace.view.places;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.kamili.rachid.seattleplace.api.ApiService;
import me.kamili.rachid.seattleplace.model.MiniVenue;
import me.kamili.rachid.seattleplace.model.MiniVenuesResponse;
import me.kamili.rachid.seattleplace.view.base.BasePresenter;

public class PlacesPresenter extends BasePresenter<PlacesView> implements Observer<List<String>> {

    private static final String TAG = PlacesPresenter.class.getSimpleName() + "TAG";

    @Inject
    protected ApiService mApiService;

    private Observable<MiniVenuesResponse> mObsSubscription;

    @Inject
    public PlacesPresenter() {
    }

    //Get suggestions only if there is more than 2 chars and clear the list if not
    public void getSuggestions(String query) {

        if (query.length() > 2) {

            //Cancel the previous call if exists
            if (mObsSubscription != null) {
                mObsSubscription.doOnDispose(() -> {
                });
                mObsSubscription = null;
            }

            //Create the request with 5 maximum suggestions
            mObsSubscription = mApiService.getSuggestions(5, query.trim());

            //Subscribe and map the results to a list of string
            subscribe(mObsSubscription, this,
                    response -> Observable.fromIterable(response.getResponse().getMinivenues())
                            .map(MiniVenue::getName)
                            .toList().toObservable()
            );
        } else {
            getView().onClearItems();
        }
    }

    //Display loaded dropdown items if exists, if not show Toast
    @Override
    public void onNext(List<String> suggestions) {
        getView().onClearItems();
        if (suggestions.size() > 0) {
            getView().onSuggestionsLoaded(suggestions);
        } else {
            getView().onShowToast("No results for this search");
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, "onError: " + e.getMessage());
        getView().onShowToast("Error loading " + e.getMessage());
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }
}
