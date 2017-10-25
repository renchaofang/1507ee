package test.bwie.com.dianapp.bean;

import java.io.Serializable;

/**
 * Created by 1 on 2017/10/18.
 */

public class ShowInfoBean implements Serializable{

    public int count;
    private int pid;
    private String title;
    private String subhead;
    private String image;
    private int price;




    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public ShowInfoBean(int pid, String title, String subhead, String image, int price) {
        this.pid = pid;
        this.title = title;
        this.subhead = subhead;
        this.image = image;
        this.price = price;
    }

    public ShowInfoBean() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubhead() {
        return subhead;
    }

    public void setSubhead(String subhead) {
        this.subhead = subhead;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ShowInfoBean(String title, String subhead, String image, int price) {
        this.title = title;
        this.subhead = subhead;
        this.image = image;
        this.price = price;
    }
}
