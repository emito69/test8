package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class ActorDPSH extends Actor implements Disposable {

    private Texture dpsh;
    private TextureRegion miDpsh;

    private boolean estado = true;

    public ActorDPSH(){

        dpsh = new Texture("sphere-00.png");
        miDpsh = new TextureRegion(dpsh);

        setSize(21, 21);
        setScale(0.75f);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) { // parentAlpha sería el de algún grupo de actores
        Color col = getColor(); //obtenemos una instancia del color de este actor
        batch.setColor(col.r, col.g, col.b, col.a);// modificamos el color si queremos

        //el batch que me pasa ya está abierto

        batch.draw(miDpsh, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        //super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (estado) {
            setVisible(true);
        } else {
            setVisible(false);
        }

    }

    @Override
    public void dispose() {
        dpsh.dispose();
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
