import java.awt.*;

public class Map {

    public int map[][];
    public int brickWidth;
    public int brickHeight;

    public Map(int row, int col){

        map = new int[row][col];
        for(int i=0; i < map.length; i++){
            for(int j=0; j < map[0].length; j++){
                map[i][j] = 1;
            }
        }
        brickWidth =600/col;
        brickHeight = 150/row;
    }
    public void paint(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j] > 0) {

                    int R = (int) (Math.random( )*256);
                    int G = (int)(Math.random( )*256);
                    int B= (int)(Math.random( )*256);
                    Color randomColor = new Color(R, G, B);
                    g.setColor(randomColor);
                    g.fillRect ( j * brickWidth + 50, i * brickHeight + 50, brickWidth - 2, brickHeight -2 );

                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col){
        map[row][col] = value;
    }
}
