package com.library.api2.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import java.awt.print.Book


@Entity(name = "author")
data class AuthorModel(

    @Id
    @GeneratedValue()
    var id: Int? = null,

    @Column
    var name: String,

    @ManyToOne
    @JoinColumn(name = "book_id")
    var book: BooksModel? = null



)