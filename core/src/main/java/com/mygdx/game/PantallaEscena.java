package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Auxiliares.LabelFormatter;
import com.mygdx.game.Modelos.GASOEstado;
import com.mygdx.game.Modelos.GasoEstadoIndefinido;
import com.mygdx.game.Modelos.PLANTAEstado;
import com.mygdx.game.Modelos.PlantaEstadoIndefinido;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class PantallaEscena extends Pantalla {

    public static final float MAPO = 10;
    public static boolean PJ1 = false;
    public static boolean PJ2 = false;
    public static boolean RST = false;
    public static boolean ACK = false;

    private GASOEstado estadoGASO;  // ESTADO: estamos declarando este atributo con una INTERFACE
    private PLANTAEstado estadoPLANTA;  // ESTADO: estamos declarando este atributo con una INTERFACE

    private Stage escenario; // escenario

    private Array<ActorValvula> valvulas;

    private Array<ActorValvulaAbierta> valvsZSA;

    private Array<ActorRetencion> retenciones;

    private Array<ActorSeparador> separadores;

    private ActorCompresor compresor;

    private ActorGas gasNuevo; //actor

    private ActorPersonaje avatar;

    private Fondo fondo;

    private ActorFondoGasoSucc fondoGasoSucc;
    private ActorFondoGasoDesc fondoGasoDesc;
    private ActorFondoPlantaSucc fondoPlantaSucc;

    private ActorFondoSeparador1 fondoSeparador1;
    private ActorFondoSeparador2 fondoSeparador2;
    private ActorFondoColectorSUCC fondoColectorSUCC;
    private ActorFondoColectorDESC fondoColectorDESC;
    private ActorFondoVent1 fondoVent1;
    private ActorFondoVent2 fondoVent2;

    private Array<ActorPIT> transmisores;
    private Array<ActorDPSH> presostatosDif;

    private LogicaPITs logicaPITs;
    private LogicaValvulas logicaValvulas;
    private LogicaFondo logicaFondo;
    private LogicaDPSHs logicaDPSHs;

    private LogicaPlanta logicaPlanta;
    private LogicaMenuEstadoGASO logicaMenuEstadoGASO;

    private ActorValvula valvulaPrueba; //actor

    private ActorTextoDialogo textoDialogo;

    private ActorMenuEstadoGASO menuEstadoGASO;
//EMI2    private ActorMenuPJ1PJ2 menuPJ1PJ2;
//EMI2    private ActorMenuACKRST menuACKRST;

    private Array<ActorGas> gases = new Array<ActorGas>();

    private TareasPeriodicas tareasPeriodicas; //actor

    private ActorPrueba actorPrueba; //actor

    private int i = 0;


    public PantallaEscena(juego game) {
        super(game);

        this.estadoGASO = new GasoEstadoIndefinido();
        this.estadoPLANTA = new PlantaEstadoIndefinido();

        escenario = new Stage(new ScreenViewport());  // ScreenViewport crea un Stage del tamaño de nuestra pantalla
        //escenario = new Stage(new FitViewport(WIDTH, HEIGHT));
        // Stage crea un SpriteBatch aunque no se lo pasemos
        Gdx.input.setInputProcessor(escenario); // Stage es un InputProcessor en si mismo (puede manejar Input Events)

        valvulas = new Array<ActorValvula>();
        valvulas.add(new ActorValvula());   // valvulas.get(0) - ByPass
        valvulas.add(new ActorValvula());   // valvulas.get(1) - Succión
        valvulas.add(new ActorValvula());   // valvulas.get(2) - Presurización
        valvulas.add(new ActorValvula());   // valvulas.get(3) - Entrada Separador 1
        valvulas.add(new ActorValvula());   // valvulas.get(4) - Pres. Separador 1
        valvulas.add(new ActorValvula());   // valvulas.get(5) - Salida Separador 1
        valvulas.add(new ActorValvula());   // valvulas.get(6) - Entrada Separador 2
        valvulas.add(new ActorValvula());   // valvulas.get(7) - Pres. Separador 2
        valvulas.add(new ActorValvula());   // valvulas.get(8) - Salida Separador 2
        valvulas.add(new ActorValvula());   // valvulas.get(9) - Venteo 1
        valvulas.add(new ActorValvula());   // valvulas.get(10) - Venteo 2
        valvulas.add(new ActorValvula());   // valvulas.get(11) - Descarga

        valvsZSA = new Array<ActorValvulaAbierta>();  // les agrego la contraparte "abierta"
        valvsZSA.add(new ActorValvulaAbierta());
        valvsZSA.add(new ActorValvulaAbierta());
        valvsZSA.add(new ActorValvulaAbierta());
        valvsZSA.add(new ActorValvulaAbierta());
        valvsZSA.add(new ActorValvulaAbierta());
        valvsZSA.add(new ActorValvulaAbierta());
        valvsZSA.add(new ActorValvulaAbierta());
        valvsZSA.add(new ActorValvulaAbierta());
        valvsZSA.add(new ActorValvulaAbierta());
        valvsZSA.add(new ActorValvulaAbierta());
        valvsZSA.add(new ActorValvulaAbierta());
        valvsZSA.add(new ActorValvulaAbierta());

        retenciones = new Array<ActorRetencion>();
        retenciones.add(new ActorRetencion());   // bypass de Colectores
        retenciones.add(new ActorRetencion());   // descarga de planta

        separadores = new Array<ActorSeparador>();
        separadores.add(new ActorSeparador());   //  separadores.get(0) - Separador 1
        separadores.add(new ActorSeparador());   //  separadores.get(1) - Separador 2

        compresor = new ActorCompresor(this);


        int escenario_medio_Y = escenario.getViewport().getScreenHeight() / 2 + 75;

        // ****  0 - ByPass  **** //
        valvulas.get(0).rotateBy(270);
        valvulas.get(0).setPosition(150,  escenario_medio_Y +27);
        valvulas.get(0).setAbierta();
        escenario.addActor(valvulas.get(0));

//        valvsZSA.get(0).rotateBy(270);
//        valvsZSA.get(0).setPosition(100,  escenario.getViewport().getScreenHeight()/2+27);
//        escenario.addActor(valvsZSA.get(0));
        // **************** //

        // ****  1 - Succion  **** //
        valvulas.get(1).setPosition(250, escenario_medio_Y +27+200);
        escenario.addActor(valvulas.get(1));

//        valvsZSA.get(1).setPosition(250, escenario.getViewport().getScreenHeight()/2+27+200);
//        escenario.addActor(valvsZSA.get(1));
        // **************** //

        // ****  2 - Presurización  **** //
        valvulas.get(2).setPosition(250, escenario_medio_Y +27+125);
        valvulas.get(2).setScale(0.75f);
        escenario.addActor(valvulas.get(2));

//        valvsZSA.get(2).setPosition(250, escenario.getViewport().getScreenHeight()/2+27+125);
//        //valvsZSA.get(2).setScale(0.75f);
//        escenario.addActor(valvsZSA.get(2));
        // **************** //

        // ****  3 - Entrada SEP 1  **** //
        valvulas.get(3).setPosition(550, escenario_medio_Y +27+200);
        escenario.addActor(valvulas.get(3));

//        valvsZSA.get(3).setPosition(550, escenario.getViewport().getScreenHeight()/2+27+200);
//        escenario.addActor(valvsZSA.get(3));
        // **************** //

        // ****  4 - Pres. SEP 1  **** //
        valvulas.get(4).setPosition(550, escenario_medio_Y +27+125);
        escenario.addActor(valvulas.get(4));
        valvulas.get(4).setScale(0.75f);

//        valvsZSA.get(4).setPosition(550, escenario.getViewport().getScreenHeight()/2+27+125);
//        escenario.addActor(valvsZSA.get(4));
        // **************** //

        // ****  5 - Salida SEP 1  **** //
        valvulas.get(5).setPosition(900, escenario_medio_Y +27+200);
        escenario.addActor(valvulas.get(5));
        valvulas.get(5).setAbierta();

//        valvsZSA.get(5).setPosition(900, escenario.getViewport().getScreenHeight()/2+27+200);
//        escenario.addActor(valvsZSA.get(5));
        // **************** //

        // ****  6 - Entrada SEP 2  **** //
        valvulas.get(6).setPosition(550, escenario_medio_Y +27+25);
        escenario.addActor(valvulas.get(6));

//        valvsZSA.get(6).setPosition(550, escenario.getViewport().getScreenHeight()/2+27+25);
//        escenario.addActor(valvsZSA.get(6));
        // **************** //

        // ****  7 - Pres. SEP 2  **** //
        valvulas.get(7).setPosition(550, escenario_medio_Y +27-50);
        escenario.addActor(valvulas.get(7));
        valvulas.get(7).setScale(0.75f);

//        valvsZSA.get(7).setPosition(550, escenario.getViewport().getScreenHeight()/2+27-50);
//        escenario.addActor(valvsZSA.get(7));
        // **************** //

        // ****  8 - Salida SEP 2  **** //
        valvulas.get(8).setPosition(900, escenario_medio_Y +27+25);
        escenario.addActor(valvulas.get(8));
        valvulas.get(8).setAbierta();

//        valvsZSA.get(8).setPosition(900, escenario.getViewport().getScreenHeight()/2+27+25);
//        escenario.addActor(valvsZSA.get(8));
        // **************** //

        // ****  9 - Venteo 1  **** //
        valvulas.get(9).rotateBy(270);
        valvulas.get(9).setPosition(1050,  escenario_medio_Y +27+300);
        valvulas.get(9).setAbierta();
        escenario.addActor(valvulas.get(9));

//        valvsZSA.get(9).rotateBy(270);
//        valvsZSA.get(9).setPosition(1050,  escenario.getViewport().getScreenHeight()/2+27+300);
//        valvsZSA.get(9).setAbierta(true);
//        escenario.addActor(valvsZSA.get(9));
        // **************** //

        // ****  10 - Venteo 2  **** //
        valvulas.get(10).rotateBy(270);
        valvulas.get(10).setPosition(450,  escenario_medio_Y -27-100);
        valvulas.get(10).setAbierta();
        escenario.addActor(valvulas.get(10));

//        valvsZSA.get(10).rotateBy(270);
//        valvsZSA.get(10).setPosition(400,  escenario.getViewport().getScreenHeight()/2-27-100);
//        valvsZSA.get(10).setAbierta(true);
//        escenario.addActor(valvsZSA.get(10));
        // **************** //

        // ****  11 - Descarga  **** //
        valvulas.get(11).setPosition(250, escenario_medio_Y -27-200);
        escenario.addActor(valvulas.get(11));

//        valvsZSA.get(11).setPosition(250, escenario.getViewport().getScreenHeight()/2-27-200);
//        escenario.addActor(valvsZSA.get(11));
        // **************** //


        // ****  0 - Separador 1  **** //
        separadores.get(0).setPosition(670, escenario_medio_Y +27+150);
        escenario.addActor(separadores.get(0));
        // **************** //

        // ****  1 - Separador 2  **** //
        separadores.get(1).setPosition(670, escenario_medio_Y +27-25);
        escenario.addActor(separadores.get(1));
        // **************** //

        // ****  0 - CGN  **** //
        compresor.setPosition(1100,  escenario_medio_Y -compresor.getHeight()/2);
        escenario.addActor(compresor);
        // **************** //

        // ****  0 - RETENCIONES  **** //
        retenciones.get(0).rotateBy(270);
        retenciones.get(0).setPosition(compresor.getX()-55, compresor.getY()+compresor.getHeight()/2+retenciones.get(0).getWidth()/2);
        retenciones.get(0).setScale(0.75f);
        escenario.addActor(retenciones.get(0));

        retenciones.get(1).rotateBy(180);
        retenciones.get(1).setPosition( valvulas.get(11).getX()+valvulas.get(11).getWidth()+retenciones.get(1).getWidth()-5, valvulas.get(11).getY()+valvulas.get(11).getHeight()-27);
        retenciones.get(1).setScale(0.9f);
        escenario.addActor(retenciones.get(1));


        // ****  CAÑOS **** //
        fondo = new Fondo(this);
        fondoGasoSucc = new ActorFondoGasoSucc(fondo);
        fondoGasoDesc = new ActorFondoGasoDesc(fondo);
        fondoPlantaSucc =  new ActorFondoPlantaSucc(fondo);
        fondoSeparador1 = new ActorFondoSeparador1(fondo);
        fondoSeparador2 = new ActorFondoSeparador2(fondo);
        fondoColectorSUCC =  new ActorFondoColectorSUCC(fondo);
        fondoColectorDESC =  new ActorFondoColectorDESC(fondo);
        fondoVent1 =  new ActorFondoVent1(fondo);
        fondoVent2 =  new ActorFondoVent2(fondo);

        escenario.addActor(fondoGasoSucc);
        escenario.addActor(fondoGasoDesc);
        escenario.addActor(fondoPlantaSucc);
        escenario.addActor(fondoSeparador1);
        escenario.addActor(fondoSeparador2);
        escenario.addActor(fondoColectorSUCC);
        escenario.addActor(fondoColectorDESC);
        escenario.addActor(fondoVent1);
        escenario.addActor(fondoVent2);
        // **************** //


        // ****  TRANSMISORES  **** //

        transmisores = new Array<ActorPIT>();

        for (i=0; i<8; i++) {
            transmisores.add(new ActorPIT());
        }

        transmisores.get(0).setTag("PIT-12001");
        transmisores.get(0).setValor("0");
        transmisores.get(0).setUnidad("Kg/cm2");
        transmisores.get(0).setPosition(valvulas.get(0).getX()-100, valvulas.get(0).getY()+50);
        escenario.addActor(transmisores.get(0));

        transmisores.get(1).setTag("PIT-12002");
        transmisores.get(1).setValor("0");
        transmisores.get(1).setUnidad("Kg/cm2");
        transmisores.get(1).setPosition(valvulas.get(0).getX()-100, valvulas.get(0).getY()-valvulas.get(0).getWidth()-150);
        escenario.addActor(transmisores.get(1));

        transmisores.get(2).setTag("PIT-5001");
        transmisores.get(2).setValor("0");
        transmisores.get(2).setUnidad("Kg/cm2");
        transmisores.get(2).setPosition(valvulas.get(1).getX()+75, valvulas.get(1).getY()+3);
        escenario.addActor(transmisores.get(2));

        transmisores.get(3).setTag("PIT-5100");
        transmisores.get(3).setValor("0");
        transmisores.get(3).setUnidad("Kg/cm2");
        //transmisores.get(3).setPosition(valvulas.get(5).getX()+50, valvulas.get(5).getY()+3);
        transmisores.get(3).setPosition(compresor.getX()-85, compresor.getY()+175);
        escenario.addActor(transmisores.get(3));

        transmisores.get(4).setTag("PIT-5200");
        transmisores.get(4).setValor("0");
        transmisores.get(4).setUnidad("Kg/cm2");
        //transmisores.get(4).setPosition(transmisores.get(3).getX(), valvulas.get(11).getY()+3);
        transmisores.get(4).setPosition(compresor.getX()-85, compresor.getY()-140);
        escenario.addActor(transmisores.get(4));

        transmisores.get(5).setTag("PIT-5301");
        transmisores.get(5).setValor("0");
        transmisores.get(5).setUnidad("Kg/cm2");
        transmisores.get(5).setPosition(retenciones.get(1).getX()+13, valvulas.get(11).getY()+3);
        escenario.addActor(transmisores.get(5));

        transmisores.get(6).setTag("PIT-SEP1");
        transmisores.get(6).setValor("0");
        transmisores.get(6).setUnidad("Kg/cm2");
        transmisores.get(6).setPosition(separadores.get(0).getX()+40, separadores.get(0).getY()+85);
        escenario.addActor(transmisores.get(6));
        transmisores.get(6).setVisible(true);

        transmisores.get(7).setTag("PIT-SEP2");
        transmisores.get(7).setValor("0");
        transmisores.get(7).setUnidad("Kg/cm2");
        transmisores.get(7).setPosition(separadores.get(1).getX()+40, separadores.get(1).getY()+85);
        escenario.addActor(transmisores.get(7));
        transmisores.get(7).setVisible(true);

        // **************** //

        // ****  PRESOSTATOS DIFERENCIALES  **** //

        presostatosDif = new Array<ActorDPSH>();

        for (i=0; i<5; i++) {
            presostatosDif.add(new ActorDPSH());
        }

        presostatosDif.get(0).setPosition(valvulas.get(0).getX()+valvulas.get(0).getHeight()+10, valvulas.get(0).getY()-valvulas.get(0).getWidth()/2-7);
        escenario.addActor(presostatosDif.get(0));

        presostatosDif.get(1).setPosition(valvulas.get(1).getX()+valvulas.get(1).getWidth()/2-7, valvulas.get(1).getY()+valvulas.get(1).getHeight()+10);
        escenario.addActor(presostatosDif.get(1));

        presostatosDif.get(2).setPosition(valvulas.get(11).getX()+valvulas.get(11).getWidth()/2-7, valvulas.get(11).getY()+valvulas.get(11).getHeight()+10);
        escenario.addActor(presostatosDif.get(2));

        presostatosDif.get(3).setPosition(valvulas.get(3).getX()+valvulas.get(3).getWidth()/2-7, valvulas.get(3).getY()+valvulas.get(3).getHeight()+10);
        escenario.addActor(presostatosDif.get(3));

        presostatosDif.get(4).setPosition(valvulas.get(6).getX()+valvulas.get(6).getWidth()/2-7, valvulas.get(6).getY()+valvulas.get(6).getHeight()+10);
        escenario.addActor(presostatosDif.get(4));

        // **************** //

        // ****  MENU-ESTADO-GASO **** //

        menuEstadoGASO = new ActorMenuEstadoGASO();
        menuEstadoGASO.setPosition(valvulas.get(0).getX()-135,  valvulas.get(0).getY()-valvulas.get(0).getHeight()-5);
        //menuEstado.setScale(0.5f);
        menuEstadoGASO.setZIndex(95);

        escenario.addActor(menuEstadoGASO);

        // ****  MENU-ACKRST**** //
//EMI2
//        menuACKRST = new ActorMenuACKRST();
//        menuACKRST.setPosition(compresor.getX()+45,  compresor.getY()+170);
//        menuACKRST.setZIndex(95);
//        escenario.addActor(menuACKRST);


        // ****  MENU-PJ1PJ2**** //
//EMI2
//        menuPJ1PJ2 = new ActorMenuPJ1PJ2();
//        menuPJ1PJ2.setPosition(compresor.getX()+45,  compresor.getY()-160);
//        menuPJ1PJ2.setZIndex(95);
//        escenario.addActor(menuPJ1PJ2);

        // **************** //


        // ****  AVATAR  **** //
        avatar = new ActorPersonaje();
        avatar.setPosition(valvulas.get(11).getX()+880,  escenario.getViewport().getScreenY() +40);
        avatar.setVisible(false);
        escenario.addActor(avatar);
        // **************** //


        // ****  TEXTO-DIALOGO **** //

        textoDialogo = new ActorTextoDialogo();
        textoDialogo.setPosition(avatar.getX()-textoDialogo.getWidth()-30,  avatar.getY()-5);
        textoDialogo.setZIndex(95);

        escenario.addActor(textoDialogo);

        // **************** //

        // ****  LOGICAS **** //
        logicaPITs = new LogicaPITs(this); // lo pongo acá luego de agregar todos los transmisore
        logicaDPSHs= new LogicaDPSHs(this);// s
        logicaFondo = new LogicaFondo(this);
        logicaPlanta = new LogicaPlanta(this);
        logicaValvulas = new LogicaValvulas(this);
        logicaMenuEstadoGASO = new LogicaMenuEstadoGASO(this);

        // **************** //

        // ****  TAREAS PERÍODICAS  **** //
        tareasPeriodicas = new TareasPeriodicas(this);
        tareasPeriodicas.getTimer().start();

        // **************** //


        // ****  EVENTOS  **** //

        // ***
        transmisores.get(0).addListener(new ClickListener() {
            @Override public void clicked(InputEvent event, float x, float y) {
                transmisores.get(0).setValor("0");
                avatar.setIdle(true);
                //textoDialogo.setVisible(false);
            }
        });


        // ***

        // creo una acción a ejecutarse una vez terminada la secuencia de acciones
        final RunnableAction accionSetIdle = new RunnableAction();
        accionSetIdle.setRunnable(new Runnable() {
            @Override
            public void run () {
                avatar.setIdle(true);
            }
        });

        textoDialogo.addListener(new ClickListener() {
            @Override public void clicked(InputEvent event, float x, float y) {

                textoDialogo.addAction( sequence(
                    new TemporalAction(4.0f){
                    LabelFormatter formatter = new LabelFormatter("Hola, vamos a aprender juntos cómo funcionan las Plantas Compresoras (PPCC).\n \n¡ Ahora hacé click en PIT-12001 !");
                        @Override protected void update(float percent) {
                            textoDialogo.setTexto(formatter.getText(percent));
                        }},

                    accionSetIdle));


               // avatar.addAction(fadeOut(0.25f));
                avatar.setIdle(false);
                avatar.setVisible(true);
                avatar.addAction(scaleTo(0, 0, 0f));
                avatar.addAction(scaleTo(2f,2f,1f, Interpolation.sineIn));
                //avatar.addAction(fadeOut(0f));

                //avatar.addAction(fadeIn(2f));


            }
        });

        // ***
//        valvulas.get(0).addCaptureListener(new InputListener() {
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                // valvula0.addAction(Actions.color(Color.RED));
//                return true;
//            }
//
//
//            @Override
//            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                if(!valvulas.get(0).getAbierta()) {
//                    fondoGasoSucc.addAction(parallel(color(Color.LIGHT_GRAY, 3), delay(0.5f)));
//                    fondoGasoDesc.addAction(parallel(color(Color.LIGHT_GRAY, 3), delay(0.5f)));
//
//                }else{
//                    fondoGasoSucc.addAction(parallel(color(Color.SKY, 3), delay(0.5f)));
//                    fondoGasoDesc.addAction(parallel(color(Color.SKY, 3), delay(0.5f)));
//
//                }
//            }
//        });



        // **************** //

        //actorPrueba = new ActorPrueba();
        //escenario.addActor(actorPrueba);
    }



    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f,  0.2f);
        //Gdx.gl.glClearColor(0.93f, 0.93f, 0.93f,  0.5f);
        Gdx.gl.glClearColor(0.89f, 0.89f, 0.89f,  0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        escenario.act(Gdx.graphics.getDeltaTime()); // para actualizar las Acciones
        escenario.draw();

    }


    public void gasNuevo() {

        //if ((i % 500) == 0) {
            //gases.add(new ActorGas(this));
            gasNuevo = new ActorGas(this);

            gasNuevo.setPosition(fondo.canosGasoSucc.get(0).x, fondo.canosGasoSucc.get(0).y);

            escenario.addActor(gasNuevo);
            //obstaculos.add(gasNuevo);
            // System.out.println(escenario.getActors());

            //i = 0; // para que no se haga un nuemero gigante
        //}

        //i++;
    }


    @Override
    public void resize(int width, int height) {
        escenario.getViewport().update(width, height);
        //fondo.setSize(width, height);

    }

    @Override
    public void show() {
        super.show();
        // Stage crea un SpriteBatch aunque no se lo pasemos
        //Gdx.input.setInputProcessor(escenario); // Stage es un InputProcessor en si mismo (puede manejar Input Events)

    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public Stage dameEscenario() {
        return escenario;
    }

    public Array<ActorValvula> dameValvulas(){
        return valvulas;
    }

    public Array<ActorRetencion> dameRetenciones(){
        return retenciones;
    }

    public Fondo dameFondo() {
        return fondo;
    }

    public LogicaFondo dameLogicaFondo() {
        return logicaFondo;
    }

    public LogicaPITs dameLogicaPITs() {
        return logicaPITs;
    }

    public LogicaValvulas dameLogicaValvulas() {
        return logicaValvulas;
    }

    public LogicaDPSHs dameLogicaDPSHs() {
        return logicaDPSHs;
    }

    public LogicaPlanta dameLogicaPlanta() {
        return logicaPlanta;
    }


    public ActorMenuEstadoGASO dameMenuEstadoGASO() {
        return menuEstadoGASO;
    }

    public LogicaMenuEstadoGASO dameLogicaMenuEstadoGASO() {
        return logicaMenuEstadoGASO;
    }

    public void actualizarEstadoGASO(){
        estadoGASO.actualizar(this);
    }




    public String getLeyendaEstadoGASO(){
        return estadoGASO.getLeyendaEstado(this);
    }

    public GASOEstado getEstadoGASO() {
        return estadoGASO;
    }

    public void setEstadoGASO(GASOEstado estadoGASO) {
        this.estadoGASO = estadoGASO;
    }


    public void actualizarEstadoPLANTA(){

        estadoPLANTA.actualizar(this);
    }

    public String getLeyendaEstadoPLANTA(){
        return estadoPLANTA.getLeyendaEstado(this);
    }

    public PLANTAEstado getEstadoPLANTA() {
        return estadoPLANTA;
    }

    public void setEstadoPLANTA(PLANTAEstado estadoPLANTA) {
        this.estadoPLANTA = estadoPLANTA;
    }



    public Array<Float> dameValores() {
        return logicaPITs.dameValores();
    }

    public ActorCompresor dameCompresor() {
        return compresor;
    }

    public Array<ActorSeparador> dameSeparadores() {
        return separadores;
    }

    public Array<ActorPIT> dameTransmisores() {
        return transmisores;
    }

    public Array<ActorDPSH> damePresostatosDif() {
        return presostatosDif;
    }



//    public ActorMenuPJ1PJ2 dameMenuPJ1PJ2() {
//        return menuPJ1PJ2;
//    }






    public ActorFondoGasoSucc getFondoGasoSucc() {
        return fondoGasoSucc;
    }

    public ActorFondoGasoDesc getFondoGasoDesc() {
        return fondoGasoDesc;
    }

    public ActorFondoPlantaSucc getFondoPlantaSucc() {
        return fondoPlantaSucc;
    }

    public ActorFondoSeparador1 getFondoSeparador1() {
        return fondoSeparador1;
    }

    public ActorFondoSeparador2 getFondoSeparador2() {
        return fondoSeparador2;
    }

    public ActorFondoColectorSUCC getFondoColectorSUCC() {
        return fondoColectorSUCC;
    }

    public ActorFondoColectorDESC getFondoColectorDESC() {
        return fondoColectorDESC;
    }

    public ActorFondoVent1 getFondoVent1() {
        return fondoVent1;
    }

    public ActorFondoVent2 getFondoVent2() {
        return fondoVent2;
    }

}
