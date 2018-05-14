package me.kamili.rachid.seattleplace.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BestPhoto implements Parcelable{

	@SerializedName("prefix")
	private String prefix;

	@SerializedName("width")
	private int width;

	@SerializedName("suffix")
	private String suffix;

	@SerializedName("height")
	private int height;

	protected BestPhoto(Parcel in) {
		prefix = in.readString();
		width = in.readInt();
		suffix = in.readString();
		height = in.readInt();
	}

	public static final Creator<BestPhoto> CREATOR = new Creator<BestPhoto>() {
		@Override
		public BestPhoto createFromParcel(Parcel in) {
			return new BestPhoto(in);
		}

		@Override
		public BestPhoto[] newArray(int size) {
			return new BestPhoto[size];
		}
	};

	public void setPrefix(String prefix){
		this.prefix = prefix;
	}

	public String getPrefix(){
		return prefix;
	}

	public void setWidth(int width){
		this.width = width;
	}

	public int getWidth(){
		return width;
	}

	public void setSuffix(String suffix){
		this.suffix = suffix;
	}

	public String getSuffix(){
		return suffix;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public int getHeight(){
		return height;
	}

	@Override
 	public String toString(){
		return 
			"BestPhoto{" + 
			"prefix = '" + prefix + '\'' + 
			",width = '" + width + '\'' + 
			",suffix = '" + suffix + '\'' + 
			",height = '" + height + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(prefix);
		dest.writeInt(width);
		dest.writeString(suffix);
		dest.writeInt(height);
	}
}