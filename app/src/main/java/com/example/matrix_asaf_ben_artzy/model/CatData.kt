package com.example.matrix_asaf_ben_artzy.model

data class CatData(
    var CatId: Int,
    var CTitle: String
) {

    override fun toString(): String {
        return "CatData(CatId=$CatId, CTitle='$CTitle')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CatData

        if (CatId != other.CatId) return false
        if (CTitle != other.CTitle) return false

        return true
    }

    override fun hashCode(): Int {
        var result = CatId
        result = 31 * result + CTitle.hashCode()
        return result
    }

}
