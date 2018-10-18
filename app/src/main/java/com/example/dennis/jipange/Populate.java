package com.example.dennis.jipange;

/**
 * Created by dennis on 3/2/2018.
 */

public class Populate {

    private int id;
    private byte[] image;
    private String Name;
    public Populate(String Name,byte[] image, int id)
    {
        this.Name = Name;
        this.image = image;
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
