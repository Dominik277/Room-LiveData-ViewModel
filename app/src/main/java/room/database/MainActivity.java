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
        if (id == R.id.programiranje){
            startActivity(new Intent(MainActivity.this, ProgramiranjeActivity.class));
        }
        if (id == R.id.hadrver){
            startActivity(new Intent(MainActivity.this, HardverActivity.class));
        }
        if (id == R.id.mrezeiprotkoli){
            startActivity(new Intent(MainActivity.this, MrezeIProtokoliActivity.class));
        }
        if (id == R.id.operativniSustavi){
            startActivity(new Intent(MainActivity.this, OperativniSustaviActivity.class));
        }
        return false;
    }
}
