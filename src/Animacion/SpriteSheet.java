package Animacion;

import java.awt.image.BufferedImage;

public class SpriteSheet
{
    private BufferedImage image;

    public SpriteSheet(BufferedImage image)
    {
        this.image = image ;

    }

// to get the coordinates of each col and row of the image drawn
    public BufferedImage grabImage(int col,int row, int width, int height)
    {
        BufferedImage img = image.getSubimage ( (col*32)-32,(row *32)-32,width,height );// getting the dimensions of the sprite sheet
        return img;
    }

}
