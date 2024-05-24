package com.rapidzz.hospital_management.adapters

import com.rapidzz.hospital_management.R
import com.rapidzz.hospital_management.databinding.RvPatientBinding
import com.rapidzz.hospital_management.models.PatientModel

class PatientsAdapter(list: ArrayList<PatientModel>, var listener: OnItemClicker) :
    BaseAdapter<RvPatientBinding>(list, R.layout.rv_patient, listener) {

    override fun bind(binding: RvPatientBinding, item: Any, position: Int) {
        val model = item as PatientModel
        binding.tvName.text = model.name
        binding.tvPhoneNumber.text = model.phone
        binding.tvEmail.text = model.email
        binding.tvAddress.text = model.address
    }
}