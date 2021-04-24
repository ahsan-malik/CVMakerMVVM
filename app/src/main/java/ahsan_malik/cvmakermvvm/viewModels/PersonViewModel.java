package ahsan_malik.cvmakermvvm.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import ahsan_malik.cvmakermvvm.database.AppRepo;
import ahsan_malik.cvmakermvvm.models.Certification;
import ahsan_malik.cvmakermvvm.models.Experience;
import ahsan_malik.cvmakermvvm.models.Person;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PersonViewModel extends ViewModel {

    AppRepo mAppRepo;
    public MutableLiveData<Person> livePerson = new MutableLiveData<>();

    Executor executor = Executors.newSingleThreadExecutor();

    @Inject
    public PersonViewModel(AppRepo mAppRepo) {
        this.mAppRepo = mAppRepo;
    }

    public void loadPerson(){
        executor.execute(() -> livePerson.postValue(mAppRepo.loadPerson()));
    }

    public void updatePerson() {
        mAppRepo.updatePerson(livePerson.getValue());
    }

    public void getLast() {
        executor.execute(() -> livePerson.postValue(mAppRepo.getLast()));
    }

    public LiveData<List<Person>> loadAll(){
        return mAppRepo.getAllPerson();
    }

    public void savePerson(Person person) {
        mAppRepo.insertPerson(person);
    }

    public void deletePerson(Person person) {
        mAppRepo.deletePerson(person);
    }

    public LiveData<List<Experience>> loadExperience() {
        return mAppRepo.loadExperienceList();
    }

    public void addExperience(Experience experience) {
        mAppRepo.addExperience(experience);
    }

    public void deleteExperience(Experience experience) {
        mAppRepo.deleteExperience(experience);
    }

    public void deleteCertificate(Certification certification) {
        mAppRepo.deleteCertificate(certification);
    }

    public LiveData<List<Certification>> loadCertificates() {
        return mAppRepo.loadCertificateList();
    }

    public void addCertificate(Certification certification) {
        mAppRepo.addCertificate(certification);
    }
}
