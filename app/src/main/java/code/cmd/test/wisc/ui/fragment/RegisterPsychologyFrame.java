package code.cmd.test.wisc.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rey.material.app.BottomSheetDialog;
import com.rey.material.widget.CompoundButton;
import com.rey.material.widget.RadioButton;

import code.cmd.test.wisc.R;
import code.cmd.test.wisc.helper.ParseHelper;
import code.cmd.test.wisc.model.Psychologist;
import code.cmd.test.wisc.model.dao.PsychologistDao;
import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterPsychologyFrame extends Fragment implements View.OnClickListener {

    TextInputLayout tilFirstName;
    TextInputLayout tilLastName;
    TextInputLayout tilUserName;
    TextInputLayout tilPassword;
    TextInputLayout tilPasswordRepeat;
    TextInputLayout tilSpecialtytName;
    FloatingActionButton fabAccept;

    CircleImageView civPruebaPassrowd;

    RadioButton rbSexMasculino;
    RadioButton rbSexFemenino;

    PsychologistDao psychologistDao;

//    ParseHelper parseHelper;


    private BottomSheetDialog mBottomSheetDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.register_psychologist_frame, container, false);

//        parseHelper = new ParseHelper(getContext());

        psychologistDao = new PsychologistDao(getContext());

        tilFirstName = (TextInputLayout) rootView.findViewById(R.id.tilFirstName);
        tilLastName = (TextInputLayout) rootView.findViewById(R.id.tilULastName);
        tilUserName = (TextInputLayout) rootView.findViewById(R.id.tilUserName);
        tilPassword = (TextInputLayout) rootView.findViewById(R.id.tilPassword);
        tilPasswordRepeat = (TextInputLayout) rootView.findViewById(R.id.tilPasswordRepeat);
        tilSpecialtytName = (TextInputLayout) rootView.findViewById(R.id.tilSpecialtyName);

        fabAccept = (FloatingActionButton) rootView.findViewById(R.id.fabAccept);

        civPruebaPassrowd=(CircleImageView)rootView.findViewById(R.id.profile_image);
        civPruebaPassrowd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rbSexMasculino = (RadioButton) rootView.findViewById(R.id.rbSexMasculino);
        rbSexFemenino = (RadioButton) rootView.findViewById(R.id.rbSexFemenino);

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(android.widget.CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rbSexMasculino.setChecked(rbSexMasculino == buttonView);
                    rbSexFemenino.setChecked(rbSexFemenino == buttonView);
                }
            }
        };

        rbSexMasculino.setOnCheckedChangeListener(listener);
        rbSexFemenino.setOnCheckedChangeListener(listener);

        rootView.findViewById(R.id.fabAccept).setOnClickListener(this);


        return rootView;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabAccept:

                Psychologist psychologist = new Psychologist();
                psychologist.setFirstName(tilFirstName.getEditText().getText().toString());
                psychologist.setLastName(tilLastName.getEditText().getText().toString());
                psychologist.setUser(tilUserName.getEditText().getText().toString());
                psychologist.setPassword(tilPassword.getEditText().getText().toString());
                psychologist.setSpecialty(tilSpecialtytName.getEditText().getText().toString());
                if (rbSexMasculino.isChecked()) {
                    psychologist.setSex("" + R.string.sexM);
                } else {
                    psychologist.setSex("" + R.string.sexF);
                }

                if ((tilPassword.getEditText().getText().toString()).equals(tilPasswordRepeat.getEditText().getText().toString())) {
//Llenado de la Base de datos ORMLITE
                    psychologistDao.AgregarContanto(psychologist);
//Llenado de la base de datos PARSE
                    ParseHelper.getInstance().addPsychologistParse(psychologist);
                    Snackbar.make(v, "Registro Satisfactorio", Snackbar.LENGTH_SHORT).show();

                } else {
                    tilPassword.getEditText().setText("");
                    tilPasswordRepeat.getEditText().setText("");
                    Snackbar.make(v, "Las contraseñas no coinciden", Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
