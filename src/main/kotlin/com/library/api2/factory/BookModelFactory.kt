package com.library.api2.factory

import com.library.api2.model.BooksModel
import com.library.api2.model.dto.openLibrary.OpenLibraryResponse
import org.springframework.beans.factory.config.AbstractFactoryBean
import org.springframework.stereotype.Component

@Component
class BookModelFactory : AbstractFactoryBean<BooksModel>() {
    override fun getObjectType(): Class<*> {
        return BooksModel::class.java
    }

    override fun createInstance(): BooksModel {
        return BooksModel(null, "", listOf(""), "")
    }

    fun createInstance(openLibraryResponse: OpenLibraryResponse): BooksModel {
        val book = openLibraryResponse.docs.first()
        return this.createInstance().apply {
            title = book.title.toString()
            category = book.subtitle.toString()
            author = book.authorName!!
        }
    }
}