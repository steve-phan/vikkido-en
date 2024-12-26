package com.vikiddo.repository


import com.vikiddo.model.entity.Question
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface QuestionRepository : ReactiveMongoRepository<Question, String>