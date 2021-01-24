package room.database;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_programiranje:
                startActivity(new Intent(MainActivity.this,ProgramiranjeActivity.class));
                break;
            case R.id.menu_hardver:
                startActivity(new Intent(MainActivity.this,HardverActivity.class));
                break;
            case R.id.menu_mrezeiprotokoli:
                startActivity(new Intent(MainActivity.this,MrezeIProtokoliActivity.class));
                break;
            case R.id.menu_operativnisustavi:
                startActivity(new Intent(MainActivity.this,MrezeIProtokoliActivity.class));
                break;
        }
        return true;
    }
}
