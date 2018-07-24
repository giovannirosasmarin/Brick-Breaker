package Objects;

import java.awt.*;
import java.util.LinkedList;

public abstract class TankGameObjects {


        protected int x;
    protected int y;
    protected  int r = 3;
    protected int vx;
    protected int vy;
    protected short angle;
    protected ObjectId id;
    protected boolean UpPressed;
    protected boolean DownPressed;
    protected boolean RightPressed;
    protected boolean LeftPressed;

        public TankGameObjects(int x, int y, int vx, int vy, short angle,ObjectId id )
        {
            this.id = id;
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.angle = angle;


        }
        public abstract void update(LinkedList<TankGameObjects> object);

    protected abstract void Collision(LinkedList<TankGameObjects> object);

    public abstract Rectangle getBounds();

    public abstract Rectangle getBoundsTop();

    public abstract Rectangle getBoundsRight();

    public abstract Rectangle getBoundsLeft();

    public abstract void render(Graphics g);



        public short getAngle(){
            return angle;
        }
        public double  getX()
        {
            return x;
        }
        public int getR()
        {
            return r;
        }

        public  double getY()
        {
            return y;
        }


        public void setX(int x)
        {
            this.x = x;
        }
        public ObjectId getId() {
             return id;
        }
        public void setY(int y)
        {
            this.y = y;
        }

        public void setVx(int vx)
        {
            this.vx = vx;
        }

        public void setVy(int vy)
        {
            this.vy = vy;
        }

        public void setAngle(short angle)
        {
            this.angle = angle;
        }


        public void toggleUpPressed()
        {
            this.UpPressed = true;
        }

        public void toggleDownPressed()
        {
            this.DownPressed = true;
        }

        public void toggleRightPressed()
        {
            this.RightPressed = true;
        }

        public void toggleLeftPressed()
        {
            this.LeftPressed = true;
        }

        public void unToggleUpPressed()
        {
            this.UpPressed = false;
        }

        public void unToggleDownPressed()
        {
            this.DownPressed = false;
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


