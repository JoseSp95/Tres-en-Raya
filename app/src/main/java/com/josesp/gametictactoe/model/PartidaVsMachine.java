package com.josesp.gametictactoe.model;

import java.util.Random;

public class PartidaVsMachine {

    private int dificultad;
    private int jugador = 1;
    private int [] casillas;
    private int [][] combinaciones = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    public PartidaVsMachine(int dificultad){
        this.dificultad = dificultad;
        this.casillas = new int[9];
        for (int i = 0; i < 9 ; i++){
            casillas[i] = 0;
        }
    }

    public int getJugador() {
        return jugador;
    }

    public int ia(){
        int casilla;
        casilla = dosEnRaya(2);
        if(casilla != -1){
            return casilla;
        }
        if(dificultad >= 1){
            casilla = dosEnRaya(1);
            if(casilla != -1){
                return casilla;
            }
        }

        if(dificultad == 2){
            if(casillas[0] == 0)
                return 0;
            if(casillas[2] == 0)
                return 2;
            if(casillas[6] == 0)
                return 6;
            if(casillas[8] == 0){
                return 8;
            }
        }


        casilla = new Random().nextInt(9);
        return casilla;
    }

    public int turno(){
        int i;
        boolean ganador;
        boolean empate = true;
        int jugadorAux = jugador;

        jugador++;
        if(jugador > 2){
            jugador = 1;
        }

        for(i = 0; i < combinaciones.length; i++){
            ganador = true;
            for(int pos : combinaciones[i]){
                if(casillas[pos] != jugadorAux){
                    ganador = false;
                }

                if(casillas[pos] == 0){
                    empate = false;
                }
            }

            if(ganador){
                return jugadorAux;
            }

        }

        if(empate){
            return 3;
        }


        return 0;
    }

    public boolean comprobarCasilla(int numberOfCasilla){
        if(casillas[numberOfCasilla] != 0){
            return false;
        }else{
            casillas[numberOfCasilla] = jugador;
            return true;
        }
    }

    private int dosEnRaya(int jugadorEnTurno){
        int casilla;
        int nroCasillasAcertadas;
        int i;

        for(i = 0 ; i < combinaciones.length; i++){
            nroCasillasAcertadas = 0;
            casilla = -1;
            for(int pos : this.combinaciones[i]){
                if(casillas[pos] == jugadorEnTurno){
                    nroCasillasAcertadas++;
                }
                if(casillas[pos] == 0){
                    casilla = pos;
                }
            }

            if(nroCasillasAcertadas == 2 && casilla != -1){
                return casilla;
            }

        }
        return -1;
    }

}
