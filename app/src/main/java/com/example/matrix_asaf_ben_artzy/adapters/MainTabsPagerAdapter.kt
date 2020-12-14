package com.example.matrix_asaf_ben_artzy.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.matrix_asaf_ben_artzy.ui.main.categories.all_coupons.AllCouponsFragment
import com.example.matrix_asaf_ben_artzy.ui.main.categories.favorites.FavoritesFragment
import com.example.matrix_asaf_ben_artzy.ui.main.categories.my_pinukim.MyPinukimFragment
import com.example.matrix_asaf_ben_artzy.ui.main.categories.recommended.RecommendedFragment

const val ALL_CAT_PAGE_INDEX = 0
const val RECOMMENDED_PAGE_INDEX = 1
const val MY_PINUKIM_PAGE_INDEX = 2
const val FAVORITES_PAGE_INDEX = 3

class MainCatPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        ALL_CAT_PAGE_INDEX to { AllCouponsFragment() },
        RECOMMENDED_PAGE_INDEX to { RecommendedFragment() },
        MY_PINUKIM_PAGE_INDEX to { MyPinukimFragment() },
        FAVORITES_PAGE_INDEX to { FavoritesFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }


}