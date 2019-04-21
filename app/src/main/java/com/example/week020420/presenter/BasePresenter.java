package com.example.week020420.presenter;

/**
 * @Auther: 白俊岭
 * @Date: 2019/4/20 08:55:04
 * @Description:
 */
public class BasePresenter<V> {
    private  V view;

    public void setView(V view) {
        this.view = view;
    }

    public V getView() {
        return view;
    }
    public  void  detacher(){
        this.view=null;
    }
}
