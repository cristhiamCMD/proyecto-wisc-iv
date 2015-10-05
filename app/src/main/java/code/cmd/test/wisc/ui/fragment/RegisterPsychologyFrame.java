package code.cmd.test.wisc.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.rey.material.widget.CompoundButton;
import com.rey.material.widget.RadioButton;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import code.cmd.test.wisc.R;
import code.cmd.test.wisc.helper.ParseHelper;
import code.cmd.test.wisc.model.Psychologist;
import code.cmd.test.wisc.model.dao.PsychologistDao;
import code.cmd.test.wisc.ui.MainActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterPsychologyFrame extends Fragment implements View.OnClickListener {

    TextInputLayout tilFirstName;
    TextInputLayout tilLastName;
    TextInputLayout tilUserName;
    TextInputLayout tilPassword;
    TextInputLayout tilPasswordRepeat;
    TextInputLayout tilSpecialtytName;
    FloatingActionButton fabAccept;

    CircleImageView civProfileUser;

    RadioButton rbSexMasculino;
    RadioButton rbSexFemenino;

    PsychologistDao psychologistDao;

    //camara
    private String APP_DIRECTORY = "appWiscIV/";
    private String MEDIA_DIRECTORY = APP_DIRECTORY + "gallerypsy";
    private String TEMPORAL_PICTURE_NAME;

    private final int PHOTO_CODE = 100;
    private final int SELECT_PICTURE = 200;

    Bitmap userImage;

    String date;
    String path = "";
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.register_psychologist_frame, container, false);
//        psychologistDao = new PsychologistDao(getContext());

        tilFirstName = (TextInputLayout) rootView.findViewById(R.id.tilFirstName);
        tilLastName = (TextInputLayout) rootView.findViewById(R.id.tilULastName);
        tilUserName = (TextInputLayout) rootView.findViewById(R.id.tilUserName);
        tilPassword = (TextInputLayout) rootView.findViewById(R.id.tilPassword);
        tilPasswordRepeat = (TextInputLayout) rootView.findViewById(R.id.tilPasswordRepeat);
        tilSpecialtytName = (TextInputLayout) rootView.findViewById(R.id.tilSpecialtyName);

        fabAccept = (FloatingActionButton) rootView.findViewById(R.id.fabAccept);

        civProfileUser = (CircleImageView) rootView.findViewById(R.id.profile_image);

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
//camera


        civProfileUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
                date = dateFormat.format(new Date());
                TEMPORAL_PICTURE_NAME = date + "_imageuser.png";
                final CharSequence[] options = {"Tomar foto", "Elegir de galeria", "Cancelar"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(rootView.getContext());
                builder.setTitle("Elige una opcion :D");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int seleccion) {
                        if (options[seleccion] == "Tomar foto") {
                            openCamera();
                        } else if (options[seleccion] == "Elegir de galeria") {
                            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"), SELECT_PICTURE);
                        } else if (options[seleccion] == "Cancelar") {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });
        return rootView;
    }

    private void openCamera() {

        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        file.mkdirs();

        path = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;

        File newFile = new File(path);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
        startActivityForResult(intent, PHOTO_CODE);
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
                psychologist.setImage(path);
                if (rbSexMasculino.isChecked()) {
                    psychologist.setSex("Masculino");
                } else {
                    psychologist.setSex("Femenino");
                }

                if ((tilPassword.getEditText().getText().toString()).equals(tilPasswordRepeat.getEditText().getText().toString())) {
//Llenado de la Base de datos ORMLITE
//                    psychologistDao.AgregarContanto(psychologist);
//Llenado de la base de datos PARSE
                    ParseHelper.getInstance().addPsychologistParse(psychologist);

                    Intent I = new Intent(getContext(), MainActivity.class);
                    I.putExtra("RegisterOk","si");
                    startActivityForResult(I, 1);

                } else {
                    tilPassword.getEditText().setText("");
                    tilPasswordRepeat.getEditText().setText("");
                    Snackbar.make(v, "Las contraseñas no coinciden", Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case PHOTO_CODE:
                if (resultCode == -1) {
                    String dir = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;

                    decodeBitmap(dir);
                }
                break;

            case SELECT_PICTURE:
                if (resultCode == -1) {
                    Uri path = data.getData();
                    try {
                        userImage = MediaStore.Images.Media.getBitmap(rootView.getContext().getContentResolver(), path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    civProfileUser.setImageURI(path);
                }
                break;
        }

    }

    //    private void decodeBitmap(String dir) {
//        Bitmap bitmap;
//        bitmap = BitmapFactory.decodeFile(dir);
//
//        if (bitmap.getHeight() > 2000 || bitmap.getWidth() > 2000) {
//            Matrix matrix = new Matrix();
//            if (bitmap.getWidth() > bitmap.getHeight()) {
//                matrix.postRotate(90);
//            }
//            float widthModify = (float) (bitmap.getWidth() * 0.3);
//            float heightModify = (float) (bitmap.getHeight() * 0.3);
//            bitmap = Bitmap.createBitmap(bitmap, (int) widthModify, (int) heightModify, (int) (widthModify + 1000), (int) (heightModify + 700), matrix, true);
//        }
//        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
//        civProfileUser.setImageDrawable(drawable);
//    }
    private void decodeBitmap(String dir) {
        Bitmap bitmap, bitmapModify;
        bitmap = BitmapFactory.decodeFile(dir);

        Matrix matrix = new Matrix();
        if (bitmap.getWidth() > bitmap.getHeight()) {
            matrix.postRotate(90);
        }
        bitmapModify = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        userImage = bitmapModify;

        Drawable drawable = new BitmapDrawable(getResources(), bitmapModify);
        civProfileUser.setImageDrawable(drawable);
    }
}
