package com.mygdx.game;

import com.badlogic.gdx.utils.Array;

import static java.lang.Math.abs;

public class LogicaDPSHs {

    private PantallaEscena pantallaEscena;
    private static Array<ActorPIT> transmisores;
    private static Array<ActorDPSH> presostatosDif;
    private static Array<ActorValvula> valvulas;
    private static Array<Float> valores;



    public LogicaDPSHs(PantallaEscena pantallaEscena) {
        this.pantallaEscena = pantallaEscena;

        this.transmisores = pantallaEscena.dameTransmisores();

        this.presostatosDif = pantallaEscena.damePresostatosDif();

        this.valvulas = pantallaEscena.dameValvulas();

        this.valores = pantallaEscena.dameValores();

    }

    public void act () {

        //*********  PERMISIVO DE VÁLVULAS  ********** ////


        //**-- BYPASS GASO
            // Apertura

        if (abs(valores.get(0)-valores.get(1)) < 2f){
            presostatosDif.get(0).setEstado(true);

        }
        else {
            presostatosDif.get(0).setEstado(false);
        }
            // Cierre


        //**-- SUCCIÓN
            // Apertura
        if (abs(valores.get(0)-valores.get(2)) < 2f){
            presostatosDif.get(1).setEstado(true);
        }
        else {
            presostatosDif.get(1).setEstado(false);
        }
            // Cierre


        //**-- DESCARGA
            // Apertura
        if (abs(valores.get(1)-valores.get(5)) < 2f){
            presostatosDif.get(2).setEstado(true);
        }
        else {
            presostatosDif.get(2).setEstado(false);
        }
            // Cierre


        //**-- ENTRADA Separador 1
            // Apertura
        if (abs(valores.get(2)-valores.get(6)) < 2f){
            presostatosDif.get(3).setEstado(true);
        }
        else {
            presostatosDif.get(3).setEstado(false);
        }
             // Cierre


        //**-- ENTRADA Separador 2
            // Apertura
        if (abs(valores.get(2)-valores.get(7)) < 2f){
            presostatosDif.get(4).setEstado(true);
        }
        else {
            presostatosDif.get(4).setEstado(false);
        }

            // Cierre

    }
}
