package com.speex.aiviewmodel;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.speex.aiviewmodel.bean.AccountBean;
import com.speex.aiviewmodel.fragment.BottomFragment;
import com.speex.aiviewmodel.fragment.TopFragment;
import com.speex.aiviewmodel.model.AccountModel;

public class MainActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();
    private AccountModel mAccountModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        final TextView tvResult = findViewById(R.id.tv_post_result);

        //加载Fragment到Activity
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_top, new TopFragment()).commit();
        transaction.replace(R.id.fragment_container_bottom, new BottomFragment());

        // 初始化ViewModel
        mAccountModel = ViewModelProviders.of(this).get(AccountModel.class);

        //点击事件
        findViewById(R.id.main_btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在Activity中post事件到LiveData,LiveData将事件传递到注册观察他的各个组件
                mAccountModel.setAccount(new AccountBean("雪碧有点白","136123456898","www.baidu.com","这是MainActivity的消息"));
            }
        });


        //注册观察者
        mAccountModel.getAccountLiveData().observe(this, new Observer<AccountBean>() {
            @Override
            public void onChanged(@Nullable AccountBean accountBean) {
                Log.i(TAG, "观察到数据变化了: " + AccountModel.getFormatContent(accountBean));
                tvResult.setText(AccountModel.getFormatContent(accountBean));
            }
        });
    }
}
