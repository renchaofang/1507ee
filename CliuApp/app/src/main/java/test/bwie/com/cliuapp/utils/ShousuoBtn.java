package test.bwie.com.cliuapp.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.类的用途
 * 2.@author1
 * 3.@data2017/9/610:43
 */
public class ShousuoBtn  {
   public List<String> liststring = new ArrayList<>();;
    public void getbtndata(String name){

        //将其放入数据库里面去
        liststring.add(name);
    }
    public List<String> find(){
        return  liststring;
    }
    public boolean getbooleandata(List<String> list){
        if(list.size()==0){
            return false;
        }else{
            return true;
        }
    }
    //清空数据库
    public List<String> delete(List<String> list){
        list.clear();
        return list;
    }





}
