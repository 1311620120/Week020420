package com.example.week020420.model;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Auther: 白俊岭
 * @Date: 2019/4/20 08:55:19
 * @Description:
 */
public class HttpUtlis {


    private final OkHttpClient okHttpClient;
    public Api api;

    public  HttpUtlis(){
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Loggintent())
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://172.17.8.100/")
                .build();
        api = retrofit.create(Api.class);
    }
    private     static  class  HttpUtilsgetIntants{
        private  static  HttpUtlis httpUtils =new HttpUtlis();
    }

    public static HttpUtlis getInstance() {
        return HttpUtilsgetIntants.httpUtils ;
    }


    public  class  Loggintent implements Interceptor{
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request request1 = request.newBuilder()
                    .addHeader("userId", "")
                    .addHeader("sessionId", "")
                    .build();
            Response proceed = chain.proceed(request1);
            return proceed;
        }
    }

}
