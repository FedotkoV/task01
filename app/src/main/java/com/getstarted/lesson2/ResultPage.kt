package com.getstarted.lesson2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.getstarted.lesson2.databinding.FragmentResultPageBinding

class ResultPage : Fragment() {
    lateinit var binding: FragmentResultPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_result_page, container, false)

        showTheResult()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        correctAnswers = 0
        questionIndex = 0
    }

    private fun showTheResult() {
        val score = String.format(getString(R.string.result_of_answers), correctAnswers)
        val travelTo = String.format(getString(R.string.travel_to), dreamCity)
        val name = String.format(getString(R.string.hi), userName)
        binding.name.text = name
        binding.score.text = score
        binding.resultText.text = travelTo
    }

}