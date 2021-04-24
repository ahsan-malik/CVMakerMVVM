package ahsan_malik.cvmakermvvm.appUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import ahsan_malik.cvmakermvvm.R;

public class Helper {

    public static volatile int personID;

    public static void fragmentTransaction(Fragment current, Fragment target, boolean addToBackStack){
            FragmentTransaction transaction = current.requireActivity().getSupportFragmentManager().beginTransaction();
        if (addToBackStack)
            transaction.replace(R.id.fragment_container, target).addToBackStack(null).commit();
        else
            transaction.replace(R.id.fragment_container, target).commit();
    }
    @SuppressLint("SimpleDateFormat")
    public static String getDateFormatted(Date date){

        return date != null ? new SimpleDateFormat("dd/MM/yyyy").format(date) : null;
    }

    public static void toast(Context requireContext, String observing) {
        Toast.makeText(requireContext, observing, Toast.LENGTH_LONG).show();
    }

    public static boolean navigateUp(AppCompatActivity activity) {
        return (
                (NavHostFragment) Objects.requireNonNull(activity.getSupportFragmentManager().findFragmentById(R.id.fragment_container))
        ).getNavController().navigateUp();
    }

    public static void navigate(Fragment fragment, NavDirections navDirections) {
        NavHostFragment.findNavController(fragment).navigate(navDirections);
    }

    public static void navigate(Fragment fragment, int actionId) {
        NavHostFragment.findNavController(fragment).navigate(actionId);
    }
}
