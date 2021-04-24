package ahsan_malik.cvmakermvvm.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Person.class, parentColumns = "id", childColumns = "person_id", onDelete = CASCADE))
public class Experience {
    @PrimaryKey(autoGenerate = true)
    int id;
    int person_id;
    String organization, designation, duration;

    @Ignore
    public Experience(){}

    public Experience(int id, int person_id, String organization, String designation, String duration) {
        this.id = id;
        this.person_id = person_id;
        this.organization = organization;
        this.designation = designation;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
