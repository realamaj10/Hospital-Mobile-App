package com.rapidzz.hospital_management.adapters

import com.rapidzz.hospital_management.R
import com.rapidzz.hospital_management.databinding.RvDashboardBinding
import com.rapidzz.hospital_management.models.HomeModel

class DashBoardAdapter(list: ArrayList<HomeModel>, listener: OnItemClicker) :
    BaseAdapter<RvDashboardBinding>(list, R.layout.rv_dashboard, listener) {

    override fun bind(binding: RvDashboardBinding, item: Any, position: Int) {
        val model = item as HomeModel
        binding.tvTitle.text = model.name
        binding.ivHomeImage.setImageResource(model.imageId)
    }
}