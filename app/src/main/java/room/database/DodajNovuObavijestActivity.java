package room.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class DodajNovuObavijestActivity extends AppCompatActivity {

    EditText editTextObavijestZaKoga;
    EditText editTextObavijestDoKada;
    EditText editTextObavijestVrijemeObjavljivanja;
    EditText editTextObavijestObavijest;
    EditText editTextObavijestAutor;
    Button gumbObjaviObavijest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_novu_obavijest);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Dodaj novu obavijest!");

        editTextObavijestZaKoga = findViewById(R.id.obavijest_zaKoga);
        editTextObavijestDoKada = findViewById(R.id.obavijest_doKada);
        editTextObavijestVrijemeObjavljivanja = findViewById(R.id.obavijest_vrijemeObajvljivanja);
        editTextObavijestObavijest = findViewById(R.id.obavijest_obavijest);
        editTextObavijestAutor = findViewById(R.id.obavijest_autor);
        gumbObjaviObavijest = findViewById(R.id.gumbObjaviObavijest);

        gumbObjaviObavijest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zaKoga = editTextObavijestZaKoga.getText().toString();
                String doKada = editTextObavijestDoKada.getText().toString();
                String autor = editTextObavijestAutor.getText().toString();

                Toast.makeText(DodajNovuObavijestActivity.this,autor
                        + " uspješno ste unijeli obavijest za "
                        + zaKoga
                        + " , a on to mora obaviti do "
                        + doKada
                        + "."
                        ,Toast.LENGTH_LONG).show();
            }
        });

    }
}