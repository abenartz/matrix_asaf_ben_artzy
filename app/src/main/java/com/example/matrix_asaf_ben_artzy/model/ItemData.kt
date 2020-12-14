package com.example.matrix_asaf_ben_artzy.model

import com.google.gson.annotations.SerializedName

data class ItemData(
    @SerializedName("Id")
    var Id: Int,
    @SerializedName("CatId")
    var CatId: Int,
    @SerializedName("Title")
    var Title: String,
    @SerializedName("STitle")
    var STitle: String,
    @SerializedName("Imag")
    var Imag: String,
    @SerializedName("DataListAddr")
    var DataListAddr: List<Address>,

) {


    override fun toString(): String {
        return "ItemData(Id=$Id, CatId=$CatId, Title='$Title', STitle='$STitle', Imag='$Imag', DataListAddr=$DataListAddr)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ItemData

        if (Id != other.Id) return false
        if (CatId != other.CatId) return false
        if (Title != other.Title) return false
        if (STitle != other.STitle) return false
        if (Imag != other.Imag) return false
        if (DataListAddr != other.DataListAddr) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Id
        result = 31 * result + CatId
        result = 31 * result + Title.hashCode()
        result = 31 * result + STitle.hashCode()
        result = 31 * result + Imag.hashCode()
        result = 31 * result + DataListAddr.hashCode()
        return result
    }

}
