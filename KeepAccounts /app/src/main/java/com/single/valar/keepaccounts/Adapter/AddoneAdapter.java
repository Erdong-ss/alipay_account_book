package com.single.valar.keepaccounts.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.single.valar.keepaccounts.Datatools;
import com.single.valar.keepaccounts.Javabean.CostTypeData;
import com.single.valar.keepaccounts.R;

import java.util.List;
import java.util.logging.Handler;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddoneAdapter extends RecyclerView.Adapter<AddoneAdapter.ViewHolder>{
    private List<CostTypeData> mtypeDataList;
    private Context mContext;
    // 使用内部类获取布局中的组件
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView typedataCiv;
        TextView typedataTv;
        public ViewHolder(View view){
          super(view);
          typedataCiv=(ImageView) view.findViewById(R.id.typedata_civ);
          typedataTv=(TextView)view.findViewById(R.id.typedata_tv);
        }

    }
    public AddoneAdapter(Context context, List<CostTypeData> typeDataList){
        mtypeDataList=typeDataList;
        this.mContext=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cost_type_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
       final CostTypeData costTypeData=mtypeDataList.get(position);
       viewHolder.typedataTv.setText(costTypeData.getTypeName());
       viewHolder.typedataCiv.setBackground(mContext.getDrawable(costTypeData.getImgID()));
        viewHolder.typedataCiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackground(mContext.getDrawable(R.mipmap.finish));
                Datatools.imgId=mtypeDataList.get(position).getImgID();
                Datatools.typename=mtypeDataList.get(position).getTypeName();
                Datatools.position=position;
            }
        });
    }
    public int getItemCount(){
        return mtypeDataList.size();

    }


}
