package br.com.kduarte.produtosfirestore.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class ProdutoRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    private val produtos = firestore.collection("Produtos")

    fun observarProdutos(): Flow<Result<List<Produto>>> = callbackFlow {
        val registration = produtos
            .orderBy("nome", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Result.failure(error))
                    return@addSnapshotListener
                }
                val lista = snapshot?.documents.orEmpty().map { document ->
                    Produto(
                        id = document.id,
                        nome = document.getString("Nome").orEmpty(),
                        categoria = document.getString("Categoria").orEmpty(),
                        preco = document.getDouble("Preço")
                            ?: document.getLong("Preço")?.toDouble()
                            ?: 0.0
                    )
                }
                trySend(Result.success(lista))
            }
        awaitClose { registration.remove() }
    }

    suspend fun cadastrar(produto: Produto) {
        val dados = mapOf(
            "Nome" to produto.nome.trim(),
            "Categoria" to produto.categoria.trim(),
            "Preço" to produto.preco
        )
        produtos.add(dados).await()
    }
}
