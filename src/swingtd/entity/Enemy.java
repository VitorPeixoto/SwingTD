package swingtd.entity;

import java.awt.Color;
import java.util.List;
import swingtd.math.Vector2f;

/**
 *
 * @author peixoto
 */
public class Enemy {
    private Vector2f position, target;
    private Color color;
    private int index;
    private float speed; // Must totalize 1.0
    private Vector2f velocity;
    private char currentMovement;
    
    public Enemy(int x, int y, Color color) {
        position = new Vector2f(x, y);
        target = position.copyOf();
        velocity = new Vector2f();
        this.speed = 0.05f + (color.getRed() + color.getGreen() + color.getBlue())/(255 * 30.0f);
        
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
        /*this.color = this.color.darker();
        this.speed = 0.05f + (color.getRed() + color.getGreen() + color.getBlue())/(255 * 30.0f);*/
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
    
    
}
