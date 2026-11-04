package br.com.kduarte.produtosfirestore.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class ProdutoRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    private val produtosCollection = firestore.collection("Produtos")

    fun observarProdutos(): Flow<List<Produto>> = callbackFlow {
        val registration = produtosCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }

            val produtos = snapshot?.documents.orEmpty().map { document ->
                Produto(
                    id = document.id,
                    nome = document.getString("Nome").orEmpty(),
                    categoria = document.getString("Categoria").orEmpty(),
                    preco = document.getDouble("Preço")
                        ?: document.getLong("Preço")?.toDouble()
                        ?: 0.0
                )
            }
            trySend(produtos)
        }

        awaitClose { registration.remove() }
    }

    suspend fun cadastrar(produto: Produto) {
        // document() cria um ID único automaticamente no Cloud Firestore.
        val novoDocumento = produtosCollection.document()
        val dados = hashMapOf(
            "Nome" to produto.nome.trim(),
            "Categoria" to produto.categoria.trim(),
            "Preço" to produto.preco
        )
        novoDocumento.set(dados).await()
    }
}
