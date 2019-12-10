package com.pomoranca.roomdemokotlin

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao //used to access Dao, and we don't have to provide body because room takes care of the code

    companion object {
        private var instance: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase? {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "note_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance

        }
        private val roomCallback = object : RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance).execute()
            }
        }



    }
    class PopulateDbAsyncTask(db : NoteDatabase?) : AsyncTask<Unit, Unit, Unit>() {
 private val noteDao : NoteDao? = db?.noteDao()
        override fun doInBackground(vararg params: Unit?) {
            noteDao?.insert(Note("Title 1", "Description 1", 1))
            noteDao?.insert(Note("Title 2", "Description 2", 2))
            noteDao?.insert(Note("Title 3", "Description 3", 3))
        }
    }


}