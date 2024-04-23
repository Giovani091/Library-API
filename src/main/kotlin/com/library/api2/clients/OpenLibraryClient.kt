package com.library.api2.clients

import com.library.api2.model.dto.openLibrary.OpenLibraryResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "openLibrary", url = "https://openlibrary.org/")
interface OpenLibraryClient {
    @GetMapping("/search.json?title={title}")
    fun searchBooks(@PathVariable title: String): OpenLibraryResponse

}