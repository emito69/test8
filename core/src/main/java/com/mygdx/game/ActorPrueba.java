package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class ActorPrueba extends Actor implements Disposable {

    public Vector2 position;
    public float radio;

    Texture texture = new Texture("XNVAbierta.png");
    TextureRegion region = new TextureRegion(texture, 0, 0, 1, 1);

    private ShapeDrawer pruebaDrawer;

    public ActorPrueba() {

        position = new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);

        // radio = (float) (Gdx.graphics.getWidth()/2);
        radio = 1;


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        pruebaDrawer = new ShapeDrawer(batch, region);
        pruebaDrawer.setColor(Color.YELLOW);
        pruebaDrawer.filledCircle(this.position, this.radio);


    }

    @Override
    public void dispose() {

    }
}
