package room.database.RoomObavijesti;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ObavijestiModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "obavijesti")
    public String obavijest;

    @ColumnInfo(name = "autor")
    public String autor;

    @ColumnInfo(name = "za_koga")
    public String zaKoga;

    @ColumnInfo(name = "do_kada")
    public String doKada;

    @ColumnInfo(name = "objavljeno")
    public String objavljeno;

}
