package ahsan_malik.cvmakermvvm.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface CertificationDao {
    @Insert(onConflict = REPLACE)
    void insert(Certification certification);

    @Delete
    void delete(Certification certification);

    @Query("SELECT * FROM Certification WHERE person_id = :person_id")
    LiveData<List<Certification>> getWithParentId(int person_id);
}
