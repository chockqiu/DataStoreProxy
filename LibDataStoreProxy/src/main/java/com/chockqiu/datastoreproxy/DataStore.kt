package com.chockqiu.datastoreproxy

interface DataStore {

    fun clear()
    fun write(key: String, obj: Any): Boolean
    fun readString(key: String, def: String?): String?
    fun readBoolean(key: String, def: Boolean): Boolean
    fun readInt(key: String, def: Int): Int
    fun readLong(key: String, def: Long): Long
    fun readFloat(key: String, def: Float): Float
    fun readDouble(key: String, def: Double): Double
    fun readBytes(key: String, def: ByteArray?): ByteArray?
//    fun <T : Parcelable> readParcelable(key: String, tClass: Class<T>, def: T?): T?
//    fun <T> readBeen(key: String, mTypeReference: TypeReference<T>, def: T): T
}