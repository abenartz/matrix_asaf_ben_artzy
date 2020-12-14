package com.example.matrix_asaf_ben_artzy.api

import com.example.matrix_asaf_ben_artzy.model.CatData
import com.example.matrix_asaf_ben_artzy.model.ItemData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("DataObject")
    @Expose
    var dataObject: DataObject
) {
    override fun toString(): String {
        return "ResponseData(dataObject=$dataObject)"
    }
}

data class DataObject(
    @SerializedName("DataListObject")
    @Expose
    var dataListObject: List<ItemData>,
    @SerializedName("DataListCat")
    @Expose
    var dataListCat: List<CatData>
) {
    override fun toString(): String {
        return "DataObject(dataListObject=$dataListObject, dataListCat=$dataListCat)"
    }
}