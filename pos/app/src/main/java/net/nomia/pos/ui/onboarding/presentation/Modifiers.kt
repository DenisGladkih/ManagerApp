package net.nomia.pos.ui.onboarding.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.unit.dp
import net.nomia.pos.R

internal fun Modifier.onboardingContentWidth(): Modifier = composed {
    val isTablet = booleanResource(id = R.bool.isTablet)
    val modifier = remember {
        if (isTablet) {
            val tabletContentWidth = 560.dp
            this.width(width = tabletContentWidth)
        } else {
            this.fillMaxWidth()
        }
    }
    modifier
}
