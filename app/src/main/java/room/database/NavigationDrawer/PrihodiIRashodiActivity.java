package room.database.NavigationDrawer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import room.database.R;

public class PrihodiIRashodiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prihodi_i_rashodi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Prihodi i Rashodi");

    }
}