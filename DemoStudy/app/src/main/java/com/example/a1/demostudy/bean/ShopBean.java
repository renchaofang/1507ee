package com.example.a1.demostudy.bean;

/**
 * Created by 1 on 2017/10/15.
 */

public class ShopBean {

    private int price;
    private String title;
    private String image;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ShopBean(int price, String title, String image) {
        this.price = price;
        this.title = title;
        this.image = image;
    }

    public ShopBean() {

    }

    @Override
    public String toString() {
        return "ShopBean{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ShopBean(String title, String image) {
        this.title = title;
        this.image = image;
    }
}
