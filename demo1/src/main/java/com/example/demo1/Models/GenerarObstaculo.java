package com.example.demo1.Models;

import java.util.Observable;
import java.util.Random;

public class GenerarObstaculo extends Observable implements Runnable {

    private Obstaculo rasho;
    private boolean status;

    public void setRasho(Obstaculo rasho)
    {
        this.rasho = rasho;
    }

    public GenerarObstaculo() {
        status = true;
    }

    int numero = (int) (Math.random() * 640 + 1);

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void run() {
        rasho.setX(numero);

        while (status) {

            rasho.setY(rasho.getY() + 7);
            setChanged();
            notifyObservers(rasho);

            try {
                Thread.sleep(50l);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (rasho.getY() >= 200) {
                numero = (int) (Math.random() * 640 + 1);
                rasho.setY(0);
                rasho.setX(numero);
            }
        }

    }
}
