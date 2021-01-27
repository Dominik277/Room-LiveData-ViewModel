package room.database.RoomObavijesti;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface DaoObavijesti {

    @Query("SELECT * FROM obavijesti")
    List<ObavijestiModel> getAll();

    @Insert
    void insertAll();

    @Delete
    void deleteAll();

}
