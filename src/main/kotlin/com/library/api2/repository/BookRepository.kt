package com.library.api2.repository

import com.library.api2.model.BooksModel
import org.springframework.data.jpa.repository.JpaRepository



interface BookRepository : JpaRepository<BooksModel, Int> {

    abstract fun findByTitleContainingIgnoreCase(title: String): List<BooksModel>
    abstract fun findByTitle(title: String): BooksModel?
}

