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
import android.support.constraint.ConstraintLayout;
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
import android.util.Base64;
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
import com.example.teamleaguebagit.pojos.Equipos;
import com.example.teamleaguebagit.pojos.EquiposUsuarios;
import com.example.teamleaguebagit.pojos.Jugadores;
import com.example.teamleaguebagit.pojos.Ligas;
import com.example.teamleaguebagit.pojos.Plantillas;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import static com.example.teamleaguebagit.Actual.ligasUsuarioActual;

public class Alineacion extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{


    byte[] test=null;

    ConstraintLayout pista;
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
    private static LinkedList<Jugadores> myPlayerListInicial = new LinkedList<Jugadores>();
    private static ArrayList<Jugadores> myPlayerListSuplentes = new ArrayList<Jugadores>();
    private static ArrayList<Jugadores> myPlayerListNoConv = new ArrayList<Jugadores>();
    static boolean actPlantilla= true;



    Jugadores cambioplayer,jugAnt = null;

    int anteriorId = -1;
    int anteriorPos=-1;
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
        pista= findViewById(R.id.pista);

        navView = (NavigationView) findViewById(R.id.nav_view);

        initMenu();

        layourtPrincipal = findViewById(R.id.layoutPrincipal);
        if (actPlantilla)initArrays();
        suplentes = (RecyclerView) findViewById(R.id.suplentes);
        EquipoUsuarioConexiones conEquipo = new EquipoUsuarioConexiones();
        Equipos nba = (conEquipo.getByLigaAndUser(Actual.usuarioActual.getIdUsuario(),Actual.ligaActual.getIdLiga())).getEquipos();
        suplentes.setBackgroundColor(Color.parseColor("#"+nba.getColor()));

        noConvocados = (RecyclerView) findViewById(R.id.noConvocados);
        noConvocados.setBackgroundColor(Color.parseColor("#"+nba.getColor()));

        layourtPrincipal.setBackgroundColor(Color.parseColor("#"+nba.getColor()));
        pista.setBackgroundResource(getResource("c_"+nba.getIdEquipo()));
        int1=findViewById(R.id.int1);
        int2=findViewById(R.id.int2);
        ext1=findViewById(R.id.ext1);
        ext2=findViewById(R.id.ext2);
        ext3=findViewById(R.id.ext3);
        inintRecycleView(suplentes);
        inintRecycleView(noConvocados);


