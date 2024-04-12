package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Array;

import static com.mygdx.game.juego.VELOCIDAD;
import static java.lang.Math.abs;

public class LogicaGas {

    private static ActorGas actorGas;
    private static Fondo fondo;
    private static Array<Vector2> canosGasoSucc;
    private static Array<Vector2> canosBypassSucc;
    private static Array<Vector2> canosBypassDesc;
    private static Array<Vector2> canosGasoDesc;
    private static Array<Vector2> canosPlantaSucc;
    private static Array<Vector2> canosPlanta;
    private static Array<Vector2> canosPlantaDesc;

    private static Vector2 origen;
    private static Vector2 destino;

    private static MoveToAction action = new MoveToAction();

    public LogicaGas(PantallaEscena pantallaEscena) {

        canosGasoSucc = pantallaEscena.dameFondo().canosGasoSucc;
        canosBypassSucc = pantallaEscena.dameFondo().canosBypassSucc;
        canosBypassDesc = pantallaEscena.dameFondo().canosBypassDesc;
        canosGasoDesc = pantallaEscena.dameFondo().canosGasoDesc;
        canosPlantaSucc = pantallaEscena.dameFondo().canosPlantaSucc;
        canosPlantaDesc = pantallaEscena.dameFondo().canosPlantaDesc;

    }


    public static MoveToAction dameCamino(ActorGas actorGas, float delta){

        actorGas = actorGas;
        origen = canosGasoSucc.get(0);
        //action = Actions.moveTo(canosGasoSucc.get(canosGasoSucc.size - 1).x, canosGasoSucc.get(canosGasoSucc.size -1).y, 5f);
        // destino = canosGasoSucc.get(canosGasoSucc.size - 1);
        destino = canosGasoDesc.get(canosGasoDesc.size - 1);
        //action = Actions.moveTo(destino.x, destino.y, ((abs(origen.x-destino.x)+abs(origen.y-destino.y))  * delta * VELOCIDAD));
        action = Actions.moveTo(destino.x, destino.y, ((abs(actorGas.getX()-destino.x)+abs(actorGas.getY()-destino.y)) * delta * VELOCIDAD));

        /*
        if (actorGas.getX() == canosBypassSucc.get(0).x && actorGas.getY() == canosBypassSucc.get(0).y) {
            origen = canosBypassSucc.get(0);
            destino = canosBypassSucc.get(canosBypassSucc.size - 1);
            action = Actions.moveTo(destino.x, destino.y, (abs(origen.x-destino.x)+abs(origen.y-destino.y))* delta * 1f);

        } else {
            origen = canosBypassSucc.get(canosBypassSucc.size - 1);
            destino = canosGasoSucc.get(canosGasoSucc.size - 1);
            action = Actions.moveTo(destino.x, destino.y, (abs(origen.x-destino.x)+abs(origen.y-destino.y))* delta * 1f);
        }
        */

        return action;

    }



}
