package com.mygdx.game;

import com.badlogic.gdx.utils.Array;

public class LogicaValvulas {

    private PantallaEscena pantallaEscena;
    private static Array<ActorPIT> transmisores;
    private static Array<ActorValvula> valvulas;
    private static Array<Float> valores;
    private static Array<ActorDPSH> presostatosDif;


    public LogicaValvulas(PantallaEscena pantallaEscena) {
        this.pantallaEscena = pantallaEscena;

        this.transmisores = pantallaEscena.dameTransmisores();

        this.presostatosDif = pantallaEscena.damePresostatosDif();

        this.valvulas = pantallaEscena.dameValvulas();

        //this.valores = new Array<Float>();

        this.valores = pantallaEscena.dameValores();

    }


    public void act () {

        //*********  PERMISIVO DE VÁLVULAS  ********** ////

        //-- BYPASS GASO

        // permisivo Apretura

        if (presostatosDif.get(0).isEstado()){
            valvulas.get(0).setPermisivoA(true);
        }
        else {
            valvulas.get(0).setPermisivoA(false);
        }

        //-- SUCCIÓN

        // permisivo Apretura

        if (presostatosDif.get(1).isEstado()){
            valvulas.get(1).setPermisivoA(true);
        }
        else {
            valvulas.get(1).setPermisivoA(false);
        }

        //-- DESCARGA

        // permisivo Apretura
        if (presostatosDif.get(2).isEstado()){
            valvulas.get(11).setPermisivoA(true);
        }
        else {
            valvulas.get(11).setPermisivoA(false);
        }

        //-- ENTRADA Separador 1

        // permisivo Apretura

        if (presostatosDif.get(3).isEstado()){
            valvulas.get(3).setPermisivoA(true);
        }
        else {
            valvulas.get(3).setPermisivoA(false);
        }

        //-- ENTRADA Separador 2

        // permisivo Apretura

        if (presostatosDif.get(4).isEstado()){
            valvulas.get(6).setPermisivoA(true);
        }
        else {
            valvulas.get(6).setPermisivoA(false);
        }

    }

    public boolean BypassABIERTA () {
        return valvulas.get(0).getAbierta();
    }

    public boolean SuccionABIERTA () {
        return valvulas.get(1).getAbierta();
    }

    public boolean PresABIERTA () {
        return valvulas.get(2).getAbierta();
    }

    public boolean Sep1SuccABIERTA () {
        return valvulas.get(3).getAbierta();
    }

    public boolean Sep1PresABIERTA () {
        return valvulas.get(4).getAbierta();
    }

    public boolean Sep1DescABIERTA () {
        return valvulas.get(5).getAbierta();
    }

    public boolean Sep2EntradaABIERTA () {
        return valvulas.get(6).getAbierta();
    }

    public boolean Sep2PresABIERTA () {
        return valvulas.get(7).getAbierta();
    }

    public boolean Sep2DescABIERTA () {
        return valvulas.get(8).getAbierta();
    }

    public boolean VenteoSuccABIERTA () {
        return valvulas.get(9).getAbierta();
    }

    public boolean VenteoDescABIERTA () {
        return valvulas.get(10).getAbierta();
    }

    public boolean DescargaABIERTA () {
        return valvulas.get(11).getAbierta();
    }

}
