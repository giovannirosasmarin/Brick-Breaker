package window;

import Animacion.BufferedImageLoader;
import Objects.*;
import framework.keyInput;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

//import framework.KeyInput;

public class tankGame extends JPanel implements Runnable {

    private boolean running = false;
    private Thread thread;

    private BufferedImage spriteSheet = null; //tank.png spritesheet
    private BufferedImage background = null;// background of the window

    public static int WIDTH, HEIGHT;
    public static  int Health = 100*2;
    public static  int HealthEnemy = 100*2;

    public static int LifeCount =1*30;
    public static int LifeCount2=1*30;
    public static int LifeCount3 =1*30;

    public static int LifeCountEnemy =1*30;
    public static int LifeCountEnemy2 =1*30;
    public static int LifeCountEnemy0 =1*30;

    TankGameObjectHandler objectHandler;

    BufferedImage world;
    Graphics2D buffer;
//---------------------------------------------------------------------------------------

    public void init()
    {
        requestFocus ();

        JFrame jf = new JFrame (  );
        jf.add ( this ); //adding JPanel and also add the dimension

        jf.setTitle ( "Tank Game" );

    this.world = new BufferedImage ( 1280, 960, BufferedImage.TYPE_INT_RGB );
        WIDTH =getWidth ();
        HEIGHT =getHeight ();
         //   C:\Users\geo62\csc413-tankgame-giovannirosasmarin\res\back.png
        //back.png

        BufferedImageLoader loader = new BufferedImageLoader ();
        try{
            spriteSheet = loader.loadImage (".\\res\\tank.png");//tank.png spritesheet
            background = loader.loadImage (".\\res\\back.png");// background of the window
        }catch (IOException e){
            e.printStackTrace ();
        }



        objectHandler = new TankGameObjectHandler ();

        objectHandler.addObject ( new Tank ( 1180, 860, 0, 0, (short)-90, objectHandler, ObjectId.Tank,this ) );
//        objectHandler.addObject ( new Tank ( 100, 100, 0, 0, (short)-90, objectHandler,ObjectId.Tank,this ) );  //test for collision between tanks
        objectHandler.addObject ( new Enemy ( 150, 150, 0, 0, (short)90,objectHandler, ObjectId.Enemy,this ) );
//        objectHandler.addObject ( new Bullet ( 180,180,(short)0, objectHandler,ObjectId.Bullet,this));

//HealthPowerUp
        objectHandler.addObject ( new PowerUp ( 400,400, objectHandler, ObjectId.PowerUp,this) );
        objectHandler.addObject ( new PowerUp ( 850,220, objectHandler, ObjectId.PowerUp,this) );
        objectHandler.addObject ( new PowerUp ( 1200,35, objectHandler, ObjectId.PowerUp,this) );
        objectHandler.addObject ( new PowerUp ( 560,800, objectHandler, ObjectId.PowerUp,this) );
        objectHandler.addObject ( new PowerUp ( 400,400, objectHandler, ObjectId.PowerUp,this) );

//BreakableWall
        objectHandler.addObject ( new BreakableWall ( 196,450, objectHandler, ObjectId.BreakableWall,this) );
        objectHandler.addObject ( new BreakableWall ( 228,450, objectHandler, ObjectId.BreakableWall,this) );
        objectHandler.addObject ( new BreakableWall ( 260,450, objectHandler, ObjectId.BreakableWall,this) );
        objectHandler.addObject ( new BreakableWall ( 800,160, objectHandler, ObjectId.BreakableWall,this) );
        objectHandler.addObject ( new BreakableWall ( 800,192, objectHandler, ObjectId.BreakableWall,this) );
        objectHandler.addObject ( new BreakableWall ( 800,224, objectHandler, ObjectId.BreakableWall,this) );
        objectHandler.addObject ( new BreakableWall ( 800,256, objectHandler, ObjectId.BreakableWall,this) );
        objectHandler.addObject ( new BreakableWall ( 900,32, objectHandler, ObjectId.BreakableWall,this) );
        objectHandler.addObject ( new BreakableWall ( 900,64, objectHandler, ObjectId.BreakableWall,this) );
        objectHandler.addObject ( new BreakableWall ( 900,96, objectHandler, ObjectId.BreakableWall,this) );
        objectHandler.addObject ( new BreakableWall ( 512,700, objectHandler, ObjectId.BreakableWall,this) );
        objectHandler.addObject ( new BreakableWall ( 544,700, objectHandler, ObjectId.BreakableWall,this) );
        objectHandler.addObject ( new BreakableWall ( 480,732, objectHandler, ObjectId.BreakableWall,this) );
        objectHandler.addObject ( new BreakableWall ( 480,764, objectHandler, ObjectId.BreakableWall,this) );
        objectHandler.addObject ( new BreakableWall ( 480,796, objectHandler, ObjectId.BreakableWall,this) );
        objectHandler.addObject ( new BreakableWall ( 480,828, objectHandler, ObjectId.BreakableWall,this) );
        objectHandler.addObject ( new BreakableWall ( 512,828, objectHandler, ObjectId.BreakableWall,this) );
        objectHandler.addObject ( new BreakableWall ( 544,828, objectHandler, ObjectId.BreakableWall,this) );

        objectHandler.Walls (); //creates the unbreakableWalls


        jf.addKeyListener ( new keyInput ( objectHandler,this) );
        jf.pack ();
//      jf.setLocationRelativeTo ( null );  //does not go to the center
        jf.setResizable ( false );
        jf.setSize ( new Dimension ( 1280, 960 ) );

        jf.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
        jf.setVisible ( true );

    }

//---------------------------------------------------------------------------------------

   public synchronized void start()
   {
        if(running) //if running its true it will get out of the method
            return;

        running = true;
        thread = new Thread(this);
        thread.start ();
    }

