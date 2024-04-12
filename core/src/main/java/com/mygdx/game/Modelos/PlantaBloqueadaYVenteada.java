package com.mygdx.game.Modelos;

import com.mygdx.game.ActorCompresor;
import com.mygdx.game.LogicaValvulas;
import com.mygdx.game.PantallaEscena;

public class PlantaBloqueadaYVenteada implements PLANTAEstado {

    @Override
    public String getLeyendaEstado(PantallaEscena pantallaEscena) {

        return "-";
    }

    @Override
    public void actualizar(PantallaEscena pantallaEscena) {

        LogicaValvulas v = pantallaEscena.dameLogicaValvulas();
        ActorCompresor c = pantallaEscena.dameCompresor();

        if (true){  /// AQUÍ VA LA CONDICIÓN DE CAMBIO
            //pantallaEscena.setEstadoPLANTA(new XXXXXXXXXXX ); ////
        }

    }
}
