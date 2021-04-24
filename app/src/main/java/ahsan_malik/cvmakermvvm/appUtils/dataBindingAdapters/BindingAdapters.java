package ahsan_malik.cvmakermvvm.appUtils.dataBindingAdapters;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.List;

import ahsan_malik.cvmakermvvm.R;
import ahsan_malik.cvmakermvvm.models.Qualification;
import ahsan_malik.cvmakermvvm.views.adapters.QualificationAdapter;
import ahsan_malik.cvmakermvvm.views.interfaces.EditClickListener;

public class BindingAdapters {

    @BindingAdapter("imageBitmap")
    public static void setImageBitmap(ImageView imageView, Bitmap bitmap){
        Glide.with(imageView.getContext()).load(bitmap).into(imageView);
    }

    @BindingAdapter(value = {"selectedValue", "selectedValueAttrChanged"}, requireAll = false)
    public static void bindSpinnerData(NiceSpinner pAppCompatSpinner, final String newSelectedValue, final InverseBindingListener newTextAttrChanged) {
        //noinspection deprecation
        pAppCompatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(newSelectedValue != null && newSelectedValue.equals(parent.getSelectedItem())){
                    return;
                }
                newTextAttrChanged.onChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        if (newSelectedValue != null) {
            /*int pos = ((ArrayAdapter<String>) pAppCompatSpinner.getAdapter()).getPosition(newSelectedValue);
            pAppCompatSpinner.setSelection(pos, true);*/
            pAppCompatSpinner.setSelectedIndex(Arrays.asList(pAppCompatSpinner.getContext().getResources().getStringArray(R.array.gender_array)).indexOf(newSelectedValue));
        }
    }

    @InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
    public static String captureSelectedValue(NiceSpinner pAppCompatSpinner) {
        return (String) pAppCompatSpinner.getSelectedItem();
    }

    @BindingAdapter({"bindList", "listener"})
    public static void bindRecyclerViewList(RecyclerView recyclerView, List<Qualification> list, EditClickListener<Qualification> editClickListener){

        if (list == null)
            return;

        if (list.isEmpty())
            return;

        if (recyclerView.getLayoutManager() == null)
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        if (recyclerView.getAdapter() == null)
            recyclerView.setAdapter(new QualificationAdapter(recyclerView.getContext(), list, editClickListener));
    }

    @BindingAdapter({"bindAdapter"})
    public static void bindRecyclerView(RecyclerView recyclerView, @SuppressWarnings("rawtypes") RecyclerView.Adapter adapter){

        if (recyclerView.getLayoutManager() == null)
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        if (recyclerView.getAdapter() == null)
            recyclerView.setAdapter(adapter);
    }

}
