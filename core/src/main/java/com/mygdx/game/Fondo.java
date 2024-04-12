package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import space.earlygrey.shapedrawer.ShapeDrawer;


public class Fondo extends Actor implements Disposable {

    //private ShapeRenderer shaper;
    //Texture texture = new Texture("XNVAbierta.png"); // necesito una imagen para agarrar un pixel
    //TextureRegion region = new TextureRegion(texture, 0, 0, 1, 1);

    private Pixmap pixmap;
    private TextureRegion region;
    private ShapeDrawer drawer;

    public Array<Vector2> canosGasoSucc;
    public Array<Vector2> canosBypassSucc;

    public Array<Vector2> canosPlantaSucc;
    public Array<Vector2> canosPresSucc;
    public Array<Vector2> canosPresDesc;

    public Array<Vector2> canosSep1Succ;
    public Array<Vector2> canosSep1Pres;
    public Array<Vector2> canosSep2Succ;
    public Array<Vector2> canosSep2Pres;

    public Array<Vector2> canosSep1SuccIN;
    public Array<Vector2> canosSep1PresIN;
    public Array<Vector2> canosSep2SuccIN;
    public Array<Vector2> canosSep2PresIN;

    public Array<Vector2> canosSep1DescIN;
    public Array<Vector2> canosSep2DescIN;

    public Array<Vector2> canosSep2DescOUT;

    public Array<Vector2> canosColSucc;
    public Array<Vector2> canosBypassColSucc;

    public Array<Vector2> canosVent1IN;
    public Array<Vector2> canosVent1OUT;
    public Array<Vector2> canosVent2IN;
    public Array<Vector2> canosVent2OUT;

    public Array<Vector2> canosColDesc;

    public Array<Vector2> canosPlantaDesc;
    public Array<Vector2> canosBypassColDesc;

    public Array<Vector2> canosBypassDesc;
    public Array<Vector2> canosGasoDesc;

    private Array<ActorValvula> valvulas;
    private Array<ActorSeparador> separadores;
    private ActorCompresor compresor;
    private Array<ActorRetencion> retenciones;

    private PantallaEscena pantallaEscena;


