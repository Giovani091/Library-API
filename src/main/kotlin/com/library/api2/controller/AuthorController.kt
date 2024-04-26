package com.library.api2.controller

import com.library.api2.enums.Errors
import com.library.api2.exception.BookNotFoundException
import com.library.api2.model.AuthorModel
import com.library.api2.model.BooksModel
import com.library.api2.repository.AuthorRepository
import com.library.api2.repository.BookRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("authors")
class AuthorController(
    val authorRepository: AuthorRepository,
    val bookRepository: BookRepository
) {

    @GetMapping("/{title}")
    fun getAuthorByTitle(@PathVariable title: String): AuthorModel{
        val book = bookRepository.findByTitle(title)
        val author1 = AuthorModel(null, book!!.author.first(),book)
        authorRepository.save(author1)
        return author1
        }
}



