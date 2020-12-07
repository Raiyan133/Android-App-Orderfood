package com.chayen.myproject;

import java.io.Serializable;

public class Food implements Serializable {

    private  String mName;
    private int mPrice;
    private int mQuantity;

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    public Food(int anInt, String string, String cursorString){}

    public Food(int anInt, String mName, int mPrice) {
        this.mName = mName;
        this.mPrice = mPrice;
        this.mQuantity = 0;
    }

    public String getmName() {
        return mName;
    }

    public int getmAmount() {
        return mPrice;
    }

    public int getmQuantity(){
        return mQuantity;
    }

    public void addToQuantity(){
        this.mQuantity += 1;
    }

    public void removeFromQuantity(){
        if(this.mQuantity > 1){
            this.mQuantity -= 1;
        }
    }

}
