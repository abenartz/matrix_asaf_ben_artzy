package com.example.matrix_asaf_ben_artzy.reopsitory

import com.example.matrix_asaf_ben_artzy.api.FakeMainServiceApi
import com.example.matrix_asaf_ben_artzy.constants.Constants.Companion.APP_DEBUG
import com.example.matrix_asaf_ben_artzy.constants.Constants.Companion.DATA_FILENAME
import com.example.matrix_asaf_ben_artzy.model.CatAndItems
import com.example.matrix_asaf_ben_artzy.model.CatData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class MainRepository(
    private val fakeMainServiceApi: FakeMainServiceApi
) {

    fun getInitData(): Flow<List<CatAndItems>> = flow {
        val listCatAndItems = ArrayList<CatAndItems>()

        val responseData = fakeMainServiceApi.getDataFromJsonAsset(DATA_FILENAME)

        responseData?.dataObject?.let { dataObject ->
            dataObject.dataListCat.forEach { catData ->
                listCatAndItems.add(
                    CatAndItems(
                        catData = CatData(catData.CatId, catData.CTitle),
                        items = dataObject.dataListObject.filter { it.CatId ==  catData.CatId }
                    )
                )
            }
        }
        Timber.tag(APP_DEBUG).e("MainRepository: getInitData: emitting listCatAndItems = $listCatAndItems")
        emit(listCatAndItems)
    }.catch {
        Timber.tag(APP_DEBUG).e("MainRepository: getInitData: Flow exception - ${it.message}")
    }.flowOn(Dispatchers.Default)


}