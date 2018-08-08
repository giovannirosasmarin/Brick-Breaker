import Animacion.SpriteSheet;
import java.awt.*;
import java.awt.image.BufferedImage;



public class Ball {

    protected int bx; //ball x
    protected int by; //ball y
    protected int vy; //ball velocity y
    protected int vx; //ball velocity x
    private BufferedImage ball;


    public Ball(int x, int y,int vy, int vx, Game game) {
        this.bx = x;
        this.by = y;
        this.vx = vx;
        this.vy = vy;

        //ball looks like mini mario
        SpriteSheet ss =new SpriteSheet ( game.getSpriteSheet ());
        ball= ss.grabImage ( 1,1,20,20 );


    }

    public void update() {



    }

    public Rectangle getBounds() {
        return new Rectangle ( (int) bx, by, 15, 15 );
    }


    public void render(Graphics g) {
        ((Graphics2D) g).drawImage ( ball, bx, by,null );

    }

    public int getBx() {
        return bx;
    }

    public int getBy() {
        return by;
    }

    public void setBx(int x) {
        this.bx = x;
    }

    public void setBy(int y) {
        this.by = y;
    }
    public int getVx() {
        return vx;
    }

    public int getVy() {
        return vy;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }
}