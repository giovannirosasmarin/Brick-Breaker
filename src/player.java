import Animacion.SpriteSheet;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

//Modified to fit with my keyInput and main class
public class player  {
    private double x;
    private double y;
    private BufferedImage bar;
    private boolean RightPressed;
    private boolean LeftPressed;
    protected int pWidth =100;


    public player(double x, double y,  Game game)
    {
        this.x = x;
        this.y = y;

//        SpriteSheet ss =new SpriteSheet ( game.getSpriteSheet ());
//        bar = ss.grabImage ( 1,6,100,32 );


    }
    public void update()
    {


        if (this.LeftPressed)
        {
            if(Game.power){
                x-=20;
            }else
          x-=10;

        }
        if (this.RightPressed)
        {
            if(Game.power){
                x+=20;
            }else
                x+=10;
        }

//border collision of player with the window
        if(x<=0) //
            x=0;
        if(x>=700-90)
            x=700-90;

    }
    public Rectangle getBounds() {
        return new Rectangle ((int)x,550,pWidth,8);
    }



    public void render (Graphics g)
    {

        g.setColor(Color.green);
        g.fillRect ( (int )x,(int)y,pWidth, 8);
        Graphics2D g2d= (Graphics2D)g;
//        ((Graphics2D) g).drawImage ( bar,(int)x,(int)y,null );

    }

    public double  getX()
    {
        return x;
    }

    public  double getY()
    {
        return y;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public void toggleRightPressed()
    {
        this.RightPressed = true;
    }

    public void toggleLeftPressed()
    {
        this.LeftPressed = true;
    }

    public void unToggleRightPressed()
    {
        this.RightPressed = false;
    }

    public void unToggleLeftPressed()
    {
        this.LeftPressed = false;
    }



}
