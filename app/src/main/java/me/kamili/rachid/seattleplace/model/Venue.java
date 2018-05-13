package me.kamili.rachid.seattleplace.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Venue {

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
}