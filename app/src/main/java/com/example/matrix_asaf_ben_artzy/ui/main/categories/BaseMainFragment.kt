package com.example.matrix_asaf_ben_artzy.ui.main.categories

import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.matrix_asaf_ben_artzy.R
import com.example.matrix_asaf_ben_artzy.adapters.CatAndItemsAdapter
import com.example.matrix_asaf_ben_artzy.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseMainFragment
constructor(
    @LayoutRes private val layoutRes: Int
) : Fragment(layoutRes) {

    @Inject
    lateinit var requestManager: RequestManager

    protected val viewModel: MainViewModel by activityViewModels()
    protected var mAdapter: CatAndItemsAdapter? = null


    fun initRecyclerView(tabIndex: Int, recyclerView: RecyclerView) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            mAdapter = CatAndItemsAdapter(tabIndex, requestManager).apply {
                onItemClick = { itemData, catData ->
//                    Toast.makeText(context, "item ${it.Id} clicked!", Toast.LENGTH_SHORT).show()
                    viewModel.setSelectedItem(itemData, catData)
                    findNavController().navigate(R.id.action_tabsCatFragment_to_detailsFragment)
                }
            }
            adapter = mAdapter
        }
    }


    override fun onDestroyView() {
        // avoid memory leak
        mAdapter = null
        super.onDestroyView()
    }


}