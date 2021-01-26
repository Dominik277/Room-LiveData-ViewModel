package room.database.NavigationDrawer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import room.database.R;

public class JajaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jaja);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Jaja");
    }
}