package code.cmd.test.wisc.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Cristhiam on 26/09/2015.
 */
public class Psychologist {

    @DatabaseField(columnName = "psychologistId", generatedId = true)
    private int psychologistId;

    @DatabaseField(columnName = "firstName")
    private String firstName;

    @DatabaseField(columnName = "lastName")
    private String lastName;

    @DatabaseField(columnName = "sex")
    private String sex;

    @DatabaseField(columnName = "user")
    private String user;

    @DatabaseField(columnName = "password")
    private String password;

    @DatabaseField(columnName = "specialty")
    private String specialty;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sexo) {
        this.sex = sexo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getPsychologistId() {
        return psychologistId;
    }

    public void setPsychologistId(int psychologistId) {
        this.psychologistId = psychologistId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
