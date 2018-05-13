package me.kamili.rachid.seattleplace.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response{

	@SerializedName("venues")
	private List<Venue> venues;

	public void setVenues(List<Venue> venues){
		this.venues = venues;
	}

	public List<Venue> getVenues(){
		return venues;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"venues = '" + venues + '\'' + 
			"}";
		}
}