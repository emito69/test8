package com.mygdx.game;


public class LogicaMenuEstadoGASO {


    private PantallaEscena pantallaEscena;

    public LogicaMenuEstadoGASO(PantallaEscena pantallaEscena) {
        this.pantallaEscena = pantallaEscena;

    }


    public void act() {

        pantallaEscena.dameMenuEstadoGASO().setTextoESTADO(pantallaEscena.getLeyendaEstadoGASO());

    }

}

