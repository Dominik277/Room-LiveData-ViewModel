package room.database.NavigationDrawer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import room.database.R;

public class PiliciActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilici);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Pilići");
    }
}