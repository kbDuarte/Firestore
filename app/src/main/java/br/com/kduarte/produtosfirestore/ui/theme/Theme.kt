package br.com.kduarte.produtosfirestore.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF245B9E),
    secondary = Color(0xFF52677F),
    background = Color(0xFFF7F9FC),
    surface = Color(0xFFFFFFFF)
)
private val DarkColors = darkColorScheme(
    primary = Color(0xFF9CC7FF),
    secondary = Color(0xFFB7C9E0)
)

@Composable
fun ProdutosTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = MaterialTheme.typography,
        content = content
    )
}
