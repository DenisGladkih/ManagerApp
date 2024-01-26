package net.nomia.pos.ui.onboarding.domain

import net.nomia.pos.core.data.Response
import net.nomia.pos.core.text.Content
import net.nomia.pos.ui.onboarding.model.Resource

fun <T> Response<T>.toResource(): Resource<T> {
    return when (val response = this) {
        is Response.Loading -> Resource.Loading()
        is Response.Success -> Resource.Success(data = response.result)
        is Response.Timeout -> Resource.Error()
        is Response.Error -> {
            val content = response.message
            val message = if (content is Content.Text) content.text else null
            Resource.Error(message)
        }
    }
}
