package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class ActorValvulaAbierta extends Actor implements Disposable {

    private Texture xnv;
    private Sprite miXnv;

    private Vector2 origen;
    private Vector2 fin;

    private Rectangle bounds = new Rectangle();

    private Boolean abierta;

    private Float alphaV;


    public ActorValvulaAbierta() {
        xnv = new Texture("XNVAbierta.png");
        miXnv = new Sprite(xnv);
        setSize(41, 59);
        setBounds(getX(), getY(), getWidth(), getHeight());

        origen = new Vector2(0, 16);
        fin = new Vector2(getWidth(), 16);
        //setOrigin(0, 16);
        abierta = true;
/*
        addCaptureListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // valvula0.addAction(Actions.color(Color.RED));
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(!abierta) {
                    abierta = true;
                }else{
                    abierta = false;
                }
            }
        });*/

    }

    @Override
    public void draw(Batch batch, float parentAlpha) { // parentAlpha sería el de algún grupo de actores

        if (abierta) {
            alphaV = 1f;  // visible
        } else {
            alphaV = 0f;  // hago invisible la cerrada
        }

        Color col = getColor(); //obtenemos una instancia del color de este actor
        batch.setColor(col.r, col.g, col.b, alphaV);// modificamos el color si queremos

        //el batch que me pasa ya está abierto
        batch.draw(miXnv, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        //super.draw(batch, parentAlpha);
        updateBounds();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public Boolean getAbierta() {
        return abierta;
    }

    public void setAbierta(Boolean abierta) {
        this.abierta = abierta;
    }

    private void updateBounds() {
        setZIndex(4);  // para que siempre se vea arriba
        if (!abierta) {
            if (getRotation() == 270) {
                bounds.set(getX(), getY() - getWidth()/4, getHeight() + 2, getWidth()/4 + 2);

                //System.out.println("ROTATION x/y: " + getRight() + " / " + getTop()  + " / " + getWidth() + " / " + getHeight());
            } else {
                bounds.set(getX(), getY(), getWidth()/4 + 2, getHeight() + 2);

            }
        }else{
            bounds.set(getX(), getY(), 0, 0);
        }

    }

    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public void dispose() {
        xnv.dispose();
    }




    /// getters / setters
    public Vector2 getOrigen() {
        return origen;
    }

    public Vector2 getFin() {
        return fin;
    }
}
