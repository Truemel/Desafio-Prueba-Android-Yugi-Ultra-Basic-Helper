package com.example.yu_gi_ohultrabasichelper.extras

import org.junit.Assert.assertEquals
import org.junit.Test

class GlobalPathToolsTest{

    @Test
    fun fixNamePathTest(){
        val name = " Arb dss: lIst-And tru "
        val result = fixNameToPath(name)

        assertEquals("Arb_dss__lIst_And_tru", result)
    }

    @Test
    fun getCardImagePathTest(){
        val card = "Ria del"
        val result = getCardImagePath(card)

        assertEquals(IMAGES_CARDS+"Ria_del.jpg", result)
    }

    @Test
    fun getSetImagePathTest(){
        val card = "Sur-Aldan"
        val result = getSetImagePath(card)

        assertEquals(IMAGES_SETS+"Sur_Aldan.jpg", result)
    }

}