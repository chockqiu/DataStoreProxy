package com.chockqiu.datastoreproxy

class MMKVCommonProperty<T>(
    name: String?,
    default: T,
    key: String? = null
) : CommonProperty<T>(MMKVDataStore(name ?: "MMKVCommonProperty_"), default, key)

fun <T> property(default: T, key: String? = null) = MMKVCommonProperty(null, default, key)