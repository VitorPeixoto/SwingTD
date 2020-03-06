package swingtd.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import swingtd.math.Vector2f;
import static swingtd.util.Const.TILE_SIZE;

/**
 *
 * @author peixoto
 */
public class Enemy extends Entity {
    private Vector2f target;
    private int index;
    private float speed;
    private Vector2f velocity;
    private char currentMovement;
    public static List<Character> MOVEMENTS;
    
    public Enemy(int x, int y, Color color) {
        position = new Vector2f(x, y);
        target = position.copyOf();
        velocity = new Vector2f();
        this.speed = 0.05f + (color.getRed()*0.5f + color.getGreen() + color.getBlue()*2)/(255 * 30.0f);
        
        this.color = color;
        this.index = 0;
    }
    
    public void move(List<Character> movements) {        
        if(!this.reached(position, target)) {
            position.add(velocity);
            return;
        }
        if(index >= movements.size()) {
            return;
        }
        velocity.scale(0);
        position.setX(Math.round(position.getX()));
        position.setY(Math.round(position.getY()));
        
        currentMovement = movements.get(index);
        switch(currentMovement) {
            case 'N':
                target.add(Vector2f.VECTOR_Y.negated());
                velocity.add(Vector2f.VECTOR_Y.negated().scaled(speed));
                break;
            case 'S':
                target.add(Vector2f.VECTOR_Y);
                velocity.add(Vector2f.VECTOR_Y.scaled(speed));
                break;
            case 'W':
                target.add(Vector2f.VECTOR_X.negated());
                velocity.add(Vector2f.VECTOR_X.negated().scaled(speed));
                break;
            case 'E':
                target.add(Vector2f.VECTOR_X);
                velocity.add(Vector2f.VECTOR_X.scaled(speed));
                break;
        }
        index++;
    }

    private boolean reached(Vector2f position, Vector2f target) {
        if(position.equals(target)) return true;
        switch(currentMovement) {
            case 'N':
                return position.getY() < target.getY();
            case 'S':
                return position.getY() > target.getY();
            case 'W':
                return position.getX() < target.getX();
            case 'E':
                return position.getX() > target.getX();
            default:
                return false;
        }
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

    @Override
    public void update() {
        this.move(MOVEMENTS);
    }
    
    @Override
    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillOval((int)(position.getX()*TILE_SIZE), (int)(position.getY()*TILE_SIZE), TILE_SIZE, TILE_SIZE);
    }
    
    
}
