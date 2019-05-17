package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class menuBottom extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView navigationBottom;
    Button crear,unir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bottom);

        crear = findViewById(R.id.crearId);
        unir = findViewById(R.id.unirseId);
        navigationBottom  = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        navigationBottom.setOnNavigationItemSelectedListener(this);

    }



    public void unirseLiga(View view){
        System.out.println("unir");

    }

    public  void crearLiga(View view){
        System.out.println("crear");

    }
    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                break;
            case R.id.ali:
                break;
            case R.id.mercado:
                break;
            case R.id.clasificacion:
                break;
            case R.id.jornada:
                break;
        }
        return true;
    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        Toast toast;
//        switch (menuItem.getItemId()) {
//            case R.id.home:
//                toast = Toast.makeText(getApplicationContext(),
//                        "Introduce todos los campos", Toast.LENGTH_SHORT);
//                toast.show();
//                break;
//            case R.id.ali:
//                toast = Toast.makeText(getApplicationContext(),
//                        "Introduce todos los campos", Toast.LENGTH_SHORT);
//                toast.show();
//                break;
//            case R.id.mercado:
//                toast = Toast.makeText(getApplicationContext(),
//                        "Introduce todos los campos", Toast.LENGTH_SHORT);
//                toast.show();
//                break;
//            case R.id.jornada:
//                toast = Toast.makeText(getApplicationContext(),
//                        "Introduce todos los campos", Toast.LENGTH_SHORT);
//                toast.show();
//                break;
//            case R.id.clasificacion:
//                toast = Toast.makeText(getApplicationContext(),
//                        "Introduce todos los campos", Toast.LENGTH_SHORT);
//                toast.show();
//                break;
//        }
//        return false;
//    }
}
