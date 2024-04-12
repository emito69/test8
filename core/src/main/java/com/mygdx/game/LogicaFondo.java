package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.mygdx.game.PantallaEscena.MAPO;

public class LogicaFondo {

    private PantallaEscena pantallaEscena;

    private Fondo fondo;
    private ActorFondoGasoSucc fondoGasoSucc;
    private ActorFondoGasoDesc fondoGasoDesc;
    private ActorFondoPlantaSucc fondoPlantaSucc;
    private ActorFondoSeparador1 fondoSeparador1;
    private ActorFondoSeparador2 fondoSeparador2;
    private ActorFondoColectorSUCC fondoColectorSUCC;
    private ActorFondoColectorDESC fondoColectorDESC;
    private ActorFondoVent1 fondoVent1;
    private ActorFondoVent2 fondoVent2;


    private static Array<ActorPIT> transmisores;
    private static Array<Float> valores;


    public LogicaFondo(PantallaEscena pantallaEscena) {

        this.pantallaEscena = pantallaEscena;

        this.valores = pantallaEscena.dameValores();

        this.fondo = pantallaEscena.dameFondo();

    }


    public void act () {

        // *** PIT-12001  *** //
        if(valores.get(0) < (0.10f * MAPO)) {

            pantallaEscena.getFondoGasoSucc().addAction(parallel(color(Color.LIGHT_GRAY, 4), delay(0.5f)));
        }

        else if (valores.get(0) >= (0.10f * MAPO) && valores.get(0) <= (0.55f * MAPO)) {

            pantallaEscena.getFondoGasoSucc().addAction(parallel(color(Color.SKY, 4), delay(0.5f)));
        }

        else if (valores.get(0) > (0.55f * MAPO)){

            pantallaEscena.getFondoGasoSucc().addAction(parallel(color(Color.CORAL, 4), delay(0.5f)));
        }
        // ***************** //

        // *** PIT-12002  *** //
        if(valores.get(1) < (0.10f * MAPO)) {

            pantallaEscena.getFondoGasoDesc().addAction(parallel(color(Color.LIGHT_GRAY, 4), delay(0.5f)));
        }

        else if (valores.get(1) >= (0.10f * MAPO) && valores.get(1) <= (0.55f * MAPO)) {

            pantallaEscena.getFondoGasoDesc().addAction(parallel(color(Color.SKY, 4), delay(0.5f)));
        }

        else if (valores.get(1) > (0.55f * MAPO)){

            pantallaEscena.getFondoGasoDesc().addAction(parallel(color(Color.CORAL, 4), delay(0.5f)));
        }
        // ***************** //

        // *** PIT-5001  *** //
        if(valores.get(2) < (0.10f * MAPO)) {

            pantallaEscena.getFondoPlantaSucc().addAction(parallel(color(Color.LIGHT_GRAY, 4), delay(0.5f)));
        }

        else if (valores.get(2) >= (0.10f * MAPO) && valores.get(2) <= (0.55f * MAPO)) {
            pantallaEscena.getFondoPlantaSucc().addAction(parallel(color(Color.SKY, 4), delay(0.5f)));
        }

        else if (valores.get(2) > (0.55f * MAPO)){
            pantallaEscena.getFondoPlantaSucc().addAction(parallel(color(Color.CORAL, 4), delay(0.5f)));
        }
        // ***************** //

        // *** PIT-SEP1  *** //
        if(valores.get(6) < (0.10f * MAPO)) {

            pantallaEscena.getFondoSeparador1().addAction(parallel(color(Color.LIGHT_GRAY, 4), delay(0.5f)));
        }

        else if (valores.get(6) >= (0.10f * MAPO) && valores.get(6) <= (0.55f * MAPO)) {
            pantallaEscena.getFondoSeparador1().addAction(parallel(color(Color.SKY, 4), delay(0.5f)));
        }

        else if (valores.get(6) > (0.55f * MAPO)){
            pantallaEscena.getFondoSeparador1().addAction(parallel(color(Color.CORAL, 4), delay(0.5f)));
        }
        // ***************** //

        // *** PIT-SEP2  *** //
        if(valores.get(7) < (0.10f * MAPO)) {

            pantallaEscena.getFondoSeparador2().addAction(parallel(color(Color.LIGHT_GRAY, 4), delay(0.5f)));
        }

        else if (valores.get(7) >= (0.10f * MAPO) && valores.get(7) <= (0.55f * MAPO)) {
            pantallaEscena.getFondoSeparador2().addAction(parallel(color(Color.SKY, 4), delay(0.5f)));
        }

        else if (valores.get(7) > (0.55f * MAPO)){
            pantallaEscena.getFondoSeparador2().addAction(parallel(color(Color.CORAL, 4), delay(0.5f)));
        }
        // ***************** //

        // *** PIT-5100  *** //
        if(valores.get(3) < (0.10f * MAPO)) {

            pantallaEscena.getFondoColectorSUCC().addAction(parallel(color(Color.LIGHT_GRAY, 4), delay(0.5f)));
        }

        else if (valores.get(3) >= (0.10f * MAPO) && valores.get(3) <= (0.55f * MAPO)) {
            pantallaEscena.getFondoColectorSUCC().addAction(parallel(color(Color.SKY, 4), delay(0.5f)));
        }

        else if (valores.get(3) > (0.55f * MAPO)){
            pantallaEscena.getFondoColectorSUCC().addAction(parallel(color(Color.CORAL, 4), delay(0.5f)));
        }
        // ***************** //

        // *** PIT-5200  *** //
        if(valores.get(4) < (0.10f * MAPO)) {

            pantallaEscena.getFondoColectorDESC().addAction(parallel(color(Color.LIGHT_GRAY, 4), delay(0.5f)));
        }

        else if (valores.get(4) >= (0.10f * MAPO) && valores.get(4) <= (0.55f * MAPO)) {
            pantallaEscena.getFondoColectorDESC().addAction(parallel(color(Color.SKY, 4), delay(0.5f)));
        }

        else if (valores.get(4) > (0.55f * MAPO)){
            pantallaEscena.getFondoColectorDESC().addAction(parallel(color(Color.CORAL, 4), delay(0.5f)));
        }
        // ***************** //

/*        // *** PIT-5300  *** //
        if(valores.get(4) < (0.10f * MAPO)) {

            pantallaEscena.getFondoColectorDESC().addAction(parallel(color(Color.LIGHT_GRAY, 4), delay(0.5f)));
        }

        else if (valores.get(4) >= (0.10f * MAPO) && valores.get(4) <= (0.55f * MAPO)) {
            pantallaEscena.getFondoColectorDESC().addAction(parallel(color(Color.SKY, 4), delay(0.5f)));
        }

        else if (valores.get(4) > (0.55f * MAPO)){
            pantallaEscena.getFondoColectorDESC().addAction(parallel(color(Color.CORAL, 4), delay(0.5f)));
        }
        // ***************** //*/

    }




}
