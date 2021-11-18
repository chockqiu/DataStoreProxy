package com.chockqiu.datastoreproxy

import android.os.Parcelable
import com.alibaba.fastjson.JSON
import com.tencent.mmkv.MMKV

class MMKVDataStore(name: String) : DataStore {

    private var mmkv: MMKV = MMKV.mmkvWithID(name)

    override fun clear() {
        mmkv.clearAll()
    }

    override fun write(key: String, obj: Any): Boolean {
        return if (obj is String) {
            mmkv.encode(key, obj)
        } else if (obj is Int) {
            mmkv.encode(key, obj)
        } else if (obj is Boolean) {
            mmkv.encode(key, obj)
        } else if (obj is Float) {
            mmkv.encode(key, obj)
        } else if (obj is Long) {
            mmkv.encode(key, obj)
        } else if (obj is Double) {
            mmkv.encode(key, obj)
        } else if (obj is ByteArray) {
            mmkv.encode(key, obj)
        } else if (obj is Parcelable) {
            mmkv.encode(key, obj)
        } else {
            mmkv.encode(key, JSON.toJSONString(obj))
        }
    }

    override fun readString(key: String, def: String?): String? {
        return mmkv.decodeString(key, def)
    }

    override fun readBoolean(key: String, def: Boolean): Boolean {
        return mmkv.decodeBool(key, def)
    }

    override fun readInt(key: String, def: Int): Int {
        return mmkv.decodeInt(key, def)
    }

    override fun readLong(key: String, def: Long): Long {
        return mmkv.decodeLong(key, def)
    }

    override fun readFloat(key: String, def: Float): Float {
        return mmkv.decodeFloat(key, def)
    }

    override fun readDouble(key: String, def: Double): Double {
        return mmkv.decodeDouble(key, def)
    }

    override fun readBytes(key: String, def: ByteArray?): ByteArray? {
        return mmkv.decodeBytes(key, def)
    }
}