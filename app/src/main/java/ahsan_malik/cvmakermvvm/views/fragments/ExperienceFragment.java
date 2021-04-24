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
import ahsan_malik.cvmakermvvm.databinding.FragmentExperienceBinding;
import ahsan_malik.cvmakermvvm.databinding.PopExperienceBinding;
import ahsan_malik.cvmakermvvm.models.Experience;
import ahsan_malik.cvmakermvvm.viewModels.PersonViewModel;
import ahsan_malik.cvmakermvvm.views.adapters.ExperienceAdapter;
import ahsan_malik.cvmakermvvm.views.interfaces.EditClickListener;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ExperienceFragment extends Fragment implements EditClickListener<Experience> {

    FragmentExperienceBinding binding;
    public PersonViewModel viewModel;
    List<Experience> experienceList = new ArrayList<>();
    PopExperienceBinding popExperienceBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentExperienceBinding.inflate(inflater, container, false);

        binding.addButton.setOnClickListener(view -> addExperienceClick(null));
        initRecyclerView(binding.rv);
        initViewModel();

        return binding.getRoot();
    }

    private void initRecyclerView(RecyclerView rv) {
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
                viewModel.deleteExperience(experienceList.get(viewHolder.getAdapterPosition()));
                Snackbar.make(viewHolder.itemView, "Item Deleted!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        itemTouchHelper.attachToRecyclerView(binding.rv);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(PersonViewModel.class);
        viewModel.loadExperience().observe(getViewLifecycleOwner(), experiences -> {

            if (experiences != null){
                experienceList.clear();
                experienceList.addAll(experiences);

                if(binding.rv.getAdapter() ==null )
                    binding.rv.setAdapter(new ExperienceAdapter(experienceList, this));

                binding.rv.getAdapter().notifyDataSetChanged();
            }
        });
    }

    void addExperienceClick(Experience experience){

        if (experience == null)
            experience = new Experience();

        popExperienceBinding = PopExperienceBinding.inflate(LayoutInflater.from(getContext()));
        popExperienceBinding.setExperience(experience);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(popExperienceBinding.getRoot());
        AlertDialog dialog = builder.show();

        popExperienceBinding.cancelButton.setOnClickListener(view -> dialog.dismiss());
        popExperienceBinding.okButton.setOnClickListener(view -> {

            Experience q = popExperienceBinding.getExperience();

            if (q.getPerson_id() == 0)
                q.setPerson_id(Helper.personID);

            viewModel.addExperience(q);
            dialog.dismiss();
        });

    }

    @Override
    public void onEditClick(Experience object) {
        addExperienceClick(object);
    }
}
