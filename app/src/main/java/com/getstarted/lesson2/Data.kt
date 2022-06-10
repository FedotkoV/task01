package com.getstarted.lesson2

data class Question(val text: String, val type: QuestionType,
                    var answers: List<String> = listOf(),
                    val correctAnswer: List<String> = listOf())


enum class QuestionType {
    SINGLE, MULTIPLE, ENTER
}

 val questions: MutableList<Question> = mutableListOf(
    Question(text = "What is the capital of Great Britain?",
            answers = listOf("London", "Manchester", "Dublin", "Amsterdam"),
            type = QuestionType.SINGLE,
            correctAnswer = listOf("London")),
    Question(text = "What cities are not ukrainian?",
            answers = listOf("Budapest", "Washington", "Lvov", "Kiev"),
            type = QuestionType.MULTIPLE,
            correctAnswer = listOf("Budapest", "Washington")),
    Question(text = "Where do you want to travel?",
            type = QuestionType.ENTER),
    Question(text = "What is the capital of Germany?",
            type = QuestionType.SINGLE,
            answers = listOf("Rome", "Spain", "Berlin", "Hamburg"),
            correctAnswer = listOf("Berlin")),
    Question(text = "What is the capital of Ukraine?",
            type = QuestionType.MULTIPLE,
            answers = listOf("Kiev", "Warsaw", "Madrid", "Prague"),
            correctAnswer = listOf("Kiev")),
    Question(text = "What is your name?",
            type = QuestionType.ENTER)
)


// stores question that will be set next
lateinit var currentQuestion: String
// stores index of current question
var questionIndex = 0

// makes it possible to change page when it true
var canGoToNextPage = false

// stores index until first page shows question
const val QUESTIONS_ON_PAGE = 3
// stores index until second page shows question
const val QUESTIONS_ON_SECOND_PAGE = 6

// stores amount of answers for single and multiple types
const val AMOUNT_OF_ANSWERS = 4

// holds user`s answers from EditText fields
var userName: String = ""
var dreamCity: String = ""

// stores current amount of correct answers
var correctAnswers = 0