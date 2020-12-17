package room.database;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainMainActivity extends AppCompatActivity {


    //ovdje smo deklarirali imena varijabli te njihove tipove, ove varijable su za sada
    //samo deklarirane, jos nismo u njih nista pohranili pa su prazne
    private FloatingActionButton buttonAddTask;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ovdje smo u recyclerView varijablu pohranili view koji se nalazi u XML-u pomocu
        //metode findViewById().Ova metoda odlazi u XML i trazi view prema imenu koje
        //smo naveli kao parametar te metode.Odlazi u XML i gleda pod naredbom gdje se odreduje
        //id i ako nađe tamo taj view onda ga sprema u pripadajucu varijablu
        recyclerView = findViewById(R.id.recyclerview_tasks);

        //
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //ovdje smo u buttonAddTask varijablu pohranili view koji se nalazi u XML-u pomocu
        //metode findViewById().Ova metoda odlazi u XML i trazi view prema imenu koje
        //smo naveli kao parametar te metode.Odlazi u XML i gleda pod naredbom gdje se odreduje
        //id i ako nađe tamo taj view onda ga sprema u pripadajucu varijablu
        buttonAddTask = findViewById(R.id.floating_button_add);

        //setOnClickListener() metoda nam govori da ce gumb imati nekakvu funkcionalnost,jos nismo
        //tocno naveli kakvu funkcionalnost, ali smo naveli da cu ju imati.U tijelu ove metode cemo
        //navesti tocno koje funkcionalnosti ce imati
        buttonAddTask.setOnClickListener(new View.OnClickListener() {

            //onClick() je metoda ciji se narebe izvrsavaju kada dode do klika gumba.Ono sto zelimo da se
            //izvrsi kada se klikne na gumb pišemo u tijelu ove metode.
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMainActivity.this,AddTastActivity.class);
                startActivity(intent);
            }
        });

        //ovo je jednostavno poziv getTasks metode koja je deklarirana ispod i koja ima svoju
        //funkcionalnost
        getTasks();
    }

    //ovo je nasa custom metoda unutar koje smo deklarirali klasu GetTasks koja nasljeđuje AsyncTask klasu
    //koja nam omogucava radnju nekih tezih zadataka u background thread-u kako bi olaksali posao main UI
    //thread-u i samim time omogucili brzu aplikaciju
    private void getTasks(){

        //klasa GetTasks nasljeđuje AsyncTask klasu koja nam omogucuje rad u background thread-u i samim
        //time smanjujemo opterecenje na main UI thread i aplikacija postaje brza
        class GetTasks extends AsyncTask<Void,Void, List<Task>>{

            //AsyncTask je abstraktna klasa sto znaci da ima neke metode koje se moraju implementirati,
            //ta klasa ima 4 metode,ali samo jedna je obavezna za implementaciju a to je doInBackground()
            //ostale 3 metode su onPreExecute(),onPostExecute() i onProgresExecute()
            //doInBackground() --> ova metoda je zasluzna za rad u background thread-u, odnosno sto
            //                     definiramo u tijelu ove metode to ce se izvrsavati u background thread-u
            @Override
            protected List<Task> doInBackground(Void... voids) {

                //na desnoj strani smo stvorili varijablu tipa taskList tipa List<Task> u koju cemo pohranjivati
                //sve ono sto se odradi na desnoj strani
                //DatabaseClient -->
                //getInstance() -->
                //getApplicationContext() -->
                //getAppDatabase() -->
                //taskDao() -->
                //getAll() -->
                List<Task> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .getAll();
                return taskList;
            }

            //ova metoda je jedan od onih koje se nalaze u abstraktnoj klasi AsyncTask koju nije obavzeno
            //implemenirati ali je dobra praksa.Ovoj metodi se salju rezultati koji su se dobili u metodi
            //doInBackground() te se preko ove metode azurira UI thread jer ova metoda salje podatke UI
            //thread-u
            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);
                TasksAdapter adapter = new TasksAdapter(MainMainActivity.this,tasks);
                recyclerView.setAdapter(adapter);
            }
        }

        //sve prije ovog koraka je bilo definiranje zadatka,mozemo zamisliti kao zadatak kojem je određeno
        //sve sto treba raditi, kada treba raditi, koliko treba raditi i gdje treba raditi jedino mu jos
        //fali nesto da ga upogoni, da ga pusti u pogon, upravo to radi execute() metoda
        //prvo moramo stvoriti objekt od klase unutar koje smo definirali background zadatke, taj objekt se
        //zove gt i preko njega mozemo pozvati metodu execute koja je zasluzna da pusti u pogon sve ove metode
        //koje smo definirali gore te da one napokon mogu poceti izvrsavati svoje zadatke
        GetTasks gt = new GetTasks();
        gt.execute();
    }

}
