package net.nomia.pos.ui.onboarding.model

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String? = null, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}
