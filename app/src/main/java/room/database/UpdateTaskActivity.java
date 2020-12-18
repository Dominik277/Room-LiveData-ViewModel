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

    private void loadTask(Task task){
        editTextTask.setText(task.getTask());
        editTextDesc.setText(task.getDesc());
        editTextFinishBy.setText(task.getFinishBy());
        checkBoxFinished.setChecked(task.isFinished());
    }

    private void updateTask(final Task task){

        final String sTask = editTextTask.getText().toString().trim();
        final String sDesc = editTextDesc.getText().toString().trim();
        final String sFinishBy = editTextFinishBy.getText().toString().trim();

        if (sTask.isEmpty()){
            editTextTask.setError("Task required");
            editTextTask.requestFocus();
            return;
        }

        if (sDesc.isEmpty()){
            editTextDesc.setError("Desc required");
            editTextDesc.requestFocus();
            return;
        }

        if (sFinishBy.isEmpty()){
            editTextFinishBy.setError("Finish by required");
            editTextFinishBy.requestFocus();
            return;
        }

        class UpdateTask extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                task.setTask(sTask);
                task.setDesc(sDesc);
                task.setFinishBy(sFinishBy);
                task.setFinished(checkBoxFinished.isChecked());
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .update(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateTaskActivity.this,MainMainActivity.class));
            }
        }
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
