package room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import room.database.RoomObavijesti.DaoObavijesti;
import room.database.RoomObavijesti.ObavijestiModel;

@Database(entities = {ObavijestiModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DaoObavijesti daoObavijesti();

}
