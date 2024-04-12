package com.mygdx.game.Modelos;

import com.mygdx.game.ActorCompresor;
import com.mygdx.game.LogicaValvulas;
import com.mygdx.game.PantallaEscena;

public class GasoGasoductoBloqueado implements GASOEstado {

    @Override
    public String getLeyendaEstado(PantallaEscena pantallaEscena) {

        return "Gasoducto Bloqueado";
    }

    @Override
    public void actualizar(PantallaEscena pantallaEscena) {

        LogicaValvulas v = pantallaEscena.dameLogicaValvulas();
        ActorCompresor c = pantallaEscena.dameCompresor();

        if (!v.BypassABIERTA() && !c.getEnCarga()){
            // me quedo en el estado que estoy
        }
        else if (!v.BypassABIERTA() && c.getEnCarga()){  /// AQUÍ VA LA CONDICIÓN DE CAMBIO
            pantallaEscena.setEstadoGASO(new GasoTransportePorPlanta());
        }
        else if (v.BypassABIERTA()){  /// AQUÍ VA LA CONDICIÓN DE CAMBIO
            pantallaEscena.setEstadoGASO(new GasoTransportePorGasoducto());
        }
        else{
            pantallaEscena.setEstadoGASO(new GasoEstadoIndefinido());
        }

    }
}
