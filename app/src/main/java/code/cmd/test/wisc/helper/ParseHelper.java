package code.cmd.test.wisc.helper;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import code.cmd.test.wisc.model.ActiveUser;
import code.cmd.test.wisc.model.Psychologist;
import code.cmd.test.wisc.ui.MainActivity;

/**
 * Created by Cristhiam on 27/09/2015.
 */
public class ParseHelper {
    private Context context;

    private static ParseHelper parseHelperInstance;

    public boolean swRegisterPsychologist;

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
//        ParseObject psychologistParse = new ParseObject("Psychologist");
//        psychologistParse.put("psychologistId", psychologist.getPsychologistId());
//        psychologistParse.put("firstName", psychologist.getFirstName());
//        psychologistParse.put("lastName", psychologist.getLastName());
//        psychologistParse.put("sex",psychologist.getSex());
//        psychologistParse.put("user", psychologist.getUser());
//        psychologistParse.put("password", psychologist.getPassword());
//        psychologistParse.put("specialty", psychologist.getSpecialty());
//        psychologistParse.saveInBackground();
        ParseUser user = new ParseUser();
        user.setUsername(psychologist.getUser());
        user.setPassword(psychologist.getPassword());
//        user.setEmail("email@example.com");


// other fields can be set just like with ParseObject
        user.put("firstName", psychologist.getFirstName());
        user.put("lastName", psychologist.getLastName());
        user.put("sex", psychologist.getSex());
        user.put("specialty", psychologist.getSpecialty());

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(context, "Registro Satisfactoriamente",Toast.LENGTH_LONG);
                } else {
                    Toast.makeText(context, "No se pudo Registrar. Intentelo Mas Tarde",Toast.LENGTH_LONG);
                }
            }
        });
//        if (swRegister) {
//            Bitmap userBitmap = bitmap;
//            // Convert it to byte
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            // Compress image to lower quality scale 1 - 100
//            userBitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
//            byte[] image = stream.toByteArray();
//
//            // Create the ParseFile
//            ParseFile file = new ParseFile("androidbegin.png", image);
//            // Upload the image into Parse Cloud
//            file.saveInBackground();
//            ParseObject imgupload = new ParseObject("UserImagePsychologist");
//            // Create a column named "ImageName" and set the string
//            imgupload.put("user", psychologist.getUser());
//            imgupload.put("userImage", file);
//            imgupload.saveInBackground();
//        }
    }

    public void addActiveUserParse(ActiveUser activeUser) {
        ParseObject activeUserParse = new ParseObject("ActiveUser");
        activeUserParse.put("userName", activeUser.getUserName());
        activeUserParse.put("active", true);
        activeUserParse.put("idActiveUser", activeUser.get_id());
        activeUserParse.saveInBackground();
    }

    public void clear() {
        parseHelperInstance = null;
    }
}
