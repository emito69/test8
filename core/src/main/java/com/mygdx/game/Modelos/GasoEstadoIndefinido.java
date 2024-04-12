package com.mygdx.game.Modelos;

import com.mygdx.game.ActorCompresor;
import com.mygdx.game.LogicaValvulas;
import com.mygdx.game.PantallaEscena;

public class GasoEstadoIndefinido implements GASOEstado {

    @Override
    public String getLeyendaEstado(PantallaEscena pantallaEscena) {

        return "-";
    }

    @Override
    public void actualizar(PantallaEscena pantallaEscena) {

        LogicaValvulas v = pantallaEscena.dameLogicaValvulas();
        ActorCompresor c = pantallaEscena.dameCompresor();

        if (v.BypassABIERTA()){  /// AQUÍ VA LA CONDICIÓN DE CAMBIO
            pantallaEscena.setEstadoGASO(new GasoTransportePorGasoducto());
        }

    }
}
