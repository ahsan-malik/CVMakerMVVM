package ahsan_malik.cvmakermvvm.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ahsan_malik.cvmakermvvm.databinding.ItemQualificationBinding;
import ahsan_malik.cvmakermvvm.models.Qualification;
import ahsan_malik.cvmakermvvm.views.interfaces.EditClickListener;

public class QualificationAdapter extends RecyclerView.Adapter<QualificationAdapter.ViewHolder> {

    Context context;
    List<Qualification> qualifications;
    EditClickListener<Qualification> editClickListener;

    public QualificationAdapter(Context context, List<Qualification> qualifications, EditClickListener<Qualification> editClickListener) {
        this.context = context;
        this.qualifications = qualifications;
        this.editClickListener = editClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemQualificationBinding binding = ItemQualificationBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemBinding.setQualification(qualifications.get(position));
        holder.itemBinding.edit.setOnClickListener(view -> editClickListener.onEditClick(qualifications.get(position)));
        holder.itemBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return qualifications.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemQualificationBinding itemBinding;

        public ViewHolder(@NonNull ItemQualificationBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}
