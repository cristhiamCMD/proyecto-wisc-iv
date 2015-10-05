package code.cmd.test.wisc.model.dao;

import android.content.Context;
import android.util.Log;

import java.sql.SQLException;
import java.util.List;

import code.cmd.test.wisc.helper.DataBaseManager;
import code.cmd.test.wisc.model.Psychologist;

/**
 * Created by Cristhiam on 27/09/2015.
 */
public class PsychologistDao extends DataBaseManager {
//    public PsychologistDao(Context context) {
//        super(context);
//    }
//
//    public void AgregarContanto(Psychologist obj) {
//        try {
//            this.getHelper().getPsychologistsDao().create(obj);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Psychologist BuscarPorId(int Id) {
//        Psychologist objBuscar = null;
//        try {
//            objBuscar = this.getHelper().getPsychologistsDao().queryForId(Id);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return objBuscar;
//    }
//    public Psychologist BuscarPorIdUser(String user) {
//        Psychologist objBuscar = null;
//        try {
//            objBuscar = this.getHelper().getPsychologistsDao().queryBuilder().where().eq("user",user).queryForFirst();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return objBuscar;
//    }
//
//    public List<Psychologist> ObtenerPsycologistPorNombre(String buscado) {
//        List<Psychologist> buscados = null;
//        try {
//            buscados = this.getHelper().getPsychologistsDao().queryBuilder().where().eq("firstName", buscado).query();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return buscados;
//    }
//
//    public List<Psychologist> AllPsycologist() {
//        List<Psychologist> buscados = null;
//        try {
//            buscados = this.getHelper().getPsychologistsDao().queryForAll();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return buscados;
//    }
//
//    public void deletePsychologist(int psichologistId) {
//        try {
//            this.getHelper().getPsychologistsDao().deleteById(psichologistId);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void updatePsychologist(Psychologist obj) {
//        try {
//            this.getHelper().getPsychologistsDao().update(obj);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public boolean Nombre(String user, String pass) {
//        List<Psychologist> psychologist = null;
//        boolean sw = false;
//        try {
//            psychologist = this.getHelper().getPsychologistsDao().queryBuilder().where().eq("user", user).and().eq("password", pass).query();
//            if (psychologist.size() > 0) {
//                sw = true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return sw;
//    }
}
