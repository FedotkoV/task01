package com.getstarted.lesson2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.getstarted.lesson2.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMainBinding>( inflater,
            R.layout.fragment_main, container, false)
        binding.startButton.setOnClickListener { view: View ->
            findNavController().navigate(R.id.action_mainFragment_to_firstPage)
        }
        return binding.root
    }

}