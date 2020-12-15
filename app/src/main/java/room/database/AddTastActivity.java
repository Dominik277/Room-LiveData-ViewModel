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

    class SaveTask extends AsyncTask<Void,Void,Void>{


        @Override
        protected Void doInBackground(Void... voids) {

            Task task = new Task();
            task.setTask(sTask);
            task.setDesc(sDesc);
            task.setFinishBy(sFinishBy);
            task.setFinished(false);

            DatabaseClient.getInstance(getApplication()).getAppDatabase()
                    .taskDao()
                    .insert(task);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();

    }

}
