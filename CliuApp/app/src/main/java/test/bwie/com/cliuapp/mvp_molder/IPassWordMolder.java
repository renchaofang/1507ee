package test.bwie.com.cliuapp.mvp_molder;

import test.bwie.com.cliuapp.mvp_view.IUserNameLogin;
import test.bwie.com.cliuapp.mvp_view.IUserNameView;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/9/12 23:12
 */
public interface IPassWordMolder {
    //定义抽象方法
    void doResgitUser(String name ,String password ,String email,IUserNameView iusername);
    void doLoginUser(String name , String pass, IUserNameLogin iUserNameLogin);
}
