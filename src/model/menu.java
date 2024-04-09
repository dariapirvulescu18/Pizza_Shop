package model;

import javax.swing.*;

public abstract class menu  {
    protected static int visits=0;
    protected JFrame menuframe;

    public abstract void choose();

    public abstract void init_menu_frame();

    public menu(){
        menuframe = new JFrame();
    };


}
