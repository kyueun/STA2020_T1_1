package model;

import view.GUI;

public class TestClass {

    public static void main(String args[]){
        DWS dws = new DWS();

       dws.setGui(new GUI());

        dws.start();
    }
}
