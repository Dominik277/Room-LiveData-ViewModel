package room.database.NavigationDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import room.database.Adapter;
import room.database.DodajNovuObavijestActivity;
import room.database.ModelExampleClass;
import room.database.R;

public class ObavijestiActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ModelExampleClass> modelExampleClasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obavijesti);
        setTitle("Obavijesti");

        recyclerView = findViewById(R.id.recyclerView);

        modelExampleClasses = new ArrayList<>();
        modelExampleClasses.add(new ModelExampleClass("Odvesti auto u garaži i naložiti vatru.","Dražen","Dominika","19:30","20:45"));
        modelExampleClasses.add(new ModelExampleClass("Ugasiti vešmašinu prije spavanja","Jasna","Leonardu","22:43","23:30"));
        modelExampleClasses.add(new ModelExampleClass("","","","",""));
        modelExampleClasses.add(new ModelExampleClass("","","","",""));
        modelExampleClasses.add(new ModelExampleClass("","","","",""));
        modelExampleClasses.add(new ModelExampleClass("","","","",""));

        Adapter adapter = new Adapter(this,modelExampleClasses);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this
                ,LinearLayoutManager.VERTICAL
                ,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_obavijesti,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_obavijest:
                startActivity(new Intent(ObavijestiActivity.this, DodajNovuObavijestActivity.class));
                break;
        }
        return true;
    }
}