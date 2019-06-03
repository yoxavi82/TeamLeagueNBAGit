package com.example.teamleaguebagit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
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
import android.widget.Toast;

import com.example.teamleaguebagit.Conexiones.EquipoUsuarioConexiones;
import com.example.teamleaguebagit.Conexiones.JugadorConexiones;
import com.example.teamleaguebagit.Conexiones.PlantillaConexiones;
import com.example.teamleaguebagit.pojos.EquiposUsuarios;
import com.example.teamleaguebagit.pojos.Jugadores;
import com.example.teamleaguebagit.pojos.Ligas;
import com.example.teamleaguebagit.pojos.Plantillas;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static com.example.teamleaguebagit.Actual.ligasUsuarioActual;

public class Alineacion extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView navigationBottom;
    boolean moving = false;
    LinearLayout a;
    View parentAnterior;
    LinearLayout layourtPrincipal;
    private RecyclerView recyclerView;
    private ArrayList<PlayerModel> imageModelArrayList;
    Integer anterior=null;
    RecyclerView suplentes, noConvocados;
    private PlayerAdapter adapter;
    private ArrayList<Drawable> myImageLisInicial = new ArrayList<Drawable>();
    private ArrayList<String> myImageNameListInicial =  new ArrayList<String>();
    private ArrayList<Drawable> myImageListSuplentes = new ArrayList<Drawable>();
    private ArrayList<Jugadores> myPlayerListInicial = new ArrayList<Jugadores>();
    private ArrayList<Jugadores> myPlayerListSuplentes = new ArrayList<Jugadores>();
    private ArrayList<Jugadores> myPlayerListNoConv = new ArrayList<Jugadores>();
    private Jugadores[] jugadores= new Jugadores[5];

    private ArrayList<String> myImageNameListSuplentes =  new ArrayList<String>(Arrays.asList("1","2" ,"3","4","5","6","7"));

    private ArrayList<Drawable> myImageListNoConv = new ArrayList<Drawable>();
    private ArrayList<String> myImageNameListNoConv =  new ArrayList<String>(Arrays.asList("1","2" ,"3","4","5","6","7"));

    Drawable cambio = null;
    Jugadores cambioplayer,jugAnt = null;

    int anteriorId = -1;
    int anteriorPos=-1;
    int numLigas=1;
    ImageView int1;
    ImageView int2;
    ImageView ext1;
    ImageView ext2;
    ImageView ext3;
    NavigationView navView;
    int titularesIntRest=2;
    int titularesExtRest=3;


    int suplentesRest=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alineacion);


        navView = (NavigationView) findViewById(R.id.nav_view);

        initMenu();

        layourtPrincipal = findViewById(R.id.layoutPrincipal);
        //llenarArrayDraw();
        //initArrays();
        suplentes = (RecyclerView) findViewById(R.id.suplentes);
        suplentes.setBackgroundColor(Color.parseColor("#FFA400"));

        noConvocados = (RecyclerView) findViewById(R.id.noConvocados);
        noConvocados.setBackgroundColor(Color.parseColor("#FFA400"));


        inintRecycleView(suplentes);
        inintRecycleView(noConvocados);
        initTitulares();

        int1=findViewById(R.id.int1);
        int2=findViewById(R.id.int2);
        ext1=findViewById(R.id.ext1);
        ext2=findViewById(R.id.ext2);
        ext3=findViewById(R.id.ext3);


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

        View headView = navigationView.getHeaderView(0);
        TextView nombre = headView.findViewById(R.id.NombreUser);
        nombre.setText(Actual.getUsuarioActual().getNombre()+" "+Actual.getUsuarioActual().getApellidos());

        navigationBottom  = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        navigationBottom.setSelectedItemId(R.id.ali);
        navigationBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent i;
                switch (menuItem.getItemId()) {
                    case R.id.inicio:
                        i = new Intent(Alineacion.super.getApplication(), Homepage.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        break;
                    case R.id.ali:
                        break;
                    case R.id.mercado:
                        i = new Intent(Alineacion.super.getApplication(), Mercado.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        break;
                    case R.id.jornada:
                        i = new Intent(Alineacion.super.getApplication(), Jornada.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        break;
                    case R.id.clasificacion:
                        i = new Intent(Alineacion.super.getApplication(), Clasificacion.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        break;
                }
                return false;

            }
        });


    }

    private void initTitulares() {

    }


    private void initArrays() {

        int intTit=0;
        int extTit=0;
        PlantillaConexiones plantilla = new PlantillaConexiones();
        EquipoUsuarioConexiones equipoConexion = new EquipoUsuarioConexiones();
        EquiposUsuarios equipo =equipoConexion.getByLigaAndUser(Actual.getUsuarioActual().getIdUsuario(), Actual.getLigaActual().getIdLiga());
       ArrayList<Plantillas>listajug = plantilla.getJugadoresByIdEquipoYLiga(equipo.getIdEquipo(), Actual.getLigaActual().getIdLiga());
        for (Plantillas jugador: listajug ) {
            if(jugador.getTitular()==1)
                if(jugador.getJugadores().getPosicion().equals("Interior"))
                    intTit++;
                else
                    extTit++;
        }
        titularesIntRest-=intTit;
        titularesExtRest-=extTit;


        for (Plantillas jugador: listajug ) {
            if(jugador.getTitular()==1) {
                if(jugador.getJugadores().getPosicion().equals("Interior")){
                    for(int i = 0; i<5;i++){
                        if(jugadores[i]==null){
                            jugadores[i]=jugador.getJugadores();
                        }
                    }
                }else{
                    for(int i = 5; i>0;i--){
                        if(jugadores[i]==null){
                            jugadores[i]=jugador.getJugadores();
                        }
                    }
                }
//                myPlayerListInicial.add(jugador.getJugadores());
//                myImageLisInicial.add(getDrawableFromBytes(jugador.getJugadores().getImagen()));
            }if(jugador.getTitular()==2){
                myPlayerListSuplentes.add(jugador.getJugadores());
                myImageListSuplentes.add(getDrawableFromBytes(jugador.getJugadores().getImagen()));
            }if (jugador.getTitular()==3){
                myPlayerListNoConv.add(jugador.getJugadores());
                myImageListNoConv.add(getDrawableFromBytes(jugador.getJugadores().getImagen()));
            }else{
                if(jugador.getJugadores().getPosicion().equals("Interior")&&titularesIntRest>0) {
                    titularesIntRest--;
                    myPlayerListInicial.add(jugador.getJugadores());
                    myImageLisInicial.add(getDrawableFromBytes(jugador.getJugadores().getImagen()));
                }if (jugador.getJugadores().getPosicion().equals("Exterior")&&titularesExtRest>0) {
                    titularesExtRest--;
                    myPlayerListInicial.add(jugador.getJugadores());
                    myImageLisInicial.add(getDrawableFromBytes(jugador.getJugadores().getImagen()));
                }else if(suplentesRest>0) {
                    myPlayerListSuplentes.add(jugador.getJugadores());
                    myImageListSuplentes.add(getDrawableFromBytes(jugador.getJugadores().getImagen()));
                    suplentesRest--;
                }else{
                    myPlayerListNoConv.add(jugador.getJugadores());
                    myImageListNoConv.add(getDrawableFromBytes(jugador.getJugadores().getImagen()));
                }
            }

        }
        myPlayerListInicial= new ArrayList<Jugadores>(Arrays.asList(jugadores));





    }

    private Drawable getDrawableFromBytes(byte[] img) {
       ByteArrayInputStream is = new ByteArrayInputStream(img);
       return Drawable.createFromStream(is,"Imagen");
    }

    private void inintRecycleView(RecyclerView recyclerView) {

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        View parent = (View)view.getParent();
                        if(cambio == null) {
                            anteriorPos=position;
                            if (parent.getId() == R.id.suplentes) {
                                cambio = myImageListSuplentes.get(position);
                                cambioplayer = myPlayerListSuplentes.get(position);
                            }else {
                                cambio = myImageListNoConv.get(position);
                                cambioplayer=myPlayerListNoConv.get(position);

                            }

                            if (cambioplayer.getPosicion().equals("ext")){
                                int1.setClickable(false);
                                int2.setAlpha((float)0.5);
                                int1.setAlpha((float)0.5);



                            }else{
                                ext1.setClickable(false);
                                ext1.setAlpha((float)0.5);
                                ext2.setAlpha((float)0.5);
                                ext3.setAlpha((float)0.5);
                                ext2.setClickable(false);
                                ext3.setClickable(false);
                            }
                        }else{
                            int1.setClickable(true);
                            int2.setClickable(true);
                            ext1.setClickable(true);
                            ext2.setClickable(true);
                            ext3.setClickable(true);
                            int1.setAlpha((float)1);
                            int2.setAlpha((float)1);
                            ext1.setAlpha((float)1);
                            ext2.setAlpha((float)1);
                            ext3.setAlpha((float)1);
                            if(anteriorId!=-1) {
                                ImageView imagAnt = findViewById(anteriorId);
                                if (parent.getId() == R.id.suplentes) {
                                    imagAnt.setImageDrawable(myImageListSuplentes.get(position));
                                    myImageListSuplentes.remove(position);
                                    myImageListSuplentes.add(position, cambio);


                                    Jugadores jugadores = myPlayerListSuplentes.get(position);
                                    myPlayerListInicial.remove(anteriorPos);
                                    myPlayerListInicial.add(anteriorPos, jugadores);
                                    myPlayerListSuplentes.remove(position);
                                    myPlayerListSuplentes.add(position, cambioplayer);

                                }else{
                                    imagAnt.setImageDrawable(myImageListNoConv.get(position));
                                    myImageListNoConv.remove(position);
                                    myImageListNoConv.add(position, cambio);

                                    Jugadores jugadores = myPlayerListNoConv.get(position);
                                    myPlayerListInicial.remove(anteriorPos);
                                    myPlayerListInicial.add(anteriorPos, jugadores);
                                    myPlayerListNoConv.remove(position);
                                    myPlayerListNoConv.add(position, cambioplayer);
                                }
                            }else{
                                if (parent.getId() == R.id.suplentes) {
                                    if(parentAnterior.getId()==R.id.noConvocados) {
                                        Drawable actual = myImageListSuplentes.get(position);
                                        myImageListNoConv.remove(anteriorPos);
                                        myImageListNoConv.add(anteriorPos, actual);
                                        myImageListSuplentes.remove(position);
                                        myImageListSuplentes.add(position, cambio);

                                        Jugadores jugadores = myPlayerListSuplentes.get(position);
                                        myPlayerListNoConv.remove(anteriorPos);
                                        myPlayerListNoConv.add(anteriorPos, jugadores);
                                        myPlayerListSuplentes.remove(position);
                                        myPlayerListSuplentes.add(position, cambioplayer);
                                    }else{
                                        Drawable actual = myImageListSuplentes.get(position);
                                        myImageListSuplentes.remove(anteriorPos);
                                        myImageListSuplentes.add(anteriorPos, actual);
                                        myImageListSuplentes.remove(position);
                                        myImageListSuplentes.add(position, cambio);

                                        Jugadores jugadores = myPlayerListSuplentes.get(position);
                                        myPlayerListSuplentes.remove(anteriorPos);
                                        myPlayerListSuplentes.add(anteriorPos, jugadores);
                                        myPlayerListSuplentes.remove(position);
                                        myPlayerListSuplentes.add(position, cambioplayer);
                                    }
                                }else{
                                    if(parentAnterior.getId()==R.id.noConvocados) {
                                        Drawable actual = myImageListNoConv.get(position);
                                        myImageListNoConv.remove(anteriorPos);
                                        myImageListNoConv.add(anteriorPos, actual);
                                        myImageListNoConv.remove(position);
                                        myImageListNoConv.add(position, cambio);

                                        Jugadores jugadores = myPlayerListNoConv.get(position);

                                        myPlayerListNoConv.remove(anteriorPos);
                                        myPlayerListNoConv.add(anteriorPos, jugadores);
                                        myPlayerListNoConv.remove(position);
                                        myPlayerListNoConv.add(position, cambioplayer);
                                    }else{
                                        Drawable actual = myImageListNoConv.get(position);
                                        myImageListSuplentes.remove(anteriorPos);
                                        myImageListSuplentes.add(anteriorPos, actual);
                                        myImageListNoConv.remove(position);
                                        myImageListNoConv.add(position, cambio);


                                        Jugadores jugadores = myPlayerListNoConv.get(position);
                                        myPlayerListSuplentes.remove(anteriorPos);
                                        myPlayerListSuplentes.add(anteriorPos, jugadores);
                                        myPlayerListNoConv.remove(position);
                                        myPlayerListNoConv.add(position, cambioplayer);
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



                    @Override public void onLongItemClick(View view, int position) {
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


    //Pulsar para atrás
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.Atencion);
        alert.setMessage(R.string.MensajeSalirApp);
        alert.setNegativeButton(R.string.Salir, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Actual.disconect();
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
        System.out.println(menu.size());
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
//    @Override
//    public boolean  onPrepareOptionsMenu(Menu menu) {
//        for (int i=0; i<5; i++){
//            menu.getItem(i).setTitle("hola");
//            if (i>=numLigas)
//                menu.getItem(i).setVisible(false);
//        }
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }





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
                        Actual.disconect();
                        Actual.setIniciarSesion();
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
                Intent i = new Intent(this, Configuracion.class);
                startActivity(i);
                break;


            default:
                for(Ligas liga: ligasUsuarioActual){
                    if(item.getTitle().equals(liga.getIdLiga())){
                        Actual.setLigaActual(liga);
                        i = new Intent(Alineacion.super.getApplication(), Homepage.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                }
                Toast toast= Toast.makeText(this,"Liga "+item.getTitle()+" seleccionada", Toast.LENGTH_SHORT);
                toast.show();
                break;
        }

        return true;
    }
    public void llenarArrayDraw(){
        ImageView img= findViewById(R.id.int1);
        img.setImageResource(R.drawable.uno);
        Jugadores player = new Jugadores();
        player.setApellido("hola");
        player.setPosicion("int");
        myPlayerListInicial.add(player);
         player = new Jugadores();
        player.setApellido("adios");
        player.setPosicion("ext");
        myPlayerListInicial.add(player);
         player = new Jugadores();
        player.setApellido("uwu");
        player.setPosicion("int");
        myPlayerListInicial.add(player);
         player = new Jugadores();
        player.setApellido("owo");
        player.setPosicion("ext");
        myPlayerListInicial.add(player);
         player = new Jugadores();
        player.setApellido("ha");
        player.setPosicion("ext");
        myPlayerListInicial.add(player);



        player = new Jugadores();
        player.setApellido("1");
        player.setPosicion("int");
        myPlayerListSuplentes.add(player);
        myImageListSuplentes.add( img.getDrawable() );
        img.setImageResource(R.drawable.dos);
        player = new Jugadores();
        player.setApellido("2");
        player.setPosicion("int");



        myPlayerListSuplentes.add(player);
        myImageListSuplentes.add( img.getDrawable() );
        img.setImageResource(R.drawable.tres);
        player = new Jugadores();
        player.setApellido("3");
        player.setPosicion("ext");
        myPlayerListSuplentes.add(player);

        myImageListSuplentes.add( img.getDrawable() );
        img.setImageResource(R.drawable.uno);
        player = new Jugadores();
        player.setApellido("lolo");
        player.setPosicion("int");
        myPlayerListSuplentes.add(player);


        myImageListSuplentes.add( img.getDrawable() );
        img.setImageResource(R.drawable.dos);
        player = new Jugadores();
        player.setApellido("lelele");
        player.setPosicion("int");
        myPlayerListSuplentes.add(player);



        img.setImageResource(R.drawable.uno);
        myImageListNoConv.add( img.getDrawable() );
        player = new Jugadores();
        player.setApellido("lelle");
        player.setPosicion("int");
        myPlayerListNoConv.add(player);


        img.setImageResource(R.drawable.dos);
        myImageListNoConv.add( img.getDrawable() );
        player.setApellido("lululu");
        player.setPosicion("int");
        myPlayerListNoConv.add(player);


        img.setImageResource(R.drawable.tres);
        myImageListNoConv.add( img.getDrawable() );
        player.setApellido("lilili");
        player.setPosicion("ext");
        myPlayerListNoConv.add(player);


    }

    public void clickjugador(View view){
        ImageView img = findViewById(view.getId());
        if(cambio == null) {
            anteriorId= view.getId();
            cambio =img.getDrawable();

            anteriorPos=getPos(view);
        }else{
            if(anteriorId==-1) {
                int1.setClickable(true);
                int2.setClickable(true);
                ext1.setClickable(true);
                ext2.setClickable(true);
                ext3.setClickable(true);
                int1.setAlpha((float)1);
                int2.setAlpha((float)1);
                ext1.setAlpha((float)1);
                ext2.setAlpha((float)1);
                ext3.setAlpha((float)1);
                if (parentAnterior.getId() == R.id.noConvocados) {
                    myImageListNoConv.remove(anteriorPos);
                    myImageListNoConv.add(anteriorPos, img.getDrawable());
                    img.setImageDrawable(cambio);


                    int position=getPos(view);
                    Jugadores jugadores = myPlayerListInicial.get(position);
                    myPlayerListNoConv.remove(anteriorPos);
                    myPlayerListNoConv.add(anteriorPos, jugadores);
                    myPlayerListInicial.remove(position);
                    myPlayerListInicial.add(position, cambioplayer);
                } else if (parentAnterior.getId() == R.id.suplentes) {
                    myImageListSuplentes.remove(anteriorPos);
                    myImageListSuplentes.add(anteriorPos, img.getDrawable());
                    img.setImageDrawable(cambio);


                    int position=getPos(view);
                    Jugadores jugadores = myPlayerListInicial.get(position);
                    myPlayerListSuplentes.remove(anteriorPos);
                    myPlayerListSuplentes.add(anteriorPos, jugadores);
                    myPlayerListInicial.remove(position);
                    myPlayerListInicial.add(position, cambioplayer);


                }
            }else{
                ImageView imgAnt = findViewById(anteriorId);
                imgAnt.setImageDrawable(img.getDrawable());
                img.setImageDrawable(cambio);

                int position=getPos(view);
                Jugadores jugadores = myPlayerListInicial.get(position);
                myPlayerListInicial.remove(anteriorPos);
                myPlayerListInicial.add(anteriorPos, jugadores);
                myPlayerListInicial.remove(position);
                myPlayerListInicial.add(position, cambioplayer);
            }
            initNoConv();
            initSuplentes();
            cambio = null;
            anteriorId = -1;
            anteriorPos = -1;
        }
    }

    private int getPos(View view) {
        switch (view.getId()){
            case R.id.ext1: return 0;
            case R.id.ext2: return 1;
            case R.id.ext3: return 2;
            case R.id.int1: return 3;
            case R.id.int2: return 4;

            default: return 0;
        }
    }

    private void initNoConv(){
        ArrayList<PlayerModel> list = new ArrayList<>();
        for(int i = 0; i < myImageListNoConv.size(); i++){
            PlayerModel playerModel = new PlayerModel();
            playerModel.setName(myPlayerListNoConv.get(i).getApellido());
            playerModel.setImage_drawable(myImageListNoConv.get(i));
            list.add(playerModel);
        }
        noConvocados.setAdapter(new PlayerAdapter(this, list));
    }

    private void initSuplentes(){
        ArrayList<PlayerModel> list = new ArrayList<>();
        for(int i = 0; i < myImageListSuplentes.size(); i++){
            PlayerModel playerModel = new PlayerModel();
            playerModel.setName(myPlayerListSuplentes.get(i).getApellido());
            playerModel.setImage_drawable(myImageListSuplentes.get(i));
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

    @NotNull
    private Menu initMenu() {
        Menu m = navView.getMenu();
        m.findItem(R.id.ligas).getSubMenu().clear();
        for(int i = 0; i< ligasUsuarioActual.size(); i++) {
            m.findItem(R.id.ligas).getSubMenu().add(ligasUsuarioActual.get(i).getIdLiga());
        }
        return m;

    }
    public void test(View view){
        JugadorConexiones conJug = new JugadorConexiones();
        byte[] test = getBytes();
        int1.setImageBitmap(getDrawable(test));

    }
    public byte[] getBytes(){
        ByteArrayOutputStream outImageStream = new ByteArrayOutputStream();
        Bitmap bitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.dos)).getBitmap();
        byte[] photo = outImageStream.toByteArray();
        return photo;
    }
    public Bitmap getDrawable(byte[] bytes){
        ByteArrayInputStream input = new ByteArrayInputStream(bytes);
        Bitmap bitmap = BitmapFactory.decodeStream(input);
        return bitmap;
    }
}
