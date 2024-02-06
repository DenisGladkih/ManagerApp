package net.nomia.pos.ui.onboarding.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.text.isDigitsOnly
import net.nomia.common.ui.composable.NomiaOutlinedTextField

@Composable
internal fun OnboardingDigitTextField(
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    showKeyboard: Boolean = false,
    imeAction: ImeAction = ImeAction.Next
) {
    var text by remember(value) {
        mutableStateOf(
            value = TextFieldValue(
                text = value,
                selection = TextRange(value.length),
            )
        )
    }

    NomiaOutlinedTextField(
        value = text,
        onValueChange = { newValue ->
            if (newValue.text.isDigitsOnly()) {
                text = newValue
                onValueChange(newValue.text)
            }
        },
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = imeAction,
        ),
        showKeyboard = showKeyboard,
        modifier = Modifier.fillMaxWidth(),
    )
}
