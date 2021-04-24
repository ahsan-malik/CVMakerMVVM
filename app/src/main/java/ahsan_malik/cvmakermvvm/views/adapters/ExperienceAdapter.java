package ahsan_malik.cvmakermvvm.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ahsan_malik.cvmakermvvm.databinding.ItemExperienceBinding;
import ahsan_malik.cvmakermvvm.models.Experience;
import ahsan_malik.cvmakermvvm.views.interfaces.EditClickListener;

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.ViewHolder> {

    List<Experience> experienceList;
    EditClickListener<Experience> editClickListener;

    public ExperienceAdapter(List<Experience> experienceList, EditClickListener<Experience> editClickListener) {
        this.experienceList = experienceList;
        this.editClickListener = editClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemExperienceBinding binding = ItemExperienceBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemBinding.setExperience(experienceList.get(position));
        holder.itemBinding.edit.setOnClickListener(view -> editClickListener.onEditClick(experienceList.get(position)));
        holder.itemBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return experienceList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ItemExperienceBinding itemBinding;

        public ViewHolder(@NonNull ItemExperienceBinding binding) {
            super(binding.getRoot());
            this.itemBinding = binding;
        }
    }
}
