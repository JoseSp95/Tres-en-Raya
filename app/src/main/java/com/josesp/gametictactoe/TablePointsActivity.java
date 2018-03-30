package com.josesp.gametictactoe;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.josesp.gametictactoe.model.BBDD_Helper;
import com.josesp.gametictactoe.model.Structure_BD;

/**
 * Created by JoseSp on 21/08/2017.
 */

public class TablePointsActivity extends AppCompatActivity {

    private BBDD_Helper mDbHelper;
    private EditText text;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_points);
        mDbHelper = new BBDD_Helper(this);
        text = (EditText)findViewById(R.id.txtPunatjes);
        showData();
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
        while (c.moveToNext()){
            text.append("Jugador : " + c.getString(0) + " PC : " + c.getString(1) + " \n");
        }

    }

}
