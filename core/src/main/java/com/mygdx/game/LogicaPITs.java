package com.mygdx.game;

import com.badlogic.gdx.utils.Array;

import java.text.DecimalFormat;
import java.util.Random;

import static com.mygdx.game.PantallaEscena.MAPO;


public class LogicaPITs {

    private PantallaEscena pantallaEscena;
    private static Array<ActorPIT> transmisores;
    private static Array<ActorValvula> valvulas;
    private static Array<Float> valores;

    private boolean sep1Habilitado;
    private boolean sep2Habilitado;
    private boolean compresorEnCarga;
    private float relComp;

    private float temp;

    DecimalFormat df = new DecimalFormat("0.0");

    public LogicaPITs(PantallaEscena pantallaEscena) {

        this.pantallaEscena = pantallaEscena;

        this.transmisores = pantallaEscena.dameTransmisores();

        this.valvulas = pantallaEscena.dameValvulas();

        this.valores = new Array<Float>();

        //compresorEnCarga = pantallaEscena.dameCompresor().getEnCarga();

        relComp = pantallaEscena.dameCompresor().getRelComp();

        for (int i=0; i < transmisores.size; i++){
            valores.add(transmisores.get(i).getValor());
        }

        //this.estado = new TransportePorGasoducto();

    }


     public void act () {

         //Supplier<Float> valorSupplier = () -> generateRandomValor();

         sep1Habilitado = ((valvulas.get(3).getAbierta() || valvulas.get(4).getAbierta()) && valvulas.get(5).getAbierta());
         sep2Habilitado = ((valvulas.get(6).getAbierta() || valvulas.get(7).getAbierta()) && valvulas.get(8).getAbierta());
         compresorEnCarga = pantallaEscena.dameCompresor().getEnCarga();

         for (int i=0; i < transmisores.size; i++){
             valores.set(i, transmisores.get(i).getValor());
             //System.out.println(transmisores.get(i).getValor());
         }

         for (int i=0; i <transmisores.size; i++ ){

             switch (i) {

                 /** PIT-12001 **/
                 case 0:

                     if (valores.get(0) <= MAPO * 0.45f){

                         actualizaPresion(i, MAPO);
                     }

                     transmisores.get(i).setValor(df.format(valores.get(i)));

                     break;

                 /** PIT-12002 **/
                 case 1:

                     if (valvulas.get(0).getAbierta()){  // ZSA-12001

                         actualizaPresion(i, valores.get(0)); // referencia PIT-12001
                     }

                     if (valvulas.get(11).getAbierta() && compresorEnCarga){ // ZSA-5301

                         actualizaPresion(i, valores.get(5)); // referencia PIT-5301
                     }

                     if ( !(valvulas.get(0).getAbierta()) && !(valvulas.get(11).getAbierta() && compresorEnCarga)){

                         actualizaPresion(i, 0.25f);
                     }

                     transmisores.get(i).setValor(df.format(valores.get(i)));

                     break;

                 /** PIT-5001**/
                 case 2:

                     if (valvulas.get(1).getAbierta() || valvulas.get(2).getAbierta()){  // ZSA-5001 / ZSA-5002

                         actualizaPresion(i, valores.get(0)); // referencia PIT-12001
                     }

                     if (sep1Habilitado || sep2Habilitado) {
                         actualizaPresion(i, valores.get(3)); // referencia PIT-5100
                     }

                     if (!(valvulas.get(1).getAbierta() || valvulas.get(2).getAbierta()) && !(sep1Habilitado || sep2Habilitado)){
                         temp = generateRandomValorFIJO(valores.get(i));
                         if (temp >= 0f) {
                             valores.set(i, temp);
                         }
                         else {
                             valores.set(i,0f);
                         }

                     }

                     transmisores.get(i).setValor(df.format(valores.get(i)));

                     break;

                 /** PIT-5100**/
                 case 3:
                     if (sep1Habilitado || sep2Habilitado) {

                         actualizaPresion(i, valores.get(2)); // referencia PIT-5001

                     }

                     if (valores.get(i)>valores.get(4)) {

                         actualizaPresion(i, valores.get(4)); // referencia PIT-5200

                     }

                     if (!(sep1Habilitado || sep2Habilitado) && !(valores.get(i)>valores.get(4))){

                         temp = generateRandomValorFIJO(valores.get(i));
                         if (temp >= 0f) {
                             valores.set(i, temp);
                         }
                         else {
                             temp = generateRandomValorFIJO(valores.get(i));
                             if (temp >= 0f) {
                                 valores.set(i, temp);
                             } else {
                                 valores.set(i, 0f);
                             }
                         }
                     }

                     if (valvulas.get(9).getAbierta()) { // VENTEO SUCCION ABIERTO

                         actualizaPresion(i, valores.get(3) * 0.85f); // referencia ella misma venteandose

                     }

                     transmisores.get(i).setValor(df.format(valores.get(i)));

                     break;

                 /** PIT-5200**/

                 case 4:

                     if (compresorEnCarga) {
                         actualizaPresion(i, valores.get(3) + relComp); // referencia PIT-5100 +relcomp
                     }

                     if ( !compresorEnCarga && !valvulas.get(11).getAbierta()){

                         if (valores.get(i)<valores.get(3)) {

                             actualizaPresion(i, valores.get(3)); // referencia PIT-5100

                         }
                         else {
                             temp = generateRandomValorFIJO(valores.get(i));
                             if (temp >= 0f) {
                                 valores.set(i, temp);
                             } else {
                                 valores.set(i, 0f);
                             }
                         }
                     }

                     if (!compresorEnCarga && valvulas.get(11).getAbierta()) {

                         if (valores.get(i)<valores.get(3) && valores.get(3)>valores.get(1)) {

                             actualizaPresion(i, valores.get(3)*0.05f); // referencia PIT-5100

                         }

                         if (valores.get(i)<valores.get(3) && valores.get(3)<valores.get(1)) {

                             actualizaPresion(i, valores.get(3)); // referencia PIT-5100

                         }

                         if (valores.get(i)>valores.get(1)) {

                             actualizaPresion(i, valores.get(3)); // referencia PIT-12002

                         }

                         else {
                             temp = generateRandomValorFIJO(valores.get(i));
                             if (temp >= 0f) {
                                 valores.set(i, temp);
                             } else {
                                 valores.set(i, 0f);
                             }
                         }
                     }

                     if (valvulas.get(10).getAbierta()) { // VENTEO DESCARGA ABIERTO

                         actualizaPresion(i, valores.get(4) * 0.85f); // referencia ella misma venteandose

                     }

                     transmisores.get(i).setValor(df.format(valores.get(i)));

                     break;

                 /** PIT-5301**/
                 case 5:

                     if (compresorEnCarga) {

                         actualizaPresion(i, valores.get(4) - 0.2f); // referencia PIT-5200 menos 200grs
                     }


                     if (!compresorEnCarga){

                         actualizaPresion(i, valores.get(4)); // referencia PIT-5200

                       }

                     transmisores.get(i).setValor(df.format(valores.get(i)));

                     break;

                 /** PIT-SEP1**/
                 case 6:

                     if (valvulas.get(3).getAbierta() || valvulas.get(4).getAbierta()){  // ENTRADAS SEP1

                         actualizaPresion(i, valores.get(2)); // referencia PIT-5001
                     }

                     if (valvulas.get(5).getAbierta()){  // SALIDA SEP1

                         actualizaPresion(i, valores.get(3)); // referencia PIT-5100
                     }

                     if (!(valvulas.get(3).getAbierta() || valvulas.get(4).getAbierta()) && !(valvulas.get(5).getAbierta())){
                         temp = generateRandomValorFIJO(valores.get(i));
                         if (temp >= 0f) {
                             valores.set(i, temp);
                         }
                         else {
                             valores.set(i,0f);
                         }

                     }

                     transmisores.get(i).setValor(df.format(valores.get(i)));

                     break;

                 /** PIT-SEP2**/
                 case 7:

                     if (valvulas.get(6).getAbierta() || valvulas.get(7).getAbierta()){  // ENTRADAS SEP1

                         actualizaPresion(i, valores.get(2)); // referencia PIT-5001
                     }

                     if (valvulas.get(8).getAbierta()){  // SALIDA SEP1

                         actualizaPresion(i, valores.get(3)); // referencia PIT-5100
                     }

                     if (!(valvulas.get(6).getAbierta() || valvulas.get(7).getAbierta()) && !(valvulas.get(8).getAbierta())){
                     temp = generateRandomValorFIJO(valores.get(i));
                         if (temp >= 0f) {
                             valores.set(i, temp);
                         }
                         else {
                             valores.set(i,0f);
                         }

                    }
                     transmisores.get(i).setValor(df.format(valores.get(i)));

                     break;

                 default:
                     valores.set(i, valores.get(i)+generateRandomValor());
                     //pantallaEscena.dameTransmisores().get(i).setValor(df.format(valores.get(i)));
                     transmisores.get(i).setValor(df.format(valores.get(i)));
             }


         }

         // estado.actualizar(this);

     }



    private void actualizaPresion(int i, float valorReferencia) {
        if (valorReferencia-0.25 < valores.get(i) && valores.get(i) < valorReferencia+0.25) {
            valores.set(i, valores.get(i) + generateRandomValorTINY());
        }

        else if (valores.get(i) <= valorReferencia-0.25) {
            valores.set(i, valores.get(i) + generateRandomValorUP());
        }
        else{
            valores.set(i, valores.get(i)-generateRandomValorUP());
        }
    }




    private Float generateRandomValorUP() {
        return new Random().nextFloat()*0.5f;
    }

    private Float generateRandomValorTINY() {
        return new Random().nextFloat() * -0.075f + new Random().nextFloat() * 0.075f;
    }

    private Float generateRandomValorFIJO(float valor) {
        return new Random().nextFloat() * -0.035f + new Random().nextFloat() * 0.005f;
    }

    private Float generateRandomValor() {
        return (new Random().nextFloat() * -1f + new Random().nextFloat() * 1f) + 1;
    }

    public Array<Float> dameValores() {
        return valores;
    }

}
