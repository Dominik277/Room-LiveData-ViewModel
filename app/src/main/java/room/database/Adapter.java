package room.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {

    private Context context;
    private ArrayList<ModelExampleClass> modelExampleClasses;

    public Adapter(Context context,ArrayList<ModelExampleClass> modelExampleClasses){
        this.context = context;
        this.modelExampleClasses = modelExampleClasses;
    }

    @NonNull
    @Override
    public Adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.obavijesti_card_item,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.Viewholder holder, int position) {
        ModelExampleClass model = modelExampleClasses.get(position);
        holder.textViewObavijest.setText(model.getObavijest());
        holder.textViewZaKoga.setText(model.getZaKoga());
        holder.textViewAutor.setText(model.getAutor());
        holder.textViewObjavljeno.setText(model.getObjavljeno());
        holder.textViewDoKada.setText(model.getDoKada());
    }

    @Override
    public int getItemCount() {
        return modelExampleClasses.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder{

        private TextView textViewObavijest;
        private TextView textViewAutor;
        private TextView textViewZaKoga;
        private TextView textViewObjavljeno;
        private TextView textViewDoKada;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            textViewObavijest = itemView.findViewById(R.id.textViewObavijest);
            textViewAutor = itemView.findViewById(R.id.textViewAutor);
            textViewZaKoga = itemView.findViewById(R.id.textViewZaKoga);
            textViewObjavljeno = itemView.findViewById(R.id.textViewObjavljeno);
            textViewDoKada = itemView.findViewById(R.id.textViewDoKada);
        }
    }

}
