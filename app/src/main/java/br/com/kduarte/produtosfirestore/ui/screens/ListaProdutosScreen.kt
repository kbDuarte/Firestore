package br.com.kduarte.produtosfirestore.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Inventory2
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.kduarte.produtosfirestore.ui.ProdutosUiState
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ListaProdutosScreen(state: ProdutosUiState, onNovoProduto: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 24.dp)) {
        Text("Meus produtos", style = MaterialTheme.typography.headlineMedium)
        Text(
            "${state.produtos.size} produto(s) cadastrados",
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.size(20.dp))

        when {
            state.carregando -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            state.erro != null -> Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(state.erro, color = MaterialTheme.colorScheme.error)
                Button(onClick = onNovoProduto) { Text("Cadastrar produto") }
            }
            state.produtos.isEmpty() -> Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    androidx.compose.material3.Icon(
                        Icons.Rounded.Inventory2, contentDescription = null,
                        modifier = Modifier.size(52.dp), tint = MaterialTheme.colorScheme.secondary
                    )
                    Text("Nenhum produto por aqui", style = MaterialTheme.typography.titleMedium)
                    Text("Cadastre seu primeiro produto para começar.")
                    Button(onClick = onNovoProduto) {
                        androidx.compose.material3.Icon(Icons.Rounded.Add, null)
                        Text("  Adicionar produto")
                    }
                }
            }
            else -> LazyColumn(
                contentPadding = PaddingValues(bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.produtos, key = { it.id }) { produto ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
                    ) {
                        Row(
                            Modifier.fillMaxWidth().padding(18.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(produto.nome, style = MaterialTheme.typography.titleMedium)
                                Text(produto.categoria, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            Text(
                                NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(produto.preco),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}
