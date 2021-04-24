package ahsan_malik.cvmakermvvm.views.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import ahsan_malik.cvmakermvvm.R;
import ahsan_malik.cvmakermvvm.appUtils.Helper;
import ahsan_malik.cvmakermvvm.databinding.FragmentCvlistBinding;
import ahsan_malik.cvmakermvvm.models.Person;
import ahsan_malik.cvmakermvvm.viewModels.PersonViewModel;
import ahsan_malik.cvmakermvvm.views.adapters.CvlistAdapter;
import ahsan_malik.cvmakermvvm.views.interfaces.EditClickListener;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CvlistFragment extends Fragment implements EditClickListener<Void> {

    PersonViewModel viewModel;
    FragmentCvlistBinding binding;

    List<Person> personList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentCvlistBinding.inflate(inflater, container, false);

        requireActivity().findViewById(R.id.fab_menu).setVisibility(View.GONE);

        Helper.personID = 0;

        initRecycerView(binding.recyclerview);
        initViewModel();

        binding.addNew.setOnClickListener(view -> addNewCv());

        return binding.getRoot();
    }

    private void addNewCv() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Create New CV");

        EditText editText = new EditText(requireContext());
        editText.setHint("Enter Your Name");
        editText.setTextColor(getResources().getColor(R.color.black));

        builder.setView(editText);

        builder.setPositiveButton("NEXT", (dialogInterface, i) -> {
            if (!TextUtils.isEmpty(editText.getText().toString().trim())) {
                Person person = new Person();
                person.setFirstName(editText.getText().toString().trim());
                viewModel.savePerson(person);
                Helper.navigate(this, R.id.action_cvlistFragment_to_mainFragment);
            }
            dialogInterface.dismiss();
        });

        builder.setNegativeButton("CANCEL", (dialogInterface, i) -> dialogInterface.dismiss());

        builder.show();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(PersonViewModel.class);
        viewModel.loadAll().observe(getViewLifecycleOwner(), people -> {

            if (people != null) {
                personList.clear();
                personList.addAll(people);
                if (binding.recyclerview.getAdapter() == null)
                    binding.recyclerview.setAdapter(new CvlistAdapter(personList, CvlistFragment.this));
                binding.recyclerview.getAdapter().notifyDataSetChanged();
            }
        });
    }

    private void initRecycerView(RecyclerView recyclerview) {
        if (recyclerview.getLayoutManager() == null)
            recyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.ACTION_STATE_IDLE, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.deletePerson(personList.get(viewHolder.getAdapterPosition()));
                Snackbar.make(viewHolder.itemView, "Item Deleted!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        itemTouchHelper.attachToRecyclerView(binding.recyclerview);
    }

    @Override
    public void onEditClick(Void object) {
        Helper.navigate(this, CvlistFragmentDirections.actionCvlistFragmentToMainFragment());
    }
}
