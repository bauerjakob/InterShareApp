import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.ActivityNavigator

@Composable
fun ToggleButton() {
    val states = listOf(
        "All",
        "Contacts",
        "Nobody",
    )
    var selectedOption by remember {
        mutableStateOf(states[1])
    }
    val onSelectionChange = { text: String ->
        selectedOption = text
    }

    Surface(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .wrapContentSize()
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            states.forEach { text->
                    Text(
                        text = text,
                        textAlign = TextAlign.Center,
                        color = (
                            if (text == selectedOption) {
                                Color.White
                            } else {
                                MaterialTheme.colorScheme.primary
                            }
                        ),
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(10.dp))
                            .clickable {
                                onSelectionChange(text)
                            }
                            .background(
                                if (text == selectedOption) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.primaryContainer
                                }
                            )
                            .padding(
                                vertical = 17.dp,
                                horizontal = 16.dp,
                            )
                            .weight(1f)
                    )
            }
        }
    }
}
