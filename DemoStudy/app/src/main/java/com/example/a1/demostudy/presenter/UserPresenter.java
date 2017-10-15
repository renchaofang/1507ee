package com.example.a1.demostudy.presenter;

import com.example.a1.demostudy.model.IUserModel;
import com.example.a1.demostudy.model.UserModel;
import com.example.a1.demostudy.view.IUserLogin;
import com.example.a1.demostudy.view.IUserShop;
import com.example.a1.demostudy.view.IUserView;

/**
 * Created by 1 on 2017/10/14.
 */

public class UserPresenter implements IUserPresenter {

    private IUserView iUserView;
    private IUserModel iUserModel;
    private IUserLogin IUserlogin;
    private IUserShop iUserShop;
    //注册
    public UserPresenter(IUserView iUserView) {
        this.iUserView=iUserView;
        iUserModel = new UserModel();
    }
    //登录
    public UserPresenter(IUserLogin IUserlogin) {
        this.IUserlogin=IUserlogin;
        iUserModel = new UserModel();
    }
    //展示数据
    public UserPresenter(IUserShop iUserShop) {
        this.iUserShop=iUserShop;
        iUserModel = new UserModel();
    }
    @Override
    public void getData(String name, String pass) {
        iUserModel.getResgit(name,pass,iUserView);
    }

    @Override
    public void getLogin(String name, String pass) {
        iUserModel.getLogin(name,pass,IUserlogin);
    }

    @Override
    public void getShoppingData(String type,int page) {
           iUserModel.getShowData(type,page,iUserShop);
    }
}
