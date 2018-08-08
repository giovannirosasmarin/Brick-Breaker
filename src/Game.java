import Animacion.BufferedImageLoader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game extends JPanel implements ActionListener, Runnable{
    public static boolean power = false;
    public static boolean power2 = false;
    private BufferedImage spriteSheet = null; //tank.png spritesheet
    private BufferedImage background = null;// background of the window
    protected static boolean isRunning = false;
    private boolean Running = false;     //start, stop , run if I have the same name for isRunning and Running after stoping the game it will restart  //but the ball would be the only thing moving and with different names I can move the player()
    protected static int score = 0;    //score
    protected static int level =15;//level number of blocks in it
    protected static int levelNum =0;//levelNum is the level number
    protected static int lives=0;//I did it backwards it adds 1 each time that the ball goes bellow the bottom and removes a live
    protected static int LifeCount=10;
    protected static int LifeCount2=10;
    protected static int LifeCount3 =10;
    private static Thread thread;
    private Timer timer;
    protected static player player; //player bar
    protected static Ball ball;
    protected static Map map;


//init()
private void init() {
     requestFocus ( );

     JFrame jf = new JFrame ( );
     jf.add ( this );
     jf.setTitle ( "BreakOut" );

    BufferedImageLoader loader = new BufferedImageLoader ( );
    try {
        spriteSheet = loader.loadImage ( ".\\res\\mario.png" );//tank.png spritesheet
        background = loader.loadImage ( ".\\res\\backBO.png" );// background of the window
    } catch (IOException e) {
        e.printStackTrace ( );
    }

     if(levelNum==0) {
         map = new Map ( 3, 5);
     }

    ball =new Ball ( 250,350,-1,-2,this );
    player = new player ( 310, 550, this );
    addKeyListener ( new keyInput ( this ) );

    setFocusable ( true );
    setFocusTraversalKeysEnabled ( false );
    int delay = 8;
    timer = new Timer ( delay, this );
    timer.start ( );

    jf.setBounds ( 10, 10, 700, 600 );
    jf.setResizable ( false );
    jf.setDefaultCloseOperation ( WindowConstants.EXIT_ON_CLOSE );
    jf.setVisible ( true );
}

    private synchronized void start()
    {
        if(Running) //if running its true it will get out of the method
            return;

        Running = true;
        thread = new Thread(this);
        thread.start ();
    }

    //not needed has much
    private synchronized void stop()
    {
        if(!Running) //if running its false it will get out of the method
            return;

        Running = false;
        try {
            thread.join ();
        } catch (InterruptedException e) {
            e.printStackTrace ( );
        }
        System.exit ( 1 );
    }

    @Override // implemented runnable
    public void run()
    {
        init ();

        long lastTime = System.nanoTime ();
        final double amountOfTicks = 60.0; // it will update 60 times
        double ns = 1000000000 / amountOfTicks;
        double delta = 0; //time that has passed  //going to catch up
        int updates = 0;
        int frames = 0 ;
        long timer = System.currentTimeMillis ();

        while(Running){//loop of the game
            long now = System.nanoTime ();
            delta += (now - lastTime )/ns;
            lastTime = now; //equaling the last time to the time now
            if (delta >= 1)
            {
                tick ();
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
//            System.out.println ("Working"); //first test of run before implementing all the game
        }
        stop ();
    }

    private void tick() // the updates
    {
        player.update ();
        ball.update ();
    }

    public void paint(Graphics g){

        g.drawImage ( background,0,0,getWidth (),getHeight (),null); // background of the window
        //////////////////////////////////////////////////////////////
        //draw map
        map.paint( (Graphics2D) g );
        //borders
        g.setColor ( Color.GRAY);
        g.fillRect ( 0,0,3,592 );
        g.fillRect ( 0,0,692,3 );
        g.fillRect ( 691,0,3,592 );
        //score
        g.setColor ( Color.GREEN );
        g.setFont ( new Font ( "Arial",Font.BOLD,20));
        g.drawString ( ""+score,650,20);
        //the bar
        player.render ( g );

        ball.render ( g );

        if(levelNum ==3){
            isRunning = false;
            ball.setVy (0);
            ball.setVx ( 0 );
            g.setColor ( Color.RED );
            g.setFont ( new Font ( "Arial",Font.BOLD,30));
            g.drawString ( "YOU WON, Score: " + score,200,300);
        }

//when finish level 1 and you can chose level 2 or 3
        if(level <=0){

            isRunning = false;
            ball.setVy (0);
            ball.setVx ( 0 );
            g.setColor ( Color.RED );
            g.setFont ( new Font ( "Arial", Font.BOLD, 30 ) );
            g.drawString ( "Next Level, Score: " + score, 190, 300 );

            g.setFont ( new Font ( "Arial", Font.ITALIC, 20 ) );
            g.drawString ( " Press 2 or 3 for Next level", 200, 350 );


        }

// shows up each time you lose a live or when game over all lives are over
        if(ball.getBy () >=570){
            isRunning = false;
            ball.setVy (0);
            ball.setVx ( 0 );
            g.setColor ( Color.GREEN );
            g.setFont ( new Font ( "Arial",Font.BOLD,40));
            g.drawString ( "Game Over/Lost Live",200,300);

            g.setFont ( new Font ( "Arial",Font.ITALIC,20));
            g.drawString ( " Enter to Restart " ,290,350);

        }
        g.setColor ( Color.GREEN );
        g.setFont ( new Font ( "Arial",Font.BOLD,12));
        g.drawString ( "Bust:",550,20);




        if(power){
            g.setColor ( Color.GREEN );
            g.setFont ( new Font ( "Arial",Font.ITALIC,12));
            g.drawString ( "Active",585,20);

            g.setColor ( Color.YELLOW );
            g.setFont ( new Font ( "Arial",Font.ITALIC,10));
            g.drawString ( " Bust Activates at 20, 165 and 320 points ",200,20);
        }

        g.setColor ( Color.GREEN );
        g.setFont ( new Font ( "Arial",Font.BOLD,12));
        g.drawString ( "Mini:",450,20);

        if(power2){
            g.setColor ( Color.GREEN );
            g.setFont ( new Font ( "Arial",Font.ITALIC,12));
            g.drawString ( "Active",480,20);

//            g.setColor ( Color.YELLOW );
//            g.setFont ( new Font ( "Arial",Font.ITALIC,10));
//            g.drawString ( " Bust Activates at 5, 20 and 27 points ",200,20);
        }
//to remove lives  when I try to put power to false when a live is lost I makes a glitch
        if (lives == 3) {
            LifeCount -= 10;
//            power =false;
        } else if (lives == 2) {
            LifeCount2 -= 10;
//            power = false;
        } else if (lives == 1) {
            LifeCount3 -= 10;
//            power = false;
        }

//Lives drawn above like a little semaforo mario Kart?? lol
        g.setColor(Color.gray);
        g.fillRect ( 40, 3,10,10 );
        g.setColor(Color.RED);
        g.fillRect ( 40, 3,LifeCount,10 );

        g.setColor(Color.gray);
        g.fillRect ( 60, 3,10,10 );
        g.setColor(Color.yellow);
        g.fillRect ( 60, 3,LifeCount2,10 );

        g.setColor(Color.gray);
        g.fillRect ( 80, 3,10,10 );
        g.setColor(Color.GREEN);
        g.fillRect ( 80, 3,LifeCount3,10 );
        //////////////////////////////////////////////////////////
        g.dispose ();

    }


    @Override         //collision
    public void actionPerformed(ActionEvent e) {
        timer.start ();

        if(isRunning){

                //collisionBall                  //bar collision
            if(ball.getBounds ().intersects ( player.getBounds ())){
                int playerPos=(int)player.getBounds ().getMinX ();
                int ballPos= (int)ball.getBounds ().getMinX ();
                //It devides the player in four depending where the ball collides it will move differently
                int first = playerPos + 25;
                int second = playerPos + 50;
                int third = playerPos + 75;
                int fourth = playerPos + 100;

                if (ballPos < first){
                    ball.setVy ( -2 );
                    ball.setVx ( -1 );
                }
                if (ballPos >= first && ballPos < second){
                    ball.setVy ( -2 * ball.getVy () );
                    ball.setVx ( -1 );
                }
                if (ballPos >= second && ballPos < third){
                    ball.setVy ( -2 );
                    ball.setVx ( 0);
                }
                if (ballPos >= third && ballPos < fourth){
                    ball.setVy ( -2 * ball.getVy () );
                    ball.setVx ( 1 );
                }
                if (ballPos > first){
                    ball.setVy ( -2 );
                    ball.setVx ( 1 );
                }

            }


           bbCollision ();

            ball.bx += ball.vx;
            ball.by += ball.vy;
            if(ball.bx <=0){
                ball.vx = -ball.vx;
            }
            if(ball.by <=0){
                ball.vy = -ball.vy;
            } if(ball.bx >=670){
                ball.vx = -ball.vx;
            }
            if(ball.by ==570) { //to remove lives
                lives ++;

            }

            //Small bust up for player
            power = score >= 20 && score <= 45 || score >= 165 && score <= 185 || score >= 320 && score <= 335;


            power2 = level >= 5 && level <= 6 || level >= 20 && level <= 22|| level >= 27 && level<= 29;
            if(power2){
                player.pWidth = 50;
            }else{
                player.pWidth =100;
            }
        }
        repaint();

    }

    public static void main(String[] args )
    {
        Game bo = new Game ();
        bo.init ();
        bo.start ();
    }

    public BufferedImage getSpriteSheet()
    {
        return spriteSheet;
    }

    //bb = ball/brick collision
    private void bbCollision(){

        //collision ball with bricks
        loop: for(int i=0; i< map.map.length;i++)
        {
                for(int j=0; j< map.map[0].length;j++)
                {
                     if(map.map[i][j] > 0)
                     {
                            int brickX = j* map.brickWidth + 50;
                            int brickY = i * map.brickHeight +50;
                            int brickWidth = map.brickWidth;
                            int brickHeight = map.brickHeight;

                         Rectangle brickgetBounds = new Rectangle ( brickX,brickY,brickWidth,brickHeight );

                            if(ball.getBounds ().intersects ( brickgetBounds ))
                            {
                                    map.setBrickValue ( 0,i,j );
                                    level--;
                                    score +=5;

                                    if(ball.bx + 19 <= brickgetBounds.x || ball.bx +1 >= brickgetBounds.x +brickgetBounds.width)
                                    {
                                             ball.vx =-ball.vx;

                                    }else{
                                        ball.vy = -ball.vy;
                                     }
                                    break loop;// will break the entire loop only goes in when ball intersects brick
                            }
                    }
                }
        }

    }

}
