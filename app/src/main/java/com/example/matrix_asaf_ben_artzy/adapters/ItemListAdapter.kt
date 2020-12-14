package com.example.matrix_asaf_ben_artzy.adapters

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.bumptech.glide.RequestManager
import com.example.matrix_asaf_ben_artzy.R
import com.example.matrix_asaf_ben_artzy.model.ItemData
import kotlinx.android.synthetic.main.adapter_item_list.view.*

class ItemListAdapter(
    private val requestManager: RequestManager
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClick: ((ItemData) -> Unit)? = null

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemData>() {

        override fun areItemsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
            return oldItem.Id == newItem.Id
        }

        override fun areContentsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
            return oldItem == newItem
        }

    }

    internal inner class ItemListRecyclerChangeCallback(
        private val adapter: ItemListAdapter
    ) : ListUpdateCallback {

        override fun onChanged(position: Int, count: Int, payload: Any?) {
            adapter.notifyItemRangeChanged(position, count, payload)
        }

        override fun onInserted(position: Int, count: Int) {
            adapter.notifyDataSetChanged()
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            adapter.notifyDataSetChanged()
        }

        override fun onRemoved(position: Int, count: Int) {
            adapter.notifyDataSetChanged()
        }
    }


    private val differ = AsyncListDiffer(
        ItemListRecyclerChangeCallback(this),
        AsyncDifferConfig.Builder(DIFF_CALLBACK).build()
    )



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ItemListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_item_list,
                parent,
                false
            ),
            requestManager,
            onItemClick
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemListViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<ItemData>) {
        differ.submitList(list)
    }

    class ItemListViewHolder
    constructor(
        itemView: View,
        private val requestManager: RequestManager,
        private val onItemClick: ((ItemData) -> Unit)?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(itemData: ItemData) = with(itemView) {

            val spannable = SpannableString("${itemData.Title} - ${itemData.STitle}").apply {
                setSpan(
                    StyleSpan(Typeface.BOLD),
                    0, this.indexOf(" - "),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            text_adapter_item_desc.text = spannable


            requestManager
                .load(itemData.Imag)
                .error(R.color.Gray)
                .into(image_adapter_item_bg)

            itemView.setOnClickListener {
                onItemClick?.invoke(itemData)
            }

        }
    }


}