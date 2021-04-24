package ahsan_malik.cvmakermvvm.di;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import javax.inject.Singleton;

import ahsan_malik.cvmakermvvm.database.AppDatabase;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public static AppDatabase provideAppDatabase(Application application){
        return Room.databaseBuilder(application, AppDatabase.class, "cvmakermvvm.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    static Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `Qualification` (`id` INTEGER NOT NULL, `person_id` INTEGER NOT NULL, "
                    + "`title` TEXT, `institute` TEXT, `score` TEXT, `year` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        }
    };
}
