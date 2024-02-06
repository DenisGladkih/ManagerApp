package net.nomia.pos.ui.onboarding.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import net.nomia.common.ui.theme.spacers

@Composable
internal fun OnboardingSegmentsBar(
    segmentsCount: Int,
    coloredSegments: Int,
) = Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacers.extraSmall)
) {
    repeat(times = segmentsCount) { segment ->
        val animatedColor by animateColorAsState(
            if (coloredSegments > segment) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            },
            label = "color"
        )

        Spacer(
            modifier = Modifier
                .height(height = MaterialTheme.spacers.extraSmall)
                .weight(weight = 1f)
                .background(color = animatedColor),
        )
    }
}
