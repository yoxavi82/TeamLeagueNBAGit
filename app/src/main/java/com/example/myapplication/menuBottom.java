package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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
        navigationBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        System.out.println("unir");
                        break;
                    case R.id.ali:
                        System.out.println("unir");
                        break;
                    case R.id.mercado:
                        System.out.println("unir");
                        break;
                    case R.id.jornada:
                        System.out.println("unir");
                        break;
                    case R.id.clasificacion:
                        System.out.println("unir");
                        break;
                }
                return false;

            }
        });

    }



    public void unirseLiga(View view){}

    public  void crearLiga(View view){

    }

    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                System.out.println("unir");
                break;
            case R.id.ali:
                System.out.println("unir");
                break;
            case R.id.mercado:
                System.out.println("unir");
                break;
            case R.id.jornada:
                System.out.println("unir");
                break;
            case R.id.clasificacion:
                System.out.println("unir");
                break;
        }
        return true;
    }
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
//}
