package br.com.kduarte.produtosfirestore.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProdutoDao {
    @Query("SELECT * FROM produtos ORDER BY nome COLLATE NOCASE ASC")
    fun observarTodos(): Flow<List<ProdutoEntity>>

    @Insert
    suspend fun inserir(produto: ProdutoEntity): Long
}
