package com.vikiddo.service

import com.vikiddo.dto.AnswerSubmissionDto
import com.vikiddo.dto.QuestionDto
import com.vikiddo.model.entity.Question
import com.vikiddo.model.entity.UserAnswer
import com.vikiddo.repository.QuestionRepository
import com.vikiddo.repository.UserAnswerRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class QuestionService(
    private val questionRepository: QuestionRepository,
    private val userAnswerRepository: UserAnswerRepository
) {

    fun getAvailableQuestions(userId: String): Flux<Question> {
        // Get all correctly answered questions by the user
        return userAnswerRepository.findByUserIdAndIsCorrect(userId, true)
            .map { it.questionId }
            .collectList()
            .flatMapMany { answeredQuestionIds ->
                // Get all questions and filter out the correctly answered ones
                questionRepository.findAll()
                    .filter { question -> question.id !in answeredQuestionIds }
            }
    }

    fun getAllQuestions() = questionRepository.findAll()

    fun createQuestion(dto: QuestionDto) = questionRepository.save(
        Question(
            text = dto.text,
            options = dto.options,
            correctAnswer = dto.correctAnswer,
            explanation = dto.explanation
        )
    ).subscribe()

    fun submitAnswer(submission: AnswerSubmissionDto): Mono<UserAnswer> {
        return questionRepository.findById(submission.questionId)
            .flatMap { question ->
                val isCorrect = isAnswerCorrect(question, submission.answer)
                userAnswerRepository.save(
                    UserAnswer(
                        userId = submission.userId,
                        questionId = submission.questionId,
                        answer = submission.answer,
                        isCorrect = isCorrect
                    )
                )
            }
    }

    private fun isAnswerCorrect(question: Question, answer: String): Boolean {
        return when {
            question.correctAnswer != null -> answer.equals(question.correctAnswer, ignoreCase = true)
            question.acceptableAnswers.isNotEmpty() -> question.acceptableAnswers.any { it.equals(answer, ignoreCase = true) }
            else -> false
        }
    }
}