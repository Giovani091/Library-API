package com.library.api2.repository

import com.library.api2.model.BooksModel
import org.springframework.data.repository.CrudRepository


interface booksRepository : CrudRepository<BooksModel, Int>{

    abstract fun findByTitleContainingIgnoreCase(title: String): List<BooksModel>
}