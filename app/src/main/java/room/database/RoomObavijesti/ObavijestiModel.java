package room.database.RoomObavijesti;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;

@Fts4
@Entity(tableName = "obavijesti")
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

    public ObavijestiModel(int id, String obavijest, String autor, String zaKoga, String doKada, String objavljeno) {
        this.id = id;
        this.obavijest = obavijest;
        this.autor = autor;
        this.zaKoga = zaKoga;
        this.doKada = doKada;
        this.objavljeno = objavljeno;
    }
}
