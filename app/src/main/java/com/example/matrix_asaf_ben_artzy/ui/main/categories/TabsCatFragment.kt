package com.example.matrix_asaf_ben_artzy.ui.main.categories

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.matrix_asaf_ben_artzy.R
import com.example.matrix_asaf_ben_artzy.adapters.*
import com.example.matrix_asaf_ben_artzy.constants.Constants.Companion.APP_DEBUG
import com.example.matrix_asaf_ben_artzy.databinding.FragmentTabsCatBinding
import com.example.matrix_asaf_ben_artzy.ui.ToolbarInteraction
import com.example.matrix_asaf_ben_artzy.ui.main.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber

class TabsCatFragment: Fragment(R.layout.fragment_tabs_cat) {

    private var _binding: FragmentTabsCatBinding? = null
    private val binding get() = _binding!!

    private lateinit var toolbarInteraction: ToolbarInteraction

    private val viewModel: MainViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarInteraction.setToolbarTitle("ראשי")
        _binding = FragmentTabsCatBinding.bind(view)
        viewModel.initSelectedItem()
        initViewPager()
    }

    private fun initViewPager() {
        binding.viewPager2.apply {
            adapter = MainCatPagerAdapter(this@TabsCatFragment)
            isUserInputEnabled = false // disable scrolling horizontal
        }

        TabLayoutMediator(binding.tabs, binding.viewPager2) { tab, position ->
            tab.text = when (position) {
                ALL_CAT_PAGE_INDEX -> getString(R.string.heb_all_cupons)
                RECOMMENDED_PAGE_INDEX -> getString(R.string.heb_recommended)
                MY_PINUKIM_PAGE_INDEX -> getString(R.string.heb_my_pinukim)
                FAVORITES_PAGE_INDEX -> getString(R.string.heb_favorites)
                else -> ""
            }
        }.attach()
    }

    override fun onDestroyView() {
        // avoid memory leak
        binding.viewPager2.adapter = null
        binding.rootView.removeAllViews()
        _binding = null
        super.onDestroyView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            toolbarInteraction = context as ToolbarInteraction
        } catch (e: ClassCastException){
            Timber.tag(APP_DEBUG).e("TabsCatFragment: onAttach: $context must implement ToolbarInteraction")
        }
    }

}