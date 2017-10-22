package test.bwie.com.cliuapp.mvp_persenter;

import test.bwie.com.cliuapp.mvp_molder.IPassWordMolder;
import test.bwie.com.cliuapp.mvp_molder.UserPassMolder;
import test.bwie.com.cliuapp.mvp_view.IUserNameView;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/9/12 23:13
 */
public class ResgterPerenter {
    private IUserNameView iUserNameView;
    private final IPassWordMolder iPassWordMolder;

    public ResgterPerenter(IUserNameView iUserNameView) {
        super();
        this.iUserNameView=iUserNameView;
        iPassWordMolder = new UserPassMolder();
    }
    //进行两个层之间的数据的传输
    public void sendData(String name ,String password ,String email,IUserNameView iusername){
        iPassWordMolder.doResgitUser(name,password,email,iUserNameView);
    }

}
