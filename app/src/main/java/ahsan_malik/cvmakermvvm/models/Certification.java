package ahsan_malik.cvmakermvvm.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Person.class, parentColumns = "id", childColumns = "person_id", onDelete = CASCADE))
public class Certification {

    @PrimaryKey(autoGenerate = true)
    int id;
    int person_id;
    String organization, title, duration;

    @Ignore
    public Certification(){}

    public Certification(int id, int person_id, String organization, String title, String duration) {
        this.id = id;
        this.person_id = person_id;
        this.organization = organization;
        this.title = title;
        this.duration = duration;
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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
