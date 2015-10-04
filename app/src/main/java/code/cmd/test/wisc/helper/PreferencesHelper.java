package code.cmd.test.wisc.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Cristhiam on 26/09/2015.
 */
public class PreferencesHelper {

    public static final String FIRST_SYNC = "first_sync";
    public static final String IS_SYNC = "is_sync";
    public static final String ACCEPT_CONDITIONS = "accept_conditions";
    private static String name = "TestWiscIV";
    public static final String NULL = "null";

    /**
     * Returns Editor to modify values of SharedPreferences
     * @param context Application context
     * @return
     */
    private static SharedPreferences.Editor getEditor(Context context){
        return getPreferences(context).edit();
    }

    /**
     * Returns SharedPreferences object
     * @param context Application context
     * @return
     */
    private static SharedPreferences getPreferences(Context context){
        return context.getSharedPreferences(name,
                Context.MODE_PRIVATE);
    }

    /**
     * Save a string on SharedPreferences
     * @param tag tag
     * @param value value
     * @param context Application context
     */
    public static void putString(String tag, String value, Context context) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(tag, value);
        editor.commit();
    }

    /**
     * Save an int on SharedPreferences
     * @param tag tag
     * @param value value
     * @param context Application context
     */
    public static void putInt(String tag, int value, Context context) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putInt(tag, value);
        editor.commit();
    }

    /**
     * Save a boolean on SharedPreferences
     * @param tag tag
     * @param value value
     * @param context Application context
     */
    public static void putBoolean(String tag, boolean value, Context context) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putBoolean(tag, value);
        editor.commit();
    }

    /**
     * Get a string value from SharedPreferences
     * @param tag tag
     * @param context Application context
     * @return
     */
    public static String getString(String tag, Context context) {
        SharedPreferences sharedPreferences = getPreferences(context);
        return sharedPreferences.getString(tag, NULL);
    }

    /**
     * Get an int value from SharedPreferences
     * @param tag tag
     * @param context Application context
     * @return
     */
    public static int getInt(String tag, Context context) {
        SharedPreferences sharedPreferences = getPreferences(context);
        return sharedPreferences.getInt(tag, 0);
    }

    /**
     * Get a boolean value from SharedPreferences
     * @param tag tag
     * @param context Application context
     * @return
     */
    public static boolean getBoolean(String tag, Context context) {
        SharedPreferences sharedPreferences = getPreferences(context);
        return sharedPreferences.getBoolean(tag, false);
    }

    /**
     * Clear all SharedPreference data
     * @param context Application context
     */
    public static void clear(Context context){
        SharedPreferences.Editor editor = getEditor(context);
        editor.clear();
        editor.commit();
    }

}

