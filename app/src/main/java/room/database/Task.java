package room.database;

import java.io.Serializable;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

//ova klasa predstavlja entity, a entity nam zapravo u bazi podataka predsavlja
//jednu tablicu, svaki atribut unutar ove klase predstavlja jedan stupac u tablici
//svaka entitiy klasa mora imati barem jedan atribut koji je primary key
//kako bi pristupili pojedinim atributima ove klase generiramo gettere i settere
//Po defaultu Room uzima da je ime tablice u bazi podataka ime klase, sto u nasem
//slucaju znaci da je ime nase tablice Task
//vec sto sam rekao svaki atribut u ovoj klasi predstavlja jedan stupac
//PrimaryKey --> atribut s anotacijom PrimaryKey nam govori da je on jedinstven i
//               da ne moze imati dvije jednake vrijednosti, znaci dva reda ne smiju
//               imati isti id, on mora za svaki red biti jedinstven
//autoGenerate --> ova naredba nam govori kako ce kako se dodavaju redovi u tablici
//                 da ce svaki sljedeci red imati drugaciji broj za id, odnosno id-ovi
//                 ce se uvecavati za jedan
//ColumnInfo --> po defaultu Room uzima imena atributa i tako naziva pojedine stupce u
//               tablici, ako zelimo da nam se pojedini stupac zove drugacije od onog
//               kako smo nazvali atribut onda koristimo ovu anotaciju i u zagradi
//               (name = "...")
//getteri i setteri --> ponekad cemo trebati pristupiti atributima i mijenjati njihovu
//                      vrijednost izvan klase u kojoj se oni nalaze, a to je uvijek bolje
//                      napraviti preko metoda nego direktno preko atributa jer tada korisnik
//                      moze lako promjeniti njihovu vrijednost i pokvariti cijeli program
public class Task implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "task")
    private String task;

    @ColumnInfo(name = "desc")
    private String desc;

    @ColumnInfo(name = "finish_by")
    private String finishBy;

    @ColumnInfo(name = "finished")
    private boolean finished;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFinishBy() {
        return finishBy;
    }

    public void setFinishBy(String finishBy) {
        this.finishBy = finishBy;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
