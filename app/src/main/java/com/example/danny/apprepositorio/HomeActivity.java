package com.example.danny.apprepositorio;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danny.apprepositorio.utilidades.Utilidades;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    private ViewPager  mSlideViewPager;
    private SliderAdapter sliderAdapter;
    public String us;
    public String ps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            Intent iin= getIntent();
            Bundle b = iin.getExtras();

            if(b!=null)
            {
                String us = b.getString("user");
                String ps =  b.getString("pass");

                ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_app",null,1);
                SQLiteDatabase db = conn.getReadableDatabase();
                String[] parametros = {us,ps};
                String[] campos = {Utilidades.CAMPO_CORREO,Utilidades.CAMPO_NOMBRE};
                String x, y;
                Cursor cursor = db.query(Utilidades.TABLA_USUARIOS, campos, Utilidades.CAMPO_NOMBRE + "=? AND " + Utilidades.CAMPO_CONTRASENIA+"=?", parametros, null, null, null);
                cursor.moveToFirst();
                x=cursor.getString(0);
                y=cursor.getString(1);

                TextView navu, navc;
                navu = (TextView) findViewById(R.id.navUser);
                navc = (TextView) findViewById(R.id.navCorreo);

                navu.setText(x);
                navc.setText(y);
                cursor.close();
                conn.close();

            }

        }catch (Exception e){
            System.out.println(e);
        }

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);

        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_foros) {
            Intent foro = new Intent(getApplicationContext(),ForoActivity.class);
            startActivity(foro);

        } else if (id == R.id.nav_circulos) {
            Intent circulos = new Intent(getApplicationContext(),CirculosActivity.class);
            startActivity(circulos);

        } else if (id == R.id.nav_manage) {
            Intent config = new Intent(getApplicationContext(),SettingsActivity.class);
            startActivity(config);
        } else if (id == R.id.nav_projects) {
            Intent mapa = new Intent(getApplicationContext(),MapsActivity.class);
            startActivity(mapa);

        } else if (id == R.id.nav_send) {
            Intent file = new Intent(getApplicationContext(),UploadActivity.class);
            startActivity(file);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void perfil (View view){
        if (getApplicationContext().getClass()!=PerfilActivity.class){
        Intent i = new Intent(getApplicationContext(),NewPerfilActivity.class);
        startActivity(i);}
    }
}
