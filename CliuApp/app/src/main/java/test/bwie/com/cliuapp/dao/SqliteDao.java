package test.bwie.com.cliuapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import test.bwie.com.cliuapp.utils.SqllitedataUtils;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/9/6 08:27
 */
public class SqliteDao {
    private static  final String TABLE_LISHI = "decord";
    private final SqllitedataUtils sqllitedataUtils;
    private final SQLiteDatabase db;

    public SqliteDao(Context context) {
        super();
        sqllitedataUtils = new SqllitedataUtils(context);
        //得到数据库资源
        db = sqllitedataUtils.getWritableDatabase();
    }
    //进行添加数据库的操作
    public void add(){}
    //查询数据信息
    public void find(){}
    //删除数据信息
    public void delete(){}

    public void adddrecord(String string){
        ContentValues values = new ContentValues();
        values.put("lishi",string);
        db.insert(TABLE_LISHI,null,values);
    }
    //查询数据信息
    public List<String> findrecord(){
        List<String> list = new ArrayList<>();
        Cursor query = db.query(TABLE_LISHI, null, null, null, null, null,null);
        while(query.moveToNext()){
            String lishi = query.getString(query.getColumnIndex("lishi"));
            list.add(lishi);
        }
       return  list;
    }
    //删除数据信息
    public void deleterecord(){
        db.delete(TABLE_LISHI,null,null);
    }
}
