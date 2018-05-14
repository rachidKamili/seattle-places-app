package me.kamili.rachid.seattleplace.model;

import com.google.gson.annotations.SerializedName;

public class DetailsResponse {

	@SerializedName("venue")
	private Venue venue;

	public void setVenue(Venue venue){
		this.venue = venue;
	}

	public Venue getVenue(){
		return venue;
	}

	@Override
 	public String toString(){
		return 
			"DetailsResponse{" +
			"venue = '" + venue + '\'' + 
			"}";
		}
}