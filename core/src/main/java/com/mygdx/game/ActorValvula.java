package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Timer;

public class ActorValvula extends Actor implements Disposable {

    private Texture xnv;
    private Sprite miXnv;

    private Texture xnvA;
    private Sprite miXnvA;

    private Texture xnvTA;
    private Sprite miXnvTA;

    private Texture xnvTC;
    private Sprite miXnvTC;

    private Sprite miXnvTEXTURE;

    private Texture xnvAnim;
    private TextureRegion miXnvRegion;
    private TextureRegion[][] temp1;
    private TextureRegion[] xnvFrames;
    private Animation<TextureRegion> xnvAnimation;
    private TextureRegion frame;

    private static final int ANCHO = 123;
    private static final int ALTO = 59;

    private Timer timerAbrir;
    private Timer timerCerrar;

    private Vector2 origen;
    private Vector2 fin;

    private Rectangle bounds = new Rectangle();

    private Boolean abierta;

    private Boolean cambioEstado;

    private Boolean permisivoA = true;
    private Boolean permisivoC = true;

    private Float alphaV;

    private Boolean be;
    private int cuenta;

    public ActorValvula() {
        xnv = new Texture("XNVCerrada.png");
        miXnv = new Sprite(xnv);

        xnvTA = new Texture("XNVTransicionA.png");
        miXnvTA = new Sprite(xnvTA);

        xnvTC = new Texture("XNVTransicionC.png");
        miXnvTC = new Sprite(xnvTC);

        xnvA = new Texture("XNVAbierta.png");
        miXnvA = new Sprite(xnvA);

        xnvAnim = new Texture("XNVsAnimation.png");
        miXnvRegion = new TextureRegion(xnvAnim);
        temp1 = miXnvRegion.split(ANCHO/3, ALTO);
        xnvFrames =  new TextureRegion [temp1.length * temp1[0].length];

        miXnvTEXTURE = miXnv;

        int indice = 0;

        for (int i = 0; i < temp1.length; i++){
            for (int j = 0; j < temp1[i].length; j++){
                xnvFrames[indice++] = temp1[i][j];
            }
        }
        xnvAnimation = new Animation<>(0.25f, xnvFrames);

        timerAbrir =new Timer();
        timerAbrir.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                animacionAbrir();
            }
        },1,0.5f);
        timerAbrir.stop();

        timerCerrar =new Timer();
        timerCerrar.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                animacionCerrar();
            }
        },1,0.5f);
        timerCerrar.stop();

        setSize(41, 59);
        setBounds(getX(), getY(), getWidth(), getHeight());

        origen = new Vector2(0, 16);
        fin = new Vector2(getWidth(), 16);
        //setOrigin(0, 16);
        abierta = false;
        cambioEstado = false;

        be = false;

        addCaptureListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //addAction(Actions.color(Color.RED));
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //cambioEstado = true;
//              alphaV = 0f;
                if(!getAbierta() && permisivoA) {
                    //addAction(Actions.color(Color.GREEN));
                    //setAbierta(true);
                    be = true;
                    cuenta = 0;
                    timerCerrar.stop();
                    timerAbrir.start();
                }else if (getAbierta() && permisivoC){
                    //addAction(Actions.color(Color.RED));
                    //setAbierta(false);
                    be = true;
                    cuenta = 0;
                    timerAbrir.stop();
                    timerCerrar.start();
                    //System.out.print(getAbierta());
                }

            }
        });

        //alphaV = 1f;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) { // parentAlpha sería el de algún grupo de actores
//        if (!abierta) {
//            alphaV = 1f;  // visible
//        } else {
//            alphaV = 0f;  // hago invisible la cerrada
//        }


        Color col = getColor(); //obtenemos una instancia del color de este actor
        batch.setColor(col.r, col.g, col.b, col.a);// modificamos el color si queremos
        //batch.setColor(col.r, col.g, col.b, alphaV);// modificamos el color si queremos

        //frame = xnvAnimation.getKeyFrame(duracion, true);

        batch.draw(miXnvTEXTURE, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

//        if (!abierta) {
//            //el batch que me pasa ya está abierto
//            batch.draw(miXnv, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
//        } else {
//            batch.draw(miXnvA, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
//        }




        //super.draw(batch, parentAlpha);

        updateBounds();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public Boolean getAbierta() {
        return this.abierta;
    }

    public void setAbierta() {
        be = true;
        cuenta = 0;
        timerCerrar.stop();
        timerAbrir.start();
    }

    public void setCerrada() {
        be = true;
        cuenta = 0;
        timerAbrir.stop();
        timerCerrar.start();
    }

    private void updateBounds() {
        //setZIndex(100);  // para que siempre se vea arriba
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

    private void animacionAbrir() {

        if (cuenta < 8) {
            if (be) {
                miXnvTEXTURE = miXnvTA;
            } else {
                miXnvTEXTURE = miXnv;
            }

        }
        else {
            miXnvTEXTURE = miXnvA;
            abierta = true;
            timerAbrir.stop();
        }
        be = !be;
        cuenta++;
        //System.out.println(cuenta);
    }

    private void animacionCerrar() {

        if (cuenta < 8) {
            if (be) {
                miXnvTEXTURE = miXnvTC;
            } else {
                miXnvTEXTURE = miXnvA;
            }

        }
        else {
            miXnvTEXTURE = miXnv;
            abierta = false;
            timerCerrar.stop();
        }
        be = !be;
        cuenta++;
        //System.out.println(cuenta);
    }

    private void cambiarEstado() {

        if(!getAbierta()) {
            abierta = true;
        }else{
            abierta = false;
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

    public Boolean getCambioEstado() {
        return cambioEstado;
    }

    public void setCambioEstado(Boolean cambioEstado) {
        this.cambioEstado = cambioEstado;
    }

    public Boolean getPermisivoA() {
        return permisivoA;
    }

    public void setPermisivoA(Boolean permisivoA) {
        this.permisivoA = permisivoA;
    }

    public Boolean getPermisivoC() {
        return permisivoC;
    }

    public void setPermisivoC(Boolean permisivoC) {
        this.permisivoC = permisivoC;
    }
}
