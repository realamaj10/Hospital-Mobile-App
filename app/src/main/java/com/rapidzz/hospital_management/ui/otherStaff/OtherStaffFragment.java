package com.rapidzz.hospital_management.ui.otherStaff;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rapidzz.hospital_management.BaseFragment;
import com.rapidzz.hospital_management.R;
import com.rapidzz.hospital_management.adapters.BaseAdapter;
import com.rapidzz.hospital_management.adapters.PatientsAdapter;
import com.rapidzz.hospital_management.adapters.StaffAdapter;
import com.rapidzz.hospital_management.databinding.FragmentOtherStaffBinding;
import com.rapidzz.hospital_management.databinding.FragmentPatientBinding;
import com.rapidzz.hospital_management.models.PatientModel;
import com.rapidzz.hospital_management.models.StaffModel;

import java.util.ArrayList;
import java.util.Objects;

public class OtherStaffFragment extends BaseFragment<FragmentOtherStaffBinding> implements BaseAdapter.OnItemClicker {
    DatabaseReference reference;
    ArrayList<StaffModel> arrayList = new ArrayList<>();
    StaffAdapter adapter;

    @Override
    public void initView(FragmentOtherStaffBinding binding) {
        reference = FirebaseDatabase.getInstance().getReference("Staff");
        adapter = new StaffAdapter(arrayList, this);
        binding.rvStaff.setAdapter(adapter);
        binding.fab.setOnClickListener(view ->
                Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment_content_main).navigate(R.id.action_otherStaffFragment_to_addStaffFragment));

        readPatients();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_other_staff;
    }

    @Override
    public void onItemClick(int position, @NonNull Object data) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", arrayList.get(position));
        navigate(R.id.action_otherStaffFragment_to_addStaffFragment, bundle);
    }

    private void readPatients() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    StaffModel model = snapshot1.getValue(StaffModel.class);
                    arrayList.add(model);
                }
                adapter.notifyDataSetChanged();
                mBinding.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                mBinding.progressBar.setVisibility(View.GONE);
            }
        });
    }
}