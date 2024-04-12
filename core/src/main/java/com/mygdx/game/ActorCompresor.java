package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class ActorCompresor extends Actor implements Disposable {

    private Texture cgn;
    private Sprite miCgn;

    private PantallaEscena pantallaEscena;
    private static Array<ActorValvula> valvulas;
    private boolean permisivo;

    private Vector2 origen;
    private Vector2 fin;

    private Rectangle bounds = new Rectangle();

    private float relComp = 5f;

    private Boolean enCarga;

    private Boolean alarma;

    private Float alphaV;


    public ActorCompresor(PantallaEscena pantallaEscena) {
        cgn = new Texture("CGN.png");
        miCgn = new Sprite(cgn);
        //setSize(136, 155);
        setSize(100, 100);
        //setScale(0.75f);
        setBounds(getX(), getY(), getWidth(), getHeight());

        this.pantallaEscena = pantallaEscena;

        alarma = false;
        enCarga =false;

        addCaptureListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //addAction(Actions.color(Color.RED));
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                cambioEstado = true;
//                alphaV = 0f;

                if(!getEnCarga()) {
                    addAction(Actions.color(Color.GREEN));
                    setEnCarga(true);
                    System.out.println(enCarga);
                }else{
                    addAction(Actions.color(Color.WHITE));
                    setEnCarga(false);
                    System.out.println(enCarga);
                }
            }
        });

    }

    @Override
    public void draw(Batch batch, float parentAlpha) { // parentAlpha sería el de algún grupo de actores

        Color col = getColor(); //obtenemos una instancia del color de este actor
        batch.setColor(col.r, col.g, col.b, col.a);// modificamos el color si queremos

        //el batch que me pasa ya está abierto
        batch.draw(miCgn, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        //super.draw(batch, parentAlpha);
        updateBounds();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    private void updateBounds() {
        setZIndex(100);  // para que siempre se vea arriba
        bounds.set(getX(), getY(), getWidth(), getHeight());
    }

    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public void dispose() {
        cgn.dispose();
    }

    public float getRelComp() {
        return relComp;
    }

    public Boolean getEnCarga() {
        return enCarga;
    }

    public void setEnCarga(Boolean enCarga) {
        this.enCarga = enCarga;
    }
}
