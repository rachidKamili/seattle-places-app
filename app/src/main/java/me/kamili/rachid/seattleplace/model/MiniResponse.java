package me.kamili.rachid.seattleplace.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MiniResponse{

	@SerializedName("minivenues")
	private List<MiniVenue> minivenues;

	public void setMinivenues(List<MiniVenue> minivenues){
		this.minivenues = minivenues;
	}

	public List<MiniVenue> getMinivenues(){
		return minivenues;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"minivenues = '" + minivenues + '\'' + 
			"}";
		}
}