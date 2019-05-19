package RoomDatabae;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface userDAO {

    @Insert
    public void addUser(user user);


    @Query("select * from users where user_email= :email and user_password = :password limit 1")
    public user getUserInfo(String email, String password);


    @Query("select * from users")
    public List<user> getAllUserInfo();



}
