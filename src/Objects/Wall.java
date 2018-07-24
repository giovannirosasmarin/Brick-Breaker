package Objects;

import window.tankGame;

import java.awt.*;
import java.util.LinkedList;

public class Wall extends TankGameObjects {

    public Wall(int x, int y, ObjectId id, tankGame game) {
        super ( x, y,0,0,(short) 0, id );
    }

    @Override
    public void update(LinkedList<TankGameObjects> object) {

    }

    @Override
    protected void Collision(LinkedList<TankGameObjects> object) {

    }

    @Override
    public void render(Graphics g) {
        Color brown = new Color(192,128,64); //color fill of the breakablewall
        g.setColor(brown);
        g.fillRect  ( (int) x, (int) y, 32, 32 );


        Color brown2 = new Color(182,128,64);     //out layer of the block
        g.setColor(brown2);
        g.drawRect ( (int) x, (int) y, 32, 32  );


    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle ( (int)x,(int) y, 32,32 );
    }

    @Override
    public Rectangle getBoundsTop() {
        return null;
    }

    @Override
    public Rectangle getBoundsRight() {
        return null;
    }

    @Override
    public Rectangle getBoundsLeft() {
        return null;
    }

}