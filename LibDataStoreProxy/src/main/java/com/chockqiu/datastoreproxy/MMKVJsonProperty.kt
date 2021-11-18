package com.chockqiu.datastoreproxy

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.TypeReference
import kotlin.reflect.KProperty

fun <T> jsonProperty(
    type: TypeReference<T>,
    default: T,
    key: String? = null
) = MMKVJsonProperty(null, type, default, key)

open class MMKVJsonProperty<T>(
    val name: String?,
    private val mTypeReference: TypeReference<T>,
    private val default: T,
    private val key: String? = null
) {

    private var cacheStore = MMKVDataStore(name ?: "MMKVJsonProperty_")

    open operator fun getValue(thisRef: Any?, property: KProperty<*>): T =
        getValue(default, key ?: property.name)

    open operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) =
        putValue(key ?: property.name, value)

    protected fun getValue(default: T, name: String): T {
        val res = cacheStore.readString(name, null)
        return if (res.isNullOrBlank()) {
            default
        } else {
            JSON.parseObject(res, mTypeReference)
        }
    }

    protected fun putValue(name: String, value: T) =
        cacheStore.write(name, if (value == null) "" else JSON.toJSONString(value))

}