package com.getstarted.lesson2

import com.google.android.material.snackbar.Snackbar

/** class SetQuestions {
    private fun setQuestion() {
        var answerIndex = 0
        for (i in  0 until QUESTIONS_ON_PAGE) {
            currentQuestion = questions[questionIndex].text

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

    private fun checkAnswers() {
        when {
            !checkRadioButtons() -> Snackbar.make(binding.snackText,
                getString(R.string.caanot_be_empty),
                Snackbar.LENGTH_LONG).show()
            !checkBoxes() -> Snackbar.make(binding.snackText,
                getString(R.string.caanot_be_empty),
                Snackbar.LENGTH_LONG).show()
            binding.textField.text.isEmpty() -> Snackbar.make(binding.snackText,
                getString(R.string.caanot_be_empty),
                Snackbar.LENGTH_LONG).show()
            !checkTextField() -> Snackbar.make(binding.snackText,
                getString(R.string.only_letters),
                Snackbar.LENGTH_LONG).show()
            else -> {
                canGoToNextPage = true
            }
        }
    }

    private fun checkTextField(): Boolean {
        val inputUser = binding.textField.text
        for (i in inputUser.indices) {
            if (!inputUser[i].isLetter()) {
                return false
            }
        }
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
        var checker = 0
        var questionAnswer = 0

        var boxIsChecked = binding.firstBox.isChecked
        if(boxIsChecked) checker++

        boxIsChecked = binding.secondBox.isChecked
        if(boxIsChecked) checker++

        boxIsChecked = binding.thirdBox.isChecked
        if (boxIsChecked) {
            checker++
            questionAnswer++
        }

        boxIsChecked =  binding.fourthBox.isChecked
        if (boxIsChecked) {
            checker++
            questionAnswer++

            if(checker == 2 && questionAnswer == 2) correctAnswers++
        }
        else if(checker == 0) return false

        return true
    }
}
 */