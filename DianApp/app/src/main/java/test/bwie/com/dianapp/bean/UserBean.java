package test.bwie.com.dianapp.bean;

/**
 * Created by 1 on 2017/10/17.
 */

public class UserBean {
    private String mobile;
    private String password;
    private String username;
    private int money;
    private int uid;

    public UserBean() {
        super();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public UserBean(String mobile, String password, String username, int money, int uid) {
        this.mobile = mobile;
        this.password = password;
        this.username = username;
        this.money = money;
        this.uid = uid;
    }
}
