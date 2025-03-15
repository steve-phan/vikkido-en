package com.vikiddo.repository

import com.vikiddo.model.entity.UserAnswer
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface UserAnswerRepository : ReactiveMongoRepository<UserAnswer, String> {
    fun findByUserIdAndQuestionId(userId: String, questionId: String): Flux<UserAnswer>
    fun findByUserIdAndIsCorrect(userId: String, isCorrect: Boolean): Flux<UserAnswer>
}
