package Objects;

import window.TankGameObjectHandler;
import window.tankGame;

import java.awt.*;
import java.util.LinkedList;

public class BreakableWall extends TankGameObjects {
    public TankGameObjectHandler objectHandler;
    tankGame game;
    public BreakableWall(int x, int y,TankGameObjectHandler handler, ObjectId id, tankGame game) {
        super ( x, y,0,0,(short) 0, id );
        this.objectHandler = handler;
        this.game = game;

    }

    @Override
    public void update(LinkedList<TankGameObjects> object) {
        Collision ( object );
    }

    @Override
    protected void Collision(LinkedList<TankGameObjects> object) {

        for (int i = 0; i < objectHandler.object.size ( ); i++) {
            TankGameObjects tempObject = objectHandler.object.get ( i );

            if (tempObject.getId ( ) == ObjectId.Bullet) {

                if (getBounds ( ).intersects ( tempObject.getBounds ( ) ) || getBounds ( ).intersects ( tempObject.getBoundsLeft ( ) ) ||
                        getBounds ( ).intersects ( tempObject.getBoundsRight ( ) ) || getBounds ( ).intersects ( tempObject.getBoundsTop ( ) )) {
                        x=1300;


                }
            }
            if (tempObject.getId ( ) == ObjectId.BulletTank) {

                if (getBounds ( ).intersects ( tempObject.getBounds ( ) ) || getBounds ( ).intersects ( tempObject.getBoundsLeft ( ) ) ||
                        getBounds ( ).intersects ( tempObject.getBoundsRight ( ) ) || getBounds ( ).intersects ( tempObject.getBoundsTop ( ) )) {

                    x=1300;

                }
            }
            if (tempObject.getId ( ) == ObjectId.Tank) {

                if (getBounds ( ).intersects ( tempObject.getBounds ( ) ) || getBounds ( ).intersects ( tempObject.getBoundsLeft ( ) ) ||
                        getBounds ( ).intersects ( tempObject.getBoundsRight ( ) ) || getBounds ( ).intersects ( tempObject.getBoundsTop ( ) )) {



                }
            }
            if (tempObject.getId ( ) == ObjectId.Enemy) {

                if (getBounds ( ).intersects ( tempObject.getBounds ( ) ) || getBounds ( ).intersects ( tempObject.getBoundsLeft ( ) ) ||
                        getBounds ( ).intersects ( tempObject.getBoundsRight ( ) ) || getBounds ( ).intersects ( tempObject.getBoundsTop ( ) )) {


                }
            }
        }
    }
    @Override
    public void render(Graphics g) {
        Color brown = new Color(182,112,64);
        g.setColor(brown);
        g.fillRect  ( (int) x, (int) y, 32, 32 );//color fill of the breakablewall

        Color brown2 = new Color(170,112,64); //out layer of the block
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