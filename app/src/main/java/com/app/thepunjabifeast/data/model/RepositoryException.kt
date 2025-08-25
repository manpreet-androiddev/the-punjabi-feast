package com.app.thepunjabifeast.data.model

data class RepositoryException(
    val code: Int,
    val errorBody: String?,
    val msg: String,
) : RuntimeException(msg)