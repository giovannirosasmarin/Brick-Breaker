package Objects;

import window.TankGameObjectHandler;
import window.tankGame;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class PowerUp extends TankGameObjects {
    // Move a PowerUp object somewhere
    Random random = new Random();
    // generate a random integer from 0 to 899, then add 32 because of the size block?
    int ran = random.nextInt(900) + 32;
    public TankGameObjectHandler objectHandler;
    tankGame game;
    public PowerUp(int x, int y, TankGameObjectHandler handler, ObjectId id, tankGame game) {
        super ( x, y, 0,0, (short)0, id );
        this.objectHandler = handler;
        this.game = game;
    }

    @Override
    public void update(LinkedList<TankGameObjects> object) {

        Collision ( object );
    }

    @Override
    protected void Collision(LinkedList<TankGameObjects> object) {

        for (int i=0;i<objectHandler.object.size ();i++)
        {
            TankGameObjects tempObject = objectHandler.object.get ( i );

            if(tempObject .getId ()== ObjectId.Enemy)
            {

                if(getBounds ().intersects ( tempObject.getBounds () )||getBounds ().intersects ( tempObject.getBoundsLeft () )||
                        getBounds ().intersects ( tempObject.getBoundsRight () )||getBounds ().intersects ( tempObject.getBoundsTop () )){

                    if(tankGame.HealthEnemy < 200) {
                        tankGame.HealthEnemy += 75;
                        x = 1300;
                    }if(tankGame.Health ==200) {
                        tankGame.Health += 0;

                        objectHandler.addObject ( new PowerUp ( ran,ran, objectHandler,ObjectId.PowerUp,game) );
                        x =1300;
                    }
                        if(tankGame.Health >200 ) {
                            tankGame.Health += 0;
                            objectHandler.addObject ( new PowerUp ( ran,ran, objectHandler,ObjectId.PowerUp,game) );
                            x=1300;


                    }
                }
            }

            if(tempObject .getId ()== ObjectId.Tank)
            {

                if(getBounds ().intersects ( tempObject.getBounds () )||getBounds ().intersects ( tempObject.getBoundsLeft () )||
                        getBounds ().intersects ( tempObject.getBoundsRight () )||getBounds().intersects ( tempObject.getBoundsTop () )){


                    if(tankGame.Health < 200) {
                        tankGame.Health += 75;
                        x = 1300;
                    }  if(tankGame.Health ==200) {
                        tankGame.Health += 0;
                        objectHandler.addObject ( new PowerUp ( ran,ran, objectHandler,ObjectId.PowerUp,game) );
                        x =1300;
                    }if(tankGame.Health > 200) {
                        tankGame.Health += 0;
                        objectHandler.addObject ( new PowerUp ( ran,ran, objectHandler,ObjectId.PowerUp,game) );
                        x = 1300;

                    }


                }
            }

        }
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle ( (int)x,(int) y, 16,16 );
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

    @Override
    public void render(Graphics g) {


        g.setColor(Color.RED);
        g.fillRect  ( (int) x, (int) y, 16, 16 );
        g.setColor(Color.WHITE);
        g.fillRect  ( (int) x+7, (int) y+3,2, 10 );
        g.setColor(Color.WHITE);
        g.fillRect  ( (int) x+3, (int) y+7,10, 2 );

    }
}
