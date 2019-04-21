package com.example.week020420.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.week020420.R;
import com.example.week020420.model.App;
import com.example.week020420.model.GreenDao;
import com.example.week020420.model.JsonBean;
import com.example.weektext0413.greendao.gen.GreenDaoDao;

import java.util.List;

/**
 * @Auther: 白俊岭
 * @Date: 2019/4/20 11:13:08
 * @Description:
 */
public class MlssAdapter extends RecyclerView.Adapter<MlssAdapter.ViewHolder> {
    Context context; JsonBean.ResultBean bean;
    GreenDaoDao greenDaoDao = App.daoSession.getGreenDaoDao();

    public MlssAdapter(Context context, JsonBean.ResultBean bean) {
        this.context=context;
        this.bean=bean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_fragment_mlss, null);
        MlssAdapter.ViewHolder viewHolder = new MlssAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(bean.getMlss().getCommodityList().get(i).getCommodityName());
        viewHolder.pirce.setText("价格："+bean.getMlss().getCommodityList().get(i).getPrice()+"");
        viewHolder.img.setImageURI(Uri.parse(bean.getMlss().getCommodityList().get(i).getMasterPic()));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<GreenDao> greenDaos = greenDaoDao.loadAll();
                GreenDao greenDao = new GreenDao();
                greenDao.setCommodityId(bean.getMlss().getCommodityList().get(i).getCommodityId());
                greenDao.setCommodityName(bean.getMlss().getCommodityList().get(i).getCommodityName());
                greenDao.setMasterPic(bean.getMlss().getCommodityList().get(i).getMasterPic());
                greenDao.setSaleNum(bean.getMlss().getCommodityList().get(i).getSaleNum());
                List<GreenDao> greenDaos1 = greenDaoDao.loadAll();
                for (GreenDao dao : greenDaos1) {
                    Log.e("sllslsllsllsls",dao.getCommodityName()+"");
                }
                long insert = App.daoSession.insert(greenDao);
            }
        });
    }



    @Override
    public int getItemCount() {
        return bean.getMlss().getCommodityList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView pirce;
        public ViewHolder(View itemView) {

            super(itemView);

            img=   itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            pirce = itemView.findViewById(R.id.pirce);
        }
    }
}
