package net.nomia.pos.ui.onboarding.model

internal data class FirstStepValue(
    val clientName: String,
    val phoneOrEmail: String,
) {
    companion object {
        val INITIAL = FirstStepValue(
            clientName = "",
            phoneOrEmail = "",
        )
    }

    fun isValid(): Boolean = clientName.isNotBlank() && phoneOrEmail.isNotBlank()
}
