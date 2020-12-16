package room.database;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {


    @NonNull
    @Override
    public TasksAdapter.TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TasksAdapter.TasksViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TasksViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
