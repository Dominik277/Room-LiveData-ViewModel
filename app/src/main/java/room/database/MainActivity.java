package room.database;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import room.database.NavigationDrawer.JajaActivity;
import room.database.NavigationDrawer.ObavijestiActivity;
import room.database.NavigationDrawer.PiliciActivity;
import room.database.NavigationDrawer.PrihodiIRashodiActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this,drawer,toolbar,R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.bringToFront();
        drawer.requestLayout();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.jaja){
            startActivity(new Intent(MainActivity.this, JajaActivity.class));
        }
        if (id == R.id.pilići){
            startActivity(new Intent(MainActivity.this, PiliciActivity.class));
        }
        if (id == R.id.obavijesti){
            startActivity(new Intent(MainActivity.this, ObavijestiActivity.class));
        }
        if (id == R.id.prihodi_i_rashodi){
            startActivity(new Intent(MainActivity.this, PrihodiIRashodiActivity.class));
        }
        return false;
    }
}
