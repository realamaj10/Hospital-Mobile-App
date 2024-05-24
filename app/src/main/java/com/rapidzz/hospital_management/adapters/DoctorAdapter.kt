package com.rapidzz.hospital_management.adapters

import com.rapidzz.hospital_management.R
import com.rapidzz.hospital_management.databinding.RvDoctorsBinding
import com.rapidzz.hospital_management.models.DoctorsModel
import com.rapidzz.hospital_management.models.HomeModel

class DoctorAdapter(list: ArrayList<DoctorsModel>, var listener: OnItemClicker) :
    BaseAdapter<RvDoctorsBinding>(list, R.layout.rv_doctors, listener) {

    override fun bind(binding: RvDoctorsBinding, item: Any, position: Int) {
        val model = item as DoctorsModel
        binding.tvName.text = model.name
        binding.tvPhoneNumber.text = model.phone
        binding.tvEmail.text = model.email
        binding.tvAddress.text = model.address
        binding.btnViewDetail.setOnClickListener {
            listener.onItemClick(position, item)
        }
    }
}