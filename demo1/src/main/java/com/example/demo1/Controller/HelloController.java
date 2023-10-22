package com.example.demo1.Controller;

import com.example.demo1.Models.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.Observable;
import java.util.Observer;


public class HelloController implements Observer {

    @FXML
    private AnchorPane rootScene;

    @FXML
    private ImageView Personaje;

    //buttones
    @FXML
    private Button Iniciar;

    @FXML
    private Button Izquierda;

    @FXML
    private Button Derecha;

    @FXML
    private Button Salir;

    //imgs obstaculos
    private ImageView rashos;
    private ImageView Objeto1;
    private ImageView objeto2;

    //cantidad obstaculos no+ no-
    private GenerarObstaculo[] cantidadObstaculos = new GenerarObstaculo[1];
    private GenerarObjeto[] cantidadObjetos = new GenerarObjeto[2];

    private MoverPersonaje moverPersonaje;

    @FXML
    void btnIzquierdaOnMouse(MouseEvent event) {
        moverPersonaje.setLeftChange();
        moverPersonaje.setLeft(true);
    }

    @FXML
    void btnDerechaOnMouse(MouseEvent event) {
        moverPersonaje.setRightChange();
        moverPersonaje.setRight(true);
    }

    @FXML
    void btnSalirOnMouse(MouseEvent event){
        System.exit(1);
    }

    @FXML
    void btnIniciarOnMouse(MouseEvent event) {

        //crea los obstaculos
        rashos = new ImageView(new Image(getClass().getResourceAsStream("/assets/imgs/Rayo.png")));
        rashos.setFitHeight(200);
        rashos.setFitWidth(20);
        rashos.setLayoutX(0);
        rashos.setLayoutY(0);
        rootScene.getChildren().addAll(rashos);

        objeto2 = new ImageView(new Image(getClass().getResourceAsStream("/assets/imgs/pelota.png")));
        objeto2.setFitHeight(70);
        objeto2.setFitWidth(70);
        objeto2.setLayoutX(0);
        objeto2.setLayoutY(0);
        rootScene.getChildren().addAll(objeto2);

        Objeto1 = new ImageView(new Image(getClass().getResourceAsStream("/assets/imgs/herradura.png")));
        Objeto1.setFitHeight(100);
        Objeto1.setFitWidth(100);
        Objeto1.setLayoutX(0);
        Objeto1.setLayoutY(0);
        rootScene.getChildren().addAll(Objeto1);

        //genera pou
        moverPersonaje = new MoverPersonaje();
        moverPersonaje.setPos(new Personaje(1, 319, 297));
        moverPersonaje.addObserver(this);
        Thread hilo1 = new Thread(moverPersonaje);
        hilo1.start();

        //genera los obstaculos
        cantidadObstaculos[0] = new GenerarObstaculo();
        cantidadObstaculos[0].setRasho(new Obstaculo(1, 0, 0));
        cantidadObstaculos[0].addObserver(this);
        Thread hilo2 = new Thread(cantidadObstaculos[0]);
        hilo2.start();
        System.out.println(Thread.currentThread().getName());

        cantidadObjetos[0] = new GenerarObjeto();
        cantidadObjetos[0].setObjeteishon(new Objeto(1, 0, 0));
        cantidadObjetos[0].addObserver(this);
        Thread hilo3 = new Thread(cantidadObjetos[0]);
        hilo3.start();

        cantidadObjetos[1] = new GenerarObjeto();
        cantidadObjetos[1].setObjeteishon(new Objeto(2, 0, 0));
        cantidadObjetos[1].addObserver(this);
        Thread hilo4 = new Thread(cantidadObjetos[1]);
        hilo4.start();

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof MoverPersonaje) {
            Personaje positionPersonage = (Personaje) arg;
            Platform.runLater(() -> Personaje.setLayoutX(positionPersonage.getX()));
        } else if (o instanceof GenerarObstaculo) {
            Obstaculo obstaculoPos = (Obstaculo) arg;
            Platform.runLater(() -> rashos.setLayoutY(obstaculoPos.getY()));
            Platform.runLater(() -> rashos.setLayoutX((obstaculoPos.getX())));
        }

        if (o instanceof GenerarObjeto) {
            Objeto obstaculoObjeto = (Objeto) arg;

            switch (obstaculoObjeto.getId()) {

                case 1:
                    Platform.runLater(() -> objeto2.setLayoutY(obstaculoObjeto.getY()));
                    Platform.runLater(() -> objeto2.setLayoutX((obstaculoObjeto.getX())));
                    break;
                case 2:
                    Platform.runLater(() -> Objeto1.setLayoutY(obstaculoObjeto.getY()));
                    Platform.runLater(() -> Objeto1.setLayoutX((obstaculoObjeto.getX())));
                    break;
            }
        }
        if(rashos.getBoundsInParent().intersects(Personaje.getBoundsInParent())){
            moverPersonaje.setStatus(false);
            cantidadObstaculos[0].setStatus(false);
            cantidadObjetos[0].setStatus(false);
            cantidadObjetos[1].setStatus(false);
        }
        if(Objeto1.getBoundsInParent().intersects(Personaje.getBoundsInParent())){
            moverPersonaje.setStatus(false);
            cantidadObstaculos[0].setStatus(false);
            cantidadObjetos[0].setStatus(false);
            cantidadObjetos[1].setStatus(false);
        }
        if(objeto2.getBoundsInParent().intersects(Personaje.getBoundsInParent())){
            moverPersonaje.setStatus(false);
            cantidadObstaculos[0].setStatus(false);
            cantidadObjetos[0].setStatus(false);
            cantidadObjetos[1].setStatus(false);
        }
    }
}