package com.example.teamleaguebagit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class Alineacion extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView navigationBottom;
    ImageView suplente1,suplente2,suplente3,suplente4,suplente5,suplente6,test;
    boolean moving = false;
    LinearLayout a;
    View parentAnterior;
    private RecyclerView recyclerView;
    private ArrayList<PlayerModel> imageModelArrayList;
    Integer anterior=null;
    RecyclerView suplentes, noConvocados;
    private PlayerAdapter adapter;

    private ArrayList<Drawable> myImageListSuplentes = new ArrayList<Drawable>();
    private ArrayList<String> myImageNameListSuplentes =  new ArrayList<String>(Arrays.asList("1","2" ,"3","4","5","6","7"));

    private ArrayList<Drawable> myImageListNoConv = new ArrayList<Drawable>();
    private ArrayList<String> myImageNameListNoConv =  new ArrayList<String>(Arrays.asList("1","2" ,"3","4","5","6","7"));

    Drawable cambio = null;
    int anteriorId = -1;
    int anteriorPos=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alineacion);
        llenarArrayDraw();
        suplentes = (RecyclerView) findViewById(R.id.suplentes);
        suplentes.setBackgroundColor(Color.parseColor("#FFA400"));

        noConvocados = (RecyclerView) findViewById(R.id.noConvocados);
        noConvocados.setBackgroundColor(Color.parseColor("#FFA400"));


        inintRecycleView(suplentes);
        inintRecycleView(noConvocados);


        //alberto
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView titulo = findViewById(R.id.toolbar_title);


        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationBottom  = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        navigationBottom.setSelectedItemId(R.id.ali);
        navigationBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent i;
                switch (menuItem.getItemId()) {
                    case R.id.inicio:
                        i = new Intent(Alineacion.super.getApplication(), Homepage.class);
                        startActivity(i);
                        break;
                    case R.id.ali:
                        i = new Intent(Alineacion.super.getApplication(), Alineacion.class);
                        startActivity(i);
                        break;
                    case R.id.mercado:
                        i = new Intent(Alineacion.super.getApplication(), Mercado.class);
                        startActivity(i);
                        break;
                    case R.id.jornada:
                        i = new Intent(Alineacion.super.getApplication(), Jornada.class);
                        startActivity(i);
                        break;
                    case R.id.clasificacion:
                        i = new Intent(Alineacion.super.getApplication(), Clasificacion.class);
                        startActivity(i);
                        break;
                }
                return false;

            }
        });


    }

    private void inintRecycleView(RecyclerView recyclerView) {

        recyclerView.addOnItemTouchListener(
                new RecyclerTouchListener(this, recyclerView ,new RecyclerTouchListener.ClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        View parent = (View)view.getParent();



                        if(cambio == null) {
                            anteriorPos=position;
                            if (parent.getId() == R.id.suplentes)
                                cambio = myImageListSuplentes.get(position);
                            else
                                cambio = myImageListNoConv.get(position);

                        }else{
                            if(anteriorId!=-1) {
                                ImageView imagAnt = findViewById(anteriorId);
                                if (parent.getId() == R.id.suplentes) {
                                    imagAnt.setImageDrawable(myImageListSuplentes.get(position));
                                    myImageListSuplentes.remove(position);
                                    myImageListSuplentes.add(position, cambio);

                                }else{
                                    imagAnt.setImageDrawable(myImageListNoConv.get(position));
                                    myImageListNoConv.remove(position);
                                    myImageListNoConv.add(position, cambio);
                                }

                            }else{
                                if (parent.getId() == R.id.suplentes) {
                                    if(parentAnterior.getId()==R.id.noConvocados) {
                                        Drawable actual = myImageListSuplentes.get(position);
                                        myImageListNoConv.remove(anteriorPos);
                                        myImageListNoConv.add(anteriorPos, actual);
                                        myImageListSuplentes.remove(position);
                                        myImageListSuplentes.add(position, cambio);

                                    }else{
                                        Drawable actual = myImageListSuplentes.get(position);
                                        myImageListSuplentes.remove(anteriorPos);
                                        myImageListSuplentes.add(anteriorPos, actual);
                                        myImageListSuplentes.remove(position);
                                        myImageListSuplentes.add(position, cambio);
                                    }
                                }else{
                                    if(parentAnterior.getId()==R.id.noConvocados) {
                                        Drawable actual = myImageListNoConv.get(position);
                                        myImageListNoConv.remove(anteriorPos);
                                        myImageListNoConv.add(anteriorPos, actual);
                                        myImageListNoConv.remove(position);
                                        myImageListNoConv.add(position, cambio);
                                    }else{
                                        Drawable actual = myImageListNoConv.get(position);
                                        myImageListSuplentes.remove(anteriorPos);
                                        myImageListSuplentes.add(anteriorPos, actual);
                                        myImageListNoConv.remove(position);
                                        myImageListNoConv.add(position, cambio);
                                    }
                                }

                            }
                            initNoConv();
                            initSuplentes();
                            cambio = null;
                            anteriorId = -1;
                            anteriorPos = -1;



                        }
                        parentAnterior= (View)view.getParent();

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {


                        // do whatever
                    }
                })
        );

        //imageModelArrayList = eatFruits();
        //adapter = new PlayerAdapter(this, imageModelArrayList);
        //recyclerView.setAdapter(adapter);
        initSuplentes();
        initNoConv();

        suplentes.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        noConvocados.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

    }

    public void unirseLiga(View view){
        System.out.println("unir");
    }

    public  void crearLiga(View view){
        System.out.println("crear");

    }

    //Pulsar para atrás
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.Atencion);
        alert.setMessage(R.string.MensajeSalirApp);
        alert.setNegativeButton(R.string.Salir, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finishAffinity();
                System.exit(0);
            }
        });
        alert.setPositiveButton(R.string.Cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.show();
    }

    //Crear menu lateral
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    //Opciones para menu lateral
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.salir:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle(R.string.Atencion);
                alert.setMessage(R.string.CerrarSesionPregunta);
                alert.setNegativeButton(R.string.CerrarSesion, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
                alert.setPositiveButton(R.string.Cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.show();
                break;
            case R.id.config:
                break;
        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void llenarArrayDraw(){
        ImageView img= findViewById(R.id.imageView11);
//        img.setImageResource(R.drawable.uno);
//        myImageListSuplentes.add( img.getDrawable() );
//        img.setImageResource(R.drawable.dos);
//        myImageListSuplentes.add( img.getDrawable() );
//        img.setImageResource(R.drawable.tres);
//        myImageListSuplentes.add( img.getDrawable() );
//        img.setImageResource(R.drawable.uno);
//        myImageListSuplentes.add( img.getDrawable() );
//        img.setImageResource(R.drawable.dos);
//        myImageListSuplentes.add( img.getDrawable() );
//        img.setImageResource(R.drawable.tres);
//        myImageListSuplentes.add( img.getDrawable() );
//        img.setImageResource(R.drawable.pelota);
//
//        img.setImageResource(R.drawable.uno);
//        myImageListNoConv.add( img.getDrawable() );
//        img.setImageResource(R.drawable.dos);
//        myImageListNoConv.add( img.getDrawable() );
//        img.setImageResource(R.drawable.tres);
//        myImageListNoConv.add( img.getDrawable() );
//        img.setImageResource(R.drawable.uno);
//        myImageListNoConv.add( img.getDrawable() );
//        img.setImageResource(R.drawable.dos);
//        myImageListNoConv.add( img.getDrawable() );
//        img.setImageResource(R.drawable.tres);
//        myImageListNoConv.add( img.getDrawable() );
//        img.setImageResource(R.drawable.pelota);


    }

    public void clickjugador(View view){
        ImageView img = findViewById(view.getId());

        if(cambio == null) {
            anteriorId= view.getId();
            cambio =img.getDrawable();

        }else{
            if(anteriorId==-1) {
                if (parentAnterior.getId() == R.id.noConvocados) {
                    myImageListNoConv.remove(anteriorPos);
                    myImageListNoConv.add(anteriorPos, img.getDrawable());
                    img.setImageDrawable(cambio);
                } else if (parentAnterior.getId() == R.id.suplentes) {
                    myImageListSuplentes.remove(anteriorPos);
                    myImageListSuplentes.add(anteriorPos, img.getDrawable());
                    img.setImageDrawable(cambio);
                }
            }else{
                ImageView imgAnt = findViewById(anteriorId);
                imgAnt.setImageDrawable(img.getDrawable());
                img.setImageDrawable(cambio);

            }
            initNoConv();
            initSuplentes();
            cambio = null;
            anteriorId = -1;
            anteriorPos = -1;


        }

        System.out.println("hola<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }


    private void initNoConv(){

        ArrayList<PlayerModel> list = new ArrayList<>();

        for(int i = 0; i < myImageListNoConv.size(); i++){
            PlayerModel playerModel = new PlayerModel();
            playerModel.setName(myImageNameListNoConv.get(i));
//            playerModel.setImage_drawable(myImageListNoConv.get(i));

            list.add(playerModel);
        }

        noConvocados.setAdapter(new PlayerAdapter(this, list));

    }
    private void initSuplentes(){

        ArrayList<PlayerModel> list = new ArrayList<>();

        for(int i = 0; i < myImageListSuplentes.size(); i++){
            PlayerModel playerModel = new PlayerModel();
            playerModel.setName(myImageNameListSuplentes.get(i));
//            playerModel.setImage_drawable(myImageListSuplentes.get(i));

            list.add(playerModel);
        }

        suplentes.setAdapter(new PlayerAdapter(this, list));

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub

        int action = MotionEventCompat.getActionMasked(event);
        System.out.println(event.getDeviceId());
        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                return true;
            case (MotionEvent.ACTION_MOVE):
                return true;
            case (MotionEvent.ACTION_UP):
                return true;
            case (MotionEvent.ACTION_CANCEL):
                return true;
            case (MotionEvent.ACTION_OUTSIDE):

                return true;
            default:
                return super.onTouchEvent(event);
        }
    }
}
