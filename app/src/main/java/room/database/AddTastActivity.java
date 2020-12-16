package room.database;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddTastActivity extends AppCompatActivity {


    //ovdje smo stvorili imena varijabli preko kojih cemo referencirati
    //view-ove unutar XML-a.Moramo paziti kojeg tipa stavimo da su te varijable
    //jer moraji biti jednakog tipa kao i one unutar XML-a
    private EditText editTextTask;
    private EditText editTextDesc;
    private EditText editTextFinishBy;
    private Button button_save;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //do ovog koraka sve te varijable koje smo deklarirali gore su bile
        //prazne, ovdje ih sada punimo pomocu metode findViewById().Ova metoda
        //radi na nacin da joj kao parametar upisemo id kojeg zelimo dohvatiti
        //iz XML-a i kada ju ta metoda dohvati ona ju pohranjuje unutar varijable
        //koju smo stavili s lijeve strane
        editTextTask = findViewById(R.id.editTextTask);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextFinishBy = findViewById(R.id.editTextFinishBy);
        button_save = findViewById(R.id.button_save);

        //u XML-u smo također naveli i gumb kojem smo dali ime button_save te cemo
        //ga referencirati pomocu tog imena.Sljedeca linija koda nam govori da tom
        //gumbu dajemo nekakvu funkcionalnost, jos nismo naveli kakvu tocno funkcionalnost
        //ali to cemo navesti unutar setOnClickListener() metode
        button_save.setOnClickListener(new View.OnClickListener() {

            //ovdje smo naveli koju tocno funkcionalnost ce imati gumb, i ta funkcionalnost je
            //da izvede neku radnju kad se na njega klikne.onClick() je metoda koja se odnosi
            //na gumb i unutar tijela te metode navodimo sta ce se desiti kada se klikne gumb,
            //u nasem slucaju kada se klikne gumb poziva se metoda saveTask koju smo naveli ispod
            //te se izvrsava sve sto je unutar tijela te metode
            //saveTask() -->
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });
    }

    private void saveTask(){


        //znaci editText klasa unutar android-a nam predstavlja onaj prostor gdje korisnik
        //upisuje nekakav text, editText nam je drugaciji jer on prikazuje samo nekakav tekst
        //koji je definiran prije pokretanja aplikacije i ne mjenja se tokom aplikacije, a
        //editText najcesce bude prazan ili je prikazan nekakav hint koji se makne kada korisnik
        //krene upisavati tekst
        //getText() --> ova metoda nam sluzi da kada korisnik unese nekakav tekst u editText
        //              ova metoda pohranjuje sve sto je korisnik unio, ili bolje receno, vraca
        //              string koji je korisnik unio
        //toString() --> ova metoda vraca tekstualan prikaz objekta, kada bi htjeli ispisati
        //               string u konzoli, konzola bi nam izbacila nekakav cudan skup slova i
        //               brojeva koji predstavljaju adresu objekta u memoriji, te nam tada ova
        //               toString() metoda postaje korisna jer ona vraca tekstualan zapis objekta
        //               kada pravimo objekt neke klase i unutar te klase smo "override" toString()
        //               metodu, i kada zelimo ispisati objekt te klase onda uopce ne moramo
        //               pozivati tu metodu jer se onda podrazumjeva od compilera da je tu
        //trim() --> ova metoda vraca onaj string koji je pohranjen ali u slucaju da postoje razmaci
        //           na kraju ili na pocetku rijeci ona ih brise.Ako je rijec o nekoj recenici koja
        //           između pojedinih rijeci ima razmake ona ih nece obrisati nego brise samo one razmake
        //           koji se nalaze na pocetku ili na kraju
        final String sTask = editTextTask.getText().toString().trim();
        final String sDesc = editTextDesc.getText().toString().trim();
        final String sFinishBy = editTextFinishBy.getText().toString().trim();


        //sljedeci if blok se odvija ako je radnja unutar zagrada istinita
        //a radnja unutar zagrada je da se provjerava je li prostor gdje je korisnik trebao upisati nekakav
        //tekst ostao prazan.Znaci imamo editText prostor u koji korisnik treba upisati nekakav zadatak koji
        //se potom pohranjuje unutar sTask varijable i mi pomocu metode isEmpty() provjeravamo je li ta varijabla
        //prazna, ako nam je ta varijabla prazna onda nam to govori da korisnik nije unio nikakav tekst te ga
        //potom trebamo upozoriti s nekakavom porukom
        if (sTask.isEmpty()){

            //editTextTask nam predstavlja onaj prozorcic unutar XML-a u koji upisujemo nekakav tekst
            //i u slucaju da je taj tekst ostao prazan kao sto nam sugerira zagrada unutar if-a onda se
            //na desnoj strani editText-a prikazuje mali crvni usklicnik te iskace popup poruka sa tekstom
            //"Task required", upravo to nam predstavlja setError() metoda
            editTextTask.setError("Task required");

            //vec sam rekao da je editText view unutar XML-a koji predstavlja prozorcic unutar kojeg se unosi
            //tekst, i nakon sto korisnik ne unese tekst unutar tog prozorcica prikazat ce se mali crveni usklicnik
            //sa popup porukum, a requestFocus() metoda nam osigurava da upravo taj view ostane ajmo reci podebljan
            //te da se ne preskoci na sljedeci editText nego da se i dalje moze upisati u ovaj editText
            //da bi mogli pozvati ovu metodu na taj view onda unutar XML-a za taj view moramo definirati metodu
            //isFocusable:"true"
            editTextTask.requestFocus();
            return;
        }

        //sljedeci if blok se odvija ako je radnja unutar zagrada istinita
        if (sDesc.isEmpty()){

            //editTextTask nam predstavlja onaj prozorcic unutar XML-a u koji upisujemo nekakav tekst
            //i u slucaju da je taj tekst ostao prazan kao sto nam sugerira zagrada unutar if-a onda se
            //na desnoj strani editText-a prikazuje mali crvni usklicnik te iskace popup poruka sa tekstom
            //"Desc required", upravo to nam predstavlja setError() metoda
            editTextTask.setError("Desc required");

            //vec sam rekao da je editText view unutar XML-a koji predstavlja prozorcic unutar kojeg se unosi
            //tekst, i nakon sto korisnik ne unese tekst unutar tog prozorcica prikazat ce se mali crveni usklicnik
            //sa popup porukum, a requestFocus() metoda nam osigurava da upravo taj view ostane ajmo reci podebljan
            //te da se ne preskoci na sljedeci editText nego da se i dalje moze upisati u ovaj editText
            //da bi mogli pozvati ovu metodu na taj view onda unutar XML-a za taj view moramo definirati metodu
            //isFocusable:"true"
            editTextTask.requestFocus();
            return;
        }

        //sljedeci if blok se odvija ako je radnja unutar zagrada istinita
        if (sFinishBy.isEmpty()){

            //editTextTask nam predstavlja onaj prozorcic unutar XML-a u koji upisujemo nekakav tekst
            //i u slucaju da je taj tekst ostao prazan kao sto nam sugerira zagrada unutar if-a onda se
            //na desnoj strani editText-a prikazuje mali crvni usklicnik te iskace popup poruka sa tekstom
            //"Finish by required", upravo to nam predstavlja setError() metoda
            editTextTask.setError("Finish by required");

            //vec sam rekao da je editText view unutar XML-a koji predstavlja prozorcic unutar kojeg se unosi
            //tekst, i nakon sto korisnik ne unese tekst unutar tog prozorcica prikazat ce se mali crveni usklicnik
            //sa popup porukum, a requestFocus() metoda nam osigurava da upravo taj view ostane ajmo reci podebljan
            //te da se ne preskoci na sljedeci editText nego da se i dalje moze upisati u ovaj editText
            //da bi mogli pozvati ovu metodu na taj view onda unutar XML-a za taj view moramo definirati metodu
            //isFocusable:"true"
            editTextTask.requestFocus();

            return;
        }


        //AsyncTask je abstraktna klasa koja nam omogucava radnju teskih operacija u background thread-u i zbog toga
        //omogucava da UI thread bude neopterecen, a upravo ta neopterecenost UI thread-a osigurava da aplikacija bude
        //brza.Znaci ukratko AsyncTask klasa nam omogucava rad teskih zadataka u backgroundu kako bi oslobodili main UI
        //thread te po zavrsetku odrađivanja zadatka u background thread-u podaci se salju u main UI thread
    class SaveTask extends AsyncTask<Void,Void,Void>{


            //doInBackground() metoda je glavna metoda unutar AsyncTask klase unutar koje se navode svi oni zadaci
            //koji se trebaju izvrsiti unutar background thread-a.Kako bi obavijestili da je background zadatak
            //obavljen moramo navesti return statement.Operacije koje se odvijaju unutar ove metode ne mogu nikako
            //doci u doticaj s UI thread-om.
        @Override
        protected Void doInBackground(Void... voids) {

            //ovdje smo na desnoj strani napravili objekt u memoriji racunala tipa Task,a na lijevoj strani smo
            //samo naveli preko kojeg imena cemo ga referencirati.Task klasa je POJO klasa u kojoj se nalaze svi
            //glavni podaci vezani za aplikaciju te getteri i setteri kako bi mogli pristupiti ili mijenjati te
            //podatke
            Task task = new Task();

            //kako bi pristupili metodama koje se nalaze unutar Task klase moramo napraviti objekt od te klase,
            //sto smo i napravili u koraku iznad.U nasem slucaju trebamo pristupiti setterima uz klase Task.
            //kako bi nam ovo bilo sto lakse zamislimo to na sljedeci nacin = imamo POJO klasu unutar koje se
            //nalaze svi atributi koji su nam potrebni za aplikaciju, ali svi ti atributi su prazni, nemaju
            //nikakvu vrijednost u sebi i sada mi preko settera njima dajemo nekakvu vrijednost, a tu vrijednost
            //koju mu zelimo dati moramo napisati u parametar od settera, a te vrijednosti koje mu zelimo dati su
            //one koje je korisnik unio unutar određenog editTexta-a
            //
            //setTask(sTask) --> ova metoda sluzi kako bi dali vrijednost atributu task unutar Task klase
            //                   radi na nacin da koju god zelimo vrijednost predati task atributu, tu vrijednost
            //                   predamo kao parametar setter-u (parametar u nasem slucaju mora biti tipa String)
            //setDesc(sDesc) --> ova metoda sluzi kako bi dali vrijednost atributu desc unutar Task klase
            //                   radi na nacin da koju god zelimo vrijednost predati desc atributu, tu vrijednost
            //                   predamo kao parametar setter-u (parametar u nasem slucaju mora biti tipa String)
            //setFinishBy(sFinishBy) --> ova metoda sluzi kako bi dali vrijednost atributu finishBy unutar Task klase
            //                           radi na nacin da koju god zelimo vrijednost predati finishBy atributu, tu vrijednost
            //                           predamo kao parametar setter-u (parametar u nasem slucaju mora biti tipa String)
            //setFinished(false) --> ova metoda sluzi kako bi dali vrijednost atributu finished unutar Task klase
            //                       radi na nacin da koju god zelimo vrijdnost predati finished atributu, tu vrijednost
            //                       predajemo kao parmeter settet-u (parametar u nasem slucaju mora biti tipa boolean)
            task.setTask(sTask);
            task.setDesc(sDesc);
            task.setFinishBy(sFinishBy);
            task.setFinished(false);


            //getInstance() -->
            //getApplication() --> vraća aplikaciju koja sadržava trenutni activity
            //getAppDatabase() -->
            //taskDao() -->
            //insert() -->
            DatabaseClient.getInstance(getApplication()).getAppDatabase()
                    .taskDao()
                    .insert(task);

            return null;
        }

        //ova metoda se poziva na main UI thread nakon sto su izvrsene sve operacije unutar doInBackground() metode.
        //rezultati koji se dobiju unutar doInBackground() metode se prosljleđuju ovoj metodi kao parametar i nakon
        //toga ova metoda salje podatke koje je dobila od doInBackground() metode u main UI thread te se main UI
        //thread azurira i promjena podataka nam je vidljiva
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            //ova metoda se poziva kada je activity zavrsen i treba se zatvoriti
            //kada zovemo metodu finish() obavalja se također i metoda onDestroy()
            //kada pozovemo metodu finish() slijedom se pozivaju sljedece metode
            //1.onPause(), 2.onStop(), 3.onDestroy()
            finish();

            //
            startActivity(new Intent(getApplicationContext(),MainActivity.class));

            //ova linija koda nam govori da ce na dnu ekrana biti prikazana mala Toast poruka
            //makeText() --> ova metoda se poziva kada zelimo prikazati standardan Toast text
            //               1.parametar je da predajemo kontext od aplikacije
            //               2.parametar je da predajemo text koji zelimo ispisati na dnu ekrana
            //               3.parametar je da određujemo koliko dugo zelimo da ta poruka bude ispisana
            //show() --> sve ovo prije sto smo napravili je bilo samo određivanje sto,kako i koliko ce
            //           se ispisivati Toast poruka, ova metod nam omogucava da se Toast poruka prikaze
            //           bez ove metode nista ovo ispred nam ne bi vrijedilo
            Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();
            }
        }

        //ovdje smo napravili objekt klase SaveTask te na lijevo strani referencirali
        //preko kojeg imena cemo ga zvati
        SaveTask st = new SaveTask();

        //execute() --> kada smo gotovi sa definiranjem zadatka, mi zapravo nismo jos nista upogonili
        //              jedino cemo upogoniti kada pozovemo metodu execute(), a kako bi ju upogonili
        //              moramo napraviti objekt one klase u kojoj smo definirali background thread i na
        //              taj objekt pozvati execute() metodu koja ce to sve pokrenuti sto smo prije
        //              definirali
        st.execute();

    }

}