    public Fondo(PantallaEscena pantallaEscena) {

//        /*** texture para ShapeDrawer *****/
//        pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
//        pixmap.setColor(Color.LIGHT_GRAY);
//        pixmap.drawPixel(0, 0);
//        Texture texture = new Texture(pixmap); //remember to dispose of later
//        pixmap.dispose();
//        region = new TextureRegion(texture, 0, 0, 1, 1);
//        /******** ******/

        this.pantallaEscena = pantallaEscena;

        this.valvulas = pantallaEscena.dameValvulas();
        this.compresor = pantallaEscena.dameCompresor();
        this.separadores = pantallaEscena.dameSeparadores();
        this.retenciones = pantallaEscena.dameRetenciones();



        // ******************** //
        //  GASODUCTO y BYPASS  //
        // ******************** //

        canosGasoSucc = new Array<Vector2>();
        //canosGasoSucc.add(new Vector2(valvulas.get(0).getX()+16, valvulas.get(1).getY()+250));
        canosGasoSucc.add(new Vector2(valvulas.get(0).getX()+16, pantallaEscena.dameEscenario().getViewport().getScreenHeight()));
        canosGasoSucc.add(new Vector2(valvulas.get(0).getX()+16, valvulas.get(1).getY()+15));

        canosBypassSucc = new Array<Vector2>();
        canosBypassSucc.add(new Vector2(valvulas.get(0).getX()+16, canosGasoSucc.get(canosGasoSucc.size-1).y));
        canosBypassSucc.add(new Vector2(valvulas.get(0).getX()+16, valvulas.get(0).getY()));

        canosBypassDesc = new Array<Vector2>();
        canosBypassDesc.add(new Vector2(valvulas.get(0).getX()+16, valvulas.get(0).getY()-valvulas.get(0).getWidth()));
        canosBypassDesc.add(new Vector2(valvulas.get(0).getX()+16, valvulas.get(11).getY()));

        canosGasoDesc = new Array<Vector2>();
        canosGasoDesc.add(new Vector2(valvulas.get(0).getX()+16, canosBypassDesc.get(canosBypassDesc.size-1).y));
        //canosGasoDesc.add(new Vector2(valvulas.get(0).getX()+16, canosGasoDesc.get(canosGasoDesc.size-1).y-250));
        canosGasoDesc.add(new Vector2(valvulas.get(0).getX()+16, pantallaEscena.dameEscenario().getViewport().getScreenY()));

        // ********************************* //
        //  PLANTA SUCCION y PRESURIZACIÓN   //
        // ********************************* //

        canosPlantaSucc = new Array<Vector2>();
        canosPlantaSucc.add(new Vector2(valvulas.get(0).getX()+16, canosGasoSucc.get(canosGasoSucc.size-1).y));
        canosPlantaSucc.add(new Vector2(valvulas.get(1).getX()-25, canosGasoSucc.get(canosGasoSucc.size-1).y));
        canosPlantaSucc.add(new Vector2(valvulas.get(1).getX(), canosPlantaSucc.get(canosPlantaSucc.size-1).y));

        canosPresSucc = new Array<Vector2>();
        canosPresSucc.add(new Vector2(canosPlantaSucc.get(canosPlantaSucc.size-2).x, canosPlantaSucc.get(canosPlantaSucc.size-2).y));
        canosPresSucc.add(new Vector2(canosPlantaSucc.get(canosPlantaSucc.size-2).x, valvulas.get(2).getY()+11));
        canosPresSucc.add(new Vector2(valvulas.get(2).getX(), canosPresSucc.get(canosPresSucc.size-1).y));

        // ********************************* //
        //  SEPARADORES SUCCION y PRESURIZACIÓN   //
        // ********************************* //

        canosSep1Succ = new Array<Vector2>();
        canosSep1Succ.add(new Vector2(valvulas.get(1).getX() + valvulas.get(1).getWidth(), valvulas.get(1).getY()+15));
        canosSep1Succ.add(new Vector2(canosSep1Succ.get(canosSep1Succ.size-1).x+25, canosSep1Succ.get(canosSep1Succ.size-1).y));
        canosSep1Succ.add(new Vector2(valvulas.get(3).getX()-75, canosSep1Succ.get(canosSep1Succ.size-1).y));
        canosSep1Succ.add(new Vector2(valvulas.get(3).getX()-25, canosSep1Succ.get(canosSep1Succ.size-1).y));
        canosSep1Succ.add(new Vector2(valvulas.get(3).getX(), canosSep1Succ.get(canosSep1Succ.size-1).y));

        canosPresDesc = new Array<Vector2>();
        canosPresDesc.add(new Vector2(valvulas.get(2).getX()+valvulas.get(2).getWidth() * valvulas.get(2).getScaleX(), valvulas.get(2).getY()+11));
        canosPresDesc.add(new Vector2(canosSep1Succ.get(1).x, canosPresDesc.get(canosPresDesc.size-1).y));
        canosPresDesc.add(new Vector2(canosPresDesc.get(canosPresDesc.size-1).x, canosSep1Succ.get(0).y));

        canosSep1Pres = new Array<Vector2>();
        canosSep1Pres.add(new Vector2(canosSep1Succ.get(3).x, canosSep1Succ.get(3).y));
        canosSep1Pres.add(new Vector2(canosSep1Pres.get(canosSep1Pres.size-1).x, valvulas.get(4).getY()+11));
        canosSep1Pres.add(new Vector2(valvulas.get(4).getX(), canosSep1Pres.get(canosSep1Pres.size-1).y));

        canosSep2Succ = new Array<Vector2>();
        canosSep2Succ.add(new Vector2(canosSep1Succ.get(2).x, canosSep1Succ.get(2).y));
        canosSep2Succ.add(new Vector2(canosSep2Succ.get(canosSep2Succ.size-1).x, valvulas.get(6).getY()+15));
        canosSep2Succ.add(new Vector2(valvulas.get(6).getX()-25, canosSep2Succ.get(canosSep2Succ.size-1).y));
        canosSep2Succ.add(new Vector2(valvulas.get(6).getX(), canosSep2Succ.get(canosSep2Succ.size-1).y));

        canosSep2Pres = new Array<Vector2>();
        canosSep2Pres.add(new Vector2(canosSep2Succ.get(2).x, canosSep2Succ.get(2).y));
        canosSep2Pres.add(new Vector2(canosSep2Pres.get(canosSep2Pres.size-1).x, valvulas.get(7).getY()+11));
        canosSep2Pres.add(new Vector2(valvulas.get(7).getX(), canosSep2Pres.get(canosSep2Pres.size-1).y));

        canosSep1SuccIN = new Array<Vector2>();
        canosSep1SuccIN.add(new Vector2(valvulas.get(3).getX()+valvulas.get(3).getWidth(), valvulas.get(3).getY()+15));
        canosSep1SuccIN.add(new Vector2(canosSep1SuccIN.get(canosSep1SuccIN.size-1).x+25, canosSep1SuccIN.get(canosSep1SuccIN.size-1).y));
        canosSep1SuccIN.add(new Vector2(separadores.get(0).getX(), canosSep1SuccIN.get(canosSep1SuccIN.size-1).y));

        canosSep1PresIN = new Array<Vector2>();
        canosSep1PresIN.add(new Vector2(valvulas.get(4).getX()+valvulas.get(4).getWidth() * valvulas.get(4).getScaleX(), valvulas.get(4).getY()+11));
        canosSep1PresIN.add(new Vector2(canosSep1SuccIN.get(1).x, canosSep1PresIN.get(canosSep1PresIN.size-1).y));
        canosSep1PresIN.add(new Vector2(canosSep1PresIN.get(canosSep1PresIN.size-1).x, canosSep1SuccIN.get(1).y));

        canosSep2SuccIN = new Array<Vector2>();
        canosSep2SuccIN.add(new Vector2(valvulas.get(6).getX()+valvulas.get(6).getWidth(), valvulas.get(6).getY()+15));
        canosSep2SuccIN.add(new Vector2(canosSep2SuccIN.get(canosSep2SuccIN.size-1).x+25, canosSep2SuccIN.get(canosSep2SuccIN.size-1).y));
        canosSep2SuccIN.add(new Vector2(separadores.get(1).getX(), canosSep2SuccIN.get(canosSep2SuccIN.size-1).y));

        canosSep2PresIN = new Array<Vector2>();
        canosSep2PresIN.add(new Vector2(valvulas.get(7).getX()+valvulas.get(7).getWidth() * valvulas.get(7).getScaleX(), valvulas.get(7).getY()+11));
        canosSep2PresIN.add(new Vector2(canosSep2SuccIN.get(1).x, canosSep2PresIN.get(canosSep2PresIN.size-1).y));
        canosSep2PresIN.add(new Vector2(canosSep2PresIN.get(canosSep2PresIN.size-1).x, canosSep2SuccIN.get(1).y));

        canosSep1DescIN = new Array<Vector2>();
        canosSep1DescIN.add(new Vector2(canosSep1SuccIN.get(canosSep1SuccIN.size-1).x+separadores.get(0).getWidth(), canosSep1SuccIN.get(canosSep1SuccIN.size-1).y));
        canosSep1DescIN.add(new Vector2(valvulas.get(5).getX(), canosSep1DescIN.get(canosSep1DescIN.size-1).y));

        canosSep2DescIN = new Array<Vector2>();
        canosSep2DescIN.add(new Vector2(canosSep2SuccIN.get(canosSep2SuccIN.size-1).x+separadores.get(1).getWidth(), canosSep2SuccIN.get(canosSep2SuccIN.size-1).y));
        canosSep2DescIN.add(new Vector2(valvulas.get(8).getX(), canosSep2DescIN.get(canosSep2DescIN.size-1).y));

        // ******************************* //
        //  COLECTOR DE SUCCIÓN Y DESCARGA //
        // ******************************* //

        canosColSucc = new Array<Vector2>();
        canosColSucc.add(new Vector2(valvulas.get(5).getX()+valvulas.get(5).getWidth(), valvulas.get(5).getY()+15));
        canosColSucc.add(new Vector2(canosColSucc.get(canosColSucc.size-1).x+50, canosColSucc.get(canosColSucc.size-1).y));
        canosColSucc.add(new Vector2(valvulas.get(9).getX()+16, canosColSucc.get(canosColSucc.size-1).y));
        canosColSucc.add(new Vector2(compresor.getX()+25, canosColSucc.get(canosColSucc.size-1).y));
        canosColSucc.add(new Vector2(canosColSucc.get(canosColSucc.size-1).x, compresor.getY()+compresor.getHeight()));

        canosBypassColSucc = new Array<Vector2>();
        canosBypassColSucc.add(new Vector2(canosColSucc.get(canosColSucc.size-1).x, canosColSucc.get(canosColSucc.size-1).y+35));
        canosBypassColSucc.add(new Vector2(retenciones.get(0).getX()+retenciones.get(0).getHeight()/2*retenciones.get(0).getScaleX(), canosBypassColSucc.get(canosBypassColSucc.size-1).y));
        canosBypassColSucc.add(new Vector2(canosBypassColSucc.get(canosBypassColSucc.size-1).x, retenciones.get(0).getY()));

        canosSep2DescOUT = new Array<Vector2>();
        canosSep2DescOUT.add(new Vector2(valvulas.get(8).getX()+valvulas.get(8).getWidth(), valvulas.get(8).getY()+15));
        canosSep2DescOUT.add(new Vector2(canosColSucc.get(1).x, canosSep2DescOUT.get(canosSep2DescOUT.size-1).y));
        canosSep2DescOUT.add(new Vector2( canosSep2DescOUT.get(canosSep2DescOUT.size-1).x, canosColSucc.get(1).y));


        canosColDesc = new Array<Vector2>();
        canosColDesc.add(new Vector2(compresor.getX()+25, compresor.getY()));
        canosColDesc.add(new Vector2(canosColDesc.get(canosColDesc.size-1).x, valvulas.get(11).getY()+15));
        canosColDesc.add(new Vector2(valvulas.get(10).getX()+16, canosColDesc.get(canosColDesc.size-1).y));
        canosColDesc.add(new Vector2(valvulas.get(11).getX()+valvulas.get(11).getWidth(), canosColDesc.get(canosColDesc.size-1).y));

        canosBypassColDesc = new Array<Vector2>();
        canosBypassColDesc.add(new Vector2(canosBypassColSucc.get(canosBypassColSucc.size-1).x, canosBypassColSucc.get(canosBypassColSucc.size-1).y-retenciones.get(0).getWidth()*retenciones.get(0).getScaleX()));
        canosBypassColDesc.add(new Vector2(canosBypassColDesc.get(canosBypassColDesc.size-1).x, canosBypassColDesc.get(canosBypassColDesc.size-1).y-compresor.getHeight()/2-20));
        canosBypassColDesc.add(new Vector2(canosBypassColSucc.get(0).x, canosBypassColDesc.get(canosBypassColDesc.size-1).y));


        // ******************************* //
        //        VENTEOS                  //
        // ******************************* //

        canosVent1IN = new Array<Vector2>();
        canosVent1IN.add(new Vector2(canosColSucc.get(2).x, canosColSucc.get(2).y+6));
        canosVent1IN.add(new Vector2(canosVent1IN.get(canosVent1IN.size-1).x, valvulas.get(9).getY()-valvulas.get(9).getWidth()));

        canosVent1OUT = new Array<Vector2>();
        canosVent1OUT.add(new Vector2(canosColSucc.get(2).x,valvulas.get(9).getY()));
        canosVent1OUT.add(new Vector2(canosVent1OUT.get(canosVent1OUT.size-1).x, canosVent1OUT.get(canosVent1OUT.size-1).y+25));
        canosVent1OUT.add(new Vector2(canosVent1OUT.get(canosVent1OUT.size-1).x-10, canosVent1OUT.get(canosVent1OUT.size-1).y-10));

        canosVent2IN = new Array<Vector2>();
        canosVent2IN.add(new Vector2(canosColDesc.get(2).x, canosColDesc.get(2).y+6));
        canosVent2IN.add(new Vector2(canosVent2IN.get(canosVent2IN.size-1).x, valvulas.get(10).getY()-valvulas.get(10).getWidth()));

        canosVent2OUT = new Array<Vector2>();
        canosVent2OUT.add(new Vector2(canosColDesc.get(2).x,valvulas.get(10).getY()));
        canosVent2OUT.add(new Vector2(canosVent2OUT.get(canosVent2OUT.size-1).x, canosVent2OUT.get(canosVent2OUT.size-1).y+25));
        canosVent2OUT.add(new Vector2(canosVent2OUT.get(canosVent2OUT.size-1).x+10, canosVent2OUT.get(canosVent2OUT.size-1).y-10));


        // ******************** //
        //  PLANTA DESCARGA     //
        // ******************** //

        canosPlantaDesc = new Array<Vector2>();
        canosPlantaDesc.add(new Vector2(valvulas.get(11).getX(), canosBypassDesc.get(canosBypassDesc.size-1).y+15));
        canosPlantaDesc.add(new Vector2(valvulas.get(0).getX()+16, canosPlantaDesc.get(canosPlantaDesc.size-1).y));


        // COLOR DEL ACTOR AL INICIO
        setColor(Color.LIGHT_GRAY);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
//        setZIndex(0);
//        drawer = new ShapeDrawer(batch, region);
//        drawer.setColor(getColor());
//
//        drawer.path(canosGasoSucc, 21, JoinType.SMOOTH,true);
//        drawer.path(canosBypassSucc, 21, JoinType.SMOOTH,true);
//
//        drawer.path(canosBypassDesc, 21, JoinType.SMOOTH,true);
//        drawer.path(canosGasoDesc, 21, JoinType.SMOOTH,true);
//
//        drawer.path(canosPlantaSucc, 21, JoinType.SMOOTH,true);
//        drawer.path(canosPresSucc,10,  JoinType.SMOOTH,true);
//        drawer.path(canosSep1Succ, 21, JoinType.SMOOTH,true);
//        drawer.path(canosPresDesc, 10, JoinType.SMOOTH,true);
//        drawer.path(canosSep2Succ, 21, JoinType.SMOOTH,true);
//        drawer.path(canosSep1Pres, 10, JoinType.SMOOTH,true);
//        drawer.path(canosSep2Pres, 10, JoinType.SMOOTH,true);
//        drawer.path(canosSep1SuccIN, 21, JoinType.SMOOTH,true);
//        drawer.path(canosSep1PresIN, 10, JoinType.SMOOTH,true);
//        drawer.path(canosSep2SuccIN, 21, JoinType.SMOOTH,true);
//        drawer.path(canosSep2PresIN, 10, JoinType.SMOOTH,true);
//        drawer.path(canosSep1DescIN, 21, JoinType.SMOOTH,true);
//        drawer.path(canosSep2DescIN, 21, JoinType.SMOOTH,true);
//
//        drawer.path(canosColSucc, 21, JoinType.SMOOTH,true);
//        drawer.path(canosSep2DescOUT, 21, JoinType.SMOOTH,true);
//        drawer.path(canosColDesc, 21, JoinType.SMOOTH,true);
//
//        drawer.path(canosPlantaDesc, 21, JoinType.SMOOTH,true);
//
//
//        drawer.setColor(Color.DARK_GRAY);
//
//        drawer.path(canosVent1IN, 10, JoinType.SMOOTH,true);
//        drawer.path(canosVent1OUT, 10, JoinType.POINTY,true);
//
//        drawer.path(canosVent2IN, 10, JoinType.SMOOTH,true);
//        drawer.path(canosVent2OUT, 10, JoinType.POINTY,true);

    }

    @Override
    public void dispose() {

    }

}



