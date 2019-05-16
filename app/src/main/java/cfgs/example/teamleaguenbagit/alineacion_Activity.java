package cfgs.example.teamleaguenbagit;

import android.graphics.drawable.Drawable;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myapplication.R;

import java.util.ArrayList;

public class alineacion_Activity extends AppCompatActivity {
    ImageView suplente1,suplente2,suplente3,suplente4,suplente5,suplente6;
    boolean moving = false;
    LinearLayout a;
    private RecyclerView recyclerView;
    private ArrayList<PlayerModel> imageModelArrayList;
    private PlayerAdapter adapter;

    private int[] myImageList = new int[]{R.drawable.uno, R.drawable.dos,R.drawable.dos, R.drawable.tres,R.drawable.uno,R.drawable.dos,R.drawable.tres};
    private String[] myImageNameList = new String[]{"1","2" ,"3","4","5","6","7"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alineacion_);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        imageModelArrayList = eatFruits();
        adapter = new PlayerAdapter(this, imageModelArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));



    }

    private ArrayList<PlayerModel> eatFruits(){

        ArrayList<PlayerModel> list = new ArrayList<>();

        for(int i = 0; i < 7; i++){
            PlayerModel playerModel = new PlayerModel();
            playerModel.setName(myImageNameList[i]);
            playerModel.setImage_drawable(R.drawable.uno);
            list.add(playerModel);
        }

        return list;
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
