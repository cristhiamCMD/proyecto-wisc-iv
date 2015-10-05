package code.cmd.test.wisc.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import code.cmd.test.wisc.R;
import code.cmd.test.wisc.model.ActiveUser;
import code.cmd.test.wisc.model.dao.ActiveUserDao;
import code.cmd.test.wisc.model.dao.PsychologistDao;
import code.cmd.test.wisc.ui.MainActivity;

public class UserLoginFragment extends Fragment implements View.OnClickListener {

    TextInputLayout tilUserNameLogin;
    TextInputLayout tilPasswordLogin;

    TextView tvUser;

    FloatingActionButton fabAcceptLogin;

    ActiveUserDao activeUserDao;
    PsychologistDao psychologistDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_login, container, false);

//        activeUserDao = new ActiveUserDao(getContext());
//        psychologistDao = new PsychologistDao(getContext());

        tilUserNameLogin = (TextInputLayout) rootView.findViewById(R.id.tilUserNameLogin);
        tilPasswordLogin = (TextInputLayout) rootView.findViewById(R.id.tilPasswordLogin);
        tvUser = (TextView) rootView.findViewById(R.id.tvNameLogin);

        fabAcceptLogin = (FloatingActionButton) rootView.findViewById(R.id.fabIniciarLogin);

        rootView.findViewById(R.id.fabIniciarLogin).setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabIniciarLogin:

                ActiveUser activeUser = new ActiveUser();
                activeUser.set_id();
                activeUser.setUserName(tilUserNameLogin.getEditText().getText().toString());
                activeUser.setPassword(tilPasswordLogin.getEditText().getText().toString());

//                if (psychologistDao.Nombre(tilUserNameLogin.getEditText().getText().toString(), tilPasswordLogin.getEditText().getText().toString())) {
//                    activeUserDao.AgregarContanto(activeUser);
//                    Psychologist psychologist = psychologistDao.BuscarPorIdUser(tilUserNameLogin.getEditText().getText().toString());
//                    Log.d("UserLoginFragment","user"+psychologist.getUser());
//                    Log.d("UserLoginFragment","image"+psychologist.getImage());
//
//                    ParseHelper.getInstance().addActiveUserParse(activeUser);
//                    Snackbar.make(v, "Los datos son correctos", Snackbar.LENGTH_SHORT).show();
//                    Intent I=new Intent(getContext(),MainActivity.class);
//                    startActivity(I);
//                } else {
//                }

                ParseUser.logInInBackground(tilUserNameLogin.getEditText().getText().toString(), tilPasswordLogin.getEditText().getText().toString(), new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            // Hooray! The user is logged in.
                            Intent I = new Intent(getContext(), MainActivity.class);
                            I.putExtra("LoginOk","LoginSi");
                            startActivityForResult(I, 1);
                            Toast.makeText(getContext(), "Inicio sesion correctamente", Toast.LENGTH_LONG);
                        } else {
                            // Signup failed. Look at the ParseException to see what happened.
                            tilUserNameLogin.getEditText().setText("");
                            tilPasswordLogin.getEditText().setText("");
                            Toast.makeText(getContext(), "Usuario o Password incorrectos", Toast.LENGTH_LONG);
//                            Snackbar.make(v, "Los datos son incorrectos", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });

//Llenado de la Base de datos ORMLITE
//Llenado de la base de datos PARSE
//                parseHelper.addPsychologistParse(psychologist);
                break;
        }
    }
}
