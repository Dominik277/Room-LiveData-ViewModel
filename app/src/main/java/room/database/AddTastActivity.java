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

        final String sTask = editTextTask.getText().toString().trim();
        final String sDesc = editTextDesc.getText().toString().trim();
        final String sFinishBy = editTextFinishBy.getText().toString().trim();

        if (sTask.isEmpty()){
            editTextTask.setError("Task required");
            editTextTask.requestFocus();
            return;
        }

        if (sDesc.isEmpty()){
            editTextTask.setError("Desc required");
            editTextTask.requestFocus();
            return;
        }

        if (sFinishBy.isEmpty()){
            editTextTask.setError("Finish by required");
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
