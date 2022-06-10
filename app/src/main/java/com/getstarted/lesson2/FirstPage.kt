package com.getstarted.lesson2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.getstarted.lesson2.databinding.FragmentFirstPageBinding
import com.google.android.material.snackbar.Snackbar

class FirstPage : Fragment() {
    private lateinit var binding: FragmentFirstPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_first_page, container, false)

        setQuestion()

        canGoToNextPage = false
        binding.nextPage.setOnClickListener{
            correctAnswers = 0
            checkAnswers()
            if (canGoToNextPage) {
                view?.findNavController()
                    ?.navigate(FirstPageDirections.actionFirstPageToSecondPage())
            }
            println(canGoToNextPage)
        }

        return binding.root
    }

    // sets questions on the page
    private fun setQuestion() {
        questionIndex = 0
        var answerIndex = 0
        for (i in  0 until QUESTIONS_ON_PAGE) {
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
            !checkRadioButtons() -> {
                Snackbar.make(binding.snackText,
                    getString(R.string.cannot_be_empty),
                    Snackbar.LENGTH_LONG).show()
            }
            !checkBoxes() -> {
                Snackbar.make(binding.snackText,
                    getString(R.string.cannot_be_empty),
                    Snackbar.LENGTH_LONG).show()
            }
            binding.textField.text.isEmpty() -> {
                Snackbar.make(binding.snackText,
                    getString(R.string.cannot_be_empty),
                    Snackbar.LENGTH_LONG).show()
            }
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
        var result = true
        val inputUser = binding.textField.text
        for (i in inputUser.indices) {
            if (!inputUser[i].isLetter()) result = false
        }
        dreamCity = inputUser.toString()
        return result
    }

    fun checkRadioButtons(): Boolean {
        val chosenButton = binding.radioGroup.checkedRadioButtonId
        if (chosenButton == -1) return false
        when (chosenButton) {
            binding.radioFirst.id-> correctAnswers++
        }
        return true
    }

    private fun checkBoxes(): Boolean {
        var result = false // will be false only if all checkBoxes are empty

        // helps to know that correct checkBox is chosen
        var amountOfAnswers = 0
        var checker = 0

        var boxIsChecked = binding.firstBox.isChecked
        if(boxIsChecked) {
            amountOfAnswers++
            checker++
            result = true
        }

        boxIsChecked = binding.secondBox.isChecked
        if(boxIsChecked) {
            amountOfAnswers++
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

        when (amountOfAnswers) {
            2 -> if (checker == 2) correctAnswers++
        }
        return result
    }
}