package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import space.earlygrey.shapedrawer.JoinType;
import space.earlygrey.shapedrawer.ShapeDrawer;


public class ActorFondoSeparador1 extends Actor implements Disposable {

    //private ShapeRenderer shaper;
    //Texture texture = new Texture("XNVAbierta.png");
    //TextureRegion region = new TextureRegion(texture, 0, 0, 1, 1);

    private Pixmap pixmap;
    private TextureRegion region;

    private Fondo fondo;

    private ShapeDrawer drawer;


    public ActorFondoSeparador1(Fondo fondo) {

        /*** texture para ShapeDrawer *****/
        pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.LIGHT_GRAY);
        pixmap.drawPixel(0, 0);
        Texture texture = new Texture(pixmap); //remember to dispose of later
        pixmap.dispose();
        region = new TextureRegion(texture, 0, 0, 1, 1);
        /******** ******/

        this.fondo = fondo;

        // COLOR DEL ACTOR AL INICIO
        setColor(Color.LIGHT_GRAY);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {

        setZIndex(0);

        drawer = new ShapeDrawer(batch, region);
        drawer.setColor(getColor());

        drawer.path(fondo.canosSep1SuccIN, 11, JoinType.SMOOTH,true); //21
        drawer.path(fondo.canosSep1PresIN, 5, JoinType.SMOOTH,true); //10

        drawer.path(fondo.canosSep1DescIN, 11, JoinType.SMOOTH,true); //21

    }

    @Override
    public void dispose() {

    }

}



