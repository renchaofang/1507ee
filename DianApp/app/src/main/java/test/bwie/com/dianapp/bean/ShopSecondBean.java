package test.bwie.com.dianapp.bean;

import org.json.JSONArray;

/**
 * Created by 1 on 2017/10/19.
 */

public class ShopSecondBean {
    private String name;
    private String pic;
    private JSONArray jsonArray;


    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public ShopSecondBean(String name, String pic, JSONArray jsonArray) {
        this.name = name;
        this.pic = pic;
        this.jsonArray = jsonArray;
    }

    public ShopSecondBean() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public ShopSecondBean(String name) {
        this.name = name;
    }
}
