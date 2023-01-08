package com.cricbuzz.assignment.dipesh.sneakerapp.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cricbuzz.assignment.dipesh.sneakerapp.R
import com.cricbuzz.assignment.dipesh.sneakerapp.databinding.ItemHomeBinding
import com.cricbuzz.assignment.dipesh.sneakerapp.home.model.SneakerItem


class SearchAdapter(private val onItemAction: OnItemAction) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    inner class ViewHolder(val view: ItemHomeBinding) : RecyclerView.ViewHolder(view.root)

    private val mDiffer: AsyncListDiffer<SneakerItem> =
        AsyncListDiffer<SneakerItem>(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun submitList(list: List<SneakerItem?>?) {
        mDiffer.submitList(list)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sneakerDetail = mDiffer.currentList.get(position)
        holder.view.ivAddToCart.setOnClickListener {
            onItemAction.onItemAction(false, sneakerDetail)
        }
        holder.itemView.setOnClickListener {
            onItemAction.onItemAction(true, sneakerDetail)
        }
        Glide.with(holder.itemView.context).load(sneakerDetail.media.imageUrl)
            .placeholder(R.drawable.sample_shoes_image).into(holder.view.ivImage.ivSneaker)
        holder.view.tvPrice.text = "$ ${sneakerDetail.retailPrice}"
        holder.view.tvTitle.text = sneakerDetail.title
    }

    override fun getItemCount(): Int {
        return mDiffer.currentList.size
    }

    interface OnItemAction {
        fun onItemAction(isItemClick: Boolean, item: SneakerItem)
    }

    companion object {
        val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<SneakerItem>() {
                override fun areItemsTheSame(oldItem: SneakerItem, newItem: SneakerItem): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: SneakerItem,
                    newItem: SneakerItem
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }

}