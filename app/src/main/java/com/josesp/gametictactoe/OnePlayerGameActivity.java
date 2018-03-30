package com.josesp.gametictactoe;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.josesp.gametictactoe.model.BBDD_Helper;
import com.josesp.gametictactoe.model.PartidaVsMachine;
import com.josesp.gametictactoe.model.Structure_BD;


public class OnePlayerGameActivity extends AppCompatActivity {

    private static PartidaVsMachine partida;
    private int[] casillas;
    private int difficulty;

    private int player1Points;
    private int machinePoints;

    private ImageView view_jugador1;
    private ImageView view_machine;
    private ImageView view_result;
    private ImageView view_continue;

    private boolean endOfGame = false;

    private BBDD_Helper mDbHelper;

    protected void onCreate(Bundle saveInstaceState){
        super.onCreate(saveInstaceState);
        fullScreen();
        setContentView(R.layout.activity_gameplay_oneplayer);
        init();
        mDbHelper = new BBDD_Helper(this);
    }

    public void init(){
        fillCasillas();
        difficulty = getIntent().getExtras().getInt("difficulty");
        partida = new PartidaVsMachine(difficulty);
        player1Points = 0;
        machinePoints = 0;
        view_jugador1 = (ImageView)findViewById(R.id.jugador_puntos);
        view_machine = (ImageView)findViewById(R.id.maquina_puntos);
        view_result = (ImageView)findViewById(R.id.result);
        view_continue = (ImageView)findViewById(R.id.continuar);
    }

    public void fullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void fillCasillas(){
        casillas = new int[9];
        casillas[0] = R.id.a1;
        casillas[1] = R.id.a2;
        casillas[2] = R.id.a3;
        casillas[3] = R.id.b1;
        casillas[4] = R.id.b2;
        casillas[5] = R.id.b3;
        casillas[6] = R.id.c1;
        casillas[7] = R.id.c2;
        casillas[8] = R.id.c3;
    }


    public void toque(View view){

        if(partida == null){
            return;
        }

        int id = view.getId();
        int i;
        int casillaElegida = 0;
        int resultado;

        for(i = 0; i < casillas.length ; i++){
            if(casillas[i] == id){
                casillaElegida = i;
                break;
            }
        }

        if(!partida.comprobarCasilla(casillaElegida)){
            return;
        }
        marca(casillaElegida);
        resultado = partida.turno();
        if(resultado > 0){
            finDelJuego(resultado);
            return;
        }
        casillaElegida = partida.ia();
        while(!partida.comprobarCasilla(casillaElegida)){
            casillaElegida = partida.ia();
        }

        marca(casillaElegida);
        resultado = partida.turno();
        if(resultado > 0){
            finDelJuego(resultado);
        }
    }

    public void marca(int casillaElegida){
        ImageView imagen = (ImageView)findViewById(casillas[casillaElegida]);
        if(partida.getJugador() == 1){
            imagen.setImageResource(R.drawable.aspa);
        }else{
            imagen.setImageResource(R.drawable.circle);
        }
    }

    public void finDelJuego(int resultado){
        view_result.setVisibility(View.VISIBLE);
        view_continue.setVisibility(View.VISIBLE);
        if(resultado == 1){

            player1Points++;
            if(player1Points == 1){
                view_jugador1.setImageResource(R.drawable.uno);
                view_result.setImageResource(R.drawable.tuganas);
            }
            else if(player1Points == 2){
                view_jugador1.setImageResource(R.drawable.dos);
                view_result.setImageResource(R.drawable.tuganas);
            }
            else{
                view_jugador1.setImageResource(R.drawable.tres);
                view_result.setImageResource(R.drawable.ganaste);
                endOfGame = true;
                view_continue.setImageResource(R.drawable.play_again);
            }



        }else if(resultado == 2){

            machinePoints++;
            if(machinePoints == 1){
                view_machine.setImageResource(R.drawable.uno);
                view_result.setImageResource(R.drawable.pc_gana);
            }
            else if(machinePoints == 2){
                view_machine.setImageResource(R.drawable.dos);
                view_result.setImageResource(R.drawable.pc_gana);
            }
            else{
                view_machine.setImageResource(R.drawable.tres);
                view_result.setImageResource(R.drawable.perdiste);
                endOfGame = true;
                view_continue.setImageResource(R.drawable.play_again);
            }

        }else{
            view_result.setImageResource(R.drawable.empate);
        }

        partida = null;

    }

    public void cleanTable(View view){
        ((ImageView)(findViewById(R.id.a1))).setImageResource(R.drawable.casilla);
        ((ImageView)(findViewById(R.id.a2))).setImageResource(R.drawable.casilla);
        ((ImageView)(findViewById(R.id.a3))).setImageResource(R.drawable.casilla);
        ((ImageView)(findViewById(R.id.b1))).setImageResource(R.drawable.casilla);
        ((ImageView)(findViewById(R.id.b2))).setImageResource(R.drawable.casilla);
        ((ImageView)(findViewById(R.id.b3))).setImageResource(R.drawable.casilla);
        ((ImageView)(findViewById(R.id.c1))).setImageResource(R.drawable.casilla);
        ((ImageView)(findViewById(R.id.c2))).setImageResource(R.drawable.casilla);
        ((ImageView)(findViewById(R.id.c3))).setImageResource(R.drawable.casilla);
        partida = new PartidaVsMachine(difficulty);
        view_result.setVisibility(View.INVISIBLE);
        view_continue.setVisibility(View.INVISIBLE);

        if(endOfGame){
            saveData();
            showData();
            machinePoints = 0;
            player1Points = 0;
            view_jugador1.setImageResource(R.drawable.cero);
            view_machine.setImageResource(R.drawable.cero);
            view_continue.setImageResource(R.drawable.continuar);
            endOfGame = false;
        }

    }

    public void saveData() {
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Structure_BD.COLUMN2, player1Points);
        values.put(Structure_BD.COLUMN3, machinePoints);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Structure_BD.TABLE_NAME, null, values);
        Toast.makeText(this,"cod " + newRowId ,Toast.LENGTH_LONG).show();

    }


    public void showData(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
            Structure_BD.COLUMN2,
            Structure_BD.COLUMN3
        };

// Filter results WHERE "title" = 'My Title'
        /*String selection = Structure_BD.COLUMN1 + " = ?";
        String[] selectionArgs = {};*/

        String selection = Structure_BD.COLUMN1;
        String[] selectionArgs = {};

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                Structure_BD.COLUMN1 + " DESC";

        Cursor c = db.query(
                Structure_BD.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        c.moveToFirst();
        System.out.println("Puntaje jugador : " + c.getString(0));
        System.out.println("Puntaje Maquina : " + c.getString(1));

    }
}
