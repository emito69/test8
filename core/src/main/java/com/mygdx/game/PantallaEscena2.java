package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Auxiliares.BackgroundColor;

public class PantallaEscena2 extends Pantalla {

    private Stage escenario; // escenario

    private Skin skin;

    public PantallaEscena2(juego game) {
        super(game);
        this.game = game;

        escenario = new Stage(new ScreenViewport());  // ScreenViewport crea un Stage del tamaño de nuestra pantalla

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Label.LabelStyle labelStyleTexto=new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.setSize(130, 100);

        //table.setColor(Color.BLACK);

        BackgroundColor backgroundColor = new BackgroundColor("white_color_texture.png");
        backgroundColor.setColor(100, 101, 100, 255); // r, g, b, a
        table.setBackground(backgroundColor);

        TextButton buttonPJ1 = new TextButton("PJ1", skin);
        buttonPJ1.getLabel().setStyle(labelStyleTexto);
        buttonPJ1.setSize(30,20);
        buttonPJ1.getLabel().setFontScale(2f);

        TextButton buttonPJ2 = new TextButton("PJ2", skin);
        buttonPJ2.getLabel().setFontScale(2f);
        buttonPJ2.getLabel().setStyle(labelStyleTexto);
        buttonPJ2.setSize(30,10);
        buttonPJ2.getLabel().setFontScale(2f);

        Label labelTITULO = new Label("ESTADO:", skin);
        labelTITULO.setColor(Color.DARK_GRAY);
        labelTITULO.setFontScale(0.9f);
        Label labelESTADO = new Label("Transporte por Gasoducto", skin);
        labelESTADO.setFontScale(1.15f);
        labelESTADO.setWrap(true);
        //labelESTADO.setColor(Color.BLACK);


        labelTITULO.setAlignment(Align.center);
        table.add(labelTITULO).expandX().width(150);

        table.add(buttonPJ1).width(75).height(50).fill();

        table.row();

        labelESTADO.setAlignment(Align.top);
        table.add(labelESTADO).top().width(150);

        table.add(buttonPJ2).width(75).height(50);

        table.setPosition(500, 500);


        //table.debug();
        //table.setSize(200, 200);
        table.setColor(Color.WHITE);
        escenario.addActor(table);


    }

    @Override
    public void render(float delta) {
        //super.render(delta);
        //Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f,  0.2f);
        //Gdx.gl.glClearColor(0.93f, 0.93f, 0.93f,  0.5f);
        Gdx.gl.glClearColor(0.89f, 0.89f, 0.89f,  0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        escenario.act(Gdx.graphics.getDeltaTime()); // para actualizar las Acciones
        escenario.draw();

    }


    @Override
    public void resize(int width, int height) {
        //escenario.getViewport().update(width, height); EMI2
        //fondo.setSize(width, height);

    }

    @Override
    public void show() {
        super.show();
        // Stage crea un SpriteBatch aunque no se lo pasemos
        Gdx.input.setInputProcessor(escenario); // Stage es un InputProcessor en si mismo (puede manejar Input Events)
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
