package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class ActorSeparador extends Actor implements Disposable {

    private Texture sep;
    private Sprite miSep;

    private Vector2 origen;
    private Vector2 fin;

    private Rectangle bounds = new Rectangle();

    private Boolean alarma;

    private Float alphaV;


    public ActorSeparador() {
        sep = new Texture("Separador.png");
        miSep = new Sprite(sep);
        setSize(159, 109);
        setBounds(getX(), getY(), getWidth(), getHeight());

        alarma = false;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) { // parentAlpha sería el de algún grupo de actores

        Color col = getColor(); //obtenemos una instancia del color de este actor
        batch.setColor(col.r, col.g, col.b, col.a);// modificamos el color si queremos

        //el batch que me pasa ya está abierto
        batch.draw(miSep, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        //super.draw(batch, parentAlpha);
        updateBounds();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    private void updateBounds() {
        setZIndex(90);  // para que siempre se vea arriba
        bounds.set(getX(), getY(), getWidth(), getHeight());
    }

    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public void dispose() {
        sep.dispose();
    }

}
