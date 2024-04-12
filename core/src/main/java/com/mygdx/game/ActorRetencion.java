package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class ActorRetencion extends Actor implements Disposable {

    private Texture retencion;
    private Sprite miRetencion;

    private Vector2 origen;
    private Vector2 fin;

    private Rectangle bounds = new Rectangle();


    public ActorRetencion() {

        retencion = new Texture("RETENCION.png");
        miRetencion = new Sprite(retencion);
        setSize(49, 35);
        setBounds(getX(), getY(), getWidth(), getHeight());

        origen = new Vector2(0, 26);
        fin = new Vector2(getWidth(), 26);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) { // parentAlpha sería el de algún grupo de actores
        Color col = getColor(); //obtenemos una instancia del color de este actor
        batch.setColor(col.r, col.g, col.b, col.a);// modificamos el color si queremos

        batch.draw(miRetencion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());


        updateBounds();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void dispose() { this.dispose();
    }

    private void updateBounds() {
        bounds.set(getX(), getY(), getWidth(), getHeight());
        //setZIndex(3);
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
