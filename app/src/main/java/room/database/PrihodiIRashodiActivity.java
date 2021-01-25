package room.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PrihodiIRashodiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prihodi_i_rashodi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Prihodi i Rashodi");

    }
}