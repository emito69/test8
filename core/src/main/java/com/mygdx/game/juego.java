package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Clase principal. Es un Game, están diseñados para soportar varias
 * pantallas y disponen de un método (setScreen) para cambiar entre una
 * pantalla y otra cómodamente.
 *
 */

public class juego extends Game {
    /**
     * SpriteBatch. Es un poco absurdo crear uno para cada pantalla, lo
     * dejamos aquí y que las pantallas accedan a éste. Un poco sucio por
     * mi parte dejarlo public pero paso de crear un getter en este momento
     * (aunque sería lo suyo, por ejemplo para evitar que se pueda modificar)
     */

    public final static int WIDTH = 1280;
    public final static int HEIGHT = 1080;

    public final static float VELOCIDAD = 1f;

    private SpriteBatch batch;  // EMI public

    private Pantalla scene; // EMI public
    private Pantalla scene2; // EMI public

    @Override
    public void create() {

        // Creamos algunas cosas sencillas...
        batch = new SpriteBatch();

        // Creamos las pantallas
        scene = new PantallaEscena(this);
        //scene2 = new PantallaEscena2(this);

        // Seteamos la pantalla de interes
        setScreen(scene);

    }

    @Override
    public void dispose() {
        scene.dispose();
        //scene2.dispose();
    }

    public Pantalla getScene() {
        return scene;
    }

    public Pantalla getScene2() {
        return scene2;
    }
}
