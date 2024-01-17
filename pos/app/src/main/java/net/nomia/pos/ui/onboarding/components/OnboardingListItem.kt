package net.nomia.pos.ui.onboarding.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import net.nomia.common.ui.composable.list_item.NomiaListItem
import net.nomia.common.ui.theme.spacers

@Composable
internal fun OnboardingListItem(
    iconPainter: Painter,
    headlineText: String,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
) = Column {
    NomiaListItem(
        headlineText = {
            Text(
                text = headlineText,
            )
        },
        leadingContent = {
            Icon(
                modifier = Modifier.size(size = 24.dp),
                painter = iconPainter,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                contentDescription = null,
            )
        },
    ) {
        Checkbox(
            modifier = Modifier.requiredSize(MaterialTheme.spacers.large),
            checked = checked,
            onCheckedChange = onCheckedChange,
        )
    }

    Divider()
}
