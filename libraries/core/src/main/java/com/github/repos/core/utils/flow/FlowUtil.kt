package com.github.repos.core.utils.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

fun <T> asFlow(transform: suspend () -> T): Flow<T> = flow {
    emit(transform())
}

fun <T> loadingEventFlow(transform: suspend () -> Flow<T>): Flow<Event<T>> =
    flow {
        try {
            emit(Event.Loading)
            transform().collect { emit(Event.Data(it)) }
        } catch (error: Throwable) {
            emit(Event.error<T>(error))
        }
    }
