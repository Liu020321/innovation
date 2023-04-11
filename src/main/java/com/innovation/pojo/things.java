package com.innovation.pojo;

public class things {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public things(int id, String name, String type, String price, int count) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.count = count;
    }

    public things() {
        super();
    }

    private int id;
    private String name;
    private String type;
    private String price;
    private int count;

}
