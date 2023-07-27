package com.space.moviesapp.common.extensions

import com.space.moviesapp.common.resource.Result
import kotlinx.coroutines.flow.*

fun <T : Any> Flow<T>.toResult(): Flow<Result<T>> =
    this.map { result ->
        Result.Success(result)
    }.catch<Result<T>> { e ->
        emit(Result.Error(e))
    }.onStart { emit(Result.Loading) }