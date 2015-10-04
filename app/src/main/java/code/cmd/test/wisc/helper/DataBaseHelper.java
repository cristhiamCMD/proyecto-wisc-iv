package code.cmd.test.wisc.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import code.cmd.test.wisc.model.ActiveUser;
import code.cmd.test.wisc.model.Psychologist;

/**
 * Created by Cristhiam on 26/09/2015.
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "AplicacionTestWiscIV.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Psychologist, Integer> psychologistsDao = null;
    private Dao<ActiveUser, Integer> activeUserDao = null;


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Psychologist.class);
            TableUtils.createTableIfNotExists(connectionSource, ActiveUser.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, Psychologist.class, true);
            TableUtils.dropTable(connectionSource, ActiveUser.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {

        }
    }

    @Override
    public void close() {
        super.close();
        psychologistsDao = null;
        activeUserDao=null;
    }

    public Dao<Psychologist, Integer> getPsychologistsDao() throws SQLException {
        if (psychologistsDao == null) {
            psychologistsDao = getDao(Psychologist.class);
        }
        return psychologistsDao;
    }

    public Dao<ActiveUser, Integer> getActiveUserDao() throws SQLException {
        if (activeUserDao == null) {
            activeUserDao = getDao(ActiveUser.class);
        }
        return activeUserDao;
    }
}
