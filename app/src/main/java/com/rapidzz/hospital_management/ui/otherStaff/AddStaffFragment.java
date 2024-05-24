package com.rapidzz.hospital_management.ui.otherStaff;

import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rapidzz.hospital_management.BaseFragment;
import com.rapidzz.hospital_management.R;
import com.rapidzz.hospital_management.databinding.FragmentAddStaffBinding;
import com.rapidzz.hospital_management.models.StaffModel;

import java.util.HashMap;

public class AddStaffFragment extends BaseFragment<FragmentAddStaffBinding> {
    DatabaseReference reference;
    String gender = "Male";
    StaffModel model;
    FragmentAddStaffBinding binding;

    @Override
    public void initView(FragmentAddStaffBinding binding) {
        this.binding = binding;
        getArgument();

        reference = FirebaseDatabase.getInstance().getReference("Staff");
        binding.btnAddStaff.setOnClickListener(view -> {
            if (isValidForm()) {
                binding.progressBar.setVisibility(View.VISIBLE);
                sendMessage();
            }
        });
        binding.btnRemoveStaff.setOnClickListener(view -> {
            reference.child(binding.etCNIC.getText().toString()).removeValue();
            showToast("Deleted Successfully");
            getActivity().onBackPressed();
        });

        binding.rgGenSelection.setOnCheckedChangeListener((group, checkedId) -> {
                    switch (checkedId) {
                        case R.id.rbDealerGenuine:
                            gender = "Male";
                            break;
                        case R.id.rbOtherGenuine:
                            gender = "FeMale";
                            break;
                    }
                }
        );

    }


    private void getArgument() {
        if (getArguments().containsKey("model") && getArguments().getSerializable("model") != null) {
            model = (StaffModel) getArguments().getSerializable("model");

            updateUI();
        }
    }

    private void updateUI() {
        binding.etName.setText(model.getName());
        binding.etEmailOrUsername.setText(model.getEmail());
        binding.etPhoneNumber.setText(model.getPhone());
        binding.etAddress.setText(model.getAddress());
        binding.etCNIC.setText(model.getCnic());
        binding.etItemDescription.setText(model.getDescription());
        binding.btnAddStaff.setText("Update Staff");
        binding.etCNIC.setEnabled(false);
        binding.btnRemoveStaff.setVisibility(View.VISIBLE);
    }

    private void sendMessage() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", binding.etName.getText().toString());
        hashMap.put("email", binding.etEmailOrUsername.getText().toString());
        hashMap.put("phone", binding.etPhoneNumber.getText().toString());
        hashMap.put("address", binding.etAddress.getText().toString());
        hashMap.put("description", binding.etItemDescription.getText().toString());
        hashMap.put("gender", gender);
        hashMap.put("cnic", binding.etCNIC.getText().toString());
        hashMap.put("specialization", binding.spSpec.getSelectedItem().toString());
        reference.child(binding.etCNIC.getText().toString()).setValue(hashMap);
        showToast("Added Successfully");
        getActivity().onBackPressed();
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_add_staff;
    }

    private Boolean isValidForm() {
        if (binding.etName.getText().toString().isEmpty()) {
            binding.etName.setError("Name is required");
            binding.etName.requestFocus();
            return false;
        } else if (binding.etEmailOrUsername.getText().toString().isEmpty()) {
            binding.etEmailOrUsername.setError("Email is required");
            binding.etEmailOrUsername.requestFocus();
            return false;
        } else if (binding.etPhoneNumber.getText().toString().isEmpty()) {
            binding.etPhoneNumber.setError("Phone Number is required");
            binding.etPhoneNumber.requestFocus();
            return false;
        } else if (binding.etAddress.getText().toString().isEmpty()) {
            binding.etAddress.setError("Address is required");
            binding.etAddress.requestFocus();
            return false;
        } else if (binding.etCNIC.getText().toString().isEmpty()) {
            binding.etCNIC.setError("CNIC is required");
            binding.etCNIC.requestFocus();
            return false;
        } else if (binding.etItemDescription.getText().toString().isEmpty()) {
            binding.etItemDescription.setError("Description is required");
            binding.etItemDescription.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}

