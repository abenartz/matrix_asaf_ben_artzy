package com.example.matrix_asaf_ben_artzy.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.viewpager2.widget.ViewPager2
import com.example.matrix_asaf_ben_artzy.R
import com.example.matrix_asaf_ben_artzy.adapters.*
import com.example.matrix_asaf_ben_artzy.adapters.ALL_CAT_PAGE_INDEX
import com.example.matrix_asaf_ben_artzy.databinding.ActivityMainBinding
import com.example.matrix_asaf_ben_artzy.ui.ToolbarInteraction
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ToolbarInteraction {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolbar)
        onRestoreInstanceState()
    }

    override fun onStart() {
        super.onStart()
        setupActionBarWithNavController()
    }

    private fun onRestoreInstanceState() {
        val host = supportFragmentManager.findFragmentById(R.id.container_fragments)
        host?.let {
            // do nothing
        } ?: createNavHost()
    }

    private fun createNavHost() {
        val navHost = NavHostFragment.create(R.navigation.nav_main_graph)
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container_fragments,
                navHost
            )
            .setPrimaryNavigationFragment(navHost)
            .commit()
    }

    private fun setupActionBarWithNavController() {
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.tabsCatFragment))
        NavigationUI.setupActionBarWithNavController(
            this,
            findNavController(R.id.container_fragments),
            appBarConfiguration
        )
    }

    override fun setToolbarTitle(title: String?) {
        binding.textTitle.text = title
    }


}