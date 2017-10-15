package com.example.a1.demostudy.model;

import com.example.a1.demostudy.view.IUserLogin;
import com.example.a1.demostudy.view.IUserShop;
import com.example.a1.demostudy.view.IUserView;

/**
 * Created by 1 on 2017/10/14.
 */

public interface IUserModel {
    void getResgit(String name , String pass, IUserView iUserView);
    void getLogin(String name , String pass, IUserLogin iUserLogin);
    void getShowData(String type,int page, IUserShop iUserShop);
}
