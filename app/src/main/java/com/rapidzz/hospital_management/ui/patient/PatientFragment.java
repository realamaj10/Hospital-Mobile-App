package com.rapidzz.hospital_management.ui.patient;

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
import com.rapidzz.hospital_management.adapters.PatientsAdapter;
import com.rapidzz.hospital_management.databinding.FragmentDcotorBinding;
import com.rapidzz.hospital_management.databinding.FragmentPatientBinding;
import com.rapidzz.hospital_management.models.DoctorsModel;
import com.rapidzz.hospital_management.models.PatientModel;

import java.util.ArrayList;
import java.util.Objects;

public class PatientFragment extends BaseFragment<FragmentPatientBinding> implements BaseAdapter.OnItemClicker {
    DatabaseReference reference;
    ArrayList<PatientModel> arrayList = new ArrayList<>();
    PatientsAdapter adapter;

    @Override
    public void initView(FragmentPatientBinding binding) {
        reference = FirebaseDatabase.getInstance().getReference("Patients");
        adapter = new PatientsAdapter(arrayList, this);
        binding.rvPatient.setAdapter(adapter);
        binding.fab.setOnClickListener(view ->
                Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment_content_main).navigate(R.id.action_nav_gallery_to_addPatientFragment));

        readPatients();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_patient;
    }

    @Override
    public void onItemClick(int position, @NonNull Object data) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", arrayList.get(position));
        navigate(R.id.action_nav_gallery_to_addPatientFragment, bundle);
    }

    private void readPatients() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    PatientModel model = snapshot1.getValue(PatientModel.class);
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