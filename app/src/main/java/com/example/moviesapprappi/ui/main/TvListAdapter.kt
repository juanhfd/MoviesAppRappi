package com.example.moviesapprappi.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapprappi.R
import com.example.moviesapprappi.databinding.ItemTvBinding
import com.example.moviesapprappi.model.Tv
import com.example.moviesapprappi.ui.tv.TvDetailActivity
import com.skydoves.bindables.binding

class TvListAdapter : RecyclerView.Adapter<TvListAdapter.TvListViewHolder>() {

    private val items: MutableList<Tv> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvListViewHolder {
        val binding = parent.binding<ItemTvBinding>(R.layout.item_tv)
        return TvListViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val tv = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                TvDetailActivity.startActivityModel(it.context, items[tv])
            }
        }
    }

    override fun onBindViewHolder(holder: TvListViewHolder, position: Int) {
        with(holder.binding) {
            tv = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size

    fun addTvList(tvs: List<Tv>) {
        val previousItemSize = items.size
        items.addAll(tvs)
        notifyItemRangeInserted(previousItemSize, tvs.size)
    }

    class TvListViewHolder(val binding: ItemTvBinding) :
        RecyclerView.ViewHolder(binding.root)
}
