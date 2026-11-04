package br.com.kduarte.produtosfirestore.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.kduarte.produtosfirestore.data.Produto
import br.com.kduarte.produtosfirestore.data.ProdutoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ProdutosUiState(
    val produtos: List<Produto> = emptyList(),
    val carregando: Boolean = true,
    val salvando: Boolean = false,
    val erro: String? = null,
    val mensagem: String? = null
)

class ProdutosViewModel(
    private val repository: ProdutoRepository = ProdutoRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProdutosUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.observarProdutos().collect { resultado ->
                resultado.onSuccess { lista ->
                    _uiState.value = _uiState.value.copy(
                        produtos = lista, carregando = false, erro = null
                    )
                }.onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        carregando = false,
                        erro = exception.localizedMessage ?: "Não foi possível carregar os produtos."
                    )
                }
            }
        }
    }

    fun cadastrar(nome: String, categoria: String, precoTexto: String, aoConcluir: () -> Unit) {
        val nomeLimpo = nome.trim()
        val categoriaLimpa = categoria.trim()
        val preco = precoTexto.trim().replace(",", ".").toDoubleOrNull()

        if (nomeLimpo.isBlank() || categoriaLimpa.isBlank() || preco == null || preco < 0) {
            _uiState.value = _uiState.value.copy(
                erro = "Preencha todos os campos e informe um preço válido."
            )
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(salvando = true, erro = null, mensagem = null)
            runCatching {
                repository.cadastrar(Produto(nome = nomeLimpo, categoria = categoriaLimpa, preco = preco))
            }.onSuccess {
                _uiState.value = _uiState.value.copy(salvando = false, mensagem = "Produto cadastrado com sucesso.")
                aoConcluir()
            }.onFailure { exception ->
                _uiState.value = _uiState.value.copy(
                    salvando = false,
                    erro = exception.localizedMessage ?: "Falha ao cadastrar produto."
                )
            }
        }
    }

    fun limparMensagens() {
        _uiState.value = _uiState.value.copy(erro = null, mensagem = null)
    }
}
