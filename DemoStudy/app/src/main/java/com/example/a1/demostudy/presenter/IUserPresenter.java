package com.example.a1.demostudy.presenter;

/**
 * Created by 1 on 2017/10/14.
 */

public interface IUserPresenter {

    void getData(String name , String pass);
    void getLogin(String name , String pass);
    void getShoppingData(String type,int page);
}
