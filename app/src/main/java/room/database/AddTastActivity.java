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

    private EditText editTextTask;
    private EditText editTextDesc;
    private EditText editTextFinishBy;
    private Button button_save;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        editTextTask = findViewById(R.id.editTextTask);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextFinishBy = findViewById(R.id.editTextFinishBy);
        button_save = findViewById(R.id.button_save);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });
    }

    private void saveTask(){

        final String sTask = editTextTask.getText().toString().trim();
        final String sDesc = editTextTask.getText().toString().trim();
        final String sFinishBy = editTextTask.getText().toString().trim();

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
