package test.bwie.com.cliuapp.bean;

import java.io.Serializable;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/9/16 09:17
 */
public class IDandNumBean implements Serializable{
    private int id ;
    private int num;

    public IDandNumBean(int id, int num) {
        this.id = id;
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
