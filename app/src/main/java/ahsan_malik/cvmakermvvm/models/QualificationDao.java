package ahsan_malik.cvmakermvvm.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QualificationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Qualification qualification);

    @Delete
    void delete(Qualification qualification);

    @Query("SELECT * FROM Qualification WHERE id= :id")
    Qualification getQualificationById(int id);

    @Query("SELECT * FROM Qualification WHERE person_id= :personId ORDER BY year DESC")
    LiveData<List<Qualification>> getQualificationList(int personId);

    @Query("SELECT COUNT(*) FROM Qualification")
    int getCount();
}
