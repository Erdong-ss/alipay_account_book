package com.single.valar.keepaccounts;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.single.valar.keepaccounts.Adapter.AddoneAdapter;

import java.util.Calendar;

public class AddoneActivity extends AppCompatActivity implements View.OnClickListener {
    private android.support.v7.widget.Toolbar toolbar;
    private TextView textView;
    private RecyclerView costTypeRV;
    private boolean isincome=false;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn0;
    private Button btnOk;
    private Button btndian;
    private Button databtn;
    private Button btnnote;
    private TextView typetextview;
    private ImageButton btndelete;
    private DatePickerDialog datePickerDialog;
    private AddoneAdapter adapter;
    private DatePickerDialog.OnDateSetListener onSetListener=new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            databtn.setText((month+1)+"-"+dayOfMonth);
            mmonth=month;
            myear=year;
            mday=dayOfMonth;
        }
    };
    private Calendar calendar=Calendar.getInstance();
    private int myear=calendar.get(Calendar.YEAR);
    private int mmonth=calendar.get(Calendar.MONTH);
    private int mday=calendar.get(Calendar.DAY_OF_MONTH);
    private String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addone);
        initView();
        setSupportActionBar(toolbar);

    }
    // 进行初始化
    public void initView(){
        textView= (TextView)findViewById(R.id.edittext);
        costTypeRV=(RecyclerView)findViewById(R.id.costType_rv);
        toolbar= (android.support.v7.widget.Toolbar)findViewById(R.id.addonetoolbar);
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        btn4=(Button)findViewById(R.id.btn4);
        btn5=(Button)findViewById(R.id.btn5);
        btn6=(Button)findViewById(R.id.btn6);
        typetextview=(TextView)findViewById(R.id.typetextview);
        btn7=(Button)findViewById(R.id.btn7);
        btn8=(Button)findViewById(R.id.btn8);
        btn9=(Button)findViewById(R.id.btn9);
        btnOk=(Button)findViewById(R.id.btnOK);
        btndian=(Button)findViewById(R.id.btndian);
        btn0=(Button)findViewById(R.id.btn0);
        btnnote=(Button)findViewById(R.id.notebtn);
        databtn=(Button)findViewById(R.id.databtn) ;
        btndelete =(ImageButton)findViewById(R.id.btndelete);
        //设置监听器
        databtn.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btndian.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btnnote.setOnClickListener(this);
        btndelete.setOnClickListener(this);
        // 时间选择对话框
        datePickerDialog = new DatePickerDialog(this,onSetListener,myear,mmonth,mday);
        //更新时间
        databtn.setText((mmonth+1)+"-"+mday);
        //设置RecycleView
        GridLayoutManager layoutManager=new GridLayoutManager(this,5);
        costTypeRV.setLayoutManager(layoutManager);
        adapter=new AddoneAdapter(this,Datatools.getInstance().getCostTypelist());
        costTypeRV.setAdapter(adapter);


    }
    public void returnMain(View view){
        finish();
    }

    // 实现view点击监听
    @Override
    public void onClick(View v) {
        if (textView.getText().toString().equals("0"))
            textView.setText("");
      switch (v.getId()){
          case R.id.btn0:
              adapter.notifyDataSetChanged();
              textView.append("0");break;
          case R.id.btn1:
              textView.append("1");break;
          case R.id.btn2:
              textView.append("2");break;
          case R.id.btn3:
              textView.append("3");break;
          case R.id.btn4:
              textView.append("4");break;
          case R.id.btn5:
              textView.append("5");break;
          case R.id.btn6:
              textView.append("6");break;
          case R.id.btn7:
              textView.append("7");break;
          case R.id.btn8:
              textView.append("8");break;
          case R.id.btn9:
              textView.append("9");break;
          case R.id.btndian:
              textView.append(".");break;
          case R.id.databtn:
              datePickerDialog.show();break;
          case R.id.notebtn:
              Intent intent=new Intent(AddoneActivity.this,NoteActivity.class);
              //startActivity(new Intent(AddoneActivity.this,NoteActivity.class));
              startActivity(intent);
              break;
          case R.id.btndelete:
              text=textView.getText().toString();
              if(text.length()>0)
              textView.setText(text.substring(0,text.length()-1));
              break;
          case R.id.btnOK:
              Datatools.getInstance().SaveDatas(Datatools.position,myear,mmonth, mday,Datatools.notes!=null?Datatools.notes:Datatools.typename!=null?Datatools.typename:"一般",
                      Float.valueOf(textView.getText().toString()),isincome);
              finish();
              break;


    }
        Datatools.money=textView.getText().toString();

}

   public void choiceType(View view){
       PopupMenu menu=new PopupMenu(this,view);
       menu.getMenuInflater().inflate(R.menu.choicetype,menu.getMenu());
       menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
           @Override
           public boolean onMenuItemClick(MenuItem item) {
               if (item.getItemId()==R.id.item_pay) {
                   isincome=false;
                   typetextview.setText("支出");
               }
               else {
                   typetextview.setText("收入");
                   isincome=true;
               }
               return true;
           }
       });
       menu.show();

   }


}
