package com.example.dennis.jipange;

/**
 * Created by dennis on 3/9/2018.
 */

public class histolist {
    private int id;
    private String Name;
    private String Price;
    private int status;




    public histolist(String Name, String Price, int id, int status )
    {
        this.Name = Name;
        this.Price = Price;
        this.id = id;
        this.status = status;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
    public int getStatus() {
        return status;
    }




}


