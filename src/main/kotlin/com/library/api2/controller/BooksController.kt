package com.library.api2.controller

import org.springframework.http.HttpStatus
import com.library.api2.controller.request.PostBooksRequest
import com.library.api2.controller.request.PutBooksRequest
import com.library.api2.extension.toBooksModel
import com.library.api2.model.BooksModel
import com.library.api2.service.BooksService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

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
    fun getBook(@PathVariable id: Int): BooksModel {
        return booksService.getBooks(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(@RequestBody @Valid book: PostBooksRequest) {
        booksService.createBook(book.toBooksModel())
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateBooks(@PathVariable id: Int, @RequestBody @Valid book: PutBooksRequest){
        booksService.updateBooks(book.toBooksModel(id))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBooks(@PathVariable id: Int){
        booksService.deleteBooks(id)
    }

    @GetMapping("/title/{titleExternal}")
    fun getExternalBook(@PathVariable titleExternal: String): ResponseEntity<String> {
        val restTemplate = RestTemplate()
        val responseTitle = restTemplate.getForEntity<String>("https://openlibrary.org/search.json?q=${titleExternal.replace(" ", "+", true)}")
        return responseTitle
    }
}