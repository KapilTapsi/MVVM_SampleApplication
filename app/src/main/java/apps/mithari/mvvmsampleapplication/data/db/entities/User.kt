package apps.mithari.mvvmsampleapplication.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 0 //as we are using one user here we don't need to create many ids

@Entity //you can change the table name using @Entity(tableName="tableName")
data class User(
    var id: Int? = null,
    var email: String? = null,
    var password: String? = null,
    var email_verified_at: String? = null,
    var created_at: String? = null,
    var updated_at: String? = null
) {
    //    as we do not want multi users we make a primary key non auto generatable
    @PrimaryKey(autoGenerate = false)
    var uid: Int? = CURRENT_USER_ID
}