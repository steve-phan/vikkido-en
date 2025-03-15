package com.vikiddo.controller

import com.vikiddo.dto.AnswerSubmissionDto
import com.vikiddo.dto.QuestionDto
import com.vikiddo.service.QuestionService
import org.springframework.web.bind.annotation.*



@RestController
@RequestMapping("/api/questions")
class QuestionController(private val questionService: QuestionService) {

    @GetMapping
    fun getAllQuestions() = questionService.getAllQuestions()

    @GetMapping("/available/{userId}")
    fun getAvailableQuestions(@PathVariable userId: String) = questionService.getAvailableQuestions(userId)

    @PostMapping
    fun createQuestion(@RequestBody questionDto: QuestionDto) = questionService.createQuestion(questionDto)

    @PostMapping("/submit")
    fun submitAnswer(@RequestBody submission: AnswerSubmissionDto) = questionService.submitAnswer(submission)
}