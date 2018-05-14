package me.kamili.rachid.seattleplace.model;

import com.google.gson.annotations.SerializedName;

public class VenueDetailsResponse{

	@SerializedName("response")
	private DetailsResponse response;

	public void setResponse(DetailsResponse response){
		this.response = response;
	}

	public DetailsResponse getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"VenueDetailsResponse{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}