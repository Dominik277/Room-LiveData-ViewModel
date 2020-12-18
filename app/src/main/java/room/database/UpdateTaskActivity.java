package room.database;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Delete;

public class UpdateTaskActivity extends AppCompatActivity {

    //ovdje smo samo definirali varijable i njihove tipove koje su sada prazne, a kasnije
    //cemo View-ove iz XML-a referencirati preko njih
    private TextView editTextTask;
    private TextView editTextDesc;
    private TextView editTextFinishBy;
    private CheckBox checkBoxFinished;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        //nakon sto smo prethodno gore deklarirali varijable sada u ovim koracima spremamo
        //u njih vrijednosti.A te vrijednosti spremamo pomocu metode findViewById(), ova
        //metoda radi na nacin da kao njen argument upisemo id View-a iz XML-a te onda taj
        //view pohranjuje unutar one varijable u koju smo naveli da spremi.U ovom koraku se
        //komponente iz XML-a pretvaraju u objekte tipa View java kodu.
        editTextTask = findViewById(R.id.editTextTask);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextFinishBy = findViewById(R.id.editTextFinishBy);
        checkBoxFinished = findViewById(R.id.checkBoxFinished);

        final  Task task = (Task) getIntent().getSerializableExtra("task");
        loadTask(task);

        //u sljedecem koraku smo također upotrijebili findViewById() metodu kako bi nasli gumb
        //view unutar XML-a, samo sto ga ovaj puta nismo pohranili unutar nikakve varijeble nego
        //smo mu odmah dali neku funkcionalnost, u ovom koraku jos nismo specificirali sto ce se
        //desiti klikom na gumb, nego smo samo rekli da ce se nesto dogoditi, ali ne i sto
        findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {

            //e u ovom koraku dolazimo do dijela kada određujemo sta ce se desiti prilikom klika na gumb
            //u nasem slucaju kada pritisnemo na gumb ispisat ce se Toast poruka na dnu ekrana koja ce glasiti
            //"Clicked", te ce se pozvati metoda updateTask(task) koju smo definirali nize u kodu i kojoj je
            //zadatak azurirati podatke u tablici onim podacima koje je korisnik unio
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_LONG).show();
                updateTask(task);
            }
        });

        //ovaj dio koda nam govori da smo uz pomoc findViewById() metode pronasli gumb button_delete iz XML-a
        //i bez da smo ga pohranili unutar neke varijeble postavili mu da ce imati neku funckcionalnost,jos nismo
        //odredili kakvu funkcionalnost, ali smo rekli da ce ju imati
        findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {

            //u ovom dijelu smo definirali da ako korisnik pritisne gumb delete da obrise podsjetnik onda mu aplikacija
            //izbacuje AlertDialog prozorcic sa naslovom "Are you sure"  i sa dva gumba koji se zovu positive button
            //i negative button
            @Override
            public void onClick(View v) {

                //kao sto sam vec naveo gore prilikom klika na gumb delete izbacuje nam se AlertDialog sa naslovom i
                //dva gumba
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateTaskActivity.this);
                builder.setTitle("Are you sure");

                //ova dva gumba koja nam se prikazuju unutar AlertDialog-a se nazivaju positive i negative button,
                //a ovdje definiramo sto ce se desiti prilikom klika na positive gumb koji u sebi ima tekst "Yes"
                //znaci prilikom klika na positive gumb se poziva metoda deleteTask() koju smo definirali u kodu dolje
                //a koja je zaduzena za brisanje podsjetnika
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteTask(task);
                    }
                });

                //drugi gumb je negative gumb koji u sebi ima tekst NO, a unutar ove metode definiramo jos jednu metodu
                //koja nam govori sta e se desiti prilikom klika na gumb NO
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    //prilikom klika na gumb NO ne zelimo da se ista desi pa zbog toga u tijelu ove metode nismo naveli
                    //nikakav kod, kada se klikne na ovaj gumb AlertDialog ce se zatvoriti i apsolutno nista se nece
                    //desiti
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                //sve ovo iznad je bilo definiranje zadataka sto ce se desiti kada kliknemo određene gumbove, ali to jos uvijek
                //nije upogonjeno, a upravo to radimo u sljedece dvije linije koda, sve ono iznad sto smo definirali upogonjujemo
                //pomocu ove dvije linije koda
                AlertDialog ad = builder.create();
                ad.show();
            }
        });

    }

    //ova metoda nam sluzi kako bi pohranili u editTextTask, editTextDesc, editTextFinishBy i checkBoxFinished
    //vrijednosti koje su unesene pomocu gettera koje smo deklarirali unutar Task POJO klase
    private void loadTask(Task task){

        //setText() --> ova metoda postavlja tekst koji treba biti prikazan
        //kao parametar joj prilazemo getter metodu iz Task klase koju pozivamo preko objekata task klase Task
        //metoda getTask vraca vrijednost koja je postavljenja u task atributu Task POJO klase i tako isto za
        //ostale cetiri varijable
        editTextTask.setText(task.getTask());
        editTextDesc.setText(task.getDesc());
        editTextFinishBy.setText(task.getFinishBy());
        checkBoxFinished.setChecked(task.isFinished());
    }

    //ova metoda nam sluzi kako bi azurirali podatke unutar baze podataka
    private void updateTask(final Task task){

        //u naredna tri reda spremamo ono sto je korisnik unio u EditText-ove u varijable koje smo deklarirali
        //s određenim View-ima.Znaci ono sto korisnik unese u prvo polje, a to prvo polje nam je editTextTask se
        //sprema u final varijablu sTask,tako isto i za sDesc i sFinishBy.Metoda koja vraca ono sto je korisnik
        //unio u određeni EditText je getText() metoda,tu je jos i toString() metoda koja vraca tekstualni zapis
        //objekta i jos imamo trim() metodu koja je zasluzna za to da se brisu razmaci bili oni na kraju ili
        //pocetku recenice, ali one razmake između rijeci ne brise
        final String sTask = editTextTask.getText().toString().trim();
        final String sDesc = editTextDesc.getText().toString().trim();
        final String sFinishBy = editTextFinishBy.getText().toString().trim();

        //nakon sto smo odredili varijable u koje ce se spremati ono sto je korisnik unio moramo odrediti i sta ce
        //se desiti ako korisnik ne unese nikakav tekst u EditText, a klikne dalje.Upravo to radimo u sljedecim koracima
        //u ovom prvom slucaju se provjerava ako je korisnik kliknuo dalje a nije nista unio za polje editTextTask onda
        //mu se pokazuje na kraju EditText-a pomocu setError() metode mali crveni uslicnik sa popup porukom "Task required"
        // i pomocu metode requestFocus() fokus mu i dalje ostaje na editTextTask view-u kako bi ovoga puta unio podatke
        if (sTask.isEmpty()){
            editTextTask.setError("Task required");
            editTextTask.requestFocus();
            return;
        }

        //u ovom prvom slucaju se provjerava ako je korisnik kliknuo dalje a nije nista unio za polje editTextDes onda
        //mu se pokazuje na kraju EditText-a pomocu setError() metode mali crveni uslicnik sa popup porukom "Desc required"
        // i pomocu metode requestFocus() fokus mu i dalje ostaje na editTextDesc view-u kako bi ovoga puta unio podatke
        if (sDesc.isEmpty()){
            editTextDesc.setError("Desc required");
            editTextDesc.requestFocus();
            return;
        }

        //u ovom prvom slucaju se provjerava ako je korisnik kliknuo dalje a nije nista unio za polje editTextFinishBy onda
        //mu se pokazuje na kraju EditText-a pomocu setError() metode mali crveni uslicnik sa popup porukom "Finish by required"
        // i pomocu metode requestFocus() fokus mu i dalje ostaje na editTextFinishBy view-u kako bi ovoga puta unio podatke
        if (sFinishBy.isEmpty()){
            editTextFinishBy.setError("Finish by required");
            editTextFinishBy.requestFocus();
            return;
        }

        //unutar metode updateTask() koja je zasluzna za to da se azuriraju podaci unutar baze podataka se nalazi i jos jedna
        //inner(nested) klasa koja nasljeđuje AsyncTask klasu koja je zasluzna za kreiranje background thread-a kako bi se
        //oslobodio main UI thread od opterecenja kako se aplikacija ne bi crashala.U novijim verzijama Androida postalo je
        //obavezno da se operacije vezane za bazu podataka obavljaju u background threadu bas zbog toga kako bi se manje
        //opterecivao UI thread i tako bi se ubrzala aplikacija.Klasa koja nasljeđuje AsnycTask mora implementirati najmanje
        //jednu metodu a ta metoda je doInBackground() unutar koje definiramo taj "heavy" zadatak koji se mora obaviti u
        //backgorund thread-u.U nasem slucaju to je pristup bazi podataka i azuriranje baze podataka pomocu Dao metode update()
        //u nasem slucaju mi smo jos implementirali metodu onPostExecute() kojoj se salju rezultati iz metode doInBackground()
        //i onda onPostExecute() metoda salje svoje podatke u UI thread kako bi ga azurirala
        class UpdateTask extends AsyncTask<Void,Void,Void>{

            //u sljedecem dijelu koda je definirano kako cemo updejtati podatke unutar baze podataka
            //setTask() --> ova metoda je setter koju smo definirali unutar Task klase, a da bi ju mogli
            //              pozvati moramo prvo napraviti objekt od klase Task.prve cetiri linije koda rade
            //              na nacin da imamo objekt klase Task koji je prazan, a klasa Task ima svoje neke
            //              atribute koje mi mozemo staviti u taj objekt pomocu setter metoda.Setter metode
            //              rade na nacin da onu vrijednost koju zelimo predati objektu preko njih moramo
            //              navesti unutar zagrada.Znaci u nasem slucaju one vrijednosti koje zelimo dati
            //              objektu su upravo one vrijednosti koje je korisnik unio u ona tri EditText-a
            @Override
            protected Void doInBackground(Void... voids) {
                task.setTask(sTask);
                task.setDesc(sDesc);
                task.setFinishBy(sFinishBy);
                task.setFinished(checkBoxFinished.isChecked());

                //sljedece tri linije koda su nam zasluzne za updejtanje baze podataka najvise uz pomoc metode
                //update() koja se nalazi unutar TaskDao interfejsa.Kao argument ovoj metodi predajemo task
                //objekt koji u sebi ima vrijednosti koje smo mu dali pomocu setter metoda
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .update(task);
                return null;
            }

            //ova metoda kao sto smo vec ranije i rekli sluzi da posalje rjesenja koja je dobila doInBackround()
            //metoda u main UI thread kako bi se on azurirao
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                //ovdje smo rekli da ce nam se kada se krene izvoditi ova metoda pojaviti Toast poruka na
                //dnu zaslona s porukom "Updated"
                Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_LONG).show();
                finish();

                //ova linija koda nam samo govori da ce kada se zavrsi sa updejtanjem baze podataka ova aktivnost
                //zatvoriti i da ce se otvoriti nova aktivnost i to ona MainActivity
                startActivity(new Intent(UpdateTaskActivity.this,MainMainActivity.class));
            }
        }

        //sve ono iznad smo smo definirali nas je vodilo do ove naredbe, sve ono gore je zapravo samo definiranje
        //sto i kako ce se odvijati updejtanje baze podataka, ali jos uvijek jos nista nije upogonjeno, a upravo
        //to radimo pomocu metode execute(), prvo moramo napraviti objekt od ove klase koja nasljeđuje AsyncTask
        //i preko tog objekta tek onda pozvati metodu execute() koja ce upogoniti sve
        UpdateTask ut = new UpdateTask();
        ut.execute();
    }

    private void deleteTask(final Task task){
        class DeleteTask extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .delete(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateTaskActivity.this,MainMainActivity.class));
            }
        }
        DeleteTask dt = new DeleteTask();
        dt.execute();
    }

}
