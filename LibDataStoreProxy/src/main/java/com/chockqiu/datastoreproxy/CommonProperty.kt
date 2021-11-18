package com.chockqiu.datastoreproxy

import com.alibaba.fastjson.JSON
import kotlin.reflect.KProperty

open class CommonProperty<T>(
    val cacheStore: DataStore,
    private val default: T,
    private val key: String? = null
) {

    open operator fun getValue(thisRef: Any?, property: KProperty<*>): T =
        getValue(key ?: property.name, default)

    open operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) =
        putValue(key ?: property.name, value)

    protected fun getValue(name: String, default: T): T {
        val res = when (default) {
            is Int -> cacheStore.readInt(name, default)
            is Long -> cacheStore.readLong(name, default)
            is Double -> cacheStore.readDouble(name, default)
            is Float -> cacheStore.readFloat(name, default)
            is Boolean -> cacheStore.readBoolean(name, default)
            is String -> cacheStore.readString(name, default)
            is ByteArray -> cacheStore.readBytes(name, default)
            else -> {
                val res = cacheStore.readString(name, null)
                return if (res == null) {
                    default
                } else {
                    JSON.parseObject(res, default!!::class.java)
                }
            }
        }
        return res as T
    }

    protected fun putValue(key: String, value: T) {
        if (value == null) {
            cacheStore.delete(key)
        } else {
            when (default) {
                is Int -> {
                    cacheStore.write(key, value as Int)
                }
                is Long -> {
                    cacheStore.write(key, value as Long)
                }
                is Double -> {
                    cacheStore.write(key, value as Double)
                }
                is Float -> {
                    cacheStore.write(key, value as Float)
                }
                is Boolean -> {
                    cacheStore.write(key, value as Boolean)
                }
                is String -> {
                    cacheStore.write(key, value as String)
                }
                is ByteArray -> {
                    cacheStore.write(key, value as ByteArray)
                }
                else -> {
                    cacheStore.write(key, JSON.toJSONString(value))
                }
            }
        }

    }
}