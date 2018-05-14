package me.kamili.rachid.seattleplace.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Venue implements Parcelable{

	@SerializedName("canonicalUrl")
	private String canonicalUrl;

	@SerializedName("name")
	private String name;

	@SerializedName("rating")
	private double rating;

	@SerializedName("description")
	private String description;

	@SerializedName("location")
	private Location location;

	@SerializedName("id")
	private String id;

	@SerializedName("categories")
	private List<Category> categories;

	@SerializedName("url")
	private String url;

	@SerializedName("bestPhoto")
	private BestPhoto bestPhoto;

	protected Venue(Parcel in) {
		canonicalUrl = in.readString();
		name = in.readString();
		rating = in.readDouble();
		description = in.readString();
		location = in.readParcelable(Location.class.getClassLoader());
		id = in.readString();
		categories = in.createTypedArrayList(Category.CREATOR);
		url = in.readString();
		bestPhoto = in.readParcelable(BestPhoto.class.getClassLoader());
	}

	public static final Creator<Venue> CREATOR = new Creator<Venue>() {
		@Override
		public Venue createFromParcel(Parcel in) {
			return new Venue(in);
		}

		@Override
		public Venue[] newArray(int size) {
			return new Venue[size];
		}
	};

	public void setCanonicalUrl(String canonicalUrl){
		this.canonicalUrl = canonicalUrl;
	}

	public String getCanonicalUrl(){
		return canonicalUrl;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setRating(double rating){
		this.rating = rating;
	}

	public double getRating(){
		return rating;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setLocation(Location location){
		this.location = location;
	}

	public Location getLocation(){
		return location;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCategories(List<Category> categories){
		this.categories = categories;
	}

	public List<Category> getCategories(){
		return categories;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setBestPhoto(BestPhoto bestPhoto){
		this.bestPhoto = bestPhoto;
	}

	public BestPhoto getBestPhoto(){
		return bestPhoto;
	}

	@Override
	public String toString(){
		return
				"Venue{" +
						"canonicalUrl = '" + canonicalUrl + '\'' +
						",name = '" + name + '\'' +
						",rating = '" + rating + '\'' +
						",description = '" + description + '\'' +
						",location = '" + location + '\'' +
						",id = '" + id + '\'' +
						",categories = '" + categories + '\'' +
						",url = '" + url + '\'' +
						",bestPhoto = '" + bestPhoto + '\'' +
						"}";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(canonicalUrl);
		dest.writeString(name);
		dest.writeDouble(rating);
		dest.writeString(description);
		dest.writeParcelable(location, flags);
		dest.writeString(id);
		dest.writeTypedList(categories);
		dest.writeString(url);
		dest.writeParcelable(bestPhoto, flags);
	}
}