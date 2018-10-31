package com.single.valar.keepaccounts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class NoteActivity extends AppCompatActivity {
    private CircleImageView imageView;
    private TextView timeText;
    private TextView note_typename;
    private TextView note_money;
    private EditText note_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note2);
        imageView=(CircleImageView)findViewById(R.id.imageview);
        timeText=(TextView)findViewById(R.id.timeText);
        note_typename=(TextView)findViewById(R.id.note_typename);
        note_money=(TextView)findViewById(R.id.note_money);
        note_edit=(EditText)findViewById(R.id.note_edit);
        Calendar calendar=Calendar.getInstance();

        note_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
             Datatools.notes=note_edit.getText().toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        imageView.setImageResource(Datatools.imgId);
        timeText.setText(calendar.get(Calendar.MONTH+1)+"月"+calendar.get(Calendar.DAY_OF_MONTH)+"日");
        note_typename.setText(Datatools.typename!=null?Datatools.typename:"一般");
        note_money.setText(Datatools.money);

    }

    public void backbtn(View view){
        finish();
    }
}
