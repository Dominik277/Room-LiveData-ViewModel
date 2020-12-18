package room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//ovo je abstraktna klasa koja je zaduzena za kreiranje baze podataka nase aplikacij
//postoji nekoliko zahtjeva koje ova klasa mora ispunjavati
//1.ova klasa mora nasljeđivati RoomDatabse klasu
//2.mora imati abstraktnu metodu sa 0 parametara koja vraca ime klase oznacene s anotacijom @Dao
//3.iznad svoga imena mora imati anotaciju @Database unutar cijih zagrada moraju pisati svi entiteti
//  odnosno tablice koje se nalaze unutar baze podataka i koja je verzija baze podataka
@Database(entities = {Task.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();

}
