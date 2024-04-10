package model;

import javax.swing.*;

public abstract class Menu {
    protected static int visits=0;
    protected JFrame menuFrame;

    public abstract void choose();

    public abstract void initMenuFrame();

    public Menu(){
        menuFrame = new JFrame();
    };


}
