package com.vikiddo.controller

import com.vikiddo.dto.QuestionDto
import com.vikiddo.service.QuestionService
import org.springframework.web.bind.annotation.*



@RestController
@RequestMapping("/api/questions")
class QuestionController(private val questionService: QuestionService) {

    @GetMapping
    fun getAllQuestions() = questionService.getAllQuestions()

    @PostMapping
    fun createQuestion(@RequestBody questionDto: QuestionDto) = questionService.createQuestion(questionDto)
}