package ahsan_malik.cvmakermvvm.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ExperienceDao {

    @Insert(onConflict = REPLACE)
    void insert(Experience experience);

    @Delete
    void delete(Experience experience);

    @Query("SELECT * FROM Experience WHERE person_id = :person_id")
    LiveData<List<Experience>> getWithParentId(int person_id);
}
