package com.github.repos.core.lists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.github.repos.core.BR

class DataBoundRecyclerAdapter(
    val items: List<DataBoundModel>,
    val lifecycleOwner: LifecycleOwner? = null
) :
    RecyclerView.Adapter<DataBoundViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return items[position].layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder {
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        binding.lifecycleOwner = lifecycleOwner
        val vh = DataBoundViewHolder(binding)
        if (binding.lifecycleOwner == null) {
            binding.lifecycleOwner = vh
        }
        return vh
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder, position: Int) {
        holder.binding.setVariable(BR.viewModel, items[position])
        holder.binding.setVariable(BR.lfo, lifecycleOwner)
        holder.binding.executePendingBindings()
    }

    override fun onViewAttachedToWindow(holder: DataBoundViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.markAttach()
    }

    override fun onViewDetachedFromWindow(holder: DataBoundViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.markDetach()
    }

}