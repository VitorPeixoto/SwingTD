package swingtd;

import swingtd.maps.MapLoader;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JFrame;
import swingtd.images.ImageLoader;
import static swingtd.util.Const.SCREEN_HEIGHT;
import static swingtd.util.Const.SCREEN_WIDTH;

/**
 *
 * @author peixoto
 */
public class Game extends JFrame implements KeyListener {
    private static final Game INSTANCE = new Game();
    private Map map;
    private Image gui;
    
    private Game() {}
    
    public static Game instance() {
        return INSTANCE;
    }
    
    public void setup(String mapName) throws IOException {
        map = MapLoader.load(mapName);
        map.setBackground(Color.red);
        
        gui = ImageLoader.load("gui.png");
        
        this.setUndecorated(true);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.setLayout(null);
        this.add(map);
        
        this.addKeyListener(this);
        
        this.getContentPane().setBackground(Color.black);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public void start() throws InterruptedException {
        while(true) {
            map.update();
            map.repaint();
            Thread.sleep(16); // 60fps
        }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);                
        g.drawImage(gui, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Keyboard.KEYS[e.getKeyCode()] = true;
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Game.this.dispose();
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Keyboard.KEYS[e.getKeyCode()] = false;
    }

}
