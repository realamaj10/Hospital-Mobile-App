package com.rapidzz.hospital_management.adapters

import com.rapidzz.hospital_management.R
import com.rapidzz.hospital_management.databinding.RvLabBinding
import com.rapidzz.hospital_management.models.StaffModel

class LaboratoryAdapter(list: ArrayList<StaffModel>, var listener: OnItemClicker) :
    BaseAdapter<RvLabBinding>(list, R.layout.rv_lab, listener) {

    override fun bind(binding: RvLabBinding, item: Any, position: Int) {
        val model = item as StaffModel
        binding.tvName.text = model.name
        binding.rvDes.text = model.specialization
        binding.tvEmail.text = model.email
        binding.tvAddress.text = model.address
    }
}