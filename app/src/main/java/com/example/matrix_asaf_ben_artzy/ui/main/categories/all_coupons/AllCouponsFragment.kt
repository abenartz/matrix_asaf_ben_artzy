package com.example.matrix_asaf_ben_artzy.ui.main.categories.all_coupons

import android.os.Bundle
import android.view.View
import com.example.matrix_asaf_ben_artzy.R
import com.example.matrix_asaf_ben_artzy.adapters.ALL_CAT_PAGE_INDEX
import com.example.matrix_asaf_ben_artzy.constants.Constants.Companion.APP_DEBUG
import com.example.matrix_asaf_ben_artzy.databinding.FragmentBaseMainBinding
import com.example.matrix_asaf_ben_artzy.ui.main.categories.BaseMainFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AllCouponsFragment: BaseMainFragment(R.layout.fragment_base_main) {

    private var _binding: FragmentBaseMainBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBaseMainBinding.bind(view)
        initRecyclerView(ALL_CAT_PAGE_INDEX, binding.rvTitleAndItems)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.cachedData.observe(viewLifecycleOwner, { listCatAndItems ->
            Timber.tag(APP_DEBUG).e("AllCatFragment: subscribeObservers: submitList listCatAndItems = ${listCatAndItems.size}")
            listCatAndItems?.let {
                mAdapter?.submitList(it)
            }
        })
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}