package swingtd.entity;

import java.awt.Color;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author peixoto
 */
public class EnemyGenerator {
    private static Random random = new Random((new Date()).getTime());
    
    public static Enemy generateRandomEnemy(int x, int y) {
        float r = random.nextFloat(),
              g = random.nextFloat(),
              b = random.nextFloat();
        
        return new Enemy(x, y, new Color(r, g, b));
    }
    
    public static Enemy generateEnemy(int x, int y, Color color) {
        return new Enemy(x, y, color);
    }
}
