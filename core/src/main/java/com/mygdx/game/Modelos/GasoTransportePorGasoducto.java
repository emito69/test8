package com.mygdx.game.Modelos;

import com.mygdx.game.ActorCompresor;
import com.mygdx.game.LogicaValvulas;
import com.mygdx.game.PantallaEscena;

public class GasoTransportePorGasoducto implements GASOEstado {



    @Override
    public String getLeyendaEstado(PantallaEscena pantallaEscena) {

        return "Transporte por Gasoducto";
    }

    @Override
    public void actualizar(PantallaEscena pantallaEscena) {

        LogicaValvulas v = pantallaEscena.dameLogicaValvulas();
        ActorCompresor c = pantallaEscena.dameCompresor();

        if (v.BypassABIERTA()){
            // me quedo en el estado que estoy
        }
        else if (!v.BypassABIERTA() && !c.getEnCarga()){  /// AQUÍ VA LA CONDICIÓN DE CAMBIO
            pantallaEscena.setEstadoGASO(new GasoGasoductoBloqueado());
        }
        else if (!v.BypassABIERTA() && c.getEnCarga()){  /// AQUÍ VA LA CONDICIÓN DE CAMBIO
            pantallaEscena.setEstadoGASO(new GasoTransportePorPlanta());
        }
        else{
            pantallaEscena.setEstadoGASO(new GasoEstadoIndefinido());
        }

    }
}
