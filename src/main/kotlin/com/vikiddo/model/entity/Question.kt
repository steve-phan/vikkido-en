package com.vikiddo.model.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "questions")
data class Question(
    @Id val id: String? = null,
    val category: String = "Grammar", // e.g., Grammar, Vocabulary, Listening
    val text: String, // The main question text
    val questionType: QuestionType = QuestionType.SINGLE_CHOICE, // Default to SINGLE_CHOICE
    val options: List<String> = listOf(), // Possible options for single-choice or similar types
    val correctAnswer: String? = null, // Correct answer for single-choice and similar formats
    val acceptableAnswers: List<String> = listOf(), // Alternative correct answers
    val grammarRule: String? = null, // Grammar rule associated with the question
    val hints: List<String> = listOf(), // Optional hints for the question
    val explanation: String? = null, // Detailed explanation for the correct answer
    val audioUrl: String? = null, // Optional URL for listening questions
    val tags: List<String> = listOf(), // Tags for filtering
    val difficultyLevel: DifficultyLevel = DifficultyLevel.BEGINNER, // Difficulty level
    val status: QuestionStatus = QuestionStatus.PENDING, // Approval status
    val author: String? = null, // Creator's user ID
    val approvedBy: String? = null, // Approver's user ID
    @CreatedDate var createdAt: Instant? = null, // Timestamp when created
    @LastModifiedDate var updatedAt: Instant? = null // Timestamp when last updated
)

enum class QuestionType {
    SINGLE_CHOICE, // Single correct answer from multiple options
    FILL_IN_THE_BLANK, // Fill-in-the-blank questions
    SENTENCE_FORMATION, // Sentence construction
    ERROR_CORRECTION, // Error identification and correction
    LISTENING, // Listening comprehension questions
    OPEN_ENDED // Open-ended responses
}

enum class DifficultyLevel {
    BEGINNER,
    INTERMEDIATE,
    ADVANCED
}

enum class QuestionStatus {
    PENDING,
    APPROVED,
    REJECTED
}