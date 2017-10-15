package com.example.a1.demostudy.view;

import com.example.a1.demostudy.bean.ShopBean;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by 1 on 2017/10/14.
 */

public interface IUserShop {
    void getData(String type,int page);
    void AddData(List<ShopBean> jsonArray);
}
