package com.chockqiu.datastore

import androidx.annotation.Keep

@Keep
class ExpBeen {

    var name: String? = null
    var value: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ExpBeen

        if (name != other.name) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (value?.hashCode() ?: 0)
        return result
    }


}