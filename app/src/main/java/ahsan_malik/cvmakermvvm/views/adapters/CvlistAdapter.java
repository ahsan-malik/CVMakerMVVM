package ahsan_malik.cvmakermvvm.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ahsan_malik.cvmakermvvm.appUtils.Helper;
import ahsan_malik.cvmakermvvm.databinding.ItemCvlistBinding;
import ahsan_malik.cvmakermvvm.models.Person;
import ahsan_malik.cvmakermvvm.views.interfaces.EditClickListener;

public class CvlistAdapter extends RecyclerView.Adapter<CvlistAdapter.ViewHolder> {

    List<Person> people;
    EditClickListener<Void> editClickListener;

    public CvlistAdapter(List<Person> people, EditClickListener<Void> editClickListener) {
        this.people = people;
        this.editClickListener = editClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCvlistBinding cvlistBinding = ItemCvlistBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(cvlistBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemBinding.setPerson(people.get(position));
        holder.itemBinding.edit.setOnClickListener(view -> {
            Helper.personID = people.get(position).getId();
            editClickListener.onEditClick(null);
        });
        holder.itemBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ItemCvlistBinding itemBinding;

        public ViewHolder(@NonNull ItemCvlistBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}
