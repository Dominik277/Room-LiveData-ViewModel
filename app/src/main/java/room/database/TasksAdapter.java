package room.database;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


//ova klasa nam sluzi za kreiranje custom adaptera za RecyclerView.Znaci odredili smo da cemo
//na zaslonu elemente prikazivati pomocu RecyclerView-a.No RecyclerView nam sluzi samo za prikaz
//podataka i nista drugo, on nema nikakvu drugu zadacu nego samo prikazivanje podataka,njega ne
//zanima koji podaci i kako ce podaci doci do njega njemu je samo bitno da on podatke prikaze.
//E upravo to obskrbljivanje podacima i definiranje na koji nacin ce biti prikazani podaci unutar
//recyclerView-a je zadaca RecyclerView adaptera.On ima zadatak opskrbiti RecyclerView podacima
//koje treba prikazivati i na koji nacin treba prikazati podatke
public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {

    //ovdje smo kreirali varijable tipa Context i List koje su jos prazne, ali ne zadugo jer
    //cemo uskoro u te varijable poceti nesto pohranjivati ili ce te varijable mozda postati
    //referenca na neki objekt u memoriji racunala
    private Context mCtx;
    private List<Task> taskList;

    //ovo je nas cutom konstruktor koji sluzi kako bi instancirali objekt prilikom njegove kreacije
    //odnosno sve vrijednosti koje zelimo da atributi nekog objekta imaju cemo predati kao argumente
    //konstruktoru
    public TasksAdapter(Context mCtx,List<Task> taskList){
        this.mCtx = mCtx;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TasksAdapter.TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_tasks,parent,false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksAdapter.TasksViewHolder holder, int position) {
        Task t = taskList.get(position);
        holder.textViewTask.setText(t.getTask());
        holder.textViewDesc.setText(t.getDesc());
        holder.textViewFinishBy.setText(t.getFinishBy());

        if (t.isFinished()){
            holder.textViewStatus.setText("Completed!");
        }else {
            holder.textViewStatus.setText("Not completed!");
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        //ovdje smo definirali varijable i njihove tipove podataka, po tipovima podataka
        //mozemo vidjeti da ce se ove varijable odnositi na nekakve View-ove koji ce biti
        //prikazani na ekranu, odnosno ti View-ovi ce zapravo biti nekakav tekstualan zapis
        //u ovom koraku te varijable su i dalje prazne, nismo jos nista pohranili u njih
        TextView textViewStatus;
        TextView textViewTask;
        TextView textViewDesc;
        TextView textViewFinishBy;

        //custom konstruktor --> ????????
        public TasksViewHolder(@NonNull View itemView) {
            super(itemView);

            //ovdje dolazimo do dijela kada u ove varijable pohranjujemo neke podatke, a
            //ti podaci nam predstavljaju view-ove unutar XML-a.Pomocu metode
            //findViewById dohvacamo iz XML-a view-ove koje dohvatimo preko njihovih id-ova,
            //te ih spremamo u njihove odgovarajuce varijable onako kako smo naveli, na ovaj
            //nacin iz XML objekata stvaramo Java objekte
            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            textViewTask = itemView.findViewById(R.id.textViewTask);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Task task = taskList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, UpdateTaskActivity.class);
            intent.putExtra("task",task);

        }
    }

}
