package test.bwie.com.cliuapp.bean;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/9/8 09:27
 */
public class LogBean {
    private int pick;
    private String title;

    public int getPick() {
        return pick;
    }

    public void setPick(int pick) {
        this.pick = pick;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "LogBean{" +
                "pick=" + pick +
                ", title='" + title + '\'' +
                '}';
    }

    public LogBean(int pick, String title) {
        this.pick = pick;
        this.title = title;
    }
}
