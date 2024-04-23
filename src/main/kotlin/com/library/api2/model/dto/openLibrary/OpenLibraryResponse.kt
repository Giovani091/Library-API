package com.library.api2.model.dto.openLibrary

data class OpenLibraryResponse(
    var numFound: Int? = null,
    var start: Int? = null,
    var numFoundExact: Boolean,
    var docs: List<DocsDTO>
)
