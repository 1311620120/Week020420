package com.example.week020420.presenter;

import com.example.week020420.model.BannerJson;
import com.example.week020420.model.HttpUtlis;
import com.example.week020420.view.fragment.OneFragment;
import com.example.week020420.view.interfaces.IMainView;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @Auther: 白俊岭
 * @Date: 2019/4/12 18:53:17
 * @Description:
 */
public class BannerPresenter extends BasePresenter<IMainView<BannerJson>> {

    private final HttpUtlis instance;

    public BannerPresenter(OneFragment oneFragment){
        instance = HttpUtlis.getInstance();
    }
    public void   BannerData(){
        Observable<BannerJson> banner = instance.api.banner();
        banner.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BannerJson>() {
                    @Override
                    public void accept(BannerJson bannerJson) throws Exception {
                            getView().onSuccess(bannerJson);
                    }
                });
    }
}
