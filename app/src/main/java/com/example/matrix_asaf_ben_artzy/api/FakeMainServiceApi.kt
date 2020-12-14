package com.example.matrix_asaf_ben_artzy.api

import android.content.Context
import com.example.matrix_asaf_ben_artzy.constants.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import timber.log.Timber

@ActivityRetainedScoped
class FakeMainServiceApi(
    @ApplicationContext private val context: Context
    ) {

    fun getDataFromJsonAsset(fileName: String): ResponseData? {
        var retData: ResponseData? = null
        try {
            context.assets.open(fileName).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val type = object : TypeToken<ResponseData>() {}.type
                    retData = Gson().fromJson(jsonReader, type)
                }
            }
        } catch (e: Exception) {
            Timber.tag(Constants.APP_DEBUG).e("MainRepository: getDataFromJsonAsset: error = ${e.message} ")
        }
        return retData
    }



}
