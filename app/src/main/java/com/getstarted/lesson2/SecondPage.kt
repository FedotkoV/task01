package com.getstarted.lesson2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.getstarted.lesson2.databinding.FragmentSecondPageBinding
import com.google.android.material.snackbar.Snackbar

class SecondPage : Fragment() {

    lateinit var binding: FragmentSecondPageBinding

    val currentCorrectAnswers = correctAnswers

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_second_page, container, false)
        binding.resultPage.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_secondPage_to_resultPage)
        }
        setQuestion()

        canGoToNextPage = false
        binding.resultPage.setOnClickListener{
            correctAnswers = currentCorrectAnswers
            checkAnswers()
            if (canGoToNextPage) {
                view?.findNavController()
                    ?.navigate(SecondPageDirections.actionSecondPageToResultPage())
            }
        }
        return binding.root
    }

    // sets questions on the page
    private fun setQuestion() {
        questionIndex = QUESTIONS_ON_PAGE
        var answerIndex = 0
        for (i in  QUESTIONS_ON_PAGE until QUESTIONS_ON_SECOND_PAGE) {
            currentQuestion = questions[questionIndex].text

            // sets question and answers in correct field by type of question
            when (questions[questionIndex].type) {
                QuestionType.MULTIPLE -> {
                    binding.multipleQuestion.text = currentQuestion
                    for (i in 0 until AMOUNT_OF_ANSWERS) {
                        when (answerIndex) {
                            0 -> binding.firstBox.text = questions[questionIndex].answers[answerIndex]
                            1 -> binding.secondBox.text = questions[questionIndex].answers[answerIndex]
                            2 -> binding.thirdBox.text = questions[questionIndex].answers[answerIndex]
                            3 -> binding.fourthBox.text = questions[questionIndex].answers[answerIndex]
                        }
                        answerIndex++
                    }
                }
                QuestionType.SINGLE -> {
                    binding.singleQuestion.text = currentQuestion
                    for (i in 0 until AMOUNT_OF_ANSWERS) {
                        when (answerIndex) {
                            0 -> binding.radioFirst.text = questions[questionIndex].answers[answerIndex]
                            1 -> binding.radioSecond.text =
                                questions[questionIndex].answers[answerIndex]
                            2 -> binding.radioThird.text = questions[questionIndex].answers[answerIndex]
                            3 -> binding.radioFourth.text =
                                questions[questionIndex].answers[answerIndex]
                        }
                        answerIndex++
                    }
                }
                else -> {
                    binding.enterQuestion.text = currentQuestion
                }
            }
            answerIndex = 0
            questionIndex++
        }
    }

    /** checks all answers on the page
     *  if someone of them without answer shows message about it
     * when all questions are answered makes it possible to change page
     */
    private fun checkAnswers() {
        when {
            !checkRadioButtons() -> Snackbar.make(binding.snackText,
                getString(R.string.cannot_be_empty),
                Snackbar.LENGTH_LONG).show()
            !checkBoxes() -> Snackbar.make(binding.snackText,
                getString(R.string.cannot_be_empty),
                Snackbar.LENGTH_LONG).show()
            binding.textField.text.isEmpty() -> Snackbar.make(binding.snackText,
                getString(R.string.cannot_be_empty),
                Snackbar.LENGTH_LONG).show()
            !checkTextField() -> Snackbar.make(binding.snackText,
                getString(R.string.only_letters),
                Snackbar.LENGTH_LONG).show()
            else -> {
                canGoToNextPage = true
            }
        }
    }

    /**
     * checks EditText field if it has only english letters
     */
    private fun checkTextField(): Boolean {
        val inputUser = binding.textField.text
        for (i in inputUser.indices) {
            if (!inputUser[i].isLetter()) {
                return false
            }
        }
        userName = inputUser.toString()
        return true
    }

    fun checkRadioButtons(): Boolean {
        when (binding.radioGroup.checkedRadioButtonId) {
            -1 -> return false
            binding.radioThird.id -> correctAnswers++
        }
        return true
    }

    private fun checkBoxes(): Boolean {
        var result = false // will be false only if all checkBoxes are empty

        // helps to know that correct checkBox is chosen
        var checker = 0
        var amountOfAnswers = 0

        var boxIsChecked = binding.firstBox.isChecked
        if(boxIsChecked) {
            amountOfAnswers++
            checker++
            result = true
        }

        boxIsChecked = binding.secondBox.isChecked
        if(boxIsChecked) {
            checker++
            result = true
        }

        boxIsChecked = binding.thirdBox.isChecked
        if (boxIsChecked) {
            checker++
            result = true
        }

        boxIsChecked =  binding.fourthBox.isChecked
        if (boxIsChecked) {
            checker++
            result = true
        }

        if (checker == 1 && amountOfAnswers == 1)
            correctAnswers++

        return result
    }
}