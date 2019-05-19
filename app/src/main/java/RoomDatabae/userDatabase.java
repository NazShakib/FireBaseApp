package RoomDatabae;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {user.class}, version = 1)
public abstract class userDatabase extends RoomDatabase {

    public abstract userDAO userDao();

}
