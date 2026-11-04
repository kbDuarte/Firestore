package br.com.kduarte.produtosfirestore.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Inventory2
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.kduarte.produtosfirestore.ui.screens.CadastroScreen
import br.com.kduarte.produtosfirestore.ui.screens.ListaProdutosScreen

private object Rotas {
    const val LISTA = "lista"
    const val CADASTRO = "cadastro"
}

@Composable
fun ProdutosApp() {
    val navController = rememberNavController()
    val viewModel: ProdutosViewModel = viewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val backStack by navController.currentBackStackEntryAsState()
    val rotaAtual = backStack?.destination?.route ?: Rotas.LISTA

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = rotaAtual == Rotas.LISTA,
                    onClick = { navController.navigate(Rotas.LISTA) { launchSingleTop = true } },
                    icon = { Icon(Icons.Rounded.Inventory2, contentDescription = null) },
                    label = { Text("Produtos") }
                )
                NavigationBarItem(
                    selected = rotaAtual == Rotas.CADASTRO,
                    onClick = { viewModel.limparMensagens(); navController.navigate(Rotas.CADASTRO) { launchSingleTop = true } },
                    icon = { Icon(Icons.Rounded.Add, contentDescription = null) },
                    label = { Text("Cadastrar") }
                )
            }
        }
    ) { padding ->
        AnimatedContent(
            targetState = rotaAtual,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "transicao_telas",
            modifier = Modifier.padding(padding)
        ) { rota ->
            when (rota) {
                Rotas.CADASTRO -> CadastroScreen(
                    state = state,
                    onCadastrar = { nome, categoria, preco ->
                        viewModel.cadastrar(nome, categoria, preco) {
                            navController.navigate(Rotas.LISTA) { popUpTo(Rotas.LISTA) { inclusive = true } }
                        }
                    },
                    onLimparErro = viewModel::limparMensagens
                )
                else -> ListaProdutosScreen(
                    state = state,
                    onNovoProduto = {
                        viewModel.limparMensagens()
                        navController.navigate(Rotas.CADASTRO)
                    }
                )
            }
        }
    }
}
