package com.single.valar.keepaccounts;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import org.litepal.LitePal;
import org.w3c.dom.Text;

import java.util.Calendar;

/**
 *    单开一个线程 一直监听datatools 数据变化 一有变化就发送message 并复原
 *
 */

public class MainActivity extends AppCompatActivity {
    //changliang
    private final int ISREADALL=1111;
    // bianliang
    private Toolbar         toolbar ;
    private TextView        yearText;
    private TabLayout       tabLayout;
    private TextView        monthText;
    private TextView        incomeText;
    private TextView        payText;
    private Calendar        calendar;
    private Message         msg        =new   Message();
    private Handler         handler;
    private FragmentManager manager=getSupportFragmentManager();
    private DetailFragment  fragment;
    private BlankFragment   blankfragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //初始化数据库
        LitePal.initialize(this);
        SQLiteDatabase db=LitePal.getDatabase();
        setSupportActionBar(toolbar);
        Datatools.getInstance().readAll();
        messageHandle();
        // chuang jian yige jiance xianchengr
        new MyThread().start();

    }

    public void init(){
        yearText    =(TextView)   findViewById(R.id.yearText);
        monthText   =(TextView)   findViewById(R.id.monthText);
        incomeText  =(TextView)   findViewById(R.id.income_textview);
        payText     =(TextView)   findViewById(R.id.pay_textview);
        toolbar     =(Toolbar)    findViewById(R.id.toolbar);
        tabLayout   =(TabLayout)findViewById(R.id.tablayout);
        fragment    =new DetailFragment();
        blankfragment=new BlankFragment();
        calendar    =Calendar.getInstance();

        //自动检测并设置日期
        yearText.setText(String.valueOf(calendar.get(Calendar.YEAR)));
        // 获取的月份是从0开始的因此需要加一
        monthText.setText(String.valueOf(calendar.get(Calendar.MONTH)+1));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i(" ", "onTabSele😄cted: "+tab.getPosition());
                switch (tab.getPosition()){
                    case 0:
                        manager.beginTransaction().replace(R.id.framelayout,fragment);
                        break;
                    case 1:
                        manager.beginTransaction().replace(R.id.framelayout,blankfragment);
                        break;
                    case 2:
                        manager.beginTransaction().replace(R.id.framelayout,blankfragment);
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
     //处理发送消息
    public void messageHandle(){
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case ISREADALL:
                        //载入recycleview数据
                        manager.beginTransaction().replace(R.id.framelayout,fragment).commit();
                        incomeText.setText(String.valueOf(
                                Datatools.getInstance().
                                        getIncomecount(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH))));
                        payText.setText(String.valueOf(
                                Datatools.getInstance().
                                        getPaycount(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH))));
                        break;
                }
                return  false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    public void EnterAddone(View view){
        startActivity(new Intent(MainActivity.this,AddoneActivity.class));
        //new MyThread().start();
    }
    //检测数据是否读取完毕 为了不占用Ui线程资源 单开的线程
    class MyThread extends  Thread{
        @Override
        public void run() {

            while(true){
            //System.out.println(Datatools.isreadAll+"😄😄😄😄😄😄😄😄😄");
            if (Datatools.isreadAll){
                msg.what=ISREADALL;
                handler.sendMessage(msg);
                Datatools.isreadAll=false;
                break;
            }

            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onResume() {
        incomeText.setText(String.valueOf(
                Datatools.getInstance().
                        getIncomecount(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH))));
        payText.setText(String.valueOf(
                Datatools.getInstance().
                        getPaycount(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH))));
        super.onResume();
    }
}

