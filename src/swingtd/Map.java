package swingtd;

import java.awt.Color;
import swingtd.entity.Enemy;
import swingtd.entity.EnemyGenerator;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import swingtd.entity.Entity;
import swingtd.entity.Tower;
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
    private List<Entity> entities;
    private Point cursor;
    
    private Tower selectedTower = null;
    
    public Map(int width, int height, char[][] tiles, List<Character> movements, Pair<Integer, Integer> start, Pair<Integer, Integer> end) throws IOException {
        this.width = width;
        this.height = height;
        this.tiles = tiles;
        this.movements = movements;
        this.start = start;
        this.end = end;
        this.cursor = new Point();
        
        /*enemies = new ArrayList<>();
        towers  = new ArrayList<>();
        for(int i = 0; i < 30; i++)
            enemies.add(EnemyGenerator.generateRandomEnemy(start.getKey(), start.getValue()));*/
        
        entities = new ArrayList<>();
        
        Enemy.MOVEMENTS = movements;
        entities.add(EnemyGenerator.generateEnemy(start.getKey(), start.getValue(), Color.red));
        entities.add(EnemyGenerator.generateEnemy(start.getKey(), start.getValue(), Color.green));
        entities.add(EnemyGenerator.generateEnemy(start.getKey(), start.getValue(), Color.blue));
        entities.add(EnemyGenerator.generateEnemy(start.getKey(), start.getValue(), Color.white));
        entities.add(EnemyGenerator.generateEnemy(start.getKey(), start.getValue(), Color.black));
        
        entities.add(EnemyGenerator.generateEnemy(start.getKey(), start.getValue(), new Color(0.5f, 0.5f, 0.5f, 0.5f)));
        
        bg = ImageLoader.load("bg.png");
        path = ImageLoader.load("path.png");
        
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX()/TILE_SIZE;
                int y = e.getY()/TILE_SIZE;
                if(selectedTower != null && tiles[y][x] == '.') {
                    selectedTower.setX(x);
                    selectedTower.setY(y);
                    entities.add(selectedTower);
                    tiles[y][x] = 'T';
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                cursor = e.getPoint();
            }
        });
        
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
                    case 'T':
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
        
        entities.forEach((Entity entity) -> entity.render(g));
        
        if(selectedTower != null) {
            selectedTower.render(g);
        }
    }
    
    public void update() {
        if(Keyboard.KEYS[KeyEvent.VK_1] && selectedTower == null) {
            selectedTower = new Tower(2, 1, Color.RED);
        }
        else if(Keyboard.KEYS[KeyEvent.VK_2] && selectedTower == null) {
            selectedTower = new Tower(2, 1, Color.GREEN);
        }
        else if(Keyboard.KEYS[KeyEvent.VK_3] && selectedTower == null) {
            selectedTower = new Tower(2, 1, Color.BLUE);
        }
        else if(!Keyboard.KEYS[KeyEvent.VK_1] && !Keyboard.KEYS[KeyEvent.VK_2] && !Keyboard.KEYS[KeyEvent.VK_3]) {
            selectedTower = null;
        }
        
        if(selectedTower != null) {
            selectedTower.setX((int)cursor.getX()/TILE_SIZE);
            selectedTower.setY((int)cursor.getY()/TILE_SIZE);
        }
        
        entities.forEach((Entity entity) -> entity.update());
    }

}
