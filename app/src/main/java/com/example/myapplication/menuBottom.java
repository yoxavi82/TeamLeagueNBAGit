package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class menuBottom extends AppCompatActivity {

    private TextView mTextMessage;
    Button crear,unir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bottom);

        crear = findViewById(R.id.crearId);
        unir = findViewById(R.id.unirseId);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@org.jetbrains.annotations.NotNull MenuItem item) {
           switch (item.getItemId()) {
                case R.id.home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.ali:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.mercado:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.jornada:
                   mTextMessage.setText(R.string.title_notifications);
                   return true;
                case R.id.clasificacion:
                   mTextMessage.setText(R.string.title_notifications);
                   return true;
           }
            return false;
        }
    };

    public void unirseLiga(View view){
        System.out.println("unir");

    }

    public  void crearLiga(View view){
        System.out.println("crear");

    }

}
