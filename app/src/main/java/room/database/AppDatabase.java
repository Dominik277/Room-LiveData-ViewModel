package room.database;

import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import room.database.RoomObavijesti.DaoObavijesti;
import room.database.RoomObavijesti.ObavijestiModel;

@Database(entities = {ObavijestiModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DaoObavijesti daoObavijesti();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            "app_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                DaoObavijesti dao = INSTANCE.daoObavijesti();
                dao.deleteAll();

                ObavijestiModel model = new ObavijestiModel(1,"Pokositi travu","Dražen","Dominika","19:45","19:00");
                dao.insert(model);
                ObavijestiModel model1 = new ObavijestiModel(2,"Oprati auto","Jasna","Leonardu","14:45","13:15");
                dao.insert(model1);
            });
        }
    };

}
