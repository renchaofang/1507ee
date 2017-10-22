package test.bwie.com.cliuapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import test.bwie.com.cliuapp.R;
import test.bwie.com.cliuapp.dao.SqliteDao;

public class SeekActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageView;
    private TextView textView;
    private EditText editText;
    private ListView listView;
    private Button button;
    private ArrayAdapter<String> arrayAdapter;
    private SqliteDao sqliteDao;
    private List<String> arrayList;
    public static  String sousuopath = "http://list.mogujie.com/search?_version=61&ratio=3%3A4&q=%25E8%2583%258C%25E5%258C%2585&cKey=46&minPrice=&_mgjuuid=dbcc6b5c-fcf7-49f4-b807-3e85fbcccc5b&ppath=&page=1&maxPrice=&sort=pop&userId=&cpc_offset=&priceList=&_=1504520539364&callback=jsonp1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek);
        sqliteDao = new SqliteDao(SeekActivity.this);
        initview();
        arrayList = sqliteDao.findrecord();
        if(arrayList.size()>0){
            Log.i("xxx", "集合的长度" + arrayList.size());
            button.setVisibility(View.VISIBLE);
            //进行适配数据
            arrayAdapter = new ArrayAdapter<String>(SeekActivity.this,
                      android.R.layout.simple_expandable_list_item_1,
                    arrayList);
            //进行是适配数据
            listView.setAdapter(arrayAdapter);
        }else{
            //进行隐藏
            button.setVisibility(View.GONE);
        }
    }
    //初始化资源的控件
    public void initview(){
        imageView = (ImageView)findViewById(R.id.back_btn);
        textView = (TextView)findViewById(R.id.seek_btn);
        editText = (EditText)findViewById(R.id.seek_name);
        listView = (ListView)findViewById(R.id.context_listview);
        button = (Button)findViewById(R.id.context_btn);
        imageView.setOnClickListener(this);
        textView.setOnClickListener(this);
        editText.setOnClickListener(this);
        button.setOnClickListener(this);


        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if(action==MotionEvent.ACTION_UP){
                    imageView.setImageResource(R.mipmap.ic_browser_nav_back);
                    finish();
                }else if(action==MotionEvent.ACTION_DOWN){
                    imageView.setImageResource(R.mipmap.ic_action_back);
                }
                return false;
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.seek_btn:
                // 获取得到输入的内容
                String string = editText.getText().toString();
                sqliteDao.adddrecord(string);
                //将数据转化为二进制
                Log.i("xxx", "获取得到文本框" + string);
                String sousuopath = "http://list.mogujie.com/search?_version=61" +
                        "&ratio=3%3A4&q="+string+"" +
                        "&cKey=46" +
                        "&minPrice=" +
                        "&_mgjuuid=dbcc6b5c-fcf7-49f4-b807-3e85fbcccc5b" +
                        "&ppath=" +
                        "&page=1&maxPrice=" +
                        "&sort=pop&userId=&cpc_offset=&priceList=&_=1504520539364&callback=jsonp1";
                Toast.makeText(SeekActivity.this, string, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SeekActivity.this,ShoppingActivity.class);
                intent.putExtra("data",sousuopath);
                startActivity(intent);
                break;
            case R.id.context_btn:
                    //将集合清空
                sqliteDao.deleterecord();
                //在进行查询
                List<String> findrecord = sqliteDao.findrecord();
                Log.i("xxx", "清空之后" + arrayList .size());
                //然后再次进行加载
                arrayAdapter = new ArrayAdapter<String>(SeekActivity.this,
                        android.R.layout.simple_expandable_list_item_1,
                             findrecord );
                //进行是适配数据
                listView.setAdapter(arrayAdapter);
                button.setVisibility(View.GONE);
                break;
        }
    }

    //将16进制转化为字符串
    public static String str2HexStr(String str)
    {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++)
        {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }

}
