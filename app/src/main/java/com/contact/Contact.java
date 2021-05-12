package com.contact;

import android.graphics.Bitmap;

public class Contact {
    Bitmap photo;
    String name;
    String number;

    public Contact() {
    }

    public Contact(Bitmap photo, String name, String number) {
        this.photo = photo;
        this.name = name;
        this.number = number;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
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
}
