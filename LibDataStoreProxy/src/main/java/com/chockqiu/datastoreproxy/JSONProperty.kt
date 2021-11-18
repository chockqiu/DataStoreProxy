package com.chockqiu.datastoreproxy

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.TypeReference
import kotlin.reflect.KProperty

open class JSONProperty<T>(
    val mDataStore: DataStore,
    private val mTypeReference: TypeReference<T>,
    private val default: T?,
    private val key: String? = null
) {

    open operator fun getValue(thisRef: Any?, property: KProperty<*>): T? =
        getValue(default, key ?: property.name)

    open operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) =
        putValue(key ?: property.name, value)

    protected fun getValue(default: T?, name: String): T? {
        val res = mDataStore.readString(name, null)
        return if (res == null) {
            default
        } else {
            JSON.parseObject(res, mTypeReference)
        }
    }

    protected fun putValue(key: String, value: T?) {
        if (value == null) {
            mDataStore.delete(key)
        } else {
            mDataStore.write(key, JSON.toJSONString(value))
        }
    }


}