package com.example.tddcitygames.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tddcitygames.R
import com.example.tddcitygames.databinding.FragmentFInalBinding

class FInalFragment : Fragment() {
    private lateinit var binding: FragmentFInalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFInalBinding.inflate(inflater,container,false)
        binding.button.setOnClickListener{
            findNavController().navigate(R.id.homeFragment, null)
        }
        return binding.root
    }
}