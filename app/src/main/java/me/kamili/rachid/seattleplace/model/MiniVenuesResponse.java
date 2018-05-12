package me.kamili.rachid.seattleplace.model;

import com.google.gson.annotations.SerializedName;

public class MiniVenuesResponse{

	@SerializedName("meta")
	private Meta meta;

	@SerializedName("response")
	private MiniResponse response;

	public void setMeta(Meta meta){
		this.meta = meta;
	}

	public Meta getMeta(){
		return meta;
	}

	public void setResponse(MiniResponse response){
		this.response = response;
	}

	public MiniResponse getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"MiniVenuesResponse{" + 
			"meta = '" + meta + '\'' + 
			",response = '" + response + '\'' + 
			"}";
		}
}