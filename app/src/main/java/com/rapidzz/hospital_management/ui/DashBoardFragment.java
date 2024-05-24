package com.rapidzz.hospital_management.ui;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import com.rapidzz.hospital_management.BaseFragment;
import com.rapidzz.hospital_management.R;
import com.rapidzz.hospital_management.adapters.BaseAdapter;
import com.rapidzz.hospital_management.adapters.DashBoardAdapter;
import com.rapidzz.hospital_management.databinding.FragmentDashboardBinding;
import com.rapidzz.hospital_management.utils.AppConstant;

import java.util.Objects;

public class DashBoardFragment extends BaseFragment<FragmentDashboardBinding> implements BaseAdapter.OnItemClicker {
    @Override
    public void initView(FragmentDashboardBinding binding) {
        binding.rvDashboard.setAdapter(new DashBoardAdapter(AppConstant.INSTANCE.getDasBoard(), this));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_dashboard;
    }

    @Override
    public void onItemClick(int position, @NonNull Object data) {
        if (position == 0) {
            navigateToFragment(R.id.action_dashBoardFragment_to_nav_home);
        } else if (position == 1) {
            navigateToFragment(R.id.action_dashBoardFragment_to_nav_gallery);
        } else if (position == 2) {
            navigateToFragment(R.id.action_dashBoardFragment_to_otherStaffFragment);
        } else {
            navigateToFragment(R.id.action_dashBoardFragment_to_nav_slideshow);
        }
    }


    public void navigateToFragment(int id) {
        Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment_content_main).navigate(id);
    }
}
