package com.example.demo1.Models;

import java.util.Observable;
import java.util.Random;

public class GenerarObjeto extends Observable implements Runnable {
    private Objeto objeteishon;
    private boolean status;

    public void setObjeteishon(Objeto objeteishon) {
        this.objeteishon = objeteishon;
    }

    public GenerarObjeto() {
        status = true;
    }

    int numero = (int) (Math.random() * 640 + 1);

    public void setStatus(boolean status) {
        this.status = status;
    }


    @Override
    public void run() {
        objeteishon.setX(numero);
        while (status) {
            objeteishon.setY(objeteishon.getY() + 4);
            setChanged();
            notifyObservers(objeteishon);
            try {
                Thread.sleep(50l);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (objeteishon.getY() >= 300) {
                numero = (int) (Math.random() * 640 + 1);
                objeteishon.setY(0);
                objeteishon.setX(numero);
            }
        }
    }
}
