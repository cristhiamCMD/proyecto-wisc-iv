package code.cmd.test.wisc.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Cristhiam on 28/09/2015.
 */
public class ActiveUser {
    @DatabaseField(columnName = "_id", id = true, uniqueIndex = true)
    private int _id;

    @DatabaseField(columnName = "userName")
    private String userName;

    @DatabaseField(columnName = "password")
    private String password;

    @DatabaseField(columnName = "image")
    private String image;

    @DatabaseField(columnName = "especialidad")
    private String especialidad;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int get_id() {
        return _id;
    }

    public void set_id() {
        this._id = 1;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
