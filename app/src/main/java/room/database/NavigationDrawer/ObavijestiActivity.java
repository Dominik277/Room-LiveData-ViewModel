package room.database.NavigationDrawer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import room.database.R;

public class ObavijestiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obavijesti);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Obavijesti");
    }
}