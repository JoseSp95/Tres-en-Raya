package com.josesp.gametictactoe.model;

/**
 * Created by JoseSp on 21/08/2017.
 */

public class Structure_BD {

    public static final String TABLE_NAME = "Puntaje";
    public static final String COLUMN1 = "ID";
    public static final String COLUMN2 = "Jugador";
    public static final String COLUMN3 = "Maquina";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Structure_BD.TABLE_NAME + " (" +
                    Structure_BD.COLUMN1 + " INTEGER PRIMARY KEY," +
                    Structure_BD.COLUMN2 + TEXT_TYPE + COMMA_SEP +
                    Structure_BD.COLUMN3 + TEXT_TYPE + " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Structure_BD.TABLE_NAME;

}
