package com.rapidzz.hospital_management.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<BINDING : ViewDataBinding>(
    var data: List<Any>, private var layoutId: Int, var onItemClicker: OnItemClicker? = null
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder<BINDING>>() {

    abstract fun bind(binding: BINDING, item: Any,position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BINDING> {
        val binder = DataBindingUtil.inflate<BINDING>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )

        return BaseViewHolder(binder)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BINDING>, position: Int) {
        val model = data[position]
        bind(holder.binder, model,position)
        holder.itemView.setOnClickListener {
            onItemClicker?.onItemClick(position, model)
        }
    }

    class BaseViewHolder<BINDING : ViewDataBinding>(val binder: BINDING) :
        RecyclerView.ViewHolder(binder.root) {
    }

    override fun getItemCount() = data.size

    interface OnItemClicker {
        fun onItemClick(position: Int, data: Any)
    }
}
