package ahsan_malik.cvmakermvvm.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ahsan_malik.cvmakermvvm.R;
import ahsan_malik.cvmakermvvm.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    FragmentMainBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentMainBinding.inflate(inflater, container, false);
        binding.setHandler(this);

        requireActivity().findViewById(R.id.fab_menu).setVisibility(View.VISIBLE);

        return binding.getRoot();
    }

}
