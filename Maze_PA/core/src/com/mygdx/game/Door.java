package com.mygdx.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Door extends MapSite {
    
    private boolean isOpen;
    private Direction direction;
    private int ID;
     
 
    public Door()
    {
        isOpen=true;
        ID = 0;
        direction = Direction.NORTH;
    }
    
    public Door(boolean o, int id1, Direction d){
        isOpen = o;
        ID = id1;
        direction = d;
        
    }
    
    public void open() {
        isOpen = true;
    }

    public void close() {
        isOpen = false;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public Direction getDirection() {
        return direction;
    }

    
    @Override
    public void enter() {
        // Implementacja, gdy użytkownik próbuje wejść przez drzwi
    }

    public void draw(Image img, int x, int y) {
        Graphics g = img.getGraphics();
        g.setColor(Color.black);
        switch (direction) {
            case NORTH:
                g.drawLine(x, y, x + (MapSite.LENGTH / 3), y);
                g.drawLine(x + ((MapSite.LENGTH / 3) * 2), y, x + MapSite.LENGTH, y);
                break;
            case EAST:
                g.drawLine(x + MapSite.LENGTH, y, x + MapSite.LENGTH, y + (MapSite.LENGTH / 3));
                g.drawLine(x + MapSite.LENGTH, y + ((MapSite.LENGTH / 3) * 2), x + MapSite.LENGTH, y + MapSite.LENGTH);
                break;
            case SOUTH:
                g.drawLine(x, y + MapSite.LENGTH, x + (MapSite.LENGTH / 3), y + MapSite.LENGTH);
                g.drawLine(x + ((MapSite.LENGTH / 3) * 2), y + MapSite.LENGTH, x + MapSite.LENGTH, y + MapSite.LENGTH);
                break;
            case WEST:
                g.drawLine(x, y, x, y + (MapSite.LENGTH / 3));
                g.drawLine(x, y + ((MapSite.LENGTH / 3) * 2), x, y + MapSite.LENGTH);
                break;
        }

    }
    
}
