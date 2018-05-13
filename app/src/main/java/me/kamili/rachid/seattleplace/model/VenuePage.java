package me.kamili.rachid.seattleplace.model;

import com.google.gson.annotations.SerializedName;

public class VenuePage{

	@SerializedName("id")
	private String id;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"VenuePage{" + 
			"id = '" + id + '\'' + 
			"}";
		}
}