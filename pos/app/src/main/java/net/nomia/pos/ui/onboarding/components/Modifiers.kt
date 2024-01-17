package net.nomia.pos.ui.onboarding.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

internal fun Modifier.onboardingContentWidth(isTablet: Boolean): Modifier {
    val tabletContentWidth = 560.dp
    return if (isTablet) {
        this.width(width = tabletContentWidth)
    } else {
        this.fillMaxWidth()
    }
}
