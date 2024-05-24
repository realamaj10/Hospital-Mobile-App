package com.rapidzz.hospital_management.ui.doctor;

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
import com.rapidzz.hospital_management.adapters.DoctorAdapter;
import com.rapidzz.hospital_management.databinding.FragmentDcotorBinding;
import com.rapidzz.hospital_management.models.DoctorsModel;

import java.util.ArrayList;
import java.util.Objects;

public class DoctorFragment extends BaseFragment<FragmentDcotorBinding> implements BaseAdapter.OnItemClicker {
    DatabaseReference reference;
    ArrayList<DoctorsModel> arrayList = new ArrayList<>();
    DoctorAdapter adapter;

    @Override
    public void initView(FragmentDcotorBinding binding) {
        reference = FirebaseDatabase.getInstance().getReference("Doctors");
        adapter = new DoctorAdapter(arrayList, this);
        binding.rvDoctor.setAdapter(adapter);
        binding.fab.setOnClickListener(view ->

                Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment_content_main).navigate(R.id.action_nav_home_to_addDoctorFragment));

        readDoctors();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_dcotor;
    }

    @Override
    public void onItemClick(int position, @NonNull Object data) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", arrayList.get(position));
        navigate(R.id.action_nav_home_to_addDoctorFragment, bundle);
    }

    private void readDoctors() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    DoctorsModel model = snapshot1.getValue(DoctorsModel.class);
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