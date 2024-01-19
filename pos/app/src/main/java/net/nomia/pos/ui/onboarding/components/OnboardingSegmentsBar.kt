package net.nomia.pos.ui.onboarding.components

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import net.nomia.common.ui.theme.spacers
import net.nomia.pos.ui.onboarding.model.OnboardingStep

@Composable
internal fun OnboardingSegmentsBar(
    onboardingStep: OnboardingStep,
) = Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(space = MaterialTheme.spacers.extraSmall)
) {
    val onboardingStepList = remember { OnboardingStep.values() }
    onboardingStepList.forEach { step ->
        val animatedColor by animateColorAsState(
            if (step.ordinal >= onboardingStep.ordinal + 1) {
                MaterialTheme.colorScheme.surfaceVariant
            } else {
                MaterialTheme.colorScheme.primary
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
