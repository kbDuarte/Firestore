package br.com.kduarte.produtosfirestore.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.Payments
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.com.kduarte.produtosfirestore.ui.ProdutosUiState

@Composable
fun CadastroScreen(
    state: ProdutosUiState,
    onCadastrar: (String, String, String) -> Unit,
    onLimparErro: () -> Unit
) {
    var nome by rememberSaveable { mutableStateOf("") }
    var categoria by rememberSaveable { mutableStateOf("") }
    var preco by rememberSaveable { mutableStateOf("") }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Novo produto", style = MaterialTheme.typography.headlineMedium)
            Text("Preencha os dados para salvar no Cloud Firestore.", color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.height(28.dp))
            OutlinedTextField(
                value = nome, onValueChange = { nome = it; onLimparErro() },
                modifier = Modifier.fillMaxWidth(), label = { Text("Nome") },
                leadingIcon = { androidx.compose.material3.Icon(Icons.Rounded.ShoppingBag, null) },
                singleLine = true
            )
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = categoria, onValueChange = { categoria = it; onLimparErro() },
                modifier = Modifier.fillMaxWidth(), label = { Text("Categoria") },
                leadingIcon = { androidx.compose.material3.Icon(Icons.Rounded.Category, null) },
                singleLine = true
            )
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = preco, onValueChange = { preco = it; onLimparErro() },
                modifier = Modifier.fillMaxWidth(), label = { Text("Preço") },
                leadingIcon = { androidx.compose.material3.Icon(Icons.Rounded.Payments, null) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true
            )
            state.erro?.let {
                Spacer(Modifier.height(12.dp))
                Text(it, color = MaterialTheme.colorScheme.error)
            }
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = { onCadastrar(nome, categoria, preco) },
                enabled = !state.salvando,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (state.salvando) CircularProgressIndicator()
                else {
                    androidx.compose.material3.Icon(Icons.Rounded.Save, contentDescription = null)
                    Text("  Salvar produto")
                }
            }
        }
    }
}
