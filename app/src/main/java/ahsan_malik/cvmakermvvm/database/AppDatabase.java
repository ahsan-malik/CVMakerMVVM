package ahsan_malik.cvmakermvvm.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import ahsan_malik.cvmakermvvm.appUtils.DateConverter;
import ahsan_malik.cvmakermvvm.appUtils.ImageConverter;
import ahsan_malik.cvmakermvvm.models.Certification;
import ahsan_malik.cvmakermvvm.models.CertificationDao;
import ahsan_malik.cvmakermvvm.models.Experience;
import ahsan_malik.cvmakermvvm.models.ExperienceDao;
import ahsan_malik.cvmakermvvm.models.Person;
import ahsan_malik.cvmakermvvm.models.PersonDao;
import ahsan_malik.cvmakermvvm.models.Qualification;
import ahsan_malik.cvmakermvvm.models.QualificationDao;

@Database(entities = {Person.class, Qualification.class, Experience.class, Certification.class},
        version = 4, exportSchema = false)
@TypeConverters({DateConverter.class, ImageConverter.class})
public abstract class AppDatabase extends RoomDatabase {

   /* static final String DATABASE_NAME = "cvmakermvvm.db";
    static volatile AppDatabase instance;
    static final Object LOCK = new Object();*/

    abstract PersonDao personDao();
    abstract QualificationDao qualificationDao();
    abstract ExperienceDao experienceDao();
    abstract CertificationDao certificationDao();

    /*public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                            .addMigrations(MIGRATION_1_2)
                            .build();
                }
            }
        }
        return instance;
    }*/

    /*static Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `Qualification` (`id` INTEGER NOT NULL, `person_id` INTEGER NOT NULL, "
                    + "`title` TEXT, `institute` TEXT, `score` TEXT, `year` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        }
    };*/
}
