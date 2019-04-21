package com.example.week020420.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.week020420.R;
import com.example.week020420.model.JsonBean;

/**
 * @Auther: 白俊岭
 * @Date: 2019/4/20 11:08:58
 * @Description:
 */
public class PzshAdapter extends RecyclerView.Adapter<PzshAdapter.ViewHolder> {
    Context context; JsonBean.ResultBean.PzshBean pzsh;
    public PzshAdapter(Context context, JsonBean.ResultBean.PzshBean pzsh) {
        this.context=context;
        this.pzsh=pzsh;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_fragment_rxxp_pzss, null);
        PzshAdapter.ViewHolder viewHolder = new PzshAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
         viewHolder.name.setText(pzsh.getCommodityList().get(i).getCommodityName());
         viewHolder.price.setText(pzsh.getCommodityList().get(i).getPrice()+"");
         viewHolder.img.setImageURI(Uri.parse(pzsh.getCommodityList().get(i).getMasterPic()));
    }


    @Override
    public int getItemCount() {
        return pzsh.getCommodityList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView price;
        public ViewHolder(View itemView) {

            super(itemView);

            img=   itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
        }
    }
}
