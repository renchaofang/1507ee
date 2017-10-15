package test.bwie.com.okhttoutils_rcf.app;

import android.app.Application;

/**
 * 1. 类的用途
 * 2. @author forever
 * 3. @date 2017/10/9 15:27
 */


public class MyApp extends Application {

    private static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static MyApp getInstance() {


        return app;
    }
}
