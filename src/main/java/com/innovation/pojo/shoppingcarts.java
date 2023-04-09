package com.innovation.pojo;

import org.apache.ibatis.type.Alias;

@Alias("shoppingcarts")
public class shoppingcarts {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public shoppingcarts(int id, String name, String type, float price, int count) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.count = count;
    }

    public shoppingcarts() {
        super();
    }

    private int id;
    private String name;
    private String type;
    private float price;
    private int count;
}
