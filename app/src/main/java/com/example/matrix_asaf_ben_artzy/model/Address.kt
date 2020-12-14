package com.example.matrix_asaf_ben_artzy.model

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("Addr")
    var Addr: String,
    @SerializedName("DAd")
    var DAd: String
) {
    override fun toString(): String {
        return "Address(Addr='$Addr', DAd='$DAd')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Address

        if (Addr != other.Addr) return false
        if (DAd != other.DAd) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Addr.hashCode()
        result = 31 * result + DAd.hashCode()
        return result
    }

}