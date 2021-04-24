package ahsan_malik.cvmakermvvm.views.adapters;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ahsan_malik.cvmakermvvm.databinding.ItemCertificateBinding;
import ahsan_malik.cvmakermvvm.models.Certification;
import ahsan_malik.cvmakermvvm.views.interfaces.EditClickListener;

public class CertificationAdapter extends RecyclerView.Adapter<CertificationAdapter.ViewHolder> {

    List<Certification> certificationList;
    EditClickListener<Certification> editClickListener;

    public CertificationAdapter(List<Certification> certificationList, EditClickListener<Certification> editClickListener) {
        this.certificationList = certificationList;
        this.editClickListener = editClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemCertificateBinding binding = ItemCertificateBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemBinding.setCertificate(certificationList.get(position));
        holder.itemBinding.edit.setOnClickListener(view -> editClickListener.onEditClick(certificationList.get(position)));
        holder.itemBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return certificationList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ItemCertificateBinding itemBinding;

        public ViewHolder(@NonNull ItemCertificateBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}
