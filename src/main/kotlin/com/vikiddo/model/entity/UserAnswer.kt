package com.vikiddo.model.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "user_answers")
data class UserAnswer(
    @Id val id: String? = null,
    val userId: String,
    val questionId: String,
    val answer: String,
    val isCorrect: Boolean,
    @CreatedDate var answeredAt: Instant? = null
)
