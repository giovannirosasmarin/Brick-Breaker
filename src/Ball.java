import Animacion.SpriteSheet;
import java.awt.*;
import java.awt.image.BufferedImage;



public class Ball {

    public int bX;
    public int bY ;
    public int bYdir;
    public int bXdir ;
    private BufferedImage ball;


    public Ball(int x, int y,int yDir, int xDIr, Game game) {
        this.bX = x;
        this.bY = y;
        this.bXdir = xDIr;
        this.bYdir = yDir;

        //ball looks like mini mario
        SpriteSheet ss =new SpriteSheet ( game.getSpriteSheet ());
        ball= ss.grabImage ( 1,1,20,20 );


    }

    public void update() {



    }

    public Rectangle getBounds() {
        return new Rectangle ( (int) bX, bY, 15, 15 );
    }


    public void render(Graphics g) {
        ((Graphics2D) g).drawImage ( ball,bX,bY,null );

    }

    public int getbX() {
        return bX;
    }

    public int getbY() {
        return bY;
    }

    public void setbX(int x) {
        this.bX = x;
    }

    public void setbY(int y) {
        this.bY = y;
    }
    public int getbXdir() {
        return bXdir;
    }

    public int getbYdir() {
        return bYdir;
    }

    public void setbXdir(int xDir) {
        this.bXdir = xDir;
    }

    public void setbYdir(int yDir) {
        this.bYdir = yDir;
    }
}