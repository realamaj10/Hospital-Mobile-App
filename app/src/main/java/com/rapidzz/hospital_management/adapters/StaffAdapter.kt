package com.rapidzz.hospital_management.adapters

import com.rapidzz.hospital_management.R
import com.rapidzz.hospital_management.databinding.RvPatientBinding
import com.rapidzz.hospital_management.databinding.RvStaffBinding
import com.rapidzz.hospital_management.models.PatientModel
import com.rapidzz.hospital_management.models.StaffModel

class StaffAdapter(list: ArrayList<StaffModel>, var listener: OnItemClicker) :
    BaseAdapter<RvStaffBinding>(list, R.layout.rv_staff, listener) {

    override fun bind(binding: RvStaffBinding, item: Any, position: Int) {
        val model = item as StaffModel
        binding.tvName.text = model.name
        binding.tvPhoneNumber.text = model.phone
        binding.tvEmail.text = model.email
        binding.tvAddress.text = model.address
    }
}