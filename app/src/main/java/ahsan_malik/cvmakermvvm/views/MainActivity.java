package ahsan_malik.cvmakermvvm.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;

import ahsan_malik.cvmakermvvm.R;
import ahsan_malik.cvmakermvvm.appUtils.Helper;
import ahsan_malik.cvmakermvvm.databinding.ActivityMainBinding;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setListeners();
    }

    private void setListeners() {
        binding.preview.setOnClickListener(this);
        binding.save.setOnClickListener(this);
        binding.share.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.preview:
                break;
            case R.id.save:
                break;
            case R.id.share:
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Helper.navigateUp(this) || super.onSupportNavigateUp();
        //return Navigation.findNavController(this, R.id.fragment_container).navigateUp() || super.onSupportNavigateUp();
    }
}