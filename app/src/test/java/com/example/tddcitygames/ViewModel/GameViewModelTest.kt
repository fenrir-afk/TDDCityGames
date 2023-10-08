package com.example.tddcitygames.ViewModel

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class GameViewModelTest {
    private lateinit var gameViewModel: GameViewModel
    private lateinit var myList: List<String>
    private val filePath = "Download/Cities.txt"
    @Before
    fun setUp() {
        gameViewModel = GameViewModel()
    }
    @Test
    fun City_is_in_List(){
        val city1: String = "Milan"
        val city2: String = "New York"
        myList = listOf("Moscow","Milan","New York","Kazan")
        val res1 = gameViewModel.checkCity(city2,myList,city1)
        assertEquals(true,res1)
    }
}