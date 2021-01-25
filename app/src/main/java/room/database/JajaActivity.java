package room.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class JajaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jaja);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Jaja");
    }
}