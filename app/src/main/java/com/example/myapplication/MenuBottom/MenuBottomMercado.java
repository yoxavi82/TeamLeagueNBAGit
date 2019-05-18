package com.example.myapplication.MenuBottom;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.R;

public class MenuBottomMercado extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView navigationBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bottom_mercado);

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

