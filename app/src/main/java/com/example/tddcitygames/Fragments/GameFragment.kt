package com.example.tddcitygames.Fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.tddcitygames.R
import com.example.tddcitygames.ViewModel.GameViewModel
import com.example.tddcitygames.databinding.FragmentGameBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private lateinit var gameMvvm: GameViewModel
    private lateinit var myList: List<String>
    private val filePath = "Download/Cities.txt"
    private  var timer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameMvvm = GameViewModel()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater,container,false)
        val timer = MyCounter()
        binding.EnterButton.setOnClickListener{
            if (binding.myEditText.text != null){
                var res = checkCity(binding.myEditText.text.toString(),binding.prevCity.text.toString())
                if (res){
                    binding.prevCity.text = binding.myEditText.text
                    binding.myEditText.text.clear()
                    if (binding.nameText.text.toString() == "First player"){
                        binding.nameText.text = "Second player"
                    }else{
                        binding.nameText.text = "First player"
                    }
                    val timer = MyCounter()
                    Toast.makeText(activity,"All good,you are right", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(activity,"I dont know such city", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch{
            if (File(Environment.getExternalStorageDirectory().toString() + "/" + filePath).exists()){
                myList = File(Environment.getExternalStorageDirectory().toString() + "/" + filePath).readText().split("\n")
            }else{
                gameMvvm.getCityList()
                if (File(Environment.getExternalStorageDirectory().toString() + "/" + filePath).exists()){
                    myList =File(Environment.getExternalStorageDirectory().toString() + "/" + filePath).readText().split("\n")

                }else{
                    Log.d("MyTag","File does not exist")
                }
            }
        }


    }
    private fun MyCounter() {
        timer?.cancel()
        timer = object: CountDownTimer(100000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.counter.textSize = 50f
                binding.counter.text = (millisUntilFinished / 1000).toString() + ""
                println("Timer  : " + millisUntilFinished / 1000)
            }
            override fun onFinish() {
                findNavController().navigate(R.id.FInalFragment, null)
            }
        }.start()
    }
    fun checkCity(city: String,prevCity:String): Boolean {
        if (city == null){
            return false
        }
        return gameMvvm.checkCity(city,myList,prevCity)
    }


}