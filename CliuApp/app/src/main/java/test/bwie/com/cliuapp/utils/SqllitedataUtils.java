package test.bwie.com.cliuapp.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/9/6 08:20
 */
public class SqllitedataUtils extends SQLiteOpenHelper {
    public SqllitedataUtils(Context context) {
        super(context, "shopping.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
            //进行创建数据库
        String path = "create table shop(_sid Integer primary key autoincrement," +
                "shopName varchar(20)," +
                "shopPrice integer," +
                "shopPickUrl varchar(20) )";
        String userpath = "create table user(_uid Integer primary key autoincrement," +
                "username varchar(20)," +
                "userpassword varchar(20))";

        String lishipath = "create table decord(_lid Integer primary key autoincrement," +
                "lishi varchar(20))";
        db.execSQL(path);
        //创建用户的数据库
        db.execSQL(userpath);
        //历史记录
        db.execSQL(lishipath);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
