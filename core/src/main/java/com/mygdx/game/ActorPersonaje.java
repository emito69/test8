package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class ActorPersonaje extends Actor implements Disposable {

    private Texture avatar1;
    private TextureRegion miAvatar1;
    private Texture avatar2;
    private TextureRegion miAvatar2;
    private TextureRegion[][] temp1;
    private TextureRegion[][] temp2;
    private TextureRegion[] avatarFrames1;
    private TextureRegion[] avatarFrames2;
    private Animation<TextureRegion> avatarAnimation1;
    private Animation<TextureRegion> avatarAnimation2;
    private TextureRegion frame1;
    private TextureRegion frame2;
    private TextureRegion frameIdle;

    private float duracion;

    private boolean idle = false;

    private static final int ANCHO = 308;
    private static final int ALTO = 84;

    private Rectangle bounds = new Rectangle();

    private Float alphaV;


    public ActorPersonaje() {
        //avatar1 = new Texture("iso-avatar-draft1.png");
        avatar1 = new Texture("iso-avatar-draftCasco11.png");
        miAvatar1 = new TextureRegion(avatar1);


        //avatar2 = new Texture("iso-avatar-draft2.png");
        avatar2= new Texture("iso-avatar-draftCasco12.png");
        miAvatar2 = new TextureRegion(avatar2);

        setSize(44, 84);
        setScale(2f);

        //setBounds(getX(), getY(), getWidth(), getHeight());
        temp1 = miAvatar1.split(ANCHO/7, ALTO);
        avatarFrames1 =  new TextureRegion [temp1.length * temp1[0].length];

        temp2 = miAvatar2.split(ANCHO/7, ALTO);
        avatarFrames2 =  new TextureRegion [temp2.length * temp2[0].length];

        duracion = 0;

        int indice = 0;

        for (int i = 0; i < temp1.length; i++){
            for (int j = 0; j < temp1[i].length; j++){
                avatarFrames1[indice++] = temp1[i][j];
            }
        }
        avatarAnimation1 = new Animation<>(0.25f, avatarFrames1);

        indice = 0;

        for (int i = 0; i < temp2.length; i++){
            for (int j = 0; j < temp2[i].length; j++){
                avatarFrames2[indice++] = temp2[i][j];
            }
        }
        avatarAnimation2 = new Animation<>(0.25f, avatarFrames2);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) { // parentAlpha sería el de algún grupo de actores

        Color col = getColor(); //obtenemos una instancia del color de este actor
        batch.setColor(col.r, col.g, col.b, col.a);// modificamos el color si queremos

        frameIdle = avatarAnimation1.getKeyFrame(5f, true);
        frame1 = avatarAnimation1.getKeyFrame(duracion, true);
        frame2 = avatarAnimation2.getKeyFrame(duracion, true);

        if (!idle) {
            if (duracion < 3) {
                //el batch que me pasa ya está abierto
                batch.draw(frame1, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
                //super.draw(batch, parentAlpha);
            } else {
                //el batch que me pasa ya está abierto
                batch.draw(frame2, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
                //super.draw(batch, parentAlpha);
            }
            if (duracion > 6) {
                duracion = 0;
            }
        }
        else{
            batch.draw(frameIdle, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }


        updateBounds();

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        duracion += delta;
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
        avatar1.dispose();
        //avatar2.dispose();
    }

    public void setIdle(boolean idle) {
        this.idle = idle;
    }

}
