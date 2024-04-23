package com.library.api2.controller

import com.library.api2.enums.Errors
import com.library.api2.exception.BookNotFoundException
import com.library.api2.model.AuthorModel
import com.library.api2.model.BooksModel
import com.library.api2.repository.AuthorRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("authors")
class AuthorController(
    val authorRepository: AuthorRepository
) {
    @GetMapping("/{id}")
    fun getBook(@PathVariable id: Int): AuthorModel {
        return authorRepository.findById(id).orElseThrow{
            BookNotFoundException(Errors.LA001.message.format(id), Errors.LA001.code)
        }
    }
}