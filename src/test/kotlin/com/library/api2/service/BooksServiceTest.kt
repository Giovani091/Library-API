package com.library.api2.service


import com.library.api2.enums.Errors
import com.library.api2.exception.NotFoundException
import com.library.api2.model.BooksModel
import com.library.api2.repository.booksRepository
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import org.springframework.test.context.ActiveProfiles
import java.util.*

@ExtendWith(MockitoExtension::class)
class BooksServiceTest {

    @Mock
    lateinit var booksRepository: booksRepository

    @InjectMocks
    lateinit var booksService: BooksService

    @Mock
    lateinit var book: BooksModel

    val bookId = 1

    @Test
    fun `getAllBooks, should return all customers`(){
        whenever(booksRepository.findAll().toList()).thenReturn(listOf(book, book))
        val result = booksService.getAllBooks(null)
        assertNotNull(result)
    }

    @Test
    fun `getBooks, should return book by id`(){
        whenever(booksRepository.findById(bookId)).thenReturn(Optional.of(book))
        val result = booksService.getBooks(bookId)
        assertDoesNotThrow{result}
    }

    @Test
    fun `getBooks, should throw when id not found`(){
        whenever(booksRepository.findById(bookId)).thenReturn(Optional.empty())
        assertThrows<NotFoundException> { booksService.getBooks(bookId) }
    }

    @Test
    fun `createBook, should not throw`(){
        whenever(booksRepository.save(book)).thenReturn(book)
        assertDoesNotThrow{booksService.createBook(book)}
    }

    @Test
    fun `updateBooks, should update a book`(){
        whenever(book.id).thenReturn(bookId)
        whenever(booksRepository.existsById(book.id!!)).thenReturn(true)
        assertDoesNotThrow{booksService.updateBooks(book)}
    }

    @Test
    fun `updateBooks, should throw`(){
        whenever(book.id).thenReturn(bookId)
        whenever(booksRepository.existsById(book.id!!)).thenReturn(false)
        assertThrows<NotFoundException> { booksService.updateBooks(book) }
    }

    @Test
    fun `deleteBooks, should delete a book`(){
        whenever(book.id).thenReturn(bookId)
        whenever(booksRepository.existsById(book.id!!)).thenReturn(true)
        assertDoesNotThrow{booksService.deleteBooks(book.id!!)}
    }

    @Test
    fun `deleteBooks, should throw`(){
        whenever(book.id).thenReturn(bookId)
        whenever(booksRepository.existsById(book.id!!)).thenReturn(false)
        assertThrows<NotFoundException> { booksService.deleteBooks(book.id!!) }
    }
}