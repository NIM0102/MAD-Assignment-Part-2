package com.example.demo;

class Product
{
    private String id;
    private String name;
    private int image;
    private int image2;
    private String desc;

    public Product(String id, String name, int image, int image2, String desc) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.image2 = image2;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public int getImage2() {return image2;}

    public String getDesc() {return desc;}

}