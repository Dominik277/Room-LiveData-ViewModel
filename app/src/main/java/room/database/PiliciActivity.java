package room.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PiliciActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilici);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Pilići");
    }
}