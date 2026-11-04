package br.com.kduarte.produtosfirestore.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ProdutoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ProdutoDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao

    companion object {
        @Volatile
        private var INSTANCE: ProdutoDatabase? = null

        fun getInstance(context: Context): ProdutoDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ProdutoDatabase::class.java,
                    "produtos.db"
                ).build().also { INSTANCE = it }
            }
    }
}
