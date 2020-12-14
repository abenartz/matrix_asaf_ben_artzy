package com.example.matrix_asaf_ben_artzy.ui.main.categories.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.matrix_asaf_ben_artzy.R
import com.example.matrix_asaf_ben_artzy.databinding.FragmentBaseMainBinding

class FavoritesFragment : Fragment(R.layout.fragment_base_main) {

    private var _binding: FragmentBaseMainBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBaseMainBinding.bind(view)
        binding.textTabName.visibility = View.VISIBLE
        binding.textTabName.text = getString(R.string.heb_recommended)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}