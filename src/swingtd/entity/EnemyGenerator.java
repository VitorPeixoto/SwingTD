package swingtd.entity;

import java.awt.Color;

/**
 *
 * @author peixoto
 */
public class EnemyGenerator {
    
    public static Enemy generateRandomEnemy(int x, int y) {
        float r = (float)Math.random(),
              g = (float)Math.random(),
              b = (float)Math.random();
        
        return new Enemy(x, y, new Color(r, g, b));
    }
}
