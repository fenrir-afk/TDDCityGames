package com.example.tddcitygames.Ui

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class GameFragmentTest {

    private lateinit var gameFragment: GameFragment
    @Before
    fun setUp() {
        gameFragment = GameFragment()
    }
    @Test
    fun City_is_Right(){
        var city: String ="Milan"
        var city1: String = "New York"
        val res = gameFragment.checkCity(city,city1)
        assertEquals(true,res)
    }

}