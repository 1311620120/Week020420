package com.example.week020420.view.interfaces;

/**
 * @Auther: 白俊岭
 * @Date: 2019/4/20 08:53:36
 * @Description:
 */
public interface IMainView<T> {
      void onSuccess(T t);
      void  onFails(String err);
}
