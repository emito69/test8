package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.Auxiliares.BackgroundColor;


public class ActorMenuEstadoGASO extends Group implements Disposable {

    private Skin skin;
    private final Label labelTITULO;
    private final Label labelESTADO;
    private final Table table;

    //private Boolean PJ1 = false;


    public ActorMenuEstadoGASO() {
        super();
        setSize(150, 100);
        //setOrigin(0, 0);
        setScale(1);

        table = new Table();

        table.setSize(125, 80);

        BackgroundColor backgroundColor = new BackgroundColor("white_color_texture.png");
        backgroundColor.setColor(240, 240, 240, 255); // r, g, b, a
        table.setBackground(backgroundColor);

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Label.LabelStyle labelStyleTexto = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        labelTITULO = new Label("ESTADO:", skin);
        labelTITULO.setColor(Color.DARK_GRAY);
        labelTITULO.setFontScale(0.8f);

        labelESTADO = new Label(" - ", skin);
        labelESTADO.setFontScale(1.25f);
        labelESTADO.setWrap(true);
        labelESTADO.setStyle(labelStyleTexto);
        labelESTADO.setColor(Color.OLIVE);

        labelTITULO.setAlignment(Align.bottom);

        table.add(labelTITULO).center().width(120);

        table.row();

        labelESTADO.setAlignment(Align.top);

        table.add(labelESTADO).top().width(120).getActor().setWrap(true);

        //table.setSize(150+75, 100);

        this.addActor(table);

        //table.debug();
        //table.setSize(200, 200);


        // ******** EVENTOS ************* //

        // En elementos de la Interfaz Gráfica es preferible usar "CHANGELISTENER"
        // en lugar del resto de los listeners ("ClickListener", etc...).


        // ******************************* //

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color col = getColor(); //obtenemos una instancia del color de este actor
        batch.setColor(col.r, col.g, col.b, col.a);// modificamos el color si queremos
        setZIndex(97);
        super.draw(batch, parentAlpha);
    }


    public void dispose() {
    }

    public void setTextoESTADO(String string) {
        this.labelESTADO.setText(string);
    }

}
