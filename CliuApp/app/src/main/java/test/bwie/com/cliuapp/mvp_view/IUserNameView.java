package test.bwie.com.cliuapp.mvp_view;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/9/12 23:00
 */
public interface IUserNameView {
    //进行抽取方法
    String getname();
    String getpassword();
    String getemail();
    void ResgitSuccess();
    void ResgitFail();
}
