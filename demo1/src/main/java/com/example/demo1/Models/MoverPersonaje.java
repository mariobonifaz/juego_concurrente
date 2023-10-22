package com.example.demo1.Models;

import java.util.Observable;

public class MoverPersonaje extends Observable implements Runnable {

    private Personaje pos;
    private boolean status,left=false,right=false;

    public void setPos(Personaje pos) {
        this.pos = pos;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setRightChange(){pos.setX(pos.getX() + 0);}
    public void setLeftChange(){pos.setX(pos.getX() + 0);}

    public MoverPersonaje(){
        status= true;
    }

    @Override
    public void run() {
        while (status){
            setChanged();
            notifyObservers(pos);
            try {
                Thread.sleep(50L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(left == true){
                if(pos.getX() >= 0){
                    pos.setX(pos.getX() - 10);
                }
                left = false;
            }
            else if (right == true){
                if (pos.getX() <= 640){
                    pos.setX(pos.getX() + 10);
                }
                right=false;
            }
        }
    }
}
