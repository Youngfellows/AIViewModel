package com.speex.aiviewmodel.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.speex.aiviewmodel.bean.AccountBean;


/**
 * 关于ViewModel
 * ViewModel类的设计目的是以一种关注生命周期的方式存储和管理与UI相关的数据。
 * 例如：Activity在配置发生改变时(屏幕旋转)，Activity就会重新创建，onCreate()方法也会重新调用。
 * 我们可以在onSaveInstanceState()方法中保存数据，并从onCreate()方法中通过Bundle恢复数据，但这种方法只适用于可以对其进行序列化的少量数据，
 * 而不适用于潜在的大量数据。使用ViewModel的话ViewModel会自动保留之前的数据并给新的Activity或Fragment使用。直到当前Activity被系统销毁时，
 * Framework会调用ViewModel的onCleared()方法，我们可以在onCleared()方法中做一些资源清理操作。
 * <p>
 * 关于LiveData
 * LiveData是一个可观察的数据持有者类。与常见的观察者不同，LiveData是有生命周期感知的。这意味着它尊重其他应用程序组件的生命周期，
 * 比如Activity、Fragment或Service。这种感知确保LiveData只更新处于生命周期状态内的应用程序组件。
 * LiveData是由observer类表示的观察者视为处于活动状态，如果其生命周期处于STARTED或RESUMED状态。LiveData会将观察者视为活动状态，
 * 并通知其数据的变化。LiveData未注册的观察对象以及非活动观察者是不会收到有关更新的通知。
 * <p>
 * 原文：https://blog.csdn.net/mjb00000/article/details/79495461
 */
public class AccountModel extends AndroidViewModel {
    private String TAG = this.getClass().getSimpleName();

    //创建LiveData
    private MutableLiveData<AccountBean> mAccountLiveData = new MutableLiveData<>();

    public AccountModel(@NonNull Application application) {
        super(application);
    }

    /**
     * 获取LiveData
     *
     * @return
     */
    public MutableLiveData<AccountBean> getAccountLiveData() {
        return mAccountLiveData;
    }

    /**
     * 使用LiveData发送任务，set方式，同步
     *
     * @param account
     */
    public void setAccount(AccountBean account) {
        mAccountLiveData.setValue(account);
    }

    /**
     * 使用LiveData发送任务，post方式，异步
     *
     * @param account
     */
    public void postValue(AccountBean account) {
        mAccountLiveData.postValue(account);
    }

    /**
     * 当Activity被销毁时,Framework会调用ViewModel的onCleared()
     */
    @Override
    protected void onCleared() {
        Log.e(TAG, "==========onCleared()==========");
        super.onCleared();
    }

    /**
     * 做数据转换
     *
     * @param account
     * @return
     */
    public static String getFormatContent(AccountBean account) {
        StringBuilder mBuilder = new StringBuilder();
        mBuilder.append("昵称:");
        mBuilder.append(account.getName());
        mBuilder.append("\n手机:");
        mBuilder.append(account.getPhone());
        mBuilder.append("\n博客:");
        mBuilder.append(account.getBlogs());
        mBuilder.append("\n消息:");
        mBuilder.append(account.getMessage());
        return mBuilder.toString();
    }
}
