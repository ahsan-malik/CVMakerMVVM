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
import ahsan_malik.cvmakermvvm.databinding.FragmentQualificationBinding;
import ahsan_malik.cvmakermvvm.databinding.PopQualificationBinding;
import ahsan_malik.cvmakermvvm.models.Qualification;
import ahsan_malik.cvmakermvvm.viewModels.QualificationViewModel;

import ahsan_malik.cvmakermvvm.views.adapters.QualificationAdapter;
import ahsan_malik.cvmakermvvm.views.interfaces.EditClickListener;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QualificationFragment extends Fragment implements EditClickListener<Qualification> {

    FragmentQualificationBinding binding;
    QualificationViewModel viewModel;

    List<Qualification> qualificationList = new ArrayList<>();

    PopQualificationBinding popQualificationBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentQualificationBinding.inflate(inflater, container, false);

        initViewModel();
        initRecyclerView();

        binding.addButton.setOnClickListener(view -> addQualificationClick(null));

        return binding.getRoot();
    }



    private void initViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(QualificationViewModel.class);
        viewModel.loadQualification();
        viewModel.qualificationList.observe(getViewLifecycleOwner(), qualifications -> {

            if (qualifications != null) {

                qualificationList.clear();
                qualificationList.addAll(qualifications);

                if (binding.rv.getAdapter() == null)
                    binding.rv.setAdapter(new QualificationAdapter(requireContext(), qualificationList, QualificationFragment.this));
                else
                    binding.rv.getAdapter().notifyDataSetChanged();

                Helper.toast(requireContext(), "ob2 " + Helper.personID);
            }

        });
    }

    public void addQualificationClick(Qualification qualification){

        if (qualification == null)
            qualification = new Qualification();

        popQualificationBinding = PopQualificationBinding.inflate(LayoutInflater.from(getContext()));
        popQualificationBinding.setQualification(qualification);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(popQualificationBinding.getRoot());
        AlertDialog dialog = builder.show();

        popQualificationBinding.cancelButton.setOnClickListener(view -> dialog.dismiss());
        popQualificationBinding.okButton.setOnClickListener(view -> {

            Qualification q = popQualificationBinding.getQualification();

            if (q.getPerson_id() == 0)
                q.setPerson_id(Helper.personID);

            viewModel.addQualification(q);
            dialog.dismiss();
        });

    }

    void initRecyclerView(){

        if (binding.rv.getLayoutManager() == null)
            binding.rv.setLayoutManager(new LinearLayoutManager(requireContext()));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.ACTION_STATE_IDLE, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.deleteQualification(qualificationList.get(viewHolder.getAdapterPosition()));
                Snackbar.make(viewHolder.itemView, "Item Deleted!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        itemTouchHelper.attachToRecyclerView(binding.rv);
    }

    @Override
    public void onEditClick(Qualification object) {
        addQualificationClick(object);
    }
}
