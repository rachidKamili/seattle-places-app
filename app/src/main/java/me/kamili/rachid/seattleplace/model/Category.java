package me.kamili.rachid.seattleplace.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Category implements Parcelable {

	@SerializedName("pluralName")
	private String pluralName;

	@SerializedName("name")
	private String name;

	@SerializedName("icon")
	private Icon icon;

	@SerializedName("id")
	private String id;

	@SerializedName("shortName")
	private String shortName;

	@SerializedName("primary")
	private boolean primary;

	protected Category(Parcel in) {
		pluralName = in.readString();
		name = in.readString();
		id = in.readString();
		shortName = in.readString();
		primary = in.readByte() != 0;
	}

	public static final Creator<Category> CREATOR = new Creator<Category>() {
		@Override
		public Category createFromParcel(Parcel in) {
			return new Category(in);
		}

		@Override
		public Category[] newArray(int size) {
			return new Category[size];
		}
	};

	public void setPluralName(String pluralName){
		this.pluralName = pluralName;
	}

	public String getPluralName(){
		return pluralName;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setIcon(Icon icon){
		this.icon = icon;
	}

	public Icon getIcon(){
		return icon;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setShortName(String shortName){
		this.shortName = shortName;
	}

	public String getShortName(){
		return shortName;
	}

	public void setPrimary(boolean primary){
		this.primary = primary;
	}

	public boolean isPrimary(){
		return primary;
	}

	@Override
 	public String toString(){
		return 
			"Category{" +
			"pluralName = '" + pluralName + '\'' + 
			",name = '" + name + '\'' + 
			",icon = '" + icon + '\'' + 
			",id = '" + id + '\'' + 
			",shortName = '" + shortName + '\'' + 
			",primary = '" + primary + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(pluralName);
		dest.writeString(name);
		dest.writeString(id);
		dest.writeString(shortName);
		dest.writeByte((byte) (primary ? 1 : 0));
	}
}