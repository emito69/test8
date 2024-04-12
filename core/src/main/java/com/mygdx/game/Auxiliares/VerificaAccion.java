package com.mygdx.game.Auxiliares;

import com.badlogic.gdx.scenes.scene2d.Action;

public class VerificaAccion extends Action {

    private boolean completa = false;

    @Override
    public boolean act(float delta) {
        this.completa = true;
        return completa;
    }

    public boolean isComplete() { return completa; }

    public void setCompleta(boolean completa) {
        this.completa = completa;
    }

}
