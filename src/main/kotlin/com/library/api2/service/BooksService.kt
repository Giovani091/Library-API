package com.library.api2.service

import com.library.api2.clients.OpenLibraryClient
import com.library.api2.enums.Errors
import com.library.api2.exception.BookAlreadyExistsException
import com.library.api2.exception.BookNotFoundException
import com.library.api2.factory.BookModelFactory
import com.library.api2.model.AuthorModel
import com.library.api2.model.BooksModel
import com.library.api2.model.dto.openLibrary.BookDTO
import com.library.api2.repository.AuthorRepository
import com.library.api2.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BooksService(
    val bookRepository: BookRepository,
    val openLibraryClient: OpenLibraryClient,
    val bookModelFactory: BookModelFactory,
    val authorRepository: AuthorRepository
) {

    fun getAllBooks(title: String?): List<BooksModel>{
        title?.let { return bookRepository.findByTitleContainingIgnoreCase(title) }

        return bookRepository.findAll().toList()
    }

    fun getBooks(id: Int): BooksModel {
        return bookRepository.findById(id).orElseThrow{BookNotFoundException(Errors.LA001.message.format(id), Errors.LA001.code)}
    }

    fun createBook(book: BooksModel) {
        bookRepository.save(book)
    }

    fun updateBooks(book: BooksModel){

        if(!bookRepository.existsById(book.id!!)){
            throw BookNotFoundException(Errors.LA001.message.format(book.id), Errors.LA001.code)
        }

        bookRepository.save(book)
    }

    fun deleteBooks(id: Int){
        if(!bookRepository.existsById(id)){
            throw BookNotFoundException(Errors.LA001.message.format(id), Errors.LA001.code)
        }

        bookRepository.deleteById(id)
    }

    fun save(book: BookDTO): BooksModel {
        if (bookRepository.findByTitle(book.title) != null)
            throw BookAlreadyExistsException()


        val openLibBook = openLibraryClient.searchBooks(book.title)
        val generatedBook = bookModelFactory.createInstance(openLibBook)

        return bookRepository.save(generatedBook)
    }



}