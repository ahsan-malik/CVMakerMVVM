package ahsan_malik.cvmakermvvm.database;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import ahsan_malik.cvmakermvvm.appUtils.Helper;
import ahsan_malik.cvmakermvvm.models.Certification;
import ahsan_malik.cvmakermvvm.models.Experience;
import ahsan_malik.cvmakermvvm.models.Person;
import ahsan_malik.cvmakermvvm.models.Qualification;

public class AppRepo {

    //static AppRepo instance;

    public AppDatabase appDataBase;
    private Executor executor = Executors.newSingleThreadExecutor();

    @Inject
    public AppRepo(AppDatabase appDataBase) {
        this.appDataBase = appDataBase;
    }


    /*public static AppRepo getInstance(Context context) {
        return instance == null? new AppRepo(context) : instance;
    }

    AppRepo(Context context){
        appDataBase = AppDatabase.getInstance(context);
    }*/

    public void insertPerson(final Person person) {
        if (person != null)
            executor.execute(() -> Helper.personID =  (int) appDataBase.personDao().insert(person));
    }

    public Person loadPerson() {
        return appDataBase.personDao().getPersonById(Helper.personID);
    }

    public LiveData<List<Person>> getAllPerson(){ return appDataBase.personDao().getAll(); }

    public LiveData<List<Qualification>> loadQualificationList(int personId) {
        return appDataBase.qualificationDao().getQualificationList(personId);
    }

    public void addQualification(Qualification qualification) {
        executor.execute(() -> appDataBase.qualificationDao().insert(qualification));
    }

    public void deleteQualification(Qualification qualification) {
        executor.execute(() -> appDataBase.qualificationDao().delete(qualification));
    }

    public Person getLast() {
        Person person = appDataBase.personDao().getLast();
        Helper.personID = person.getId();
        return person;
    }

    public void deletePerson(Person person) {
        executor.execute(() -> appDataBase.personDao().delete(person));
    }

    public void updatePerson(Person value) {
        executor.execute(() -> appDataBase.personDao().update(value));
    }

    public LiveData<List<Experience>> loadExperienceList() {
        return appDataBase.experienceDao().getWithParentId(Helper.personID);
    }

    public void addExperience(Experience experience) {
        executor.execute(() -> appDataBase.experienceDao().insert(experience));
    }

    public void deleteExperience(Experience experience) {
        executor.execute(() -> appDataBase.experienceDao().delete(experience));
    }

    public void deleteCertificate(Certification certification) {
        executor.execute(() -> appDataBase.certificationDao().delete(certification));
    }

    public LiveData<List<Certification>> loadCertificateList() {
        return appDataBase.certificationDao().getWithParentId(Helper.personID);
    }

    public void addCertificate(Certification certification) {
        executor.execute(() -> appDataBase.certificationDao().insert(certification));
    }
}

