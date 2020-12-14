package com.example.matrix_asaf_ben_artzy.model

data class CatAndItems(
    var catData: CatData,
    var items: List<ItemData>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CatAndItems

        if (catData != other.catData) return false
        if (items != other.items) return false

        return true
    }

    override fun hashCode(): Int {
        var result = catData.hashCode()
        result = 31 * result + items.hashCode()
        return result
    }

    override fun toString(): String {
        return "CatAndItems(catData=$catData, items=$items)"
    }

}