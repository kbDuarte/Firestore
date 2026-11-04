package br.com.kduarte.produtosfirestore.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProdutoRepository(
    private val produtoDao: ProdutoDao
) {
    fun observarProdutos(): Flow<List<Produto>> =
        produtoDao.observarTodos().map { produtos ->
            produtos.map { entity ->
                Produto(
                    id = entity.id.toString(),
                    nome = entity.nome,
                    categoria = entity.categoria,
                    preco = entity.preco
                )
            }
        }

    suspend fun cadastrar(produto: Produto) {
        produtoDao.inserir(
            ProdutoEntity(
                nome = produto.nome.trim(),
                categoria = produto.categoria.trim(),
                preco = produto.preco
            )
        )
    }
}
