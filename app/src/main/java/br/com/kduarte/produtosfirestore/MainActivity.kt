package br.com.kduarte.produtosfirestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.kduarte.produtosfirestore.ui.ProdutosApp
import br.com.kduarte.produtosfirestore.ui.theme.ProdutosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProdutosTheme { ProdutosApp() }
        }
    }
}
