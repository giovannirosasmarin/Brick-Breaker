package Objects;


import Animacion.SpriteSheet;
import window.TankGameObjectHandler;
import window.tankGame;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.LinkedList;


/**
 *
 * @author anthony-pc
 */
//Modified to fit with my keyInput and main tankGame class
public class Enemy extends TankGameObjects {


    private BufferedImage Enemy;
    public TankGameObjectHandler objectHandler;

    public Enemy(int x, int y, int vx, int vy, short angle, TankGameObjectHandler handler, ObjectId id, tankGame game) {
        super ( x, y, vx, vy, angle, id );
        this.objectHandler = handler;
        SpriteSheet ss =new SpriteSheet ( game.getSpriteSheet ());
        Enemy = ss.grabImage ( 3,1,32,32 );
    }

    @Override
    public void update(LinkedList<TankGameObjects> object) {
        // x++;   //test to make it move just to the right
        if (this.UpPressed)
        {
            vx = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
            vy = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
            x+= vx;
            y+= vy;
        }

        if (this.DownPressed)
        {
            vx = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
            vy = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
            x -= vx;
            y -= vy;
        }

        if (this.LeftPressed)
        {
            this.angle -= 3;
        }
        if (this.RightPressed)
        {
            this.angle += 3;
        }

//border collision of player with the window
        if(x<=0) //
            x=0;
        if(x>=1280-20)
            x=12800-20;
        if (y<=0)
            y=0;
        if(y>=960-20)
            y=960-20;
        Collision ( object );

    }
    @Override
    protected void Collision(LinkedList<TankGameObjects> object)
    {
        for (int i=0;i<objectHandler.object.size ();i++){
            TankGameObjects tempObject = objectHandler.object.get ( i );

            if(tempObject .getId ()== ObjectId.Wall){

                if(getBoundsTop ().intersects ( tempObject.getBounds () )){
                    y = (int) (tempObject.getY () + 32);
                    vy =0;

                }

                if(getBounds ().intersects ( tempObject.getBounds () )){
                    y = (int) (tempObject.getY ()- Enemy.getHeight ());

                    vy =0;

                }

                //right
                if(getBoundsRight ().intersects ( tempObject.getBounds () )) {
                    x = (int) (tempObject.getX ( ) - Enemy.getHeight ());

                }
                //left
                if(getBoundsLeft ().intersects ( tempObject.getBounds () )) {
                    x = (int) (tempObject.getX ( ) + 35);

                }

            }

            if(tempObject .getId ()== ObjectId.Tank){

                if(getBoundsTop ().intersects ( tempObject.getBounds () )){
                    y = (int) (tempObject.getY () + 32);
                    vy =0;
                    vx=0;

                }

                if(getBounds ().intersects ( tempObject.getBounds () )){
                    y = (int) (tempObject.getY ()- Enemy.getHeight ());

                    vy =0;
                    vx=0;

                }

                //right
                if(getBoundsRight ().intersects ( tempObject.getBounds () )) {
                    x = (int) (tempObject.getX ( ) - Enemy.getHeight ());
                    vy =0;
                    vx=0;
                }
                //left
                if(getBoundsLeft ().intersects ( tempObject.getBounds () )) {
                    x = (int) (tempObject.getX ( ) + 35);
                    vy =0;
                    vx=0;
                }

            }
            if(tempObject .getId ()== ObjectId.BreakableWall){

                if(getBoundsTop ().intersects ( tempObject.getBounds () )){
                    y = (int) (tempObject.getY () + 32);
                    vy =0;
                    vx=0;

                }

                if(getBounds ().intersects ( tempObject.getBounds () )){
                    y = (int) (tempObject.getY ()- Enemy.getHeight ());

                    vy =0;
                    vx=0;

                }

                //right
                if(getBoundsRight ().intersects ( tempObject.getBounds () )) {
                    x = (int) (tempObject.getX ( ) - Enemy.getHeight ());
                    vy =0;
                    vx=0;
                }
                //left
                if(getBoundsLeft ().intersects ( tempObject.getBounds () )) {
                    x = (int) (tempObject.getX ( ) + 35);
                    vy =0;
                    vx=0;
                }

            }
        }
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle ( (int)x+(Enemy.getWidth ()/2)-((Enemy.getWidth ()/2)/2),(int)y+(Enemy.getHeight ()/2),Enemy.getWidth ()/2,Enemy.getHeight ()/2 );
    }
    @Override
    public Rectangle getBoundsTop() {
        return new Rectangle ( (int)x+(Enemy.getWidth ()/2)-((Enemy.getWidth ()/2)/2),(int)y,Enemy.getWidth ()/2,Enemy.getHeight ()/2 );
    }
    @Override

    public Rectangle getBoundsRight() {
        return new Rectangle ( (int)x+Enemy.getWidth ()-5,(int)y+3,5,Enemy.getHeight ()-5 );
    }

    @Override
    public Rectangle getBoundsLeft() {
        return new Rectangle ( (int)x,(int)y+3,5,Enemy.getHeight ()-5 );
    }



    @Override
    public void render(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), Enemy.getWidth() / 2, Enemy.getHeight() / 2);
        Graphics2D g2d= (Graphics2D) g;
        g2d.drawImage( Enemy, rotation, null);
        //this gives the bounds of the player

//   a

    }
}
