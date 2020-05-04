package apps.mithari.mvvmsampleapplication.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import apps.mithari.mvvmsampleapplication.data.db.entities.CURRENT_USER_ID
import apps.mithari.mvvmsampleapplication.data.db.entities.User

@Dao
interface UserDao {
//    Dao is data access object which handles the read write query delete operations for us

    //    Now we are creating function update and insert
    @Insert(onConflict = OnConflictStrategy.REPLACE) // this will replace the row id when conflic is found
    suspend fun upsert(user: User): Long // we are supposed to get inserted row id back

    @Query("SELECT * FROM user WHERE uid = $CURRENT_USER_ID")
    fun getuser(): LiveData<User>
}