package com.rapidzz.hospital_management;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.Objects;

abstract public class BaseFragment<B extends ViewDataBinding> extends Fragment {
    @NonNull
    protected B mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(mBinding);
    }

    public void navigate(int navigationId, Bundle bundle){
        Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment_content_main).navigate(navigationId,bundle);
    }
    public void showToast(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    abstract public void initView(B binding);

    abstract public int getLayoutId();
}
