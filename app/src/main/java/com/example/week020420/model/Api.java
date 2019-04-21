package com.example.week020420.model;



import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @Auther: 白俊岭
 * @Date: 2019/4/20 08:55:32
 * @Description:
 */
public interface Api {

     @GET("small/commodity/v1/commodityList")
     Observable<JsonBean> show();
    @GET("small/commodity/v1/bannerShow")
    Observable<BannerJson> banner();

}
