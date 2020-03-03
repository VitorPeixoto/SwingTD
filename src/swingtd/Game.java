package swingtd;

import swingtd.maps.MapLoader;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import swingtd.images.ImageLoader;
import static swingtd.util.Const.SCREEN_HEIGHT;
import static swingtd.util.Const.SCREEN_WIDTH;

/**
 *
 * @author peixoto
 */
public class Game extends JFrame {
    private Map map;
    private Image gui;
    
    public Game() throws IOException, InterruptedException {
        map = MapLoader.load("Map0");
        map.setBackground(Color.red);
        
        gui = ImageLoader.load("gui.png");
        
        this.setUndecorated(true);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.setLayout(null);
        this.add(map);
        
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    Game.this.dispose();
                    System.exit(0);
                }
                    
            }

            @Override
            public void keyReleased(KeyEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        this.setBackground(Color.black);
        this.setVisible(true);
        this.repaint();
        
        while(true) {
            map.update();
            map.repaint();
            Thread.sleep(16); // 60fps
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);        
        g.drawImage(gui, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this);
    }

}
