package me.kamili.rachid.seattleplace.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Location implements Parcelable{

	@SerializedName("cc")
	private String cc;

	@SerializedName("country")
	private String country;

	@SerializedName("address")
	private String address;

	@SerializedName("labeledLatLngs")
	private List<LabeledLatLng> labeledLatLngs;

	@SerializedName("lng")
	private double lng;

	@SerializedName("distance")
	private int distance;

	@SerializedName("formattedAddress")
	private List<String> formattedAddress;

	@SerializedName("city")
	private String city;

	@SerializedName("postalCode")
	private String postalCode;

	@SerializedName("state")
	private String state;

	@SerializedName("crossStreet")
	private String crossStreet;

	@SerializedName("lat")
	private double lat;

	protected Location(Parcel in) {
		cc = in.readString();
		country = in.readString();
		address = in.readString();
		labeledLatLngs = in.createTypedArrayList(LabeledLatLng.CREATOR);
		lng = in.readDouble();
		distance = in.readInt();
		formattedAddress = in.createStringArrayList();
		city = in.readString();
		postalCode = in.readString();
		state = in.readString();
		crossStreet = in.readString();
		lat = in.readDouble();
	}

	public static final Creator<Location> CREATOR = new Creator<Location>() {
		@Override
		public Location createFromParcel(Parcel in) {
			return new Location(in);
		}

		@Override
		public Location[] newArray(int size) {
			return new Location[size];
		}
	};

	public void setCc(String cc){
		this.cc = cc;
	}

	public String getCc(){
		return cc;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setLabeledLatLngs(List<LabeledLatLng> labeledLatLngs){
		this.labeledLatLngs = labeledLatLngs;
	}

	public List<LabeledLatLng> getLabeledLatLngs(){
		return labeledLatLngs;
	}

	public void setLng(double lng){
		this.lng = lng;
	}

	public double getLng(){
		return lng;
	}

	public void setDistance(int distance){
		this.distance = distance;
	}

	public int getDistance(){
		return distance;
	}

	public void setFormattedAddress(List<String> formattedAddress){
		this.formattedAddress = formattedAddress;
	}

	public List<String> getFormattedAddress(){
		return formattedAddress;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setPostalCode(String postalCode){
		this.postalCode = postalCode;
	}

	public String getPostalCode(){
		return postalCode;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public void setCrossStreet(String crossStreet){
		this.crossStreet = crossStreet;
	}

	public String getCrossStreet(){
		return crossStreet;
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
			"Location{" + 
			"cc = '" + cc + '\'' + 
			",country = '" + country + '\'' + 
			",address = '" + address + '\'' + 
			",labeledLatLngs = '" + labeledLatLngs + '\'' + 
			",lng = '" + lng + '\'' + 
			",distance = '" + distance + '\'' + 
			",formattedAddress = '" + formattedAddress + '\'' + 
			",city = '" + city + '\'' + 
			",postalCode = '" + postalCode + '\'' + 
			",state = '" + state + '\'' + 
			",crossStreet = '" + crossStreet + '\'' + 
			",lat = '" + lat + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(cc);
		dest.writeString(country);
		dest.writeString(address);
		dest.writeTypedList(labeledLatLngs);
		dest.writeDouble(lng);
		dest.writeInt(distance);
		dest.writeStringList(formattedAddress);
		dest.writeString(city);
		dest.writeString(postalCode);
		dest.writeString(state);
		dest.writeString(crossStreet);
		dest.writeDouble(lat);
	}
}