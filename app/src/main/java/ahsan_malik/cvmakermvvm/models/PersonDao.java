package ahsan_malik.cvmakermvvm.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Person person);

    @Update
    void update(Person person);

    @Delete
    void delete(Person person);

    @Query("SELECT * FROM Person WHERE id = :id LIMIT 1")
    Person getPersonById(int id);

    @Query("SELECT * FROM Person LIMIT 1")
    Person getTop();

    @Query("SELECT * FROM Person WHERE id = (SELECT MAX(id) FROM Person) LIMIT 1")
    Person getLast();

    @Query("SELECT COUNT(*) FROM Person")
    int getCount();

    @Query("SELECT id, firstName FROM Person ORDER BY id")
    LiveData<List<Person>> getAll();
}
