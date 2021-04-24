package ahsan_malik.cvmakermvvm.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Person.class,
        parentColumns = "id",
        childColumns = "person_id",
        onDelete = CASCADE))
public class Qualification {
    @PrimaryKey(autoGenerate = true)
    int id;
    int person_id;
    String title, institute, score;
    int year;

    @Ignore
    public Qualification() {
    }

    public Qualification(int id, int person_id, String title, String institute, String score, int year) {
        this.id = id;
        this.person_id = person_id;
        this.title = title;
        this.institute = institute;
        this.score = score;
        this.year = year;
    }

    @Ignore
    public Qualification(String title, String institute, String score, int year) {
        this.title = title;
        this.institute = institute;
        this.score = score;
        this.year = year;
    }

    @Ignore
    public Qualification(int person_id, String title, String institute, String score, int year) {
        this.person_id = person_id;
        this.title = title;
        this.institute = institute;
        this.score = score;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
