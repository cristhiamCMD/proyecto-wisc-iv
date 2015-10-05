package code.cmd.test.wisc.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.parse.ParseUser;

import code.cmd.test.wisc.R;
import code.cmd.test.wisc.ui.fragment.NewPatientFragment;
import code.cmd.test.wisc.ui.fragment.RegisterPsychologyFrame;
import code.cmd.test.wisc.ui.fragment.UserLoginFragment;

public class MainActivity extends AppCompatActivity {

    TextView tvLogin, tvEspecialidad;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Test Wisc - IV");


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        setupDrawerToggle();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        tvLogin = (TextView) findViewById(R.id.tvNameLogin);
        tvEspecialidad = (TextView) findViewById(R.id.tvNameEspecialidad);

//        ActiveUserDao activeUserDao=new ActiveUserDao(this.getApplicationContext());
//        ActiveUser activeUser=activeUserDao.BuscarPorId();
//        if(activeUser!=null)
//        {
//            tvLogin.setText("Doctor : "+activeUser.getUserName());
//            Log.d(this.getLocalClassName(), "IdUser"+activeUser.get_id());
//            Log.d(this.getLocalClassName(), "UserName"+activeUser.getUserName());
//            Log.d(this.getLocalClassName(), "UserPass"+activeUser.getPassword());
//            Log.d(this.getLocalClassName(),"verdade");
//        }
//        else
//        {
//         Log.d(this.getLocalClassName(),"falseete");
//        }

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
            if (currentUser.getString("sex") == "Masculino") {
                tvLogin.setText("Doctor : " + currentUser.getString("firstName") + " " + currentUser.getString("lastName"));
            } else {
                tvLogin.setText("Doctora : " + currentUser.getString("firstName") + " " + currentUser.getString("lastName"));
            }
            currentUser.put("online",true);
            currentUser.saveInBackground();
            tvEspecialidad.setText(currentUser.getString("specialty"));
        } else {
            // show the signup or login screen
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the use
            currentUser.put("online", false);
            currentUser.saveInBackground();
        } else {
            // show the signup or login screen
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            super.onBackPressed();
        }
    }

    private void setupDrawerToggle() {
        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setItemIconTintList(getResources().getColorStateList(R.color.tint_selector));
        navigationView.setItemTextColor(getResources().getColorStateList(R.color.tint_selector));
        navigationView.getMenu().findItem(R.id.nav_diary).setChecked(true);
//        if (PreferencesHelper.getBoolean(PreferencesHelper.FIRST_SYNC, this)) {
//            changeFrameContent(new RegisterPsychologyFrame());
//        }
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
//                        if (PreferencesHelper.getBoolean(PreferencesHelper.FIRST_SYNC, MainActivity.this)) {
                        switch (menuItem.getItemId()) {
                            case R.id.nav_map:
                                getSupportActionBar().setTitle("Nuevo Test");
                                changeFrameContent(new NewPatientFragment());
                                break;
                            case R.id.nav_diary:
                                getSupportActionBar().setTitle("Iniciar Sesion");
                                changeFrameContent(new UserLoginFragment());
                                break;
                            case R.id.nav_explore:
                                getSupportActionBar().setTitle("Registrate");
                                changeFrameContent(new RegisterPsychologyFrame());
                                break;
                            case R.id.nav_social:
                                getSupportActionBar().setTitle(R.string.social_title);
//                                    changeFrameContent(new SocialFragment());
                                break;
                            case R.id.nav_about:
                                getSupportActionBar().setTitle(R.string.about_title);
//                                    changeFrameContent(new AboutFragment());
                                break;
                            case R.id.cerrar_sesion:
                                ParseUser currentUser = ParseUser.getCurrentUser();
                                if (currentUser != null) {
                                    // do stuff with the use
                                    currentUser.put("online", false);
                                    currentUser.saveInBackground();
                                } else {
                                    // show the signup or login screen
                                }
                                ParseUser.logOut();
                                currentUser = ParseUser.getCurrentUser();
                                tvEspecialidad.setText("");
                                tvLogin.setText("");
                                break;
                        }
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    public void changeFrameContent(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    }
}
