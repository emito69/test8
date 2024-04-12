package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Disposable;

import java.text.NumberFormat;


public class ActorPIT extends Table implements Disposable {

    NumberFormat nf = NumberFormat.getInstance();

    Label tag;
    //Label valor;
    Label unidad;
    TextField valor;

    //private static final DecimalFormat df = new DecimalFormat("0.0");

    private BitmapFont bitmapFont;

    private Rectangle bounds = new Rectangle();

    public ActorPIT() {
        super();

        setSize(100, 100);
        setBounds(getX(), getY(), getWidth(), getHeight());
        setScale(1);

        //bitmapFont=new BitmapFont();
        //bitmapFont.getData().markupEnabled=true;
        //bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        Label.LabelStyle labelStyleTexto=new Label.LabelStyle(new BitmapFont(), Color.GRAY);
        //Label.LabelStyle labelStyleTexto=new Label.LabelStyle(bitmapFont, Color.DARK_GRAY);
        tag =new Label(String.format("%03d",0), labelStyleTexto);
        unidad =new Label(String.format("%03d",0),labelStyleTexto);


        //Label.LabelStyle labelStyleValor=new Label.LabelStyle(new BitmapFont(), Color.BLUE);
        //Label.LabelStyle labelStyleValor=new Label.LabelStyle(bitmapFont, Color.CYAN);
        TextField.TextFieldStyle textFieldStyleValor=new TextField.TextFieldStyle(new BitmapFont(), Color.BLUE, null, null, null);

        //valor =new Label(String.format("%02d",0),labelStyleValor);
        valor = new TextField(String.format("%03d",0), textFieldStyleValor);
        valor.setMaxLength(4);

        //Table tabla =new Table();
        this.add(tag).colspan(2);
        this.row();
        this.add(valor).width(30);
        this.add(unidad);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color col = getColor(); //obtenemos una instancia del color de este actor
        batch.setColor(col.r, col.g, col.b, col.a);// modificamos el color si queremos
        super.draw(batch, parentAlpha);
    }


    @Override
    public void dispose() {
    }

    public Label getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag.setText(tag);
    }

    public Float getValor() {
        return Float.parseFloat(valor.getText().toString().replace(',', '.'));  /// para cambiarle la , por . porque no me lo toma ParseFlooat
        //return nf.parse(valor.getText()).floatValue();
    }

    public void setValor(String valor) {
        this.valor.setText(valor);

    }

    public Label getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad.setText(unidad);
    }

    private void updateBounds() {
        bounds.set(getX(), getY(), getWidth(), getHeight());
        //setZIndex(3);
    }

    public Rectangle getBounds() {
        return bounds;
    }


}
