package me.kamili.rachid.seattleplace.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Venue implements Parcelable{

	@SerializedName("name")
	private String name;

	@SerializedName("location")
	private Location location;

	@SerializedName("id")
	private String id;

	@SerializedName("url")
	private String url;

	@SerializedName("categories")
	private List<Category> categories;

	protected Venue(Parcel in) {
		name = in.readString();
		id = in.readString();
		url = in.readString();
		categories = in.createTypedArrayList(Category.CREATOR);
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
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

	@Override
 	public String toString(){
		return 
			"Venue{" +
			"name = '" + name + '\'' +
			",location = '" + location + '\'' + 
			",id = '" + id + '\'' + 
			",url = '" + url + '\'' +
			",categories = '" + categories + '\'' +
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(id);
		dest.writeString(url);
		dest.writeTypedList(categories);
	}
}