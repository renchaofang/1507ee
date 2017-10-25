package test.bwie.com.dianapp.bean;

/**
 * Created by 1 on 2017/10/19.
 */

public class ShopDetailsBean {
    private int pid;
    private String img ;
    private String title;
    private String dianapu;
    private int price;
    private String fubiaoti;
    //店铺ID
    private String sellerid;

    public ShopDetailsBean(int pid, String img, String title, String dianapu, int price, String fubiaoti, String sellerid) {
        this.pid = pid;
        this.img = img;
        this.title = title;
        this.dianapu = dianapu;
        this.price = price;
        this.fubiaoti = fubiaoti;
        this.sellerid = sellerid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public ShopDetailsBean() {
        super();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDianapu() {
        return dianapu;
    }

    public void setDianapu(String dianapu) {
        this.dianapu = dianapu;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFubiaoti() {
        return fubiaoti;
    }

    public void setFubiaoti(String fubiaoti) {
        this.fubiaoti = fubiaoti;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

}
