package com.single.valar.keepaccounts;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;

import com.single.valar.keepaccounts.Adapter.RecordAdapter;
import com.single.valar.keepaccounts.Javabean.CostTypeData;
import com.single.valar.keepaccounts.Javabean.Record;

import org.litepal.LitePal;
import org.litepal.crud.callback.FindMultiCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 此类用于处理各种信息 将操作做到最简单
 */
public class Datatools {
    //单例模式
    private Datatools(){}
    private int mYear;
    private int mMonth;
    private int mDay;
    private Calendar calendar;
    private static Datatools instance;
    public static Boolean isreadAll=false;
    public  List<Record> records;
    public static RecordAdapter adapter;
    public static String money="0";
    public static String notes;
    public static int position=0;
    public static int imgId;
    public static String typename;
    public static Datatools getInstance(){
        if (instance==null)
            instance = new Datatools();
        return instance;
    }
    //保存数据方法
    public  void SaveDatas(int type,int year,int month,int day,String note,float amount,Boolean isIncome){
        Record record = new Record();
        record.setAmount(amount);
        record.setYear(year);
        record.setDay(day);
        record.setMonth(month);
        record.setNote(note);
        record.setType(type);
        record.setIncome(isIncome);
        record.save();
        records.add(record);
    }
    //读取所有数据 在系统初始化的时候调用  因为异步操作的原因 数据会慢半拍拿到
    public void readAll(){
      records=LitePal.findAll(Record.class);
      Datatools.isreadAll=true;
    }
    public List<Record> getRecordList() {
        return records;
    }

    //获取时间文本
    public String getTime(){
        calendar=Calendar.getInstance();
        return  null;
    }
    //数据金额文本处理
    public static String amountToString(float amount,Boolean isIncome){
        if(amount<=0.0)return "0.0";
        if (isIncome)
            return ("+"+String.valueOf(amount));
        return ("-"+String.valueOf(amount));
    }
    //Put datas into CostTypeList
    public List<CostTypeData> getCostTypelist(){
        List<CostTypeData> list=new ArrayList<>();
        list.add(new CostTypeData("一般",R.mipmap.normalico));
        list.add(new CostTypeData("餐饮",R.mipmap.foodsico));
        list.add(new CostTypeData("购物",R.mipmap.shoppingico));
        list.add(new CostTypeData("服饰",R.mipmap.clothesico));
        list.add(new CostTypeData("交通",R.mipmap.trafficico));
        list.add(new CostTypeData("娱乐",R.mipmap.funningico));
        list.add(new CostTypeData("社交",R.mipmap.socil));
        list.add(new CostTypeData("居家",R.mipmap.homeico));
        list.add(new CostTypeData("通讯",R.mipmap.phoneico));
        list.add(new CostTypeData("零食",R.mipmap.snakeico));
        return list;
    }
    //获取总收入文本
    public float getIncomecount(int year,int month){
       float num=0;
        for (Record r:records) {
            if (!r.getIncome()&&r.getYear()==year&&r.getMonth()==month)
                num=num+r.getAmount();
        }
        return num;
    }
    //获取总支出文本
    public float getPaycount(int year,int month){
        float num=0;
        for (Record r:records) {
            if (r.getIncome()&&r.getYear()==year&&r.getMonth()==month)
                num=num+r.getAmount();
        }
        return num;
    }

}
