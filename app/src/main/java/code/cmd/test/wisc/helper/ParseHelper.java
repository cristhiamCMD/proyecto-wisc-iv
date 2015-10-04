package code.cmd.test.wisc.helper;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParseObject;

import code.cmd.test.wisc.model.ActiveUser;
import code.cmd.test.wisc.model.Psychologist;

/**
 * Created by Cristhiam on 27/09/2015.
 */
public class ParseHelper {
    private Context context;

    private static ParseHelper parseHelperInstance;

    public static ParseHelper getInstance() {
        if (parseHelperInstance == null) {
            parseHelperInstance = new ParseHelper();
        }
        return parseHelperInstance;
    }

    public ParseHelper() {
    }

    public void setContext(Context cont) {
        this.context = cont;
        Parse.enableLocalDatastore(context);
        Parse.initialize(context, "SDBHFviKGIpaR4rrUOeV9SrJOmOuZdpeXnk0hN7z", "R6kj17nEIDIkozCWg7jXfnXJgxsXqGmL6ppwfYeq");
    }

    public void addPsychologistParse(Psychologist psychologist) {
        ParseObject psychologistParse = new ParseObject("Psychologist");
        psychologistParse.put("psychologistId", psychologist.getPsychologistId());
        psychologistParse.put("firstName", psychologist.getFirstName());
        psychologistParse.put("lastName", psychologist.getLastName());
        psychologistParse.put("sex",psychologist.getSex());
        psychologistParse.put("user", psychologist.getUser());
        psychologistParse.put("password", psychologist.getPassword());
        psychologistParse.put("specialty", psychologist.getSpecialty());
        psychologistParse.saveInBackground();
    }

    public void addActiveUserParse(ActiveUser activeUser) {
        ParseObject activeUserParse = new ParseObject("ActiveUser");
        activeUserParse.put("userName", activeUser.getUserName());
        activeUserParse.put("active", true);
        activeUserParse.put("idActiveUser", activeUser.get_id());
        activeUserParse.saveInBackground();
    }
    public void clear(){
        parseHelperInstance=null;
    }
}
