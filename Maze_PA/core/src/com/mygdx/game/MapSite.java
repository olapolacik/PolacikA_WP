package com.mygdx.game;

import java.awt.Image;


enum Direction {
    NORTH, EAST, SOUTH, WEST;
}    


public abstract class MapSite {
    
    static final int LENGTH = 40;

    public abstract void enter();
   
    public abstract void draw(Image image, int x, int y);
}
