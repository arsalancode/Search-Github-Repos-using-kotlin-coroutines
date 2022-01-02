package com.github.repos.core.utils.flow

/**
 * A data structure to serve a stream event approach.
 *
 * It could represent four states:
 *
 * - [Data]: there is some data available to be consumed
 * - [Error]: some error has occurred on the processing and it is available to be consumed
 * - [Loading]: some processing is running and the consumer should wait for another event
 * - [Idle]: nothing is being done
 */
sealed class Validator<out T> {

    data class Data<T>(val data: T) : Validator<T>()

    object Loading : Validator<Nothing>()
    object Idle : Validator<Nothing>()

    fun isData() = this is Data
    fun isLoading() = this is Loading
    fun isIdle() = this is Idle

    companion object {
        fun <T> data(data: T): Validator<T> =
            Data(data)

        fun <T> loading(): Validator<T> =
            Loading

        fun <T> idle(): Validator<T> =
            Idle
    }
}