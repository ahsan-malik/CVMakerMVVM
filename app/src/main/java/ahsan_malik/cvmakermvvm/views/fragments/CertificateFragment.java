package ahsan_malik.cvmakermvvm.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import ahsan_malik.cvmakermvvm.appUtils.Helper;
import ahsan_malik.cvmakermvvm.databinding.FragmentCertificateBinding;
import ahsan_malik.cvmakermvvm.databinding.PopCertificateBinding;
import ahsan_malik.cvmakermvvm.models.Certification;
import ahsan_malik.cvmakermvvm.viewModels.PersonViewModel;
import ahsan_malik.cvmakermvvm.views.adapters.CertificationAdapter;
import ahsan_malik.cvmakermvvm.views.interfaces.EditClickListener;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CertificateFragment extends Fragment implements EditClickListener<Certification> {

    FragmentCertificateBinding binding;
    PopCertificateBinding popCertificateBinding;
    List<Certification> certificationList = new ArrayList<>();
    PersonViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentCertificateBinding.inflate(inflater, container, false);

        binding.addButton.setOnClickListener(view -> addCertificateClick(null));
        initRecyclerview(binding.rv);
        initViewModel(binding.rv);

        return binding.getRoot();
    }

    private void initRecyclerview(RecyclerView rv) {
        if (rv.getAdapter() == null)
            rv.setLayoutManager(new LinearLayoutManager(requireContext()));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.ACTION_STATE_IDLE, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.deleteCertificate(certificationList.get(viewHolder.getAdapterPosition()));
                Snackbar.make(viewHolder.itemView, "Item Deleted!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        itemTouchHelper.attachToRecyclerView(rv);
    }

    private void initViewModel(RecyclerView rv) {
        viewModel = new ViewModelProvider(requireActivity()).get(PersonViewModel.class);
        viewModel.loadCertificates().observe(getViewLifecycleOwner(), certificates -> {

            if (certificates != null){
                certificationList.clear();
                certificationList.addAll(certificates);

                if(rv.getAdapter() ==null )
                    rv.setAdapter(new CertificationAdapter(certificationList, this));

                rv.getAdapter().notifyDataSetChanged();
            }
        });
    }

    void addCertificateClick(Certification certification){

        if (certification == null)
            certification = new Certification();

        popCertificateBinding = PopCertificateBinding.inflate(LayoutInflater.from(getContext()));
        popCertificateBinding.setCertificate(certification);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(popCertificateBinding.getRoot());
        AlertDialog dialog = builder.show();

        popCertificateBinding.cancelButton.setOnClickListener(view -> dialog.dismiss());
        popCertificateBinding.okButton.setOnClickListener(view -> {

            Certification q = popCertificateBinding.getCertificate();

            if (q.getPerson_id() == 0)
                q.setPerson_id(Helper.personID);

            viewModel.addCertificate(q);
            dialog.dismiss();
        });

    }

    @Override
    public void onEditClick(Certification object) {
        addCertificateClick(object);
    }
}
