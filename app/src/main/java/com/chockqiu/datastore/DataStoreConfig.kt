package com.chockqiu.datastore

import com.alibaba.fastjson.TypeReference
import com.chockqiu.datastoreproxy.jsonProperty
import com.chockqiu.datastoreproxy.property

inline val mDataStoreConfigs: DataStoreConfig get() = DataStoreConfig.instance

class DataStoreConfig {

    /**
     * 字符串测试
     */
    var expString by property("")

    var expBoolean by property(false)

    var expInt by property(0)

    var expLong by property(0L)

    var expFloat by property(0.0f)

    var expDouble by property(0.0)

    /**
     * bytes测试
     */
    var expBytes by property(byteArrayOf(0, 1, 2, 3, 4, 5))

    /**
     * Been测试
     */
    var expBeen by property(ExpBeen())

    /**
     * Been测试
     */
    var expBeen2 by jsonProperty(object : TypeReference<ExpBeen>() {}, ExpBeen())

    /**
     * Map 测试
     */
    var expMap by jsonProperty(
        object : TypeReference<Map<String, ExpBeen>>() {},
        mutableMapOf()
    )

    /**
     * List 测试
     */
    var expList by jsonProperty(
        object : TypeReference<MutableList<ExpBeen>>() {},
        mutableListOf()
    )

    companion object {
        val instance by lazy { return@lazy DataStoreConfig() }
    }
}