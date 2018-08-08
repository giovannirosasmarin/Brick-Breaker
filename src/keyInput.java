
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyInput implements KeyListener {

    Game game;
    public keyInput(Game game){
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode ( ) == KeyEvent.VK_RIGHT) {

            Game.player.unToggleRightPressed ( );
        }
        if (e.getKeyCode ( ) == KeyEvent.VK_LEFT) {

            Game.player.unToggleLeftPressed ( );
        }
    }

    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode () ==KeyEvent.VK_RIGHT){
            if(Game.player.getX () >=600){
                Game.player.setX ( 600 );
            }else{
                Game.isRunning =true;
                Game.player.toggleRightPressed ();
            }
        }

        if(e.getKeyCode () ==KeyEvent.VK_LEFT){
            if(Game.player.getX () <10){
                Game.player.setX ( 10 );
            }else{
                Game.isRunning =true;
                Game.player.toggleLeftPressed ();
            }

        }
        //level 3
        if(e.getKeyCode () ==KeyEvent.VK_3){

            if(Game.level==0){
                Game.ball.setbX ( 250 );
                Game.ball.setbY ( 350 );
                Game.ball.setbXdir(-1);
                Game.ball.setbYdir(-2);
                Game.player.getX ();
                Game.levelNum ++;
                // this 4 lines of code down restarts the game
                Game.level =30;
                Game.map = new Map ( 5,6 );
               game.repaint ( );

            }

        }
        //level 2
        if(e.getKeyCode () ==KeyEvent.VK_2){
            if(Game.level==0){
                Game.ball.setbX ( 250 );
                Game.ball.setbY ( 350 );
                Game.ball.setbXdir(-1);
                Game.ball.setbYdir(-2);
                Game.player.getX ();
                Game.levelNum ++;
                // this 4 lines of code down restarts the game
                Game.level =24;
                Game. map = new Map ( 4,6);
                game.repaint ();


            }


        }
        //restart again after lives are over and also each time the you lose a life
        if(e.getKeyCode () ==KeyEvent.VK_ENTER){
            //if the lives are over the game is over and it will restart lives and to first level
            if(Game.lives==3){
                Game.ball.setbX ( 250 );
                Game.ball.setbY ( 350 );
                Game.ball.setbXdir(-1);
                Game.ball.setbYdir(-2);
                Game.player.getX ();
                Game.LifeCount =10;
                Game.LifeCount2 =10;
                Game.LifeCount3 =10;
                Game.lives =0;
                // this 4 lines of code down restarts all the game
                Game.score =0;
                Game.level =15;
                Game.map = new Map ( 3,5 );
                game.repaint ();

            }

            if(!Game.isRunning){
                //this is for the lives it will restart where you left only three chances
                Game. ball.setbX ( 250 );
                Game.ball.setbY ( 350 );
                Game.ball.setbXdir(-1);
                Game.ball.setbYdir(-2);
                Game.player.getX ();
                game.repaint ();

            }
        }

    }
}