package com.library.api2.service

import com.library.api2.enums.Errors
import com.library.api2.exception.NotFoundException
import com.library.api2.model.BooksModel
import com.library.api2.repository.booksRepository
import org.springframework.stereotype.Service

@Service
class BooksService(
    val booksRepository: booksRepository
) {

    fun getAllBooks(title: String?): List<BooksModel>{
        title?.let { return booksRepository.findByTitleContainingIgnoreCase(title) }

        return booksRepository.findAll().toList()
    }

    fun getBooks(id: Int): BooksModel {
        return booksRepository.findById(id).orElseThrow{NotFoundException(Errors.LA001.message.format(id), Errors.LA001.code)}
    }

    fun createBook(book: BooksModel) {
        booksRepository.save(book)
    }

    fun updateBooks(book: BooksModel){

        if(!booksRepository.existsById(book.id!!)){
            throw NotFoundException(Errors.LA001.message.format(book.id), Errors.LA001.code)
        }

        booksRepository.save(book)
    }

    fun deleteBooks(id: Int){
        if(!booksRepository.existsById(id)){
            throw NotFoundException(Errors.LA001.message.format(id), Errors.LA001.code)
        }

        booksRepository.deleteById(id)
    }
}