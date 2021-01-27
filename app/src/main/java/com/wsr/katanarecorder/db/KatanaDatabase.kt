package com.wsr.katanarecorder.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [KatanaData::class], version = 1, exportSchema = false)
@TypeConverters(MutableMapConverter::class)
abstract class KatanaDatabase : RoomDatabase(){

    abstract  fun katanaDataDao(): KatanaDataDao

    private class KatanaDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback(){

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let{ database ->
                scope.launch {
                    seeding(database.katanaDataDao())
                }
            }
        }

        fun seeding(katanaDataDao: KatanaDataDao){
            Completable.fromAction{
                katanaDataDao.deleteAll()

                katanaDataDao.insert(KatanaData(
                    0, "備前長船盛光", null,
                    mutableMapOf(
                        "銘" to "備前長船盛光",
                        "種別" to "太刀",
                        "産地" to "備前長船派",
                        "時代" to "室町中期",
                        "刃長" to "７９ｃｍ",
                        "反り" to "２．４ｃｍ",
                        "刃文" to "丁子",
                        "地鉄" to "柾目",
                        "帽子" to "小丸返る",
                        "茎" to "生ぶ",
                        "備考" to "こちらは備前長船刀剣博物館にて貯蔵されている一振り。室町前期の太刀でありながら生ぶであり、大変貴重なものとなっている。"
                    )))

                katanaDataDao.insert(KatanaData(0, "昨日のやつ", null,
                    mutableMapOf(
                        "銘" to "備前長船長光",
                        "種別" to "太刀",
                        "産地" to "備前長船派",
                        "時代" to "鎌倉後期",
                        "刃長" to "１２２ｃｍ",
                        "反り" to "４，２ｃｍ",
                        "刃文" to "丁子",
                        "地鉄" to "柾目",
                        "帽子" to "小丸返る",
                        "茎" to "生ぶ",
                        "備考" to "こちらは備前長船刀剣博物館にて貯蔵されている一振り。室町前期の太刀でありながら生ぶであり、大変貴重なものとなっている。"
                    )))
            }
                .subscribeOn(Schedulers.io())
                .subscribe()
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: KatanaDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): KatanaDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KatanaDatabase::class.java,
                    "katana_data_database"
                )
                    .addCallback(KatanaDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}