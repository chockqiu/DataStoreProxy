package com.chockqiu.datastoreproxy

import com.alibaba.fastjson.TypeReference

class MMKVJSONProperty<T>(
    name: String?,
    mTypeReference: TypeReference<T>,
    default: T?,
    key: String? = null
) : JSONProperty<T>(MMKVDataStore(name ?: "MMKVJsonProperty_"), mTypeReference, default, key)

fun <T> jsonProperty(
    type: TypeReference<T>,
    default: T?,
    key: String? = null
) = MMKVJSONProperty(null, type, default, key)