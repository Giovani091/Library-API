package com.library.api2.repository

import com.library.api2.model.AuthorModel
import com.library.api2.model.BooksModel
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepository : JpaRepository<AuthorModel, Int> {
}