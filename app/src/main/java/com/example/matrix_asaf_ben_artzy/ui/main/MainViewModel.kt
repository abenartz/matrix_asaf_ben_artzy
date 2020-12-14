package com.example.matrix_asaf_ben_artzy.ui.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.matrix_asaf_ben_artzy.constants.Constants.Companion.APP_DEBUG
import com.example.matrix_asaf_ben_artzy.model.CatAndItems
import com.example.matrix_asaf_ben_artzy.model.CatData
import com.example.matrix_asaf_ben_artzy.model.ItemData
import com.example.matrix_asaf_ben_artzy.reopsitory.MainRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean


class MainViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    private var selectedItem: ItemData? = null
    var selectedCategory: CatData? = null

    private val _cacheData = MutableLiveData<List<CatAndItems>>()
    val cachedData: MutableLiveData<List<CatAndItems>>
        get() = _cacheData


    init {
        viewModelScope.launch {
            mainRepository.getInitData().collect {
                Timber.tag(APP_DEBUG).e("MainViewModel: collected data of: $it")
                _cacheData.value = it
            }
        }
    }


    fun getSelectedItemImageUrl(): String = selectedItem?.Imag ?: ""
    fun getSelectedItemId(): Int = selectedItem?.Id ?: -1
    fun getSelectedItemCatName(): String = selectedCategory?.CTitle ?: "N/A"

    fun initSelectedItem() {
        selectedItem = null
        selectedCategory = null
    }

    fun setSelectedItem(selItem: ItemData, catData: CatData) {
        selectedItem = selItem
        selectedCategory = catData
    }

    fun getSelectedItemTitle(): String? = selectedItem?.Title

}