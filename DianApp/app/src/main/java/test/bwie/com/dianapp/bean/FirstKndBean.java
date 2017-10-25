package test.bwie.com.dianapp.bean;

import java.io.Serializable;

/**
 * Created by 1 on 2017/10/19.
 */

public class FirstKndBean implements Serializable{
    private int cid;
    private String title;

    public FirstKndBean() {
        super();
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FirstKndBean(int cid, String title) {
        this.cid = cid;
        this.title = title;
    }
}
