package com.chockqiu.datastoreproxy

import com.alibaba.fastjson.JSON
import kotlin.reflect.KProperty

open class CommonProperty<T>(
    protected val mDataStore: DataStore,
    protected val default: T,
    protected val key: String? = null
) {

    open operator fun getValue(thisRef: Any?, property: KProperty<*>): T =
        getValue(key ?: property.name, default)

    open operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) =
        putValue(key ?: property.name, value)

    protected fun getValue(name: String, default: T): T {
        val res = when (default) {
            is Int -> mDataStore.readInt(name, default)
            is Long -> mDataStore.readLong(name, default)
            is Double -> mDataStore.readDouble(name, default)
            is Float -> mDataStore.readFloat(name, default)
            is Boolean -> mDataStore.readBoolean(name, default)
            is String -> mDataStore.readString(name, default)
            is ByteArray -> mDataStore.readBytes(name, default)
            else -> {
                val res = mDataStore.readString(name, null)
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
            mDataStore.delete(key)
        } else {
            when (default) {
                is Int -> {
                    mDataStore.write(key, value as Int)
                }
                is Long -> {
                    mDataStore.write(key, value as Long)
                }
                is Double -> {
                    mDataStore.write(key, value as Double)
                }
                is Float -> {
                    mDataStore.write(key, value as Float)
                }
                is Boolean -> {
                    mDataStore.write(key, value as Boolean)
                }
                is String -> {
                    mDataStore.write(key, value as String)
                }
                is ByteArray -> {
                    mDataStore.write(key, value as ByteArray)
                }
                else -> {
                    mDataStore.write(key, JSON.toJSONString(value))
                }
            }
        }

    }
}