package test.bwie.com.cliuapp.mvp_persenter;

import test.bwie.com.cliuapp.mvp_molder.IPassWordMolder;
import test.bwie.com.cliuapp.mvp_molder.UserPassMolder;
import test.bwie.com.cliuapp.mvp_view.IUserNameLogin;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/9/12 23:13
 */
public class LoginPerenter {
    private IUserNameLogin iUserNameLogin;
    private final IPassWordMolder iPassWordMolder;
    public LoginPerenter(IUserNameLogin iUserNameLogin) {
        super();
        this.iUserNameLogin=iUserNameLogin;
        iPassWordMolder = new UserPassMolder();
    }
    public void loginData(String name,String pass,IUserNameLogin iUserNameLogin){
        iPassWordMolder.doLoginUser(name,pass,iUserNameLogin);
    }
}
