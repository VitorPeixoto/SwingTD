package swingtd.entity;

import java.awt.Color;
import java.awt.Graphics;
import swingtd.math.Vector2f;
import swingtd.math.Vector3f;
import static swingtd.util.Const.TILE_SIZE;

/**
 *
 * @author peixoto
 */
public class Tower extends Entity {
    private int radius;
    private float rawDamage;
    private Vector3f damage;
    
    public Tower(int x, int y, int radius, float rawDamage, Color color) {
        position = new Vector2f(x, y);
        this.color = color;
        this.rawDamage = rawDamage;
        this.radius = radius;
        
        damage = new Vector3f(
            rawDamage*(0*color.getRed() + 1*color.getGreen() + 2*color.getBlue()),
            rawDamage*(1*color.getRed() + 1*color.getGreen() + 1*color.getBlue()),
            rawDamage*(2*color.getRed() + 1*color.getGreen() + 0*color.getBlue())
        );
    }
    
    public Tower(int radius, float rawDamage, Color color) {
        position = new Vector2f(0, 0);
        this.color = color;
        this.rawDamage = rawDamage;
        this.radius = radius;
        
        damage = new Vector3f(
            rawDamage*(0*color.getRed() + 1*color.getGreen() + 2*color.getBlue()),
            rawDamage*(1*color.getRed() + 1*color.getGreen() + 1*color.getBlue()),
            rawDamage*(2*color.getRed() + 1*color.getGreen() + 0*color.getBlue())
        );
    }

    public float getX() {
        return position.getX();
    }

    public void setX(int x) {
        position.setX(x);
    }

    public float getY() {
        return position.getY();
    }

    public void setY(int y) {
        position.setY(y);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public float getRawDamage() {
        return rawDamage;
    }

    public void setRawDamage(float rawDamage) {
        this.rawDamage = rawDamage;
    }

    public Vector3f getDamage() {
        return damage;
    }

    public void setDamage(Vector3f damage) {
        this.damage = damage;
    }

    @Override
    public void update() {}

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.translate((int)((position.getX()+0.5f)*TILE_SIZE), (int)((position.getY()+0.5f)*TILE_SIZE));
            graphics.fillRect(-TILE_SIZE/4, -TILE_SIZE/4, TILE_SIZE/2, TILE_SIZE/2);            
            graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 50));
            graphics.fillOval(-(radius)*TILE_SIZE, -(radius)*TILE_SIZE, 2*radius*TILE_SIZE, 2*radius*TILE_SIZE);
        graphics.translate(-(int)((position.getX()+0.5f)*TILE_SIZE), -(int)((position.getY()+0.5f)*TILE_SIZE));
    }
    
    
}
