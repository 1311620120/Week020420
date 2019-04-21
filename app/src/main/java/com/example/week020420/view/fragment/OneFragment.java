package com.example.week020420.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.week020420.R;
import com.example.week020420.model.App;
import com.example.week020420.model.BannerJson;
import com.example.week020420.model.GreenDao;
import com.example.week020420.model.JsonBean;
import com.example.week020420.model.Work;
import com.example.week020420.presenter.BannerPresenter;
import com.example.week020420.presenter.MainPresenter;

import com.example.week020420.view.adapter.MlssAdapter;
import com.example.week020420.view.adapter.PzshAdapter;
import com.example.week020420.view.adapter.RxxpAdapter;
import com.example.week020420.view.interfaces.IMainView;
import com.example.weektext0413.greendao.gen.DaoSession;
import com.example.weektext0413.greendao.gen.GreenDaoDao;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Auther: 白俊岭
 * @Date: 2019/4/20 09:33:13
 * @Description:
 */
public class OneFragment extends Fragment implements IMainView {

    Unbinder unbinder;
    private View inflate;
    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager1;
    private RecyclerView recycler_rxxp;
    private RecyclerView recycler_pzsh;
    private RecyclerView recycler_mlss;
    private MainPresenter mainPresenter;
    private XBanner xBanner;
    private JsonBean jsonBean;
    private  JsonBean.ResultBean bean;
    private GreenDao greenDao;
    private DaoSession user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.onefragment, null);
        //-------------初始化数据库
        greenDao = new GreenDao();
        //引用表
        user = App.daoSession;

        initData();
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }



    private void initData() {
        xBanner = inflate.findViewById(R.id.Xbanner);
        mainPresenter = new MainPresenter(this);
        BannerPresenter bannerPresenter = new BannerPresenter(this);
        bannerPresenter.BannerData();
        bannerPresenter.setView(this);

          /*
             热销产品
         */
        recycler_rxxp = inflate.findViewById(R.id.recycler_rxxp);
        gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recycler_rxxp.setLayoutManager(gridLayoutManager);
        RxxpAdapter rxxpAdapter = new RxxpAdapter(getActivity(), bean);
        recycler_rxxp.setAdapter(rxxpAdapter);
        boolean b = Work.newwork(getActivity());
        if (b){

            mainPresenter.setView(this);
            mainPresenter.ShowData();
        }else {
            //没网显示数据库中的数据
            //创建集合
            ArrayList<JsonBean.ResultBean.RxxpBean.CommodityListBean> list = new ArrayList<>();
            //查询数据库并放入集合
            List<GreenDao> list1 = user.queryBuilder().list();
            for (int i = 0; i < list.size(); i++) {
                //创建对象------必须有
                JsonBean.ResultBean.RxxpBean.CommodityListBean bean=new JsonBean.ResultBean.RxxpBean.CommodityListBean();
                //写入数据
                bean.setCommodityName(list1.get(i).getCommodityName());
                bean.setMasterPic(list1.get(i).getMasterPic());
                bean.setPrice(list1.get(i).getPrice());
                list.add(bean);
            }
          //  myAdapter.setData(list);


        }
         /*
             魔力时尚
         */
        recycler_mlss = inflate.findViewById(R.id.recycler_mlss);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recycler_mlss.setLayoutManager(linearLayoutManager);
        /*
           品质生活
         */
        recycler_pzsh = inflate.findViewById(R.id.recycler_pzsh);
        gridLayoutManager1 = new GridLayoutManager(getActivity(), 2);
        recycler_pzsh.setLayoutManager(gridLayoutManager1);
    }


    @Override
    public void onSuccess(Object o) {


        if (o instanceof JsonBean){
            jsonBean = (JsonBean)o;
            bean = jsonBean.getResult();
        /*
             热销产品
         */
            List<JsonBean.ResultBean.RxxpBean.CommodityListBean> list = bean.getRxxp().getCommodityList();
            for (int i = 0; i < list.size(); i++) {
              //添加数据进库
           greenDao.setCommodityName(list.get(i).getCommodityName());
           greenDao.setCommodityId(list.get(i).getCommodityId());
           greenDao.setMasterPic(list.get(i).getMasterPic());
           greenDao.setSaleNum(list.get(i).getSaleNum());
           //添加或者替换 ----添加进表中
           user.insertOrReplace(greenDao);
            }
         /*
             品质生活
         */
        JsonBean.ResultBean.PzshBean pzsh = bean.getPzsh();
        PzshAdapter pzshAdapter = new PzshAdapter(getActivity(), pzsh);
        recycler_pzsh.setAdapter(pzshAdapter);
        /*
             魔力时尚
         */
        MlssAdapter mlssAdapter = new MlssAdapter(getActivity(), bean);
        recycler_mlss.setAdapter(mlssAdapter);

        }

        if (o instanceof BannerJson){
            /*
            * 轮播图
            * */
            BannerJson bannerJson = (BannerJson) o;
            final List<BannerJson.ResultBean> result = bannerJson.getResult();
            final ArrayList<String> objects = new ArrayList<>();
            for (int i = 0; i < result.size(); i++) {
                objects.add(result.get(i).getImageUrl());
            }
            xBanner.setData(objects, null);
            xBanner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(OneFragment.this).load(objects.get(position)).into((ImageView) view);
                }
            });
            xBanner.setPageTransformer(Transformer.Cube);    //立体旋转
            // 设置XBanner页面切换的时间，即动画时长
            xBanner.setPageChangeDuration(1000);
        }

    }


    @Override
    public void onFails(String err) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
