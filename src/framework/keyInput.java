package framework;

import Objects.Bullet;
import Objects.BulletTank;
import Objects.ObjectId;
import Objects.TankGameObjects;
import window.TankGameObjectHandler;
import window.tankGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class keyInput extends KeyAdapter
{
    tankGame game;
    TankGameObjectHandler objectHandler;
    private boolean isShooting = false;
    private boolean isShootingEnemy = false;

    public keyInput(TankGameObjectHandler handler, tankGame game){
        this.objectHandler = handler;
        this.game =game;
    }

    public void  keyPressed(KeyEvent e){

        int key = e.getKeyCode ();

        for(int i = 0; i< objectHandler.object.size (); i++)
        {
            TankGameObjects tempObject = objectHandler.object.get ( i );

            if(tempObject.getId () == ObjectId.Tank)
            {
                if(key == KeyEvent.VK_RIGHT) {
                    tempObject.toggleRightPressed ();
                } else if(key == KeyEvent.VK_LEFT) {
                    tempObject.toggleLeftPressed ();
                } else if(key == KeyEvent.VK_DOWN) {
                    tempObject.toggleDownPressed ();
                } else if(key == KeyEvent.VK_UP) {
                    tempObject.toggleUpPressed ();
                }else if(key == KeyEvent.VK_NUMPAD0 && !isShooting){
                    isShooting = true;
                    objectHandler.addObject ( new BulletTank ( (int)tempObject.getX (),(int)tempObject.getY (),(short)tempObject.getAngle(), objectHandler,ObjectId.BulletTank,game ) );
                }
            }

            if(tempObject.getId () == ObjectId.Enemy)
            {
                if(key == KeyEvent.VK_D) {
                    tempObject.toggleRightPressed ();
                } else if(key == KeyEvent.VK_A) {
                    tempObject.toggleLeftPressed ();
                } else if(key == KeyEvent.VK_S) {
                    tempObject.toggleDownPressed ();
                } else if(key == KeyEvent.VK_W) {
                    tempObject.toggleUpPressed ();
                }else if(key == KeyEvent.VK_SPACE&& !isShootingEnemy){
                    isShootingEnemy = true;
                    objectHandler.addObject ( new Bullet ( (int)tempObject.getX (),(int)tempObject.getY (),(short)tempObject.getAngle(), objectHandler,ObjectId.Bullet,game ) );
                }
            }


        }

        if(key == KeyEvent.VK_ESCAPE){   //press esc to exit game
            System.exit ( 1 );
        }

    }

    public void  keyReleased(KeyEvent e)
    {

        int key = e.getKeyCode ();

        for(int i = 0; i< objectHandler.object.size (); i++)
        {
            TankGameObjects tempObject = objectHandler.object.get ( i );

            if(tempObject.getId () == ObjectId.Tank)
            {
                if(key == KeyEvent.VK_RIGHT) {
                    tempObject.unToggleRightPressed ();
                } else if(key == KeyEvent.VK_LEFT) {
                    tempObject.unToggleLeftPressed ();
                } else if(key == KeyEvent.VK_DOWN) {
                    tempObject.unToggleDownPressed ();
                } else if(key == KeyEvent.VK_UP) {
                    tempObject.unToggleUpPressed ();
                } else if(key == KeyEvent.VK_NUMPAD0) {
                    isShooting = false;
                }

            }
            if(tempObject.getId () == ObjectId.Enemy)
            {
                if(key == KeyEvent.VK_D) {
                    tempObject.unToggleRightPressed ();
                } else if(key == KeyEvent.VK_A) {
                    tempObject.unToggleLeftPressed ();
                } else if(key == KeyEvent.VK_S) {
                    tempObject.unToggleDownPressed ();
                } else if(key == KeyEvent.VK_W) {
                    tempObject.unToggleUpPressed ();
                } else if(key == KeyEvent.VK_SPACE) {
                    isShootingEnemy = false;
                }


            }

        }


    }


}
