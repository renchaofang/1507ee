package test.bwie.com.cliuapp.mvp_view;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/9/13 08:30
 */
public interface IUserNameLogin {

    String gname();
    String gpassword();

    void LoginSuccess();
    void LoginFail();
}
