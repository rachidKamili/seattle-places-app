package me.kamili.rachid.seattleplace.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LabeledLatLng implements Parcelable{

	@SerializedName("lng")
	private double lng;

	@SerializedName("lat")
	private double lat;

	protected LabeledLatLng(Parcel in) {
		lng = in.readDouble();
		lat = in.readDouble();
	}

	public static final Creator<LabeledLatLng> CREATOR = new Creator<LabeledLatLng>() {
		@Override
		public LabeledLatLng createFromParcel(Parcel in) {
			return new LabeledLatLng(in);
		}

		@Override
		public LabeledLatLng[] newArray(int size) {
			return new LabeledLatLng[size];
		}
	};

	public void setLng(double lng){
		this.lng = lng;
	}

	public double getLng(){
		return lng;
	}

	public void setLat(double lat){
		this.lat = lat;
	}

	public double getLat(){
		return lat;
	}

	@Override
 	public String toString(){
		return 
			"LabeledLatLng{" +
			"lng = '" + lng + '\'' + 
			",lat = '" + lat + '\'' +
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeDouble(lng);
		dest.writeDouble(lat);
	}
}