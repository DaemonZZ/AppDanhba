package com.contact;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;




public class Contact implements Parcelable {
    private Bitmap photo;
    private String name;
    private String number;

    public Contact() {
    }

    public Contact(Bitmap photo, String name, String number) {
        this.photo = photo;
        this.name = name;
        this.number = number;
    }

    protected Contact(Parcel in) {
        photo = in.readParcelable(Bitmap.class.getClassLoader());
        name = in.readString();
        number = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        Bitmap inp ;
        if(photo.getByteCount()>1000000){
            inp = Bitmap.createScaledBitmap(photo,photo.getWidth()/10,photo.getHeight()/10,false);
        }
        else {
            inp=photo;
        }
        this.photo = inp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(photo, flags);
        dest.writeString(name);
        dest.writeString(number);
    }
}
