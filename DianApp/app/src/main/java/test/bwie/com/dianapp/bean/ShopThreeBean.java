package test.bwie.com.dianapp.bean;

/**
 * Created by 1 on 2017/10/19.
 */

public class ShopThreeBean {

    private String icon;
    private String name;

    public ShopThreeBean() {
        super();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShopThreeBean(String icon, String name) {
        this.icon = icon;
        this.name = name;
    }
}
