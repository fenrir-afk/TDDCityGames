package com.example.tddcitygames.Fragments

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class GameFragmentTest {

    //private lateinit var gameFragment: GameFragment
    @Before
    fun setUp() {
        //gameFragment = GameFragment()
    }
    @Test
    fun City_is_Right(){
        //gameFragment = GameFragment()
        var city: String ="Milan"
        var city1: String = "New York"
        val res = GameFragment().checkCity(city,city1)
        assertEquals(true,res)
    }

}