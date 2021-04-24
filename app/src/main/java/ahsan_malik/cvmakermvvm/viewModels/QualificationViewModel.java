package ahsan_malik.cvmakermvvm.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import ahsan_malik.cvmakermvvm.appUtils.Helper;
import ahsan_malik.cvmakermvvm.database.AppRepo;
import ahsan_malik.cvmakermvvm.models.Qualification;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class QualificationViewModel extends ViewModel {

    AppRepo appRepo;
    public LiveData<List<Qualification>> qualificationList;

    @Inject
    public QualificationViewModel(AppRepo appRepo) {
        this.appRepo = appRepo;
    }

    public void loadQualification(){
        qualificationList = appRepo.loadQualificationList(Helper.personID);
    }

    public void addQualification(Qualification qualification){
        appRepo.addQualification(qualification);
    }

    public void deleteQualification(Qualification qualification){
        appRepo.deleteQualification(qualification);
    }

   /* public LiveData<List<Qualification>> getQualificationList() {
        if (qualificationList == null)
        return qList;
    }*/
}
