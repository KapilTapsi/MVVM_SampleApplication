package apps.mithari.mvvmsampleapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import apps.mithari.mvvmsampleapplication.data.db.entities.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() { //whenever we make database we make class
//    an abstract class

    //    we have to create abstract functions for all our daos/here we have single dao
    abstract fun getUserDao(): UserDao

    companion object {
//        we need companion object to create app database

        //        now we get instance of our database
        @Volatile // @Volatile means it is visible to all the threads
        private var instance: AppDatabase? = null
        private val LOCK = Any() // to make sure we have only one instance of database

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
//            the above function takes instance if not null. if null then it create synchronised
//            using lock and then checks if instance not null. if null then call function buildDatabase
//            with the context and will assign return value of database to instance using "it"
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, // we pass abstract class of database
            "MyDatabase"
        ).build()

    }
}