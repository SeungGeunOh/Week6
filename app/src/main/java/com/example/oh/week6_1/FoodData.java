package com.example.oh.week6_1;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by OH on 2017-04-06.
 */

public class FoodData implements Parcelable{
    String name;
    int type;
    String tel;
    String menu1;
    String menu2;
    String menu3;
    String homepage;

    public FoodData(String name, int type, String tel, String menu1, String menu2, String menu3, String homepage){
        this.name = name;
        this.type = type;
        this.tel = tel;
        this.menu1 = menu1;
        this.menu2 = menu2;
        this.menu3 = menu3;
        this.homepage = homepage;
    }

    protected FoodData(Parcel in) {
        name = in.readString();
        type = in.readInt();
        tel = in.readString();
        menu1 = in.readString();
        menu2 = in.readString();
        menu3 = in.readString();
        homepage = in.readString();
    }

    public static final Creator<FoodData> CREATOR = new Creator<FoodData>() {
        @Override
        public FoodData createFromParcel(Parcel in) {
            return new FoodData(in);
        }

        @Override
        public FoodData[] newArray(int size) {
            return new FoodData[size];
        }
    };

    public String getName(){
        return name;
    }

    public int getType(){
        return type;
    }

    public String getTel(){
        return tel;
    }

    public String getMenu1(){
        return menu1;
    }

    public String getMenu2(){
        return menu2;
    }

    public String getMenu3(){
        return menu3;
    }

    public String getHomepage(){
        return homepage;
    }

    @Override
    public String toString() {
        String str = name + " " + type + " " + tel  + " " + menu1  + " " + menu2 + " " + menu3  + " " + homepage;
        return str;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(type);
        dest.writeString(tel);
        dest.writeString(menu1);
        dest.writeString(menu2);
        dest.writeString(menu3);
        dest.writeString(homepage);
    }
}
