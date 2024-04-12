package com.mygdx.game.Modelos;

import com.mygdx.game.PantallaEscena;


public interface PLANTAEstado {

    public abstract String getLeyendaEstado(PantallaEscena pantallaEscena);
    public abstract void actualizar(PantallaEscena pantallaEscena);  // Le agrego como parámetro LogicaPlanta por si luego quiero que funcione para otras plantas (pantallaEscena2, pantallaEscena3, etc...)


}
