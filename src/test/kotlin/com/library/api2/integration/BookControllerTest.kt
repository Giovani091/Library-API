package com.library.api2.integration



import com.fasterxml.jackson.databind.ObjectMapper
import com.library.api2.controller.request.PostBooksRequest
import com.library.api2.model.BooksModel
import com.library.api2.repository.BookRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.junit.jupiter.api.Assertions.*


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
class BookControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `should return all customers`() {

        val book1 = bookRepository.save(BooksModel(null, "ttest1", listOf("test1"), "test1"))
        val book2 = bookRepository.save(BooksModel(null, "test2", listOf("test2"), "test2"))


        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].id").value(book1.id))
            .andExpect(jsonPath("$[0].title").value(book1.title))
            .andExpect(jsonPath("$[0].author.first()").value(book1.author.first()))
            .andExpect(jsonPath("$[0].category").value(book1.category))
            .andExpect(jsonPath("$[1].id").value(book2.id))
            .andExpect(jsonPath("$[1].title").value(book2.title))
            .andExpect(jsonPath("$[1].author.first()").value(book2.author.first()))
            .andExpect(jsonPath("$[1].category").value(book2.category))

    }


    @Test
    fun `should filter all books by title when get all`() {

        val book1 = bookRepository.save(BooksModel(null, "ttest1", listOf("test1"), "test1"))
        bookRepository.save(BooksModel(null, "test2", listOf("test2"), "test2"))


        mockMvc.perform(MockMvcRequestBuilders.get("/books?title=tt"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(jsonPath("$.length()").value(1))
            .andExpect(jsonPath("$[0].id").value(book1.id))
            .andExpect(jsonPath("$[0].title").value(book1.title))
            .andExpect(jsonPath("$[0].author.first()").value(book1.author.first()))
            .andExpect(jsonPath("$[0].category").value(book1.category))


    }

    @Test
    fun `should create books`() {
        val request = PostBooksRequest("test1","test1","test1")
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(MockMvcResultMatchers.status().isCreated)

    }

    @Test
    fun `should get book by id`() {

        val book1 = bookRepository.save(BooksModel(null, "test1", listOf("test1"), "test1"))


        mockMvc.perform(MockMvcRequestBuilders.get("/books/${book1.id}"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(jsonPath("$.id").value(book1.id))
            .andExpect(jsonPath("$.title").value(book1.title))
            .andExpect(jsonPath("$.author.first()").value(book1.author.first()))
            .andExpect(jsonPath("$.category").value(book1.category))


    }

    @Test
    fun `should update book`(){
        val book1 = bookRepository.save(BooksModel(null, "test1", listOf("test1"), "test1"))
        val request = PostBooksRequest("test2","test2","test2")
        mockMvc.perform(MockMvcRequestBuilders.put("/books/${book1.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(MockMvcResultMatchers.status().isNoContent)

        val books = bookRepository.findAll().toList()
        assertEquals(1, books.size)
        assertEquals(request.title, books[0].title)
        assertEquals(request.category, books[0].category)

    }

    @Test
    fun `should delete book`(){
        val book1 = bookRepository.save(BooksModel(null, "test1", listOf("test1"), "test1"))
        mockMvc.perform(delete("/books/${book1.id}"))
            .andExpect(status().isNoContent)


    }

    @Test
    fun `should return not found when delete book not exists`() {
        mockMvc.perform(delete("/books/1"))
            .andExpect(status().isNotFound)
            .andExpect(jsonPath("$.httpCode").value(404))
    }
}
