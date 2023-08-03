package com.raikwaramit.cryptoatlas.common

/**
 * Sealed class for getting the response from server.
 *
 * @property data Data fetched from server.
 * @property message Message associated to the response.
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}