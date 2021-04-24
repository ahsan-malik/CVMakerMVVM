package ahsan_malik.cvmakermvvm.models;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Person {
    @PrimaryKey(autoGenerate = true)
    int id;
    String firstName;
    String fatherName;
    String gender;
    String email;
    String phone;
    String location;
    Date dateOfBirth;
    Bitmap image;

    @Ignore
    public Person(){}

    public Person(int id, String firstName, String fatherName, String gender, String email, String phone, String location, Date dateOfBirth, Bitmap image) {
        this.id = id;
        this.firstName = firstName;
        this.fatherName = fatherName;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.dateOfBirth = dateOfBirth;
        this.image = image;
    }

    @Ignore
    public Person(String firstName, String fatherName, String gender, String email, String phone, String location, Date dateOfBirth, Bitmap image) {
        this.firstName = firstName;
        this.fatherName = fatherName;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.dateOfBirth = dateOfBirth;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
