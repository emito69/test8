package com.mygdx.game;

import com.badlogic.gdx.utils.Timer;

public class TareasPeriodicas {



    private Timer timer;

    /*Timer has clear() and stop() method that help in removing all scheduled tasks
    and stops the timer respectively that may start again.*/

    private PantallaEscena pantallaEscena;


    public TareasPeriodicas(PantallaEscena pantallaEscena) {

        this.pantallaEscena = pantallaEscena;

        timer =new Timer();

        //*** 0.5 segundo *** ///

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                ejecutarLogicas();
            }
        },2,0.5f);            //  -->  2 is your first delay and 0.5 is interval in sec.

        //*** 5 segundos *** ///
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                gasNuevo();
            }
        },2,5f);            //  -->  2 is your first delay and 5 is interval in sec.

    }


    private void ejecutarLogicas() {

        pantallaEscena.dameLogicaPITs().act();
        pantallaEscena.dameLogicaDPSHs().act();
        pantallaEscena.dameLogicaValvulas().act();
        pantallaEscena.dameLogicaFondo().act();
        pantallaEscena.dameLogicaPlanta().act();
        pantallaEscena.actualizarEstadoGASO();
        pantallaEscena.dameLogicaMenuEstadoGASO().act();

    }

    private void gasNuevo() {
        pantallaEscena.gasNuevo();
    }


    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }



}
