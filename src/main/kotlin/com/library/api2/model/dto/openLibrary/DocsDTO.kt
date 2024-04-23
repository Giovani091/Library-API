package com.library.api2.model.dto.openLibrary

import com.fasterxml.jackson.annotation.JsonProperty

data class DocsDTO(
    var title: Any? = null,
    var subtitle: Any? = null,
    @JsonProperty("author_name")
    var authorName: List<String>? = null
)
