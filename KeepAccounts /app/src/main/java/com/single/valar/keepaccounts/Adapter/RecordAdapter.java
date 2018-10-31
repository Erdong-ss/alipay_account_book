package com.single.valar.keepaccounts.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.single.valar.keepaccounts.Datatools;
import com.single.valar.keepaccounts.Javabean.CostTypeData;
import com.single.valar.keepaccounts.Javabean.Record;
import com.single.valar.keepaccounts.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {
    // 使用内部类获取布局中的组件
    private List<Record> records;
    private Context context;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_textviewNote;
        TextView item_textviewAmout;
        CircleImageView item_circleimage;
        View view;
        public ViewHolder(View view){
            //接受的view是ViewHolder中实例化布局文件中的view
            super(view);
            this.view=view;
            item_circleimage=(CircleImageView)view.findViewById(R.id.item_circleimage);
            item_textviewNote=(TextView)view.findViewById(R.id.item_textviewNote);
            item_textviewAmout=(TextView)view.findViewById(R.id.item_textviewAmout);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int ViewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        // 这个holder 包含组件所有信息
        return holder;
    }

    // 构造函数  获取对象
    public RecordAdapter(List<Record> records, Context context){
        this.context=context;
        this.records=records;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Record record = records.get(position);
        viewHolder.item_textviewAmout.setText(Datatools.amountToString(record.getAmount(),record.getIncome()));
        viewHolder.item_textviewNote.setText(record.getNote());
        // 这个地方需要修改 做图标适配
        CostTypeData costTypeData=Datatools.getInstance().getCostTypelist().get(record.getType());
        viewHolder.item_circleimage.setImageResource(costTypeData.getImgID());
        viewHolder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu menu=new PopupMenu(context,v);
                menu.getMenuInflater().inflate(R.menu.delmenu,menu.getMenu());
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Datatools.getInstance().getRecordList().remove(position);
                        return true;
                    }
                });
                menu.show();
                return false;
            }
        });
    }
    public int getItemCount(){
        return records.size();
    }
}
