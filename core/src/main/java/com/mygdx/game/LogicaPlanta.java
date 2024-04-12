package com.mygdx.game;

import com.badlogic.gdx.utils.Array;

import static com.mygdx.game.PantallaEscena.PJ1;
import static com.mygdx.game.PantallaEscena.PJ2;

public class LogicaPlanta {

    // public static final float MAPO = 10;
    // public static boolean PJ1 = false;
    // public static boolean PJ2 = false;


    private PantallaEscena pantallaEscena;
    private Array<ActorPIT> transmisores;
    private Array<ActorValvula> valvulas;
    private Array<Float> valores;
    private Array<ActorDPSH> presostatosDif;
    //EMI2    private ActorMenuEstadoGASO menuEstadoGASO;
//EMI2    private ActorMenuPJ1PJ2 menuPJ1PJ2;
//EMI2    private ActorMenuACKRST menuackres;

    public LogicaPlanta(PantallaEscena pantallaEscena) {
        this.pantallaEscena = pantallaEscena;

        this.transmisores = pantallaEscena.dameTransmisores();

        this.presostatosDif = pantallaEscena.damePresostatosDif();

        this.valvulas = pantallaEscena.dameValvulas();

        this.valores = new Array<Float>();

        this.valores = pantallaEscena.dameValores();

//EMI2        this.menuEstadoGASO = pantallaEscena.dameMenuEstadoGASO();
//EMI2        this.menuPJ1PJ2 = pantallaEscena.dameMenuPJ1PJ2();


    }


    public void act() {


        // ********  PJ1  ******** //

        if (PJ1) {
            //EMI2         menuPJ1PJ2.setbuttonPJ1Color(Color.RED);

        } else {

            //EMI2           menuPJ1PJ2.setbuttonPJ1Color(Color.WHITE);
        }


        // ********  PJ2  ******** //

        if (PJ2) {
            //menuPJ1PJ2.setbuttonPJ2Color(new Color(159,152,0,1));
            //EMI2    menuPJ1PJ2.setbuttonPJ2Color(Color.RED);

        } else {

            //EMI2    menuPJ1PJ2.setbuttonPJ2Color(Color.WHITE);
        }

        // ********  ACK  ******** //



        // ********  RST  ******** //




        // ********  ESTADO DE PLANTA  ******** //

        //EMI2   pantallaEscena.actualizarEstadoGASO();
        //EMI2   pantallaEscena.actualizarEstadoPLANTA();

/*
        switch (i) {

            case 0:

                break;

            case 1:

                break;


        }*/

    }


}
