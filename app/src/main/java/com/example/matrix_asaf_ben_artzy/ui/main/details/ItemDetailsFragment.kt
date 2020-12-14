package com.example.matrix_asaf_ben_artzy.ui.main.details

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.matrix_asaf_ben_artzy.R
import com.example.matrix_asaf_ben_artzy.constants.Constants
import com.example.matrix_asaf_ben_artzy.databinding.FragmentItemDetailsBinding
import com.example.matrix_asaf_ben_artzy.ui.ToolbarInteraction
import com.example.matrix_asaf_ben_artzy.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class ItemDetailsFragment : Fragment(R.layout.fragment_item_details) {

    @Inject
    lateinit var requestManager: RequestManager

    private var _binding: FragmentItemDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()
    lateinit var toolbarInteraction: ToolbarInteraction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarInteraction.setToolbarTitle(viewModel.getSelectedItemTitle())
        _binding = FragmentItemDetailsBinding.bind(view)
        setupView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                findNavController().popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupView() {
        val imageUrl = viewModel.getSelectedItemImageUrl()
        binding.apply {
            requestManager
                .load(imageUrl)
                .error(R.color.Gray)
                .into(imageItem)

            textItemId.text = viewModel.getSelectedItemId().toString()
            textItemCategoryName.text = viewModel.getSelectedItemCatName()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            toolbarInteraction = context as ToolbarInteraction
        } catch (e: ClassCastException){
            Timber.tag(Constants.APP_DEBUG).e("ItemDetailsFragment: onAttach: $context must implement ToolbarInteraction")
        }

    }



}