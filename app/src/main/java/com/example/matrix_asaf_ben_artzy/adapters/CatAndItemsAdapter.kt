package com.example.matrix_asaf_ben_artzy.adapters

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.matrix_asaf_ben_artzy.R
import com.example.matrix_asaf_ben_artzy.model.CatAndItems
import com.example.matrix_asaf_ben_artzy.model.CatData
import com.example.matrix_asaf_ben_artzy.model.ItemData
import com.example.matrix_asaf_ben_artzy.util.MyPagerSnapHelper
import com.example.matrix_asaf_ben_artzy.util.TopSpacingItemDecoration
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import kotlinx.android.synthetic.main.adapter_cat_title_and_items.view.*


class CatAndItemsAdapter(
    private val tabIndex: Int,
    private val requestManager: RequestManager
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClick: ((ItemData, CatData) -> Unit)? = null


    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CatAndItems>() {

        override fun areItemsTheSame(oldItem: CatAndItems, newItem: CatAndItems): Boolean {
            return oldItem.catData == newItem.catData
        }

        override fun areContentsTheSame(oldItem: CatAndItems, newItem: CatAndItems): Boolean {
            return oldItem == newItem
        }

    }

    internal inner class CatAndItemsRecyclerChangeCallback(
        private val adapter: CatAndItemsAdapter
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
        CatAndItemsRecyclerChangeCallback(this),
        AsyncDifferConfig.Builder(DIFF_CALLBACK).build()
    )



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return CatDataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_cat_title_and_items,
                parent,
                false
            ),
            tabIndex = tabIndex,
            requestManager = requestManager,
            onItemClick = onItemClick
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CatDataViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<CatAndItems>) {
        differ.submitList(list)
    }

    class CatDataViewHolder
    constructor(
        itemView: View,
        private val tabIndex: Int,
        private val requestManager: RequestManager,
        var onItemClick: ((ItemData, CatData) -> Unit)?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(catAndItems: CatAndItems) = with(itemView) {

            text_cat_title.text = catAndItems.catData.CTitle

            rv_items_horizontal.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                val spacingItemDecoration = TopSpacingItemDecoration(
                    context.resources.getDimension(R.dimen.rv_cat_and_items_margin).toInt()
                )
                addItemDecoration(spacingItemDecoration)
                adapter = ItemListAdapter(requestManager).also {
                    it.submitList(catAndItems.items)
                    it.onItemClick = { clickedItemData ->
                        onItemClick?.invoke(clickedItemData, catAndItems.catData)
                    }
                }
                val snapHelper =
                    if (tabIndex == ALL_CAT_PAGE_INDEX) {
                        GravitySnapHelper(Gravity.START)
                    } else {
                        MyPagerSnapHelper()
                    }
                snapHelper.attachToRecyclerView(this)
            }
        }
    }

}