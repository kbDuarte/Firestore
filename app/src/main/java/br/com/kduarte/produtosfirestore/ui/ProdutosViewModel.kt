package br.com.kduarte.produtosfirestore.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.kduarte.produtosfirestore.data.Produto
import br.com.kduarte.produtosfirestore.data.ProdutoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

data class ProdutosUiState(
    val produtos: List<Produto> = emptyList(),
    val carregando: Boolean = true,
    val salvando: Boolean = false,
    val erro: String? = null,
    val mensagem: String? = null
)

class ProdutosViewModel : ViewModel() {
    private val repository = ProdutoRepository()

    private val _uiState = MutableStateFlow(ProdutosUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.observarProdutos()
                .catch { exception ->
                    _uiState.value = _uiState.value.copy(
                        carregando = false,
                        erro = exception.localizedMessage
                            ?: "Não foi possível carregar os produtos do Firestore."
                    )
                }
                .collect { lista ->
                    _uiState.value = _uiState.value.copy(
                        produtos = lista,
                        carregando = false,
                        erro = null
                    )
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
            try {
                repository.cadastrar(
                    Produto(nome = nomeLimpo, categoria = categoriaLimpa, preco = preco)
                )
                _uiState.value = _uiState.value.copy(
                    salvando = false,
                    mensagem = "Produto cadastrado com sucesso no Cloud Firestore."
                )
                aoConcluir()
            } catch (exception: Exception) {
                _uiState.value = _uiState.value.copy(
                    salvando = false,
                    erro = exception.localizedMessage ?: "Falha ao cadastrar produto no Firestore."
                )
            }
        }
    }

    fun limparMensagens() {
        _uiState.value = _uiState.value.copy(erro = null, mensagem = null)
    }
}