    //---------------------------------------------------------------------------------------
    //not needed has much
    public synchronized void stop()
    {
        if(!running) //if running its false it will get out of the method
            return;

        running = false;
        try {
            thread.join ();
        } catch (InterruptedException e) {
            e.printStackTrace ( );
        }
        System.exit ( 1 );
    }
//--------------------------------------------------------------------------------------

    @Override // implemented runnable
    public void run()
    {

        init();

        long lastTime = System.nanoTime ();
        final double amountOfTicks = 80.0; // it will update 60 times
        double ns = 1000000000/ amountOfTicks;
        double delta = 0; //time that has passed  //going to catch up
        int updates = 0;
        int frames = 0 ;
        long timer = System.currentTimeMillis ();

        while(running){//loop of the game
            long now = System.nanoTime ();
            delta += (now - lastTime )/ns;
            lastTime = now; //equaling the last time to the time now
            if (delta >= 1)
            {
                update ();
                updates++;
                delta--; //make delta back to zero
            }

            this.repaint ();
            frames ++;

            if(System.currentTimeMillis () - timer >1000)
            {
                timer +=1000;
                System.out.println ( updates + " Ticks, Fps " + frames );
                updates = 0; //just to rest
                frames = 0;//just to rest
            }
        }
        stop ();
    }

    //---------------------------------------------------------------------------------------
    public void paintComponent(Graphics g)//everything in the game that renders
    {
        //used only if using canvas
//        BufferStrategy bs = this.getBufferStrategy (); //this refering to canvas  //getBufferStrategy returns a bufferStrategy
//
//        if(bs == null )
//        {
//            createBufferStrategy ( 3 );
//            return;
//        }

        Graphics2D g2 = this.world.createGraphics () ; //draws are buffers
        ////////////////////////////////////

        g2.drawImage ( background,0,0,getWidth (),getHeight (),null); // background of the window

        objectHandler.render ( g2 );

        //Health Tank

        g2.setColor ( Color.gray );
        g2.fillRect ( 40, 36, 200, 25 );
        g2.setColor ( Color.green );
        g2.fillRect ( 40, 36, Health, 25 );
        g2.setColor ( Color.white );
        g2.drawRect ( 40, 36, 200, 25 );



        //Health enemy
        g2.setColor ( Color.gray );
        g2.fillRect ( 900,36 ,200,25);
        g2.setColor ( Color.green );
        g2.fillRect ( 900,36 ,HealthEnemy,25);
        g2.setColor ( Color.white );
        g2.drawRect ( 900,36 ,200,25);



////Lives for tank ???? can figure it out still
        g2.setColor(Color.gray);
        g2.fillOval ( 40, 63,30,30 );
        g2.setColor(Color.blue);
        g2.fillOval ( 40, 63,LifeCount,30 );
        g2.setColor(Color.WHITE);
        g2.drawOval ( 40, 63,30,30 );

        g2.setColor(Color.gray);
        g2.fillOval ( 80, 63,30,30 );
        g2.setColor(Color.blue);
        g2.fillOval ( 80, 63,LifeCount2,30 );
        g2.setColor(Color.WHITE);
        g2.drawOval ( 80, 63,30,30 );
//Enemy Lives
        g2.setColor(Color.gray);
        g2.fillOval ( 120, 63,30,30 );
        g2.setColor(Color.blue);
        g2.fillOval ( 120, 63,LifeCount3,30 );
        g2.setColor(Color.WHITE);
        g2.drawOval ( 120, 63,30,30 );

        g2.setColor(Color.gray);
        g2.fillOval ( 1000, 63,30,30 );
        g2.setColor(Color.blue);
        g2.fillOval ( 1000, 63,LifeCountEnemy0,30 );
        g2.setColor(Color.WHITE);
        g2.drawOval ( 1000, 63,30,30 );

        g2.setColor(Color.gray);
        g2.fillOval ( 1040, 63,30,30 );
        g2.setColor(Color.blue);
        g2.fillOval ( 1040, 63,LifeCountEnemy,30 );
        g2.setColor(Color.WHITE);
        g2.drawOval ( 1040, 63,30,30 );

        g2.setColor(Color.gray);
        g2.fillOval ( 1080, 63,30,30 );
        g2.setColor(Color.blue);
        g2.fillOval ( 1080, 63,LifeCountEnemy2,30 );
        g2.setColor(Color.WHITE);
        g2.drawOval ( 1080, 63,30,30 );


        Graphics2D g22 = (Graphics2D)g ;
        g22.drawImage ( world,0,0,null);
//        BufferedImage lh = world.getSubimage ( 0,0,1280,960 );
//        BufferedImage rh = world.getSubimage ( 1280/2,0,1280/2,960 );
        BufferedImage mm = world.getSubimage ( 0,0,1270,932 );  //minimap
//        g22.drawImage ( lh,0,0, null  );
//        g22.drawImage ( rh,1280/2,0, null  );
        g22.scale ( .2,.2 );//minimap scalecx
        g22.drawImage ( mm, 2500,0, null ); //location of the minimap in the window

        ////////////////////////////////
        g.dispose ();
        //bs.show ();

    }
//---------------------------------------------------------------------------------------
    private void update()//everything in the game that updates
    {
        objectHandler.update();
    }
//--------------------------------------------------------------------------------------------------

    public static void main (String args[])
    {
        tankGame tg = new tankGame ();
        tg.init ();
        tg.start ();
        //new Window ( 1280, 960,"Tank Game", new tankGame () ); // initializing the window

    }
//---------------------------------------------------------------------------------------

    public BufferedImage getSpriteSheet()
    {
        return spriteSheet;
    }


}
