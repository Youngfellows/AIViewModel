package com.speex.aiviewmodel.fragment;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.speex.aiviewmodel.R;
import com.speex.aiviewmodel.bean.AccountBean;
import com.speex.aiviewmodel.model.AccountModel;


public class TopFragment extends Fragment {

    private String TAG = this.getClass().getSimpleName();
    private AccountModel mAccountModel;

    public TopFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //加载布局
        return inflater.inflate(R.layout.fragment_top, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //布局加载完成，初始化控件
        init(view);
    }

    /**
     * 初始化
     *
     * @param view
     */
    private void init(View view) {
        final TextView tvResult = view.findViewById(R.id.tv_post_result);

        // 初始化ViewModel
        mAccountModel = ViewModelProviders.of(getActivity()).get(AccountModel.class);

        //点击事件
        view.findViewById(R.id.top_frag_btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在Activity中post事件到LiveData,LiveData将事件传递到注册观察他的各个组件
                mAccountModel.setAccount(new AccountBean("雪碧有点白", "136123456898", "www.baidu.com", "这是TopFragment的消息"));
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
