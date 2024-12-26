package com.vikiddo.service


import com.vikiddo.dto.QuestionDto
import com.vikiddo.model.entity.Question
import com.vikiddo.repository.QuestionRepository
import org.springframework.stereotype.Service

@Service
class QuestionService(private val questionRepository: QuestionRepository) {

    fun getAllQuestions() = questionRepository.findAll()

    fun createQuestion(dto: QuestionDto) = questionRepository.save(
        Question(
            text = dto.text,
            options = dto.options,
            correctAnswer = dto.correctAnswer,
            explanation = dto.explanation
        )
    ).subscribe()
}