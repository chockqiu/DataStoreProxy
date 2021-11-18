package com.chockqiu.datastore

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.tencent.mmkv.MMKV
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    var initFlag = false

    fun init() {
        if (initFlag) return
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        MMKV.initialize(appContext)
        initFlag = true
    }

    @Test
    fun testString() {
        init()
        var s = mDataStoreConfigs.expString
        assertEquals("", s)
        val ts = "test string"
        mDataStoreConfigs.expString = ts
        s = mDataStoreConfigs.expString
        assertEquals(ts, s)
        mDataStoreConfigs.expString = ""
    }

    @Test
    fun testString2() {
        init()
        mDataStoreConfigs.expString2 = null
        var s = mDataStoreConfigs.expString2
        assertEquals(null, s)
        val ts = "test string"
        mDataStoreConfigs.expString2 = ts
        s = mDataStoreConfigs.expString2
        assertEquals(ts, s)
        mDataStoreConfigs.expString2 = null
    }

    @Test
    fun testString3() {
        init()
        var s = mDataStoreConfigs.expString3
        assertEquals("", s)
        val ts = "test string"
        mDataStoreConfigs.expString3 = ts
        s = mDataStoreConfigs.expString3
        assertEquals(ts, s)
        mDataStoreConfigs.expString3 = null
    }

    @Test
    fun testBoolean() {
        init()
        var s = mDataStoreConfigs.expBoolean
        assertEquals(false, s)
        mDataStoreConfigs.expBoolean = true
        s = mDataStoreConfigs.expBoolean
        assertEquals(true, s)
        mDataStoreConfigs.expBoolean = false
    }

    @Test
    fun testInt() {
        init()
        var s = mDataStoreConfigs.expInt
        assertEquals(0, s)
        mDataStoreConfigs.expInt = 123
        s = mDataStoreConfigs.expInt
        assertEquals(123, s)
        mDataStoreConfigs.expInt = 0
    }

    @Test
    fun testLong() {
        init()
        var s = mDataStoreConfigs.expLong
        assertEquals(0L, s)
        mDataStoreConfigs.expLong = 23242424235234L
        s = mDataStoreConfigs.expLong
        assertEquals(23242424235234L, s)
        mDataStoreConfigs.expLong = 0L
    }


    @Test
    fun testFloat() {
        init()
        mDataStoreConfigs.expFloat = 0.0f
        var s = mDataStoreConfigs.expFloat
        assertEquals(0f, s)
        mDataStoreConfigs.expFloat = 42352.34f
        s = mDataStoreConfigs.expFloat
        assertEquals(42352.34f, s)
        mDataStoreConfigs.expFloat = 0.0f
    }

    @Test
    fun testDouble() {
        init()
        var s = mDataStoreConfigs.expDouble
        assertEquals(0.000, s, 0.0)
        mDataStoreConfigs.expDouble = 2.3242424235234
        s = mDataStoreConfigs.expDouble
        assertEquals(2.3242424235234, s, 0.0)
        mDataStoreConfigs.expDouble = 0.0
    }

    @Test
    fun testByteArray() {
        init()
        val def = byteArrayOf(0, 1, 2, 3, 4, 5)
        var s = mDataStoreConfigs.expBytes
        assertArrayEquals(def, s)
        val bytes = byteArrayOf(9, 8, 7, 6)
        mDataStoreConfigs.expBytes = bytes
        s = mDataStoreConfigs.expBytes
        assertArrayEquals(bytes, s)
        mDataStoreConfigs.expBytes = def
    }

    @Test
    fun textBeen() {
        init()
        val been = ExpBeen()
        var s = mDataStoreConfigs.expBeen
        assertEquals(been, s)
        val ta = ExpBeen().apply {
            name = "Chock"
            value = "2021"
        }
        mDataStoreConfigs.expBeen = ta
        s = mDataStoreConfigs.expBeen
        assertEquals(ta, s)
        mDataStoreConfigs.expBeen = been
    }

    @Test
    fun textBeen2() {
        init()
        val been = ExpBeen()
        var s = mDataStoreConfigs.expBeen2
        assertEquals(been, s)
        val ta = ExpBeen().apply {
            name = "Chock"
            value = "2021"
        }
        mDataStoreConfigs.expBeen2 = ta
        s = mDataStoreConfigs.expBeen2
        assertEquals(ta, s)
        mDataStoreConfigs.expBeen2 = been
    }

    @Test
    fun testList() {
        init()
        val def = mutableListOf<ExpBeen>()
        var s = mDataStoreConfigs.expList
        assertEquals(def, s)
        val ts = mutableListOf(ExpBeen().apply {
            name = "fwe345"
            value = "1234234234"
        }, ExpBeen().apply {
            name = "432f"
            value = "32623547645"
        })
        mDataStoreConfigs.expList = ts
        s = mDataStoreConfigs.expList
        assertEquals(ts, s)
        mDataStoreConfigs.expList = def
    }

    @Test
    fun testMap() {
        init()
        val def = mutableMapOf<String, ExpBeen>()
        var s = mDataStoreConfigs.expMap
        assertEquals(def, s)
        val ret = mutableMapOf<String, ExpBeen>().apply {
            put("sdsdr", ExpBeen().apply {
                name = "235rfasf"
                value = "asdgsetg"
            })
            put("rfaesrg", ExpBeen().apply {
                name = "cdeftgwer134"
                value = "fergf233"
            })
        }
        mDataStoreConfigs.expMap = ret
        s = mDataStoreConfigs.expMap
        assertEquals(ret, s)
        mDataStoreConfigs.expMap = def
    }
}