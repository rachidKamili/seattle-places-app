package me.kamili.rachid.seattleplace.api;

import io.reactivex.Observable;
import me.kamili.rachid.seattleplace.model.MiniVenuesResponse;
import me.kamili.rachid.seattleplace.model.VenuesResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    //Getting suggestions for Autocomplete search
    @GET("/v2/venues/suggestcompletion")
    Observable<MiniVenuesResponse> getSuggestions(@Query("limit") int limit,
                                                  @Query("query") String query);

    //Getting Places
    @GET("/v2/venues/search")
    Observable<VenuesResponse> getPlaces(@Query("limit") int limit,
                                         @Query("query") String query);

}
