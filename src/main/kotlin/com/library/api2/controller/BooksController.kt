package com.library.api2.controller

import org.springframework.http.HttpStatus
import com.library.api2.controller.request.PostBooksRequest
import com.library.api2.controller.request.PutBooksRequest
import com.library.api2.extension.toBooksModel
import com.library.api2.model.BooksModel
import com.library.api2.service.BooksService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("books")
class BooksController(
    val booksService: BooksService
) {

    @GetMapping
    fun getAllBooks(@RequestParam title: String?): List<BooksModel>{
        return booksService.getAllBooks(title)
    }

    @GetMapping("/{id}")
    fun getBooks(@PathVariable id: Int): BooksModel {
        return booksService.getBooks(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(@RequestBody book: PostBooksRequest) {
        booksService.createBook(book.toBooksModel())
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateBooks(@PathVariable id: Int, @RequestBody book: PutBooksRequest){
        booksService.updateBooks(book.toBooksModel(id))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBooks(@PathVariable id: Int){
        booksService.deleteBooks(id)
    }
}