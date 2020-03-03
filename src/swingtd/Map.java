package swingtd;

import swingtd.entity.Enemy;
import swingtd.entity.EnemyGenerator;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import swingtd.images.ImageLoader;
import static swingtd.util.Const.TILE_SIZE;
/**
 *
 * @author peixoto
 */
public class Map extends JPanel {
    private final int width, height;
    private char[][] tiles;
    private List<Character> movements;
    private Pair<Integer, Integer> start, end;
    private final Image bg, path;
    private List<Enemy> enemies;
    
    public Map(int width, int height, char[][] tiles, List<Character> movements, Pair<Integer, Integer> start, Pair<Integer, Integer> end) throws IOException {
        this.width = width;
        this.height = height;
        this.tiles = tiles;
        this.movements = movements;
        this.start = start;
        this.end = end;
        
        enemies = new ArrayList<>();
        for(int i = 0; i < 30; i++)
            enemies.add(EnemyGenerator.generateRandomEnemy(start.getKey(), start.getValue()));
        
        bg = ImageLoader.load("bg.png");
        path = ImageLoader.load("path.png");
        
        this.setBounds(0, 0, width*TILE_SIZE, height*TILE_SIZE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Image image;
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                switch(tiles[y][x]) {
                    case '.':
                        image = bg;
                        break;
                    case '#':
                        image = path;
                        break;
                    default:
                        image = path;
                }
                g.drawImage(image, x*TILE_SIZE, y*TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
            }
        }
        
        for(Enemy enemy : enemies) {
            g.setColor(enemy.getColor());
            g.fillOval((int)(enemy.getX()*TILE_SIZE), (int)(enemy.getY()*TILE_SIZE), TILE_SIZE, TILE_SIZE);
        }
        
    }
    
    public void update() {
        enemies.forEach( (Enemy enemy) -> enemy.move(movements));
    }

}