        //initTitulares();


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
        navigationBottom.setBackgroundColor(Color.parseColor(Colores.mapa.get(Actual.getEquipoActual().getEquipos().getIdEquipo())));
        toolbar.setBackgroundColor(Color.parseColor(Colores.mapa.get(Actual.getEquipoActual().getEquipos().getIdEquipo())));


    }

    private void initTitulares() {
        ext1.setImageResource(getResource(myPlayerListInicial.get(0).getIdJugador().toLowerCase()));
        ext2.setImageResource(getResource(myPlayerListInicial.get(1).getIdJugador().toLowerCase()));
        ext3.setImageResource(getResource(myPlayerListInicial.get(2).getIdJugador().toLowerCase()));
        int1.setImageResource(getResource(myPlayerListInicial.get(3).getIdJugador().toLowerCase()));
        int2.setImageResource(getResource(myPlayerListInicial.get(4).getIdJugador().toLowerCase()));



    }


    private void initArrays() {
        actPlantilla=false;


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
                    myPlayerListInicial.addLast(jugador.getJugadores());
                }else{
                    myPlayerListInicial.addFirst(jugador.getJugadores());

                }
            }if(jugador.getTitular()==2){
                myPlayerListSuplentes.add(jugador.getJugadores());
            }if (jugador.getTitular()==3){
                myPlayerListNoConv.add(jugador.getJugadores());
            }else{
                if(jugador.getJugadores().getPosicion().equals("Interior")&&titularesIntRest>0) {
                    titularesIntRest--;
                    myPlayerListInicial.add(jugador.getJugadores());
                }else if (jugador.getJugadores().getPosicion().equals("Exterior")&&titularesExtRest>0) {
                    titularesExtRest--;
                    myPlayerListInicial.add(jugador.getJugadores());
                }else if(suplentesRest>0) {
                    myPlayerListSuplentes.add(jugador.getJugadores());
                    suplentesRest--;
                }else{
                    myPlayerListNoConv.add(jugador.getJugadores());
                }
            }
        }
        if (titularesIntRest!=0){
            for (int i = 0; i<titularesIntRest;i++) {
                Jugadores cambio = myPlayerListNoConv.remove(0);
                Jugadores nuevo = new JugadorConexiones().getByStarsInterior(cambio.getEstrellas(), Actual.ligaActual.getIdLiga()).get(i);

                PlantillaConexiones conexion = new PlantillaConexiones();
                conexion.removePlantilla(Actual.getEquipoActual().getIdEquipo(), cambio.getIdJugador());
                Plantillas plantillaCambio = new Plantillas();
                plantillaCambio.setLigas(Actual.getLigaActual());
                plantillaCambio.setTitular(0);
                plantillaCambio.setPuja(0);
                plantillaCambio.setJugadores(nuevo);
                plantillaCambio.setEquiposUsuarios(new EquipoUsuarioConexiones().getByLigaAndUser(Actual.getUsuarioActual().getIdUsuario(),
                        Actual.getLigaActual().getIdLiga()));
                plantillaCambio.setFechaCompra(new java.sql.Date(new Date().getTime()));
                plantillaCambio.setPrecio(cambio.getEstrellas() * 1000000);
                conexion.addPlantilla(plantillaCambio);
            }
        }
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
                        if(cambioplayer == null) {
                            anteriorPos=position;
                            if (parent.getId() == R.id.suplentes) {
                                cambioplayer = myPlayerListSuplentes.get(position);
                            }else {
                                cambioplayer=myPlayerListNoConv.get(position);

                            }

                            if (cambioplayer.getPosicion().equals("Exterior")){
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



                                    Jugadores jugadores = myPlayerListSuplentes.get(position);
                                    myPlayerListInicial.remove(anteriorPos);
                                    myPlayerListInicial.add(anteriorPos, jugadores);
                                    myPlayerListSuplentes.remove(position);
                                    myPlayerListSuplentes.add(position, cambioplayer);

                                }else{

                                    Jugadores jugadores = myPlayerListNoConv.get(position);
                                    myPlayerListInicial.remove(anteriorPos);
                                    myPlayerListInicial.add(anteriorPos, jugadores);
                                    myPlayerListNoConv.remove(position);
                                    myPlayerListNoConv.add(position, cambioplayer);
                                }
                            }else{
                                if (parent.getId() == R.id.suplentes) {
                                    if(parentAnterior.getId()==R.id.noConvocados) {


                                        Jugadores jugadores = myPlayerListSuplentes.get(position);
                                        myPlayerListNoConv.remove(anteriorPos);
                                        myPlayerListNoConv.add(anteriorPos, jugadores);
                                        myPlayerListSuplentes.remove(position);
                                        myPlayerListSuplentes.add(position, cambioplayer);
                                    }else{

                                        Jugadores jugadores = myPlayerListSuplentes.get(position);
                                        myPlayerListSuplentes.remove(anteriorPos);
                                        myPlayerListSuplentes.add(anteriorPos, jugadores);
                                        myPlayerListSuplentes.remove(position);
                                        myPlayerListSuplentes.add(position, cambioplayer);
                                    }
                                }else{
                                    if(parentAnterior.getId()==R.id.noConvocados) {

                                        Jugadores jugadores = myPlayerListNoConv.get(position);

                                        myPlayerListNoConv.remove(anteriorPos);
                                        myPlayerListNoConv.add(anteriorPos, jugadores);
                                        myPlayerListNoConv.remove(position);
                                        myPlayerListNoConv.add(position, cambioplayer);
                                    }else{


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
                            initTitulares();

                            cambioplayer = null;
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
        initSuplentes();
        initNoConv();
        initTitulares();

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

    public void clickjugador(View view){
        ImageView img = findViewById(view.getId());
        if(cambioplayer == null) {
            anteriorId= view.getId();
            cambioplayer =   myPlayerListInicial.get(getPos(view));

            anteriorPos=getPos(view);

            if (cambioplayer.getPosicion().equals("Exterior")){
                int1.setClickable(false);
                int2.setClickable(false);

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


                    int position=getPos(view);
                    Jugadores jugadores = myPlayerListInicial.get(position);
                    myPlayerListNoConv.remove(anteriorPos);
                    myPlayerListNoConv.add(anteriorPos, jugadores);
                    myPlayerListInicial.remove(position);
                    myPlayerListInicial.add(position, cambioplayer);
                } else if (parentAnterior.getId() == R.id.suplentes) {



                    int position=getPos(view);
                    Jugadores jugadores = myPlayerListInicial.get(position);
                    myPlayerListSuplentes.remove(anteriorPos);
                    myPlayerListSuplentes.add(anteriorPos, jugadores);
                    myPlayerListInicial.remove(position);
                    myPlayerListInicial.add(position, cambioplayer);


                }
            }else{


                int position=getPos(view);
                Jugadores jugadores = myPlayerListInicial.get(position);
                myPlayerListInicial.remove(anteriorPos);
                myPlayerListInicial.add(anteriorPos, jugadores);
                myPlayerListInicial.remove(position);
                myPlayerListInicial.add(position, cambioplayer);
            }
            initNoConv();
            initSuplentes();
            initTitulares();
            cambioplayer = null;
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
        for(int i = 0; i < myPlayerListNoConv.size(); i++){

            PlayerModel playerModel = new PlayerModel();
            playerModel.setName(myPlayerListNoConv.get(i).getApellido());
            playerModel.setResourceImg(getResource(myPlayerListNoConv.get(i).getIdJugador().toLowerCase()));
            list.add(playerModel);
        }
        noConvocados.setAdapter(new PlayerAdapter(this, list));
    }

    private void initSuplentes(){
        ArrayList<PlayerModel> list = new ArrayList<>();
        for(int i = 0; i < myPlayerListSuplentes.size(); i++){
            PlayerModel playerModel = new PlayerModel();
            playerModel.setName(myPlayerListSuplentes.get(i).getApellido());
            playerModel.setResourceImg(getResource(myPlayerListSuplentes.get(i).getIdJugador().toLowerCase()));
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
   public void test(View view) {
        JugadorConexiones conJug = new JugadorConexiones();
        Jugadores jugador =conJug.getById("UTA_45");
        byte[]bytes =getFromBlob( jugador.blob);
        ext1.setImageBitmap(getBitmap(bytes));

   }

    public byte[] getBytes(Jugadores jugador){
        int resource = getResources().getIdentifier(jugador.getIdJugador().toLowerCase(),"drawable", getPackageName());
        Bitmap bitmap = ((BitmapDrawable)getResources().getDrawable(resource)).getBitmap();
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100,boas ); //bm is the bitmap object
        byte[] byteArrayImage = boas .toByteArray();
        String image_str = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
        jugador.test = image_str;
        return byteArrayImage;
    }
    public Bitmap getBitmap(byte[] bytes){

        ByteArrayInputStream input = new ByteArrayInputStream(bytes);
        Bitmap bitmap = BitmapFactory.decodeStream(input);
        return bitmap;
    }

    public  byte[] getFromBlob(Blob blob){
        try {
            int blobLength = (int) blob.length();
            byte[] blobAsBytes = blob.getBytes(1, blobLength);
            blob.free();
            return  blobAsBytes;
        }catch(SQLException e){
            e.printStackTrace();

        }
        return null;
    }

    public int getResource(String string){
       return getResources().getIdentifier(string.toLowerCase(),"drawable", getPackageName());

    }

    public void comprobarHora(View view){
        Calendar min = new GregorianCalendar();
        min.set(Calendar.HOUR_OF_DAY,8 );
        min.set(Calendar.MINUTE, 0);
        min.set(Calendar.SECOND, 0);
        Calendar max = new GregorianCalendar();
        max.set(Calendar.HOUR_OF_DAY,17 );
        max.set(Calendar.MINUTE, 0);
        max.set(Calendar.SECOND, 0);
        Calendar c = new GregorianCalendar();
        Plantillas plantillaUpdate=null;
        PlantillaConexiones plantillaCon = new PlantillaConexiones();
        if (c.before(max)&&c.after(min)) {
            for (Jugadores jugador : myPlayerListInicial) {
                plantillaUpdate = plantillaCon.getJugadoresByJugadorYLiga(jugador.getIdJugador(), Actual.getLigaActual().getIdLiga());
                plantillaUpdate.setTitular(1);
                plantillaCon.actualizarPlantilla(plantillaUpdate);
            }
            for (Jugadores jugador : myPlayerListSuplentes){
                plantillaUpdate = plantillaCon.getJugadoresByJugadorYLiga(jugador.getIdJugador(), Actual.getLigaActual().getIdLiga());
                plantillaUpdate.setTitular(2);
                plantillaCon.actualizarPlantilla(plantillaUpdate);
            }
            for (Jugadores jugador : myPlayerListNoConv){
                plantillaUpdate = plantillaCon.getJugadoresByJugadorYLiga(jugador.getIdJugador(), Actual.getLigaActual().getIdLiga());
                plantillaUpdate.setTitular(3);
                plantillaCon.actualizarPlantilla(plantillaUpdate);
            }
            //guardamos
            Toast.makeText(this," Alineacion guardada correctamente",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this,c.get(Calendar.HOUR_OF_DAY)+ ":"+c.get(Calendar.MINUTE)+" no es una hora valida intentalo de nuevo entre las 8:00 y las 17:00",Toast.LENGTH_SHORT).show();

        }
    }
}
