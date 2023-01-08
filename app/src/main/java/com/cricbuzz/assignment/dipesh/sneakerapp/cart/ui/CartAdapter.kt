package com.cricbuzz.assignment.dipesh.sneakerapp.cart.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cricbuzz.assignment.dipesh.sneakerapp.R
import com.cricbuzz.assignment.dipesh.sneakerapp.databinding.ItemCartBinding
import com.cricbuzz.assignment.dipesh.sneakerapp.roomdb.CartItem

class CartAdapter(private val clickList: onItemClick) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    private val mDiffer: AsyncListDiffer<CartItem> = AsyncListDiffer<CartItem>(
        this,
        DIFF_CALLBACK
    )

    inner class ViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCartBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun submitList(list: List<CartItem>) {
        mDiffer.submitList(list)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cartItem = mDiffer.currentList.get(holder.adapterPosition)
        holder.binding.tvTitle.text = cartItem.title
        holder.binding.tvPrice.text = holder.itemView.context.getString(
            R.string.price_, cartItem.price.toString()
        )

        holder.binding.ivClose.setOnClickListener {

            clickList.onClick(false, cartItem)
        }
    }

    override fun getItemCount(): Int = mDiffer.currentList.size

    interface onItemClick {
        fun onClick(isItemClick: Boolean, item: CartItem)
    }

    companion object {
        val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<CartItem>() {
                override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
                    return oldItem.itemId == newItem.itemId
                }

                override fun areContentsTheSame(
                    oldItem: CartItem,
                    newItem: CartItem
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }

}