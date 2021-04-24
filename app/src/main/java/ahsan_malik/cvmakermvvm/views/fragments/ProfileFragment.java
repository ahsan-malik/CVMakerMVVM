package ahsan_malik.cvmakermvvm.views.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.gkemon.XMLtoPDF.PdfGenerator;
import com.gkemon.XMLtoPDF.PdfGeneratorListener;
import com.gkemon.XMLtoPDF.model.FailureResponse;
import com.gkemon.XMLtoPDF.model.SuccessResponse;

import java.io.IOException;
import java.util.Calendar;

import ahsan_malik.cvmakermvvm.appUtils.Helper;
import ahsan_malik.cvmakermvvm.databinding.FragmentProfileBinding;
import ahsan_malik.cvmakermvvm.models.Person;
import ahsan_malik.cvmakermvvm.viewModels.PersonViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    PersonViewModel viewModel;
    Person p;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        initViewModel();

        pickImage();

        binding.setHandler(this);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.updatePerson();
    }

    public void pickDate(){

        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(requireContext(), AlertDialog.THEME_DEVICE_DEFAULT_DARK, (view1, year, monthOfYear, dayOfMonth) -> {
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            p = binding.getPerson();
            p.setDateOfBirth(newDate.getTime());
            viewModel.livePerson.setValue(p);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void pickImage() {
        binding.profileImg.setOnClickListener(view -> ImagePicker.Companion.with(ProfileFragment.this).crop().compress(412).start());
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            assert data != null;
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), imageUri);
                p = binding.getPerson();
                p.setImage(bitmap);
                viewModel.livePerson.setValue(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

   /* private void onBackPress() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                viewModel.updatePerson();
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }*/

    private void initViewModel() {
       /* viewModel = new ViewModelProvider(
                requireActivity(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication())).get(PersonViewModel.class);*/

       viewModel = new ViewModelProvider(requireActivity()).get(PersonViewModel.class);

       viewModel.loadPerson();

        viewModel.livePerson.observe(getViewLifecycleOwner(), person -> {

            if (person != null)
                binding.setPerson(person);

            //Helper.toast(requireContext(), "observing"+person.getId());

        });
    }

    public void pdf(){
        PdfGenerator.getBuilder()
                .setContext(requireContext())
                .fromViewSource()
                .fromView(binding.getRoot())
                .setPageSize(PdfGenerator.PageSize.A4)
                .setFileName("demo-cv")
                .setFolderName("cv-maker/")
                .openPDFafterGeneration(true)
                .build(new PdfGeneratorListener() {
                    @Override
                    public void onStartPDFGeneration() {
                        Helper.toast(requireContext(), "started");
                    }

                    @Override
                    public void onFinishPDFGeneration() {
                        Helper.toast(requireContext(), "finish");
                    }

                    @Override
                    public void onSuccess(SuccessResponse response) {
                        super.onSuccess(response);
                        Helper.toast(requireContext(), "success");
                    }

                    @Override
                    public void onFailure(FailureResponse failureResponse) {
                        super.onFailure(failureResponse);
                        Helper.toast(requireContext(), failureResponse.getErrorMessage());
                    }
                });
    }

}
