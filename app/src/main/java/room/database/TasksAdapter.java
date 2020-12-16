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

    //ovo je nas custom konstruktor koji sluzi kako bi instancirali objekt prilikom njegove kreacije
    //odnosno sve vrijednosti koje zelimo da atributi nekog objekta imaju cemo predati kao argumente
    //konstruktoru.Kao parametre prima context i listu objekata iz POJO klase Task, objekt te klase u
    //sebi sadrzi sve informacije vezane za aplikaciju
    public TasksAdapter(Context mCtx,List<Task> taskList){
        this.mCtx = mCtx;
        this.taskList = taskList;
    }

    //onCreateViewHolder metoda se poziva samo kada treba stvoriti novi ViewHolder kada ne postoji niti
    //jedan ViewHolder koji RecyclerView moze reciklirati
    // parent -->ovaj parametar nam zapravo predstavlja RecyclerView.parent je poslan u metodu samo zbog toga
    //          kako bi mago biti predan LayoutInflater-u kako bi on mogao odraditi određene LayoutParams
    //na inflatirani View
    //viewType --> view tip "of the new View"
    @NonNull
    @Override
    public TasksAdapter.TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //napravili smo varijablu view tipa View u koju pohranjujemo ono sto se obradi s desne strane
        //View je osnovna klasa zaduzena za sve UI komponente ili "widgete", a to su button,TextView,EditView...
        //ViewGroup je je podklasa View klase koja je zaduzena za layout-e, a layout-e mozemo zamisliti kao
        //nekakav kontejner koji u sebi sadrzi mnogo drugih View-ova koju se poslagani po nekom redosljedu
        //LayoutInflater --> uloga LayoutInflatera je da uzima layout iz XML-a i od njih pravi Java
        //                   objekte tipa View, znaci cijeli layout pretvori u Java objekt tipa View
        //inflate --> pomocu ove metode smo deklarirali koji view cemo pohraniti u objekt view tipa View
        //            u nasem slucaju to je recyclerview_task
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_tasks,parent,false);
        return new TasksViewHolder(view);
    }

    //ova metoda nam sluzi kako bi prikazali sve podatke koje je adapter u sebe spremio u RecyclerView
    //RecyclerView zove ovu metodu kako bi prikazao podatke na tocno određenoj poziciji, zbog toga kao
    //parametar mu predajemo position da bi on tocno znao na kojoj poziciji treba prikazati podatke
    //kada RecylcerView reciklira redke, svaki sljedeci redak koji je prikaze na screen-u je prazan, te se
    //on mora popuniti podacima, a upravo to je zadaca ove metode
    //holder -->ViewHolder koji bi trebao biti azuriran da predstavlja elemente jednog retka na posebno
    //          određenoj poziciji uutar neke kolekcije podataka
    //position --> pozicija jednog retka unutar adapterovih podataka
    @Override
    public void onBindViewHolder(@NonNull TasksAdapter.TasksViewHolder holder, int position) {

        //na lijevoj strani smo kreirali varijablu t tipa Task u koju spremamo sve s desne strane
        //taskList --> taskList nam je objekt tipa List u koji spremamo podatke tipa Task
        //get() --> ova metoda je tipa int pa onda ona vraca broj koji predstavlja poziciju
        //          u listi
        //position --> ovaj parametar tipa int nam predstavlja redni broj tog item-a koji se nalazi
        //             u RecyclerView-u
        //holder --> holder nam predstavlja ViewHolder koji bi trebao biti azuriran kada god se novi
        //           redak prikaze na ekranu na tocno određenoj poziciji unutar neke kolekcije podataka
        //           koja je određena parametrom positio
        //setText() --> ova metoda se poziva na objekte tipa TextView te ona postavlja tekst koji ce biti
        //              prikazan na pojedinom TextView-u.U nasem slucaju posto su kao parametri ove metode
        //              prilozeni getteri oni vracaju vrijednost atributa Task klase,a svi atributi task
        //              klase su tipa string, tako da ce oni ispisivati text
        Task t = taskList.get(position);
        holder.textViewTask.setText(t.getTask());
        holder.textViewDesc.setText(t.getDesc());
        holder.textViewFinishBy.setText(t.getFinishBy());

        //ako atribut isFinished iz klase Task koji je tipa boolean ima vrijednost true onda se
        //izvrsava dio koda unutar if() bloka, u suprotnom slucaju, ako taj atribut ima vrijednost
        //false onda se izvrsava dio koda unutar else bloka
        if (t.isFinished()){
            holder.textViewStatus.setText("Completed!");
        }else {
            holder.textViewStatus.setText("Not completed!");
        }
    }

    //ova metoda bi trebala vratiti broj list item-a
    //varijabla taskList je array i u nju smo spremili sve one vrijednosti koje smo naveli unutar
    //POJO Task klase.Metoda lenght() izracunava koliko ima elemenata unutar taskList varijable
    //i vraca ukupan broj elemenata jer je ova metoda tipa int
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

        //custom konstruktor koji kao parametar prima varijablu itemView tipa View
        public TasksViewHolder(@NonNull View itemView) {

            //ovdje smo uz pomoc kljucne rijeci super dozvali konstruktor od ViewHolder nadklase
            //a nadklasa od ViewHolder je RecyclerView.ViewHolder
            //poziv konstruktora od nadklase mora biti prva stvar u konstruktoru
            //kada napisemo super() bez parametara onda se poziva default konstruktor nadklase
            //kada napisemo super(...) s nekoliko parametara onda on trazi u nadklasi koji
            //konstruktor se podudara, gledajuci na parametre, i onda ako se neki konstruktor
            //podudara onda poziva njega.
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


            //ova metoda se poziva kada je itemView kliknut, ako smo u XML-u definirali da
            //ovaj itemView nije "clickable" onda kada na njega pozovemo ovu metodu onda
            //on postaje "clickable"
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
