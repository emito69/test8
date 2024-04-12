package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.Auxiliares.VerificaAccion;

import java.util.Iterator;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class ActorGas extends Actor implements Disposable {

    private Array<ActorValvula> obstaculosValvulas;

    private Texture bolaGas;
    //private Sprite miBolaGas;
    private TextureRegion miBolaGas;
    private Vector2 origen;

    private Fondo fondo;
    private Array<Vector2> canosGasoSucc;
    private Array<Vector2> canosBypassSucc;
    private Array<Vector2> canosBypassDesc;
    private Array<Vector2> canosGasoDesc;
    private Array<Vector2> canosPlantaSucc;
    private Array<Vector2> canosPlanta;
    private Array<Vector2> canosPlantaDesc;

    private final VerificaAccion verificaAccion = new VerificaAccion();

    private LogicaGas logicaGas;

    private MoveToAction action;

    private PantallaEscena pantallaEscena;

    private Rectangle bounds = new Rectangle();

    private int finalpantalla;

    public ActorGas(PantallaEscena pantallaEscena) {

        this.pantallaEscena = pantallaEscena;

        obstaculosValvulas = new Array<ActorValvula>();

        bolaGas = new Texture("sphere-23.png");
        //miBolaGas = new Sprite(bolaGas);
        miBolaGas = new TextureRegion(bolaGas);

        setSize(21, 21);
        setBounds(getX(), getY(), getWidth(), getHeight());

        origen = new Vector2(getWidth() / 2, getHeight() / 2);

        setScale(1);

        canosGasoSucc = pantallaEscena.dameFondo().canosGasoSucc;
        canosBypassSucc = pantallaEscena.dameFondo().canosBypassSucc;
        canosBypassDesc = pantallaEscena.dameFondo().canosBypassDesc;
        canosGasoDesc = pantallaEscena.dameFondo().canosGasoDesc;
        canosPlantaSucc = pantallaEscena.dameFondo().canosPlantaSucc;
        canosPlantaDesc = pantallaEscena.dameFondo().canosPlantaDesc;

        verificaAccion.setCompleta(true);

        action = new MoveToAction();

        logicaGas = new LogicaGas(pantallaEscena);

        //miBolaGas.setScale(10); este no funciona porque luego abajo uso batch.draaw que no le da bola a lo que contiene el Sprite, porque lo toma como una textura nomas


    }

    @Override
    public void draw(Batch batch, float parentAlpha) { // parentAlpha sería el de algún grupo de actores
        //setZIndex(50);  // para que siempre se vea arriba
        Color col = getColor(); //obtenemos una instancia del color de este actor
        batch.setColor(col.r, col.g, col.b, col.a);// modificamos el color si queremos

        //el batch que me pasa ya está abierto

        batch.draw(miBolaGas, getX() - origen.x, getY() - origen.y, getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        //super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {

        //addAction(moveTo(canosGasoSucc.get(canosGasoSucc.size - 1).x, canosGasoSucc.get(canosGasoSucc.size -1).y, 1f));

        if (verificaAccion.isComplete()) {

            verificaAccion.setCompleta(false);

            Action ultimaAccion = LogicaGas.dameCamino(this, delta);
            addAction(sequence(ultimaAccion, verificaAccion));
        }

        updateBounds();

        obstaculosValvulas = pantallaEscena.dameValvulas();
        Iterator<ActorValvula> iter1 = obstaculosValvulas.iterator();

        while (iter1.hasNext()) {
            ActorValvula valvulaActual = iter1.next();

            if (getBounds().overlaps(valvulaActual.getBounds())) {
                    verificaAccion.setCompleta(true);
                    this.clearActions();
            }

        }

        super.act(delta);

        if (getY() <= canosGasoDesc.get(canosGasoDesc.size - 1).y){
            this.remove();
        }
    }

    private void updateBounds() {
        bounds.set(getX(), getY(), getWidth(), getHeight());
        setZIndex(3);
    }



    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public void dispose() {
        bolaGas.dispose();
    }

}
