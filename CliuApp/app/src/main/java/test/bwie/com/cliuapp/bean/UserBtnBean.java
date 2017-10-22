package test.bwie.com.cliuapp.bean;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/9/4 20:27
 */
public class UserBtnBean {
    private String title;
    private int tupian;

    public UserBtnBean() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTupian() {
        return tupian;
    }

    public void setTupian(int tupian) {
        this.tupian = tupian;
    }

    public UserBtnBean(String title, int tupian) {
        this.title = title;
        this.tupian = tupian;
    }
}
