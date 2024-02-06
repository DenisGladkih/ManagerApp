package net.nomia.pos.ui.onboarding.domain.model

sealed interface Resource<T> {
    class Success<T>(val data: T? = null) : Resource<T>
    class Error(val message: String? = null) : Resource<Nothing>
    object Loading : Resource<Nothing>
}
