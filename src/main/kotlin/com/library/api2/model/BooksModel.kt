package com.library.api2.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity(name = "book")
data class BooksModel (

    @Id
    @GeneratedValue()
    var id: Int? = null,

    @Column
    var title: String,

    @Column
    var author: String,

    @Column
    var category: String
)
