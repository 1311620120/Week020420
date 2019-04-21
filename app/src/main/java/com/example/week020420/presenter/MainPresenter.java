package com.example.week020420.presenter;

import com.example.week020420.model.HttpUtlis;
import com.example.week020420.model.JsonBean;
import com.example.week020420.view.fragment.OneFragment;
import com.example.week020420.view.interfaces.IMainView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @Auther: 白俊岭
 * @Date: 2019/4/20 09:08:11
 * @Description:
 */
public class MainPresenter extends  BasePresenter<IMainView<JsonBean>> {

    private final HttpUtlis instance;

    public  MainPresenter(OneFragment oneFragment){
        instance = HttpUtlis.getInstance( );
     }
     public  void  ShowData(){
         Observable<JsonBean> show = instance.api.show();
         show.subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<JsonBean>() {
                     @Override
                     public void accept(JsonBean jsonBean) throws Exception {
                         getView().onSuccess(jsonBean);
                     }
                 });
     }
}
