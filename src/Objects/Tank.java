package Objects;

import Animacion.SpriteSheet;
import window.TankGameObjectHandler;
import window.tankGame;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Tank extends TankGameObjects {
    private BufferedImage Tank2;

    public TankGameObjectHandler objectHandler;


    public Tank(int x, int y, int vx, int vy, short angle, TankGameObjectHandler handler, Objects.ObjectId id , tankGame game) {
        super ( x, y, vx, vy, angle, id );
        this.objectHandler = handler;

        SpriteSheet ss =new SpriteSheet ( game.getSpriteSheet ());
        Tank2 = ss.grabImage ( 1,1,32,32 );
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

                if(getBoundsTop ().intersects ( tempObject.getBounds () ))
                {
                    y = (int) (tempObject.getY () + 32);
                    vy =0;

                }

                if(getBounds ().intersects ( tempObject.getBounds () ))
                {
                    y = (int) (tempObject.getY ()- Tank2.getHeight ());
                    vy =0;

                }

                //right
                if(getBoundsRight ().intersects ( tempObject.getBounds () ))
                {
                    x = (int) (tempObject.getX ( ) - Tank2.getHeight ());

                }
                //left
                if(getBoundsLeft ().intersects ( tempObject.getBounds () ))
                {
                    x = (int) (tempObject.getX ( ) + 35);

                }

            }
            if(tempObject .getId ()== ObjectId.BreakableWall)
            {

                if(getBoundsTop ().intersects ( tempObject.getBounds () ))
                {
                    y = (int) (tempObject.getY () + 32);
                    vy =0;



                }

                if(getBounds ().intersects ( tempObject.getBounds () ))
                {
                    y = (int) (tempObject.getY ()- Tank2.getHeight ());
                    vy =0;


                }

                //right
                if(getBoundsRight ().intersects ( tempObject.getBounds () ))
                {
                    x = (int) (tempObject.getX ( ) - Tank2.getHeight ());


                }
                //left
                if(getBoundsLeft ().intersects ( tempObject.getBounds () ))
                {
                    x = (int) (tempObject.getX ( ) + 35);


                }

            }
            if(tempObject .getId ()== ObjectId.Enemy){

                if(getBoundsTop ().intersects ( tempObject.getBounds () ))
                {
                    y = (int) (tempObject.getY () + 32);
                    vy =0;
                    vx=0;

                }

                if(getBounds ().intersects ( tempObject.getBounds () ))
                {
                    y = (int) (tempObject.getY ()- Tank2.getHeight ());
                    vy =0;
                    vx=0;

                }

                //right
                if(getBoundsRight ().intersects ( tempObject.getBounds () ))
                {
                    x = (int) (tempObject.getX ( ) - Tank2.getHeight ());

                    vy =0;
                    vx=0;
                }
                //left
                if(getBoundsLeft ().intersects ( tempObject.getBounds () ))
                {
                    x = (int) (tempObject.getX ( ) + 35);
                    vy =0;
                    vx=0;
                }

            }

        }
    }

    @Override
    public Rectangle getBounds() {
       return new Rectangle ( (int)x+(Tank2.getWidth ()/2)-((Tank2.getWidth ()/2)/2),(int)y+(Tank2.getHeight ()/2),Tank2.getWidth ()/2,Tank2.getHeight ()/2 );
    }
    @Override
    public Rectangle getBoundsTop() {
        return new Rectangle ( (int)x+(Tank2.getWidth ()/2)-((Tank2.getWidth ()/2)/2),(int)y,Tank2.getWidth ()/2,Tank2.getHeight ()/2 );
    }
    @Override

    public Rectangle getBoundsRight() {
        return new Rectangle ( (int)x+Tank2.getWidth ()-5,(int)y+3,5,Tank2.getHeight ()-5 );
    }


    @Override
    public Rectangle getBoundsLeft() {
        return new Rectangle ( (int)x,(int)y+3,5,Tank2.getHeight ()-5 );
    }




    @Override
    public void render(Graphics g) {


        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), Tank2.getWidth() / 2, Tank2.getHeight() / 2);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage( Tank2, rotation, null);

        //this gives the bounds of the player
//        g.setColor ( Color.RED );
//        g2d.draw (getBounds()  );
//        g2d.draw ( getBoundsTop() );
//        g2d.draw (  getBoundsRight());
//        g2d.draw ( getBoundsLeft() );


    }

}
