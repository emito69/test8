package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.Auxiliares.BackgroundColor;

import static com.badlogic.gdx.utils.Align.left;

public class ActorTextoDialogo extends Group implements Disposable {

    String text;
    Label label;
    //TypingLabel label;
    Skin skin;

    private BitmapFont bitmapFont;

    private BackgroundColor backgroundColor;

    private Rectangle bounds = new Rectangle();

    private Image backGround;

    public ActorTextoDialogo() {
        super();

        //setSize(1277, 319);
        setSize(880, 260);
        setBounds(getX(), getY(), getWidth(), getHeight());
        setScale(1);

        //backgroundColor = new BackgroundColor("white_color_texture.png");
        //backgroundColor.setColor(2, 179, 228, 255); // r, g, b, a
        //this.setBackground(backgroundColor);
        //this.setZIndex(75);

        // *** cuadro_de_dialogo.png *** //
        //backGround = new Image(new Texture(Gdx.files.internal("cuadro_de_dialogo.png")));
        //backGround.setSize(551, 193);

        // *** cuadro_de_dialogo2.png *** //
        backGround = new Image(new Texture(Gdx.files.internal("cuadro_de_dialogo4.png")));
        backGround.setSize(810, 344);

        this.addActor(backGround);
        this.backGround.setFillParent(true);
        //this.backGround.setColor(Color.BLUE);

        //Label.LabelStyle labelStyleTexto=new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY);
        Label.LabelStyle labelStyleTexto=new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        //**** LABEL CON TypingLabel  *** ////

        // Create some text with tokens
        // String text = "{COLOR=WHITE}Hello,{WAIT} world!"
        //    + "{COLOR=WHITE}{SLOWER} Did you know orange is my favorite color?";
        // Create a TypingLabel instance with your custom text
        //TypingLabel label = new TypingLabel(text, labelStyleTexto);

        ///* ********************* ////

        //**** LABEL   *** ////
        label = new Label("¡ Hacé click en este Texto !", labelStyleTexto);
        //label.setFontScale(2f, 1.5f);
        label.setFontScale(1.8f, 1.4f);
        ///* ********************* ////

        //**** LABEL CON sKIN  *** ////
        //skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        //label = new Label("¡¡ Hacé click en el personaje!!", skin);
        ///* ********************* ////

        //label.setAlignment(left);

        label.setPosition(this.getX()+30, this.getHeight()/2+20-label.getHeight(), left);
        //label.setAlignment(left, left);
        this.addActor(label);
        label.setWidth(this.getWidth()-50);
        label.setWrap(true);
        //label.setBounds(bounds.x,bounds.y, bounds.width, bounds.height);
        //setFillParent(true);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color col = getColor(); //obtenemos una instancia del color de este actor
        batch.setColor(col.r, col.g, col.b, col.a);// modificamos el color si queremos
        setZIndex(96);
        super.draw(batch, parentAlpha);
    }

    private void updateBounds() {
        bounds.set(getX(), getY(), getWidth(), getHeight());
        //setZIndex(3);
    }

    public Rectangle getBounds() {
        return bounds;
    }


    public void dispose() {
    }

    public void setTexto(String text) {
        this.label.setText(text);

    }
}

