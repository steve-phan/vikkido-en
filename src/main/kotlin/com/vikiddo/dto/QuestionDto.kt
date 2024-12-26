package com.vikiddo.dto

data class QuestionDto(
    val text: String,
    val options: List<String>,
    val correctAnswer: String,
    val explanation: String
)